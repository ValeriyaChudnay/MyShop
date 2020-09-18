package captcha;

import entity.Captcha;
import org.apache.log4j.Logger;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import static entity.Captcha.CAPTCHA_COOKIE;
import static entity.Captcha.CAPTCHA_STR;

public class CookieCaptchaProvider extends ApplicationContextCaptchaProvider {
    Logger logger = Logger.getLogger(CookieCaptchaProvider.class);

    @Override
    public int getIdentifier(HttpServletRequest request) {
        String cookieStr = request.getParameter("codeCookie");
        logger.debug("cookieStr " + cookieStr);
        return getMap().get(Integer.parseInt(cookieStr.trim())).getCaptchaValue();
    }

    @Override
    protected Captcha getCaptcha(HttpServletRequest request) {
        Captcha captcha = new Captcha();
        captcha.setCaptchaStartTime(0);
        String captchaUser = request.getParameter(CAPTCHA_STR);
        logger.debug("userCaptcha" + captchaUser);
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                logger.debug("cookie:" + cookie.getName() + " | val:" + cookie.getValue());
                if (cookie.getName().equals(CAPTCHA_COOKIE)) {
                    logger.debug("Name cookie: " + cookie.getName());
                    if (getMap().get(Integer.parseInt(cookie.getValue())).getCaptchaValue() == Integer.parseInt(captchaUser)) {
                        captcha = getMap().get(Integer.parseInt(cookie.getValue()));
                    }
                }
            }
        }
        return captcha;
    }
}
