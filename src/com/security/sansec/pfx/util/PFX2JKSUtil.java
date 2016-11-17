package com.security.sansec.pfx.util;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.io.OutputStream;
import java.security.Key;
import java.security.KeyStore;
import java.security.cert.Certificate;
import java.security.cert.X509Certificate;
import java.util.Enumeration;

public class PFX2JKSUtil {

	public static void transform(String pfxPath,String pfxPSW,String jksPath,String jksPSW) throws Exception {

		File fileIn = new File(pfxPath);

		File fileOut = new File(pfxPSW);

		if (!(fileIn.canRead())) {
			System.err.println("Unable to access input keystore: " + fileIn.getPath());

			System.exit(2);
		}

		if ((fileOut.exists()) && (!(fileOut.canWrite()))) {
			System.err.println("Output file is not writable: " + fileOut.getPath());

			System.exit(2);
		}

		KeyStore kspkcs12 = KeyStore.getInstance("pkcs12");
		KeyStore ksjks = KeyStore.getInstance("jks");

		char[] inphrase = pfxPSW.toCharArray();		//pfx����
		char[] outphrase = jksPSW.toCharArray();	//jks����

		kspkcs12.load(new FileInputStream(fileIn), inphrase);

		ksjks.load((fileOut.exists()) ? new FileInputStream(fileOut) : null, outphrase);

		Enumeration eAliases = kspkcs12.aliases();
		int n = 0;
		while (eAliases.hasMoreElements()) {
			String strAlias = (String) eAliases.nextElement();
			System.err.println("Alias " + (n++) + ": " + strAlias);

			if (kspkcs12.isKeyEntry(strAlias)) {
				System.err.println("Adding key for alias " + strAlias);
				Key key = kspkcs12.getKey(strAlias, inphrase);

				Certificate[] chain = kspkcs12.getCertificateChain(strAlias);

				ksjks.setKeyEntry(strAlias, key, outphrase, chain);
			}
		}

		OutputStream out = new FileOutputStream(fileOut);
		ksjks.store(out, outphrase);
		out.close();
	}

}