package com.security.sansec.jks.util;

import java.io.FileInputStream;
import java.security.KeyStore;
import java.security.Security;
import java.security.cert.X509Certificate;
import java.util.Enumeration;

import com.sansec.jce.provider.SwxaProvider;
import com.security.util.util.Base64Util;

public class JKSListUtil {

	public static String getKeyStoreList(String keyStoreType, String keyStorePsw, String keyStoreFilePath,String swxaIniPath)throws Exception {
		switch (keyStoreType) {
		case "JKS":
			break;
		case "SWKS":
			Security.insertProviderAt(new SwxaProvider(swxaIniPath), 1);
			break;
		}
		FileInputStream in = new FileInputStream(keyStoreFilePath);
		KeyStore ks = KeyStore.getInstance(keyStoreType);
	    ks.load(in, keyStorePsw.toCharArray());

	    Enumeration<String> aliases = ks.aliases();
		StringBuilder sb = new StringBuilder(ks.size() * 7);
		while (aliases.hasMoreElements()) {
			String aliasesNameString = aliases.nextElement();
			X509Certificate certificate = (X509Certificate)ks.getCertificate(aliasesNameString);
			sb.append("Aliase:"+aliasesNameString+"\n");
			sb.append("SubjectDN:"+certificate.getSubjectDN()+"\n");
			sb.append("Valid:"+certificate.getNotBefore().toLocaleString()+"\t"+certificate.getNotAfter().toLocaleString()+"\n");
			sb.append("CertBsae64:"+Base64Util.encode(certificate.getEncoded())+"\n\n");

		}

		String list = "KeyStore Type:"+ks.getType()+"\nKeyStore Provider:"+ks.getProvider()+"\nKeyStore Size:"+ks.size()+"\n\n";

		return list+sb.toString();
	}

}
