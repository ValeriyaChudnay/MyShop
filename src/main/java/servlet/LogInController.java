package servlet;

import dto.UserDTO;
import entity.User;
import exeption.LoginException;
import org.apache.log4j.Logger;
import service.UserService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/logIn")
public class LogInController extends HttpServlet {
    private static UserService userService;
    private final Logger logger = Logger.getLogger(LogInController.class);

    @Override
    public void init() {
        userService = (UserService) this.getServletContext().getAttribute("userService");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user;
        try {
            user = userService.getUserByEmailAndPassword(request.getParameter("email"), request.getParameter("psw1"));
            request.getSession().setAttribute("currentUser", new UserDTO(user));
            System.out.println(request.getSession().getAttribute("currentUser"));
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("cart");
            requestDispatcher.forward(request, response);
        } catch (LoginException e) {
            request.setAttribute("alert", e.getMessage());
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("LogIn.jsp");
            requestDispatcher.forward(request, response);
        }
    }
}
