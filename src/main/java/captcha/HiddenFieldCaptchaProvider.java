package captcha;

import entity.Captcha;

import javax.servlet.http.HttpServletRequest;
import java.util.Objects;

import static entity.Captcha.CAPTCHA_STR;

public class HiddenFieldCaptchaProvider extends ApplicationContextCaptchaProvider {
    @Override
    protected Captcha getCaptcha(HttpServletRequest request) {
        Captcha captcha;
        String captchaUser = request.getParameter(CAPTCHA_STR);
        String key = request.getParameter("captchaCodeHidden");
        captcha = getMap().get(Integer.parseInt(key));
        if (Objects.nonNull(captcha) && captcha.getCaptchaValue() == Integer.parseInt(captchaUser)) {
            return captcha;
        }
        captcha = new Captcha();
        captcha.setCaptchaStartTime(0);
        return captcha;
    }
}
