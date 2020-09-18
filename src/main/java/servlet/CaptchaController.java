package servlet;


import captcha.CaptchaProvider;
import org.apache.log4j.Logger;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

@WebServlet("/captcha")
public class CaptchaController extends HttpServlet {
    private static final long serialVersionUID = -1761346889117186607L;
    private static final Logger logger = Logger.getLogger(CaptchaController.class);
    private CaptchaProvider captchaProvider;

    @Override
    public void init() {
        captchaProvider = (CaptchaProvider) this.getServletContext().getAttribute("captchaType");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {

        response.setContentType("image/jpg");
        ServletOutputStream out = response.getOutputStream();

        BufferedImage image = new BufferedImage(200, 40, BufferedImage.TYPE_BYTE_INDEXED);

        Graphics2D graphics = image.createGraphics();

        graphics.setColor(Color.WHITE);
        graphics.fillRect(0, 0, 200, 40);

        GradientPaint gradientPaint = new GradientPaint(10, 5, Color.CYAN, 20, 10, Color.LIGHT_GRAY, true);
        graphics.setPaint(gradientPaint);
        Font font = new Font("Comic Sans MS", Font.BOLD, 30);
        graphics.setFont(font);
        int ident = captchaProvider.getIdentifier(request);
        logger.debug("ident:" + ident);
        graphics.drawString(String.valueOf(ident), 5, 30);

        graphics.dispose();
        ImageIO.write(image, "jpeg", out);

        out.close();
    }
}
