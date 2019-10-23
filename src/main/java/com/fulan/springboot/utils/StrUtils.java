package com.fulan.springboot.utils;

import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.Collection;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

/**
 * 常见辅助类
 * 
 * @author zyh
 * 
 */
@SuppressWarnings({ "rawtypes" })
public class StrUtils {

	private static Log log = LogFactory.getLog(StrUtils.class);

	public static final String pattern = "auth_";

	/**
	 * 判断对象是否为空
	 * 
	 * @param obj
	 *            object
	 * @return true or false
	 */
	public static boolean isEmpty(Object obj) {
		if (obj == null)
			return true;
		if (obj == "")
			return true;
		if (obj instanceof String) {
			if (((String) obj).length() == 0 || "[]".equals(obj) || "null".equals(obj) || ((String) obj).trim().equals("")) {
				return true;
			}
		} else if (obj instanceof Collection) {
			if (((Collection) obj).size() == 0) {
				return true;
			}
		} else if (obj instanceof Map) {
			if (((Map) obj).size() == 0) {
				return true;
			}
		}
		return false;
	}

	/**
	 * 获取当前请求
	 * 
	 * @return
	 */
	public static HttpServletRequest getHttpServletRequest() {
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
		return request;
	}

	/**
	 * 解密前端encodeURIComponent()密文
	 * 
	 * @return
	 */
	public static String decode(String param) {
		String val = "";

		try {
			if (StrUtils.isEmpty(param)) {
				return val;
			}
			val = URLDecoder.decode(param, "UTF-8");
		} catch (Exception e) {
			log.warn(param + "解密异常：" + e.getMessage());
		}

		return val;
	}

	/**
	 * encodeURIComponent()方式加密
	 * 
	 * @return
	 */
	public static String encode(String param) {
		String val = "";

		try {
			if (StrUtils.isEmpty(param)) {
				return val;
			}
			val = URLEncoder.encode(param, "UTF-8");
		} catch (Exception e) {
			log.warn(param + "加密异常：" + e.getMessage());
		}

		return val;
	}

	/**
	 * 获取随机数
	 * 
	 * @param len
	 *            获取几位数
	 * @return
	 */
	public static String getRandomNumber(Integer len) {
		String chars = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
		String result = "";

		for (int i = 0; i < len; i++) {
			int rand = (int) (Math.random() * 62);
			result = result + chars.charAt(rand);
		}

		return result;
	}

}
