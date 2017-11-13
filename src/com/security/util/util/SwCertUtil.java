package com.security.util.util;

import java.math.BigInteger;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Security;
import java.security.cert.X509Certificate;
import java.util.Date;

import com.sansec.jce.X509Principal;
import com.sansec.jce.provider.SwxaProvider;
import com.sansec.x509.X509V3CertificateGenerator;

public class SwCertUtil {


	/**
	 *	生成证书
	 * @param serialNum
	 * @param notBefore
	 * @param notAfter
	 * @param issuerName
	 * @param subjectName
	 * @param signAlgorithm
	 * @param subjectPublicKey
	 * @param issuerPrivateKey
	 * @param providerConf    Swxa.ini 路径
	 * @return
	 * @throws Exception
	 */
	public static X509Certificate genCert(BigInteger serialNum, Date notBefore, Date notAfter, X509Principal issuerName, X509Principal subjectName,
										  String signAlgorithm, PublicKey subjectPublicKey, PrivateKey issuerPrivateKey) throws Exception{


		X509V3CertificateGenerator certGen = new X509V3CertificateGenerator();

		certGen.setSerialNumber(serialNum);

		certGen.setIssuerDN(issuerName);

		certGen.setNotBefore(notBefore);

		certGen.setNotAfter(notAfter);

		certGen.setSubjectDN(subjectName);

		certGen.setPublicKey(subjectPublicKey);

		certGen.setSignatureAlgorithm(signAlgorithm);

		return certGen.generate(issuerPrivateKey);
	}


}
