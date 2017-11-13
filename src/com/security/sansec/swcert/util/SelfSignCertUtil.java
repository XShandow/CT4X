package com.security.sansec.swcert.util;

import java.math.BigInteger;
import java.security.KeyPair;
import java.security.Security;
import java.security.cert.X509Certificate;
import java.util.Date;

import com.sansec.jce.X509Principal;
import com.sansec.jce.provider.SwxaProvider;
import com.security.util.util.Base64Util;
import com.security.util.util.CertUtil;
import com.security.util.util.SwCertUtil;
import com.security.util.util.SwKeyUtil;

public class SelfSignCertUtil {

	/**
	 * 生成自签名证书 （内部密钥索引）
	 * @param subjectInfo 使用信息
	 * @param validYear	    有效期
	 * @param keyIndex	    密钥索引
	 * @param swxaIniPath swxa配置文件
	 * @param keyAlg	    密钥算法
	 * @param signAlg     签名算法
	 * @return	返回值
	 * @throws Exception
	 */
	public static String genSelfSignCertTrue(String subjectInfo, String validYear, String keyIndex,
											 String keyAlg, String signAlgorithm, String swxaIniPath) throws Exception {
		Security.insertProviderAt(new SwxaProvider(swxaIniPath), 1);

		BigInteger serialNum =CertUtil.genCertSerialNum();

		String subjectDN = CertUtil.getSubjectDN(subjectInfo);

		//设置有效期
		Date notBefore = new Date();
		long validity =Long.parseLong(validYear) * 1000 * 60 * 60 * 24*365;
		Date notAfter = new Date(notBefore.getTime() + validity);

		//获取根密钥算法类型
		KeyPair certKeyPair =null;
		certKeyPair =SwKeyUtil.getKeyPairByIndex(keyAlg, Integer.parseInt(keyIndex));

		X509Certificate certificate = SwCertUtil.genCert(serialNum, notBefore, notAfter, new X509Principal(subjectDN) , new X509Principal(subjectDN), signAlgorithm, certKeyPair.getPublic(), certKeyPair.getPrivate());

		StringBuffer stringBuffer = new StringBuffer();
		stringBuffer.append("[PublicKey]\n");
		stringBuffer.append(Base64Util.encode(certKeyPair.getPublic().getEncoded()));
		stringBuffer.append("\n\n[Cert]\n");
		stringBuffer.append(Base64Util.encode(certificate.getEncoded()));

		return stringBuffer.toString();
	}

	/**
	 * 生成自签名证书 （密钥长度）
	 * @param subjectInfo
	 * @param validYear
	 * @param keyLength
	 * @param keyAlg
	 * @param signAlgorithm
	 * @param swxaIniPath
	 * @return
	 * @throws Exception
	 */
	public static String genSelfSignCertFalse(String subjectInfo, String validYear, String keyLength,
											  String keyAlg, String signAlgorithm, String swxaIniPath)throws Exception {
		Security.insertProviderAt(new SwxaProvider(swxaIniPath), 1);

		BigInteger serialNum =CertUtil.genCertSerialNum();

		String subjectDN = CertUtil.getSubjectDN(subjectInfo);

		//设置有效期
		Date notBefore = new Date();
		long validity =Long.parseLong(validYear) * 1000 * 60 * 60 * 24*365;
		Date notAfter = new Date(notBefore.getTime() + validity);

		//获取根密钥算法类型
		KeyPair certKeyPair =null;
		certKeyPair =SwKeyUtil.getKeyPairByLength(keyAlg, Integer.parseInt(keyLength));

		X509Certificate certificate = SwCertUtil.genCert(serialNum, notBefore, notAfter, new X509Principal(subjectDN) , new X509Principal(subjectDN), signAlgorithm, certKeyPair.getPublic(), certKeyPair.getPrivate());

		StringBuffer stringBuffer = new StringBuffer();
		stringBuffer.append("[PubKey]\n");
		stringBuffer.append(Base64Util.encode(certKeyPair.getPublic().getEncoded()));
		stringBuffer.append("\n\n[PriKey]\n");
		stringBuffer.append(Base64Util.encode(certKeyPair.getPrivate().getEncoded()));
		stringBuffer.append("\n\n[Cert]\n");
		stringBuffer.append(Base64Util.encode(certificate.getEncoded()));

		return stringBuffer.toString();
	}


}
