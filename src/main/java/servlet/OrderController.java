package servlet;

import entity.Cart;
import entity.Order;
import org.apache.log4j.Logger;
import service.OrderService;
import service.ProductService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/order")
public class OrderController extends HttpServlet {
    private static final Logger logger = Logger.getLogger(OrderController.class);
    private static OrderService orderService;
    private static ProductService productService;

    @Override
    public void init() {
        productService=(ProductService) this.getServletContext().getAttribute("productService");
        orderService = (OrderService) this.getServletContext().getAttribute("orderService");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Cart cart = (Cart) req.getSession().getAttribute("cart");
        if (cart.getSum() == 0) {
            req.setAttribute("orderAlert", "For making order you need add something in cart");
            req.getRequestDispatcher("cart.jsp");
        } else {
            Order order = new Order(req);
            int orderId = orderService.makeOrder(order);
            productService.cleanUserTemporaryCart(order.getUserMail());
            req.setAttribute("orderAlert", "You order №" + orderId + " is ready");
            cart.clear();
            req.getSession().setAttribute("cart", cart);
        }
        req.getRequestDispatcher("cart.jsp").forward(req, resp);
    }
}
