package com.security.util.util;

import java.math.BigInteger;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.interfaces.RSAPublicKey;
import java.util.Date;

public class CertUtil {
	/**
	 * 转换SubjectDN信息
	 * @param userInfo
	 * @return
	 */
	public static String getSubjectDN(String userInfo) {
		userInfo = userInfo.replace(" = ", "=");
		userInfo = userInfo.replace("= ", "=");
		userInfo = userInfo.replace(" =", "=");
		userInfo = userInfo.replace(" , ", ",");
		userInfo = userInfo.replace(", ", ",");
		userInfo = userInfo.replace(" ,", ",");
		userInfo = userInfo.replace("LOCAL", "l");
		userInfo = userInfo.replace("TEL", "telephonenumber");
		userInfo = userInfo.replace("PC", "postalcode");
		userInfo = userInfo.replace("PA", "postaladdress");
		userInfo = userInfo.replace("Email", "emailaddress");

		return userInfo;
	}

	/**
	 * 产生证书序列号
	 * @return
	 * @throws Exception
	 */
	public static BigInteger genCertSerialNum() throws Exception{

		BigInteger sr = null;

		byte[] serno = new byte[8];
		SecureRandom random;

		random = SecureRandom.getInstance("SHA1PRNG");
		long seed = 1L;
		synchronized (CertUtil.class) {
			try {Thread.sleep(1);} catch (InterruptedException e) {}
			seed = new Date().getTime();
		}
		random.setSeed(seed);
		random.nextBytes(serno);
		sr = new BigInteger(serno).abs();

		return sr;

	}


}
