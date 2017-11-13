package com.security.util.soft_sm4;

public class Sm4Util {


	  public static byte[] decryptData_ECB(byte[] cipherText,byte[] keyBytes) throws Exception {
            SM4_Context ctx = new SM4_Context();
            ctx.isPadding = false;
            ctx.mode = SM4.SM4_DECRYPT;

            SM4 sm4 = new SM4();
            sm4.sm4_setkey_dec(ctx, keyBytes);

            byte[] clearText = sm4.sm4_crypt_ecb(ctx, cipherText);

            return clearText;
	 }
}
