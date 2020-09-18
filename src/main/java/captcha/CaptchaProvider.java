package captcha;

import exeption.CaptchaException;

import javax.servlet.http.HttpServletRequest;

public interface CaptchaProvider {
    int getIdentifier(HttpServletRequest request);

    boolean checkIdentifier(HttpServletRequest request, long time) throws CaptchaException;


}
