package servlet;

import captcha.ApplicationContextCaptchaProvider;
import captcha.CaptchaProvider;
import captcha.CookieCaptchaProvider;
import captcha.SessionCaptchaProvider;
import entity.Captcha;
import entity.User;
import exeption.CaptchaException;
import org.apache.log4j.Logger;
import service.UserService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.*;
import java.util.Objects;
import java.util.Random;

import static entity.Captcha.CAPTCHA_COOKIE;
import static entity.Captcha.CAPTCHA_HIDDEN;

@MultipartConfig
@WebServlet("/registration")
public class RegistrationController extends HttpServlet {
    public static final String SIGN_UP_JQUERY_JSP = "SignUpJQuery.jsp";
    private final Logger logger = Logger.getLogger(RegistrationController.class);
    private CaptchaProvider captchaProvider;
    private UserService userService;
    private long captchaLifeTime;
    private Random random = new Random();

    public int random() {
        return (random.nextInt(100000));
    }

    @Override
    public void init() {
        userService = (UserService) this.getServletContext().getAttribute("userService");
        captchaProvider = (CaptchaProvider) this.getServletContext().getAttribute("captchaType");
        captchaLifeTime = (long) this.getServletContext().getAttribute("captchaLifeTime");
    }


    @Override
    public void doGet(HttpServletRequest request,
                      HttpServletResponse response) {
        request.getSession().removeAttribute("user");
        request.getSession().removeAttribute("alertFromServer");
        try {
            setCaptcha(request, response);
        } catch (ServletException | IOException e) {
            logger.error(e.getMessage());
        }
    }

    private void setCaptcha(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Captcha captcha = new Captcha();
        int captchaValue = random();
        int captchaKey = random();
        captcha.setCaptchaValue(captchaValue);
        captcha.setCaptchaStartTime(System.currentTimeMillis());
        if (captchaProvider instanceof SessionCaptchaProvider) {
            request.getSession().setAttribute("captcha", captcha);
            response.sendRedirect(SIGN_UP_JQUERY_JSP);
        }
        if (captchaProvider instanceof ApplicationContextCaptchaProvider) {
            ApplicationContextCaptchaProvider applicationContextCaptchaProvider = (ApplicationContextCaptchaProvider) captchaProvider;
            applicationContextCaptchaProvider.cleanOld(captchaLifeTime);
            applicationContextCaptchaProvider.setInMap(captchaKey, captcha);
            logger.debug("MAP:" + applicationContextCaptchaProvider.getMap());
            if (captchaProvider instanceof CookieCaptchaProvider) {
                Cookie cookie = new Cookie(CAPTCHA_COOKIE, String.valueOf(captchaKey));
                response.addCookie(cookie);
                response.sendRedirect(SIGN_UP_JQUERY_JSP);
            } else {
                request.setAttribute(CAPTCHA_HIDDEN, captchaKey);
                RequestDispatcher requestDispatcher = request.getRequestDispatcher(SIGN_UP_JQUERY_JSP);
                requestDispatcher.forward(request, response);
            }
        }
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = new User(
                request.getParameter("firstName"),
                request.getParameter("secondName"),
                request.getParameter("email"),
                request.getParameter("psw1"),
                Boolean.parseBoolean(request.getParameter("emailAccept")));
        boolean isValid = false;
        logger.debug(user);
        try {
            userService.valid(request.getParameter("psw2"), user);
            captchaProvider.checkIdentifier(request, captchaLifeTime);
            isValid = true;
        } catch (CaptchaException e) {
            request.getSession().setAttribute("alertFromServer", System.lineSeparator() + e.getMessage());
            request.getSession().setAttribute("user", user);
            setCaptcha(request, response);
        }
        if (isValid) {
            user.setImgPath(saveImage(request));
            userService.registerUser(user);
            request.getRequestDispatcher("/logIn").forward(request, response);
        }
    }


    protected String saveImage(HttpServletRequest request) {
        Part filePart;
        String imgName = null;
        try {
            filePart = request.getPart("file");
            if (Objects.isNull(filePart)) {
                return null;
            }
            String path = System.getProperty("user.dir") + File.separator + "src" +
                    File.separator + "main" +
                    File.separator + "webapp" +
                    File.separator + "upload";
            File file = new File(path);
            if (!file.exists()) {
                file.mkdir();
            }
            imgName = random() + "avatar.jpg";
            String pathAvatar = path + File.separator + imgName;
            save(filePart, pathAvatar);
        } catch (IOException | ServletException e) {
            logger.error(e.getMessage());
        }
        return "upload\\" + imgName;
    }

    private void save(Part filePart, String pathAvatar) throws IOException {
        try (OutputStream out = new FileOutputStream(new File(pathAvatar));
             InputStream filecontent = filePart.getInputStream()) {
            int read;
            final byte[] bytes = new byte[1024];
            while ((read = filecontent.read(bytes)) != -1) {
                out.write(bytes, 0, read);
            }
            logger.debug("File being uploaded ");
        } catch (FileNotFoundException e) {
            logger.error("Problems during file upload." + e.getMessage());
        }
    }

}
