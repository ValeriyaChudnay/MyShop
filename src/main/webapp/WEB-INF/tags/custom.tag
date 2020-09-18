<%@ attribute name="captchaValue" required="true" %>
<%@ attribute name="captchaCodeCookie" required="true" %>
<%@ attribute name="captchaCodeHidden" required="true" %>
<%@ tag description="Category Options" trimDirectiveWhitespaces="true"  pageEncoding="UTF-8" %>
  <img alt="captcha" src=captcha?value=${captchaValue}&codeCookie=${captchaCodeCookie}&codeHidden=${captchaCodeHidden} />
  <input hidden="true" name="captchaCodeHidden" value="${captchaCodeHidden}">