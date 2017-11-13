package com.security.util.util;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;

public class SwKeyUtil {
	/**
	 * 通过内部 索引 算法 获取密钥对
	 * @param keyAlg
	 * @param keyIndex
	 * @return
	 * @throws NoSuchAlgorithmException
	 */
	public static KeyPair getKeyPairByIndex(String keyAlg,int keyIndex ) throws NoSuchAlgorithmException {
		KeyPairGenerator keyGen =KeyPairGenerator.getInstance(keyAlg);

		keyGen.initialize(keyIndex << 16);
		KeyPair keypair =keyGen.generateKeyPair();

		return keypair;
	}

	/**
	 * 通过 算法和长度 获取密钥对
	 * @param keyAlg
	 * @param length
	 * @return
	 * @throws NoSuchAlgorithmException
	 */
	public static KeyPair getKeyPairByLength(String keyAlg,int length ) throws NoSuchAlgorithmException {
		KeyPairGenerator keyGen =KeyPairGenerator.getInstance(keyAlg);

		keyGen.initialize(length);
		KeyPair keypair =keyGen.generateKeyPair();

		return keypair;
	}
}
