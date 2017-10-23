package com.example.demo.utils;

import java.nio.charset.Charset;

import com.alibaba.fastjson.JSON;

public class JsonSerializeUtil {

	public static byte[] serialize(Object object) {
		String rs = JSON.toJSONString(object);
		StringBuffer strBuf = new StringBuffer();
		strBuf.append("__javaclass__").append(";")
				.append(object.getClass().getName()).append(";").append(rs);
		return strBuf.toString().getBytes(Charset.forName("UTF8"));
	}

	public static byte[] serializeKey(String object) {
		return (object == null ? null : object
				.getBytes(Charset.forName("UTF8")));
	}

	public static Object unserialize(byte[] bytes) {
		if (bytes != null) {
			String all = new String(bytes, Charset.forName("UTF8"));
			if (all != null && all.startsWith("__javaclass__")) {
				String[] vals = all.split(";", 3);
				if (vals != null && vals.length == 3) {
					try {
						return JSON.parseObject(vals[2], Class.forName(vals[1]));
					} catch (ClassNotFoundException e) {
						// TODO Auto-generated catch block
						// e.printStackTrace();
					}
				}
			}
		}
		return null;
	}

	public static String unserializeKey(byte[] bytes) {
		return new String(bytes, Charset.forName("UTF8"));
	}
}
