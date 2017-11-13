package com.security.util.util;

import org.apache.commons.codec.binary.Base64;

public class Base64Util {
private static Base64 base64 = new Base64();

	public static String encode(byte[] data){
		return base64.encodeToString(data);
	}
	public static byte[] decode(String data){
		return base64.decode(data.getBytes());
	}
	public static String decodeToString(String data){
		return new String(decode(data));
	}
}
