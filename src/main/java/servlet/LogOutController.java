package servlet;

import dto.UserDTO;
import entity.Cart;
import org.apache.log4j.Logger;
import service.ProductService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/logOut")
public class LogOutController extends HttpServlet {
    private static final Logger logger = Logger.getLogger(LogOutController.class);
    private static ProductService productService;

    @Override
    public void init() {
        productService = (ProductService) this.getServletContext().getAttribute("productService");
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse resp) throws ServletException, IOException {
        UserDTO u= (UserDTO) request.getSession().getAttribute("currentUser");
        productService.cleanUserTemporaryCart(u.getEmail());
        productService.addInTemporaryCart(u.getEmail(), (Cart) request.getSession().getAttribute("cart"));
        request.getSession().removeAttribute("currentUser");
        resp.sendRedirect("index.jsp");
    }
}
