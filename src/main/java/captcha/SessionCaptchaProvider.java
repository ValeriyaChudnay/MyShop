package captcha;

import entity.Captcha;
import exeption.CaptchaException;

import javax.servlet.http.HttpServletRequest;

public class SessionCaptchaProvider implements CaptchaProvider {


    private static final String CAPTCHA_STR = "captcha";

    @Override
    public int getIdentifier(HttpServletRequest request) {
        int captchaCode = Integer.parseInt(request.getParameter("value"));
        return captchaCode;
    }

    @Override
    public boolean checkIdentifier(HttpServletRequest request, long lifeTime) throws CaptchaException {
        Captcha captcha = (Captcha) request.getSession().getAttribute(CAPTCHA_STR);
        if (captcha.getCaptchaStartTime() + lifeTime < System.currentTimeMillis()) {
            throw new CaptchaException("Captcha life time is end ");
        }
        String captchaUser = request.getParameter(CAPTCHA_STR);
        System.out.println(captcha);
        if (captchaUser.equals(String.valueOf(captcha.getCaptchaValue()))) {
            return true;
        }
        throw new CaptchaException("Captcha not valid");
    }

}
