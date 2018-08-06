package cn.gzggzy.yyh.response.bean;

import java.util.HashMap;
import java.util.Map;

public class RestResponseHashMap extends HashMap<String, Object> {
	private static final long serialVersionUID = 1L;

	public RestResponseHashMap() {
		this.put((String) "code", Integer.valueOf(200));
		this.put((String) "message", "访问成功！");
	}

	public static RestResponseHashMap error() {
		return error(500, "未知异常，请联系管理员", (Object) null);
	}

	public static RestResponseHashMap error(String msg, Object content) {
		return error(500, msg, (Object) null);
	}

	public static RestResponseHashMap error(int code, String msg, Object content) {
		RestResponseHashMap r = new RestResponseHashMap();
		r.put((String) "code", Integer.valueOf(code));
		r.put((String) "message", msg);
		r.put("content", content);
		return r;
	}

	public static RestResponseHashMap success(String msg, Object content) {
		RestResponseHashMap r = new RestResponseHashMap();
		r.put((String) "message", msg);
		r.put("content", content);
		return r;
	}

	public static RestResponseHashMap success(Object content) {
		RestResponseHashMap r = new RestResponseHashMap();
		r.put("content", content);
		return r;
	}

	public static RestResponseHashMap success(Map<String, Object> map) {
		RestResponseHashMap r = new RestResponseHashMap();
		r.putAll(map);
		return r;
	}

	public static RestResponseHashMap success() {
		return new RestResponseHashMap();
	}

	public RestResponseHashMap put(String key, Object value) {
		super.put(key, value);
		return this;
	}
}