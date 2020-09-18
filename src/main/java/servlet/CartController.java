package servlet;

import dto.UserDTO;
import entity.Cart;
import entity.User;
import entity.product.Shoes;
import org.apache.log4j.Logger;
import service.ProductService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

@WebServlet("/cart")
public class CartController extends HttpServlet {
    private static final Logger logger = Logger.getLogger(CartController.class);
    private static ProductService productService;

    @Override
    public void init() {
        productService = (ProductService) this.getServletContext().getAttribute("productService");
    }


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        logger.debug(req.getParameter("shoesId"));
        logger.debug(req.getParameter("amount"));
        Cart cart = (Cart) req.getSession().getAttribute("cart");
        if (Objects.isNull(cart)) {
            cart = new Cart();
        }
        int id = Integer.parseInt(req.getParameter("shoesId"));
        if(id<0){
            clearCart(req, resp, cart);
        }else {
            setNewAmountToShoes(req, resp, cart, id);
        }
    }

    private void setNewAmountToShoes(HttpServletRequest req, HttpServletResponse resp, Cart cart, int id) throws IOException {
        Shoes shoes = productService.getById(id);
        int amount;
        if(Objects.isNull(req.getParameter("action"))){
            try {
                amount = Integer.parseInt(req.getParameter("amount"));
            } catch (NumberFormatException e) {
                amount = cart.getCart().get(shoes);
            }
        }else{
            logger.debug(cart.getCart().get(shoes));
            if(Objects.isNull(cart.getCart().get(shoes))){
                amount=0;
            }else{
                amount=cart.getCart().get(shoes);
            }
            amount++;
        }
        if (amount <= 0) {
            cart.remove(shoes);
            amount=1;
        } else {
            cart.add(shoes, amount);
            amount++;
        }
        req.getSession().setAttribute("cart", cart);
        resp.setContentType("application/json");
        resp.getWriter().write("{\n" +
                "\"dataCount\":" + cart.getAmount() + "," +
                "\"sum\":" + ((amount - 1) * shoes.getPrice()) + "," +
                "\"total\":" + cart.getSum() + "," +
                "\"id\":" + shoes.getId() + "," +
                "\"amount\": " + amount +
                "}");
    }

    private void clearCart(HttpServletRequest req, HttpServletResponse resp, Cart cart) throws IOException {
        cart.clear();
        req.getSession().setAttribute("cart", cart);
        resp.setContentType("application/json");
        resp.getWriter().write("{\n" +
                "\"dataCount\":"+0+"," +
                "\"sum\":"+0+"," +
                "\"total\":"+cart.getSum()+"," +
                "\"id\":"+0+"," +
                "\"amount\": " +0+
                "}");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Cart cartBeforeLogIn = (Cart) req.getSession().getAttribute("cart");
        logger.debug("post");
        if (Objects.isNull(cartBeforeLogIn)) {
            cartBeforeLogIn = new Cart();
        }
        UserDTO u= (UserDTO) req.getSession().getAttribute("currentUser");
        String mail=u.getEmail();
        Cart oldCart = productService.getOldCardByUser(mail);
        oldCart.putAll(cartBeforeLogIn);
        req.getSession().setAttribute("cart", oldCart);
        resp.sendRedirect("index.jsp");
    }
}
