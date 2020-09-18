package listener;

import captcha.CaptchaProvider;
import captcha.CookieCaptchaProvider;
import captcha.HiddenFieldCaptchaProvider;
import captcha.SessionCaptchaProvider;
import db.TransactionManager;
import org.apache.log4j.Logger;
import repository.*;
import service.OrderService;
import service.ProductService;
import service.UserService;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@WebListener
public class ConfigurationListener implements ServletContextListener {

    public static final String CAPTCHA_TYPE = "captchaType";
    private static final Logger logger = Logger.getLogger(ConfigurationListener.class);
    private static Map<String, CaptchaProvider> captchaMap = new HashMap<>();

    static {
        captchaMap.put("session", new SessionCaptchaProvider());
        captchaMap.put("cookie", new CookieCaptchaProvider());
        captchaMap.put("hiddenField", new HiddenFieldCaptchaProvider());
    }

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        DataSource ds = null;
        try {
            Context context = new InitialContext();
            Context envContext = (Context) context.lookup("java:comp/env");
            ds = (DataSource) envContext.lookup("jdbc/shop");
        } catch (NamingException e) {
            logger.error(e.getCause());
        }
        logger.debug("Data source ==> " + ds);
        TransactionManager transactionManager = new TransactionManager(ds);
        UserRepository userRepoMySQL = new UserRepoMySQL();
        ProductRepository productRepoMySQL = new ProductRepoMySQL();
        OrderRepository orderRepoMySQL=new OrderRepoMySQL();
        ProductService productService = new ProductService(productRepoMySQL, transactionManager);
        UserService userService = new UserService(userRepoMySQL, transactionManager);
        OrderService orderService=new OrderService(orderRepoMySQL,transactionManager);
        servletContextEvent.getServletContext().setAttribute("userService", userService);
        servletContextEvent.getServletContext().setAttribute("orderService", orderService);
        servletContextEvent.getServletContext().setAttribute("productService", productService);
        logger.debug("Type servlet context: " + servletContextEvent.getServletContext().getInitParameter(CAPTCHA_TYPE));
        CaptchaProvider captchaProvider = captchaMap.get(servletContextEvent.getServletContext().getInitParameter(CAPTCHA_TYPE));
        logger.debug(servletContextEvent.getServletContext().getInitParameter("captchaTimeMin"));
        servletContextEvent.getServletContext().setAttribute("captchaLifeTime", Integer.parseInt(
                servletContextEvent.getServletContext().getInitParameter("captchaTimeMin")) * 60000L);
        servletContextEvent.getServletContext().setAttribute(CAPTCHA_TYPE, captchaProvider);
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        //don use for now
    }

}
