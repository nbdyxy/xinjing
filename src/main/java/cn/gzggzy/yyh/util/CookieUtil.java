package cn.gzggzy.yyh.util;

import java.net.URLDecoder;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

public class CookieUtil {
	
	private static final Logger log = LoggerFactory.getLogger(CookieUtil.class);
	
	public static Cookie[] getCookies() {
		ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
		HttpServletRequest request = attributes.getRequest();
		Cookie[] c = request.getCookies();
		return c;
	}
	
	public static void saveCookie(Cookie cookie) {
		ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
		HttpServletResponse response = attributes.getResponse();
		response.addCookie(cookie);
	}
	
	/*
	 * 添加cookie
	 */
	public static void setCookie(String cookieName, int expiry, String cookieKey) {
        Cookie cookie = new Cookie(cookieName, cookieKey);
        cookie.setSecure(false);// 为true时用于https
        cookie.setMaxAge(expiry);
        cookie.setPath("/");
        cookie.setHttpOnly(true);
        saveCookie(cookie);
    }
	
	/*
	 * 根据名称获取cookie值
	 */
	public static String getCookie(String cookieName) {
		try {

			Cookie[] cookies = getCookies();

			for (int i = 0; i < (cookies == null ? 0 : cookies.length); i++) {
				if ((cookieName).equalsIgnoreCase(cookies[i].getName())) {
					return URLDecoder.decode(cookies[i].getValue(), "UTF-8");
				}
			}
		} catch (Exception e) {
			log.error(" --------获取String cookie 失败--------   {}", e.getMessage());
		}
		return null;
	}
	
	/*
	 * 根据名称删除cookie
	 */
	public static void removeCookie(String cookieName) {
		try {

			Cookie[] cookies = getCookies();

			for (int i = 0; i < (cookies == null ? 0 : cookies.length); i++) {
				if ((cookieName).equalsIgnoreCase(cookies[i].getName())) {

					Cookie cookie = new Cookie(cookieName, "");
					cookie.setPath("/");
					cookie.setMaxAge(0);
					saveCookie(cookie);
				}
			}

		} catch (Exception e) {
			log.error(" -------删除cookie失败！-------- {}", e.getMessage());
		}
	}
}	
