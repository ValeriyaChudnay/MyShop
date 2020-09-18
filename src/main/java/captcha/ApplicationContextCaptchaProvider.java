package captcha;

import entity.Captcha;
import exeption.CaptchaException;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;


public abstract class ApplicationContextCaptchaProvider implements CaptchaProvider {

    private Map<Integer, Captcha> map;
    private Logger logger = Logger.getLogger(ApplicationContextCaptchaProvider.class);

    public ApplicationContextCaptchaProvider() {
        this.map = new HashMap<>();
    }

    public Map<Integer, Captcha> getMap() {
        return map;
    }

    public void setInMap(int code, Captcha captcha) {
        map.put(code, captcha);
    }

    @Override
    public int getIdentifier(HttpServletRequest request) {
        String cookieStr = request.getParameter("codeHidden");
        logger.debug("cookieStr " + cookieStr);
        if (cookieStr.equals("")) {
            cookieStr = request.getParameter("codeCookie");
        }
        logger.debug("cookieStr " + cookieStr);
        return map.get(Integer.parseInt(cookieStr.trim())).getCaptchaValue();
    }

    @Override
    public boolean checkIdentifier(HttpServletRequest request, long lifeTime) throws CaptchaException {
        Captcha captcha = getCaptcha(request);
        logger.debug("captcha:" + captcha);
        if (captcha.getCaptchaStartTime() + lifeTime < System.currentTimeMillis()) {
            throw new CaptchaException("Captcha time is end or captcha not valid");
        }
        return true;
    }

    protected abstract Captcha getCaptcha(HttpServletRequest request);

    public void cleanOld(long timeLifeCaptcha) {
        Iterator<Map.Entry<Integer, Captcha>> it = map.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry<Integer, Captcha> pair = it.next();
            if (System.currentTimeMillis() - pair.getValue().getCaptchaStartTime() > timeLifeCaptcha) {
                it.remove();
            }
        }
    }
}
