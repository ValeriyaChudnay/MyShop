package servlet;

import chain.MiddlewareFactory;
import entity.product.Shoes;
import entity.UserRequestParameters;
import org.apache.log4j.Logger;
import service.ProductService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/catalog")
public class CatalogController extends HttpServlet {

    public static final String SELECT_FROM_PRODUCT_WHERE = "Select * from product where";
    private static ProductService productService;
    private final Logger logger = Logger.getLogger(CatalogController.class);

    @Override
    public void init() {
        productService = (ProductService) this.getServletContext().getAttribute("productService");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UserRequestParameters urp = new UserRequestParameters(req);
        int count = productService.getCount(MiddlewareFactory.getMiddlewareForCount(), urp);
        urp.setCount(count);
        logger.debug(MiddlewareFactory.getMiddleware().check(urp, SELECT_FROM_PRODUCT_WHERE));
        List<Shoes> shoes = productService.getAll(MiddlewareFactory.getMiddleware(), urp);
        req.setAttribute("userRequestParameter", urp);
        req.setAttribute("productList", shoes);
        req.getRequestDispatcher("catalog.jsp").forward(req, resp);
    }
}
