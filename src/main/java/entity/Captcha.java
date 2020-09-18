package entity;

import java.io.Serializable;

public class Captcha implements Serializable {
    public static final String CAPTCHA_STR = "captcha";
    public static final String CAPTCHA_COOKIE = "captchaCookie";
    public static final String CAPTCHA_HIDDEN = "captchaHidden";
    private static final long serialVersionUID = 12633456277283L;
    private long captchaStartTime;
    private int captchaValue;

    public long getCaptchaStartTime() {
        return captchaStartTime;
    }

    public void setCaptchaStartTime(long time) {
        captchaStartTime = time;
    }

    public int getCaptchaValue() {
        return captchaValue;
    }

    public void setCaptchaValue(int r) {
        captchaValue = r;
    }

    @Override
    public String toString() {
        return "Captcha{" +
                ", captchaStartTime=" + captchaStartTime +
                ", captchaValue=" + captchaValue +
                '}';
    }

}
