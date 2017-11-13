package com.security.hsm_jna;

import java.io.File;
import java.util.Arrays;
import java.util.List;

import com.sun.jna.Library;
import com.sun.jna.Native;
import com.sun.jna.Pointer;
import com.sun.jna.Structure;
import com.sun.jna.ptr.IntByReference;
import com.sun.jna.ptr.PointerByReference;

public interface PKI_HSM extends Library{

	/*设备信息*/
	public static class DeviceInfo extends Structure{
		public byte[] 	IssuerName =new byte[40];
		public byte[] 	DeviceName=new byte[16];
		public byte[] 	DeviceSerial=new byte[16];
		public int  	DeviceVersion;
		public int  	StandardVersion;
		public int[]  	AsymAlgAbility=new int[2];
		public int  	SymAlgAbility;
		public int  	HashAlgAbility;
		public int  	BufferSize;

		//结构体传指针
		public static class ByReference extends DeviceInfo implements Structure.ByReference{}

		@Override
		protected List getFieldOrder() {
			// TODO Auto-generated method stub
			return Arrays.asList(new String[]{"IssuerName","DeviceName","DeviceSerial","DeviceVersion","StandardVersion","AsymAlgAbility","SymAlgAbility","HashAlgAbility","BufferSize"});
		}
	}

	public static class Device_Run_Status extends Structure{
		public int onboot;			//服务是否开机自启动
		public int service;			//当前服务状态，0-未启动，1-已启动，>1状态异常
		public int concurrency;		//当前并发数
		public int memtotal;		//内存大小
		public int memfree;			//内存空闲
		public int cpu;				//CPU占用率，不包含小数点部分
		public int reserve1;
		public int reserve2;

		//结构体传指针
		public static class ByReference extends Device_Run_Status implements Structure.ByReference{}

		@Override
		protected List getFieldOrder() {
			// TODO Auto-generated method stub
			return Arrays.asList(new String[]{"onboot","service","concurrency","memtotal","memfree","cpu","reserve1","reserve2"});
		}
	}

	/*RSA密钥*/
	public static final int  LiteRSAref_MAX_BITS=2048;
	public static final int  LiteRSAref_MAX_LEN =((LiteRSAref_MAX_BITS + 7)/8);
	public static final int  LiteRSAref_MAX_PBITS = ((LiteRSAref_MAX_BITS + 1) / 2);
	public static final int  LiteRSAref_MAX_PLEN = ((LiteRSAref_MAX_PBITS + 7)/ 8);


	public static class RSArefPublicKeyLite extends Structure {
		public int bits;
		public byte[] m =new byte[LiteRSAref_MAX_LEN];
		public byte[] e =new byte[LiteRSAref_MAX_LEN];

		//结构体传指针
	    public static class ByReference extends RSArefPublicKeyLite implements Structure.ByReference { }
	    //结构体传值
	    public static class ByValue extends RSArefPublicKeyLite implements Structure.ByValue{ }

		@Override
		protected List getFieldOrder() {
			// TODO Auto-generated method stub
			return Arrays.asList(new String[]{"bits","m","e"});
		}
	}

	public static class RSArefPrivateKeyLite extends Structure {
		public int bits;
		public byte[] 	m =new byte[LiteRSAref_MAX_LEN];
		public byte[]  	e=new byte[LiteRSAref_MAX_LEN];
		public byte[] 	d =new byte[LiteRSAref_MAX_LEN];
		public byte[] prime =new byte[LiteRSAref_MAX_LEN];   //JNA不支持多维数组
		public byte[] pexp =new byte[LiteRSAref_MAX_LEN];
		public byte[] 	coef =new byte[LiteRSAref_MAX_PLEN];

		//结构体传指针
	    public static class ByReference extends RSArefPrivateKeyLite implements Structure.ByReference { }
	    //结构体传值
	    public static class ByValue extends RSArefPrivateKeyLite implements Structure.ByValue{ }

		@Override
		protected List getFieldOrder() {
			// TODO Auto-generated method stub
			return Arrays.asList(new String[]{"bits","m","e","d","prime","pexp","coef"});
		}
	}

	public static final int ExRSAref_MAX_BITS  = 4096;
	public static final int ExRSAref_MAX_LEN   = ((ExRSAref_MAX_BITS + 7) / 8);
	public static final int ExRSAref_MAX_PBITS = ((ExRSAref_MAX_BITS + 1) / 2);
	public static final int ExRSAref_MAX_PLEN  = ((ExRSAref_MAX_PBITS + 7)/ 8);

	public static class RSArefPublicKeyEx extends Structure{
		 public int bits;
		 public byte[] m = new byte[ExRSAref_MAX_LEN];
		 public byte[] e = new byte[ExRSAref_MAX_LEN];

		 public static class ByReference extends RSArefPublicKeyEx implements Structure.ByReference{};
		 public static class ByValue extends RSArefPublicKeyEx implements Structure.ByValue{}

		@Override
		protected List getFieldOrder() {
			// TODO Auto-generated method stub
			return Arrays.asList(new String[]{"bits","m","e"});
		}
	}

	public static class RSArefPrivateKeyEx extends Structure{

		public int bits;
		public byte[] m = new byte[ExRSAref_MAX_LEN];
		public byte[] e = new byte[ExRSAref_MAX_LEN];
		public byte[] d = new byte[ExRSAref_MAX_LEN];
		public byte[] prime = new byte[ExRSAref_MAX_PLEN * 2];
		public byte[] pexp = new byte[ExRSAref_MAX_PLEN * 2];
		public byte[] coef = new byte[ExRSAref_MAX_PLEN];

		public static class ByReference extends RSArefPrivateKeyEx implements Structure.ByReference{}
		public static class ByValue extends RSArefPrivateKeyEx implements Structure.ByValue{}

		@Override
		protected List getFieldOrder() {
			// TODO Auto-generated method stub
			return Arrays.asList(new String[]{"bits", "m", "e", "d", "prime", "pexp", "coef"});
		}
	}

	/*ECC密钥*/
	public static final int ECCref_MAX_BITS = 256;
	public static final int ECCref_MAX_LEN = ((ECCref_MAX_BITS+7) / 8);
	public static final int ECCref_MAX_CIPHER_LEN = 136;

	public static class ECCrefPublicKey extends Structure{

		public int bits;
		public byte[] x = new byte[ECCref_MAX_LEN];
		public byte[] y = new byte[ECCref_MAX_LEN];

		public static class ByReference extends ECCrefPublicKey implements Structure.ByReference{}
		public static class ByValue extends ECCrefPublicKey implements Structure.ByValue{}

		@Override
		protected List getFieldOrder() {
			// TODO Auto-generated method stub
			return Arrays.asList(new String[]{"bits","x","y"});
		}
	}

	public static class ECCrefPrivateKey extends Structure{

		public int bits;
		public byte[] D = new byte[ECCref_MAX_LEN];

		public static class ByReference extends ECCrefPrivateKey implements Structure.ByReference{}
		public static class ByValue extends ECCrefPrivateKey implements Structure.ByValue{}

		@Override
		protected List getFieldOrder() {
			// TODO Auto-generated method stub
			return Arrays.asList(new String[]{"bits","D"} );
		}
	}

	/*ECC密文*/
	public static class ECCCipher extends Structure{

		public int clength; //C的有效长度
		public byte[] x = new byte[ECCref_MAX_LEN];
		public byte[] y = new byte[ECCref_MAX_LEN];
		public byte[] C = new byte[ECCref_MAX_CIPHER_LEN];
		public byte[] M = new byte[ECCref_MAX_LEN];

		public static class ByReference extends ECCCipher implements Structure.ByReference{}
		public static class ByValue extends ECCCipher implements Structure.ByValue{}
		@Override
		protected List getFieldOrder() {
			// TODO Auto-generated method stub
			return Arrays.asList(new String[]{"clength","x","y","C","M"});
		}
	}

	/*ECC签名*/
	public static class ECCSignature extends Structure{

		public byte[] r = new byte[ECCref_MAX_LEN];
		public byte[] s = new byte[ECCref_MAX_LEN];

		public static class ByReference extends ECCSignature implements Structure.ByReference{}
		public static class ByValue extends ECCSignature implements Structure.ByValue{}

		@Override
		protected List getFieldOrder() {
			// TODO Auto-generated method stub
			return Arrays.asList(new String[]{"r","s"});
		}
	}

	/*算法标识*/
	public static final int  SGD_SM1_ECB	= 0x00000101;
	public static final int  SGD_SM1_CBC	= 0x00000102;
	public static final int  SGD_SM1_CFB	= 0x00000104;
	public static final int  SGD_SM1_OFB	= 0x00000108;
	public static final int  SGD_SM1_MAC	= 0x00000110;
	public static final int  SGD_SM1_CTR	= 0x00000120;

	public static final int  SGD_SSF33_ECB = 0x00000201;
	public static final int  SGD_SSF33_CBC = 0x00000202;
	public static final int  SGD_SSF33_CFB = 0x00000204;
	public static final int  SGD_SSF33_OFB = 0x00000208;
	public static final int  SGD_SSF33_MAC = 0x00000210;
	public static final int  SGD_SSF33_CTR = 0x00000220;

	public static final int  SGD_AES_ECB	= 0x00000401;
	public static final int  SGD_AES_CBC	= 0x00000402;
	public static final int  SGD_AES_CFB	= 0x00000404;
	public static final int  SGD_AES_OFB	= 0x00000408;
	public static final int  SGD_AES_MAC	= 0x00000410;
	public static final int  SGD_AES_CTR	= 0x00000420;

	public static final int  SGD_3DES_ECB = 0x00000801;
	public static final int  SGD_3DES_CBC = 0x00000802;
	public static final int  SGD_3DES_CFB = 0x00000804;
	public static final int  SGD_3DES_OFB = 0x00000808;
	public static final int  SGD_3DES_MAC = 0x00000810;
	public static final int  SGD_3DES_CTR = 0x00000820;

	public static final int  SGD_SMS4_ECB = 0x00002001;
	public static final int  SGD_SMS4_CBC = 0x00002002;

	public static final int  SGD_RSA		= 0x00010000;
	public static final int  SGD_RSA_SIGN 	= 0x00010100;
	public static final int  SGD_RSA_ENC	= 0x00010200;

	public static final int SGD_SM2   = 0x00020000;
	public static final int SGD_SM2_1 = 0x00020100;
	public static final int SGD_SM2_2 = 0x00020200;
	public static final int SGD_SM2_3 = 0x00020400;

	public static final int SGD_SM3		= 0x00000001;
	public static final int SGD_SHA1    = 0x00000002;
	public static final int SGD_SHA256	= 0x00000004;
	public static final int SGD_SHA512	= 0x00000008;
	public static final int SGD_SHA384	= 0x00000010;
	public static final int SGD_SHA224	= 0x00000020;
	public static final int SGD_MD5		= 0x00000080;

	public static final int KEY_TYPE_ECDSA     = 7;
	public static final int KEY_TYPE_DSA       = 6;
	public static final int KEY_TYPE_RSA	   = 4;
	public static final int KEY_TYPE_KEK	   = 1;
	public static final int KEY_TYPE_SYMM_KEY  = 8;


	PKI_HSM instanseHSM = (PKI_HSM) Native.loadLibrary("."+File.separator+"swsds", PKI_HSM.class);

	/*设备管理类函数*/
	public int SDF_OpenDevice(PointerByReference ppDeviceHandle);
	public int SDF_CloseDevice(Pointer pDeviceHandle);

	public int SDF_OpenSession(Pointer pDeviceHandle, PointerByReference ppSessionHandle);
	public int SDF_CloseSession(Pointer pSessionHandle);

	public int SDF_GetPrivateKeyAccessRight();

	public int SDF_GetDeviceInfo(Pointer pSessionHandle, DeviceInfo.ByReference pstDeviceInfo);
	public int SDF_GenerateRandom(Pointer pSessionHandle,int length,byte[] random);
	public int SDF_GetKeyStatus(Pointer pSessionHandle, int uiKeyType, IntByReference puiKeyStatus, IntByReference puiKeyCount);
	public int SDF_GetDeviceRunStatus(Pointer pSessionHandle,Device_Run_Status.ByReference pstDeviceRunStatus);

	/*非对称密码RSA密钥管理、运算函数*/
	public int SDF_GenerateKeyPair_RSA(Pointer pSessionHandle, int uiKeyBits, RSArefPublicKeyLite.ByReference pucPublicKey, RSArefPrivateKeyLite.ByReference pucPrivateKey);
	public int SDF_ExportSignPublicKey_RSA(Pointer pSessionHandle, int  uiKeyIndex, RSArefPublicKeyLite.ByReference pucPublicKey);
	public int SDF_ExportEncPublicKey_RSA(Pointer pSessionHandle,int uiKeyIndex, RSArefPublicKeyLite.ByReference pucPublicKey);

		/**-----未测---------**/
	public int SDF_ExternalPublicKeyOperation_RSA(	Pointer pSessionHandle, RSArefPublicKeyLite.ByReference  pucPublicKey, byte[] pucDataInput,int uiInputLength,byte[] pucDataOutput,IntByReference puiOutputLength);
	public int SDF_ExternalPrivateKeyOperation_RSA(Pointer pSessionHandle, RSArefPrivateKeyLite.ByReference pucPrivateKey,byte[] pucDataInput,int uiInputLength,byte[] pucDataOutput,IntByReference puiOutputLength);
	public int SDF_InternalPublicKeyOperation_RSA(Pointer pSessionHandle,int  uiKeyIndex,int  uiKeyUsage,byte[] pucDataInput,int uiInputLength, byte[] pucDataOutput, IntByReference puiOutputLength);
	public int SDF_InternalPrivateKeyOperation_RSA(Pointer pSessionHandle,int uiKeyIndex,int  uiKeyUsage,byte[] pucDataInput,int  uiInputLength,byte[] pucDataOutput, IntByReference puiOutputLength);
	public int SDF_ExchangeDigitEnvelopeBaseOnRSA(Pointer pSessionHandle, int uiKeyIndex,RSArefPublicKeyLite.ByReference pucPublicKey,byte[] pucDEInput,int uiDELength,byte[] pucDEOutput,IntByReference puiDELength);

	/*非对称密码ECC密钥管理、运算函数*/
	public int SDF_GenerateKeyPair_ECC(Pointer pSessionHandle, int uiAlgID, int uiKeyBits, ECCrefPublicKey.ByReference pucPublicKey,ECCrefPrivateKey.ByReference pucPrivateKey);
	public int SDF_ExportSignPublicKey_ECC(Pointer pSessionHandle, int uiKeyIndex, ECCrefPublicKey.ByReference pucPublicKey);

	public int SDF_ExportEncPublicKey_ECC(Pointer pSessionHandle, int  uiKeyIndex,ECCrefPublicKey.ByReference pucPublicKey);

	public int SDF_ExternalSign_ECC(Pointer pSessionHandle, int uiAlgID,ECCrefPrivateKey.ByReference pucPrivateKey,byte[] pucData,int  uiDataLength,ECCSignature.ByReference pucSignature);
	public int SDF_ExternalVerify_ECC(Pointer pSessionHandle,int uiAlgID,ECCrefPublicKey.ByReference pucPublicKey,byte[] pucDataInput,int  uiInputLength,ECCSignature.ByReference pucSignature);

	public int SDF_InternalSign_ECC(Pointer pSessionHandle,int  uiISKIndex,byte[] pucData,int  uiDataLength,ECCSignature.ByReference pucSignature);
	public int SDF_InternalVerify_ECC(Pointer pSessionHandle,int  uiISKIndex,byte[] pucData,int  uiDataLength,ECCSignature.ByReference pucSignature);

	public int SDF_ExternalEncrypt_ECC(Pointer pSessionHandle,int uiAlgID,ECCrefPublicKey.ByReference  pucPublicKey,byte[] pucData,int  uiDataLength,ECCCipher.ByReference pucEncData);
	public int SDF_ExternalDecrypt_ECC(Pointer pSessionHandle,int uiAlgID,ECCrefPrivateKey.ByReference pucPrivateKey,ECCCipher.ByReference pucEncData,byte[] pucData,IntByReference puiDataLength);

	public int SDF_InternalEncrypt_ECC(Pointer pSessionHandle,int uiISKIndex,int uiAlgID,byte[] pucData,int  uiDataLength,ECCCipher.ByReference pucEncData);
	public int SDF_InternalDecrypt_ECC(Pointer pSessionHandle,int uiISKIndex,int uiAlgID,ECCCipher.ByReference pucEncData,byte[] pucData,IntByReference puiDataLength);

	public int SDF_InternalSign_ECC_Ex(Pointer  pSessionHandle,int  uiISKIndex,int uiAlgID, byte[] pucData,int uiDataLength,ECCSignature.ByReference pucSignature);
	public int SDF_InternalVerify_ECC_Ex(Pointer pSessionHandle,int  uiISKIndex,int uiAlgID,byte[] pucData,int uiDataLength,ECCSignature.ByReference pucSignature);

	/*对称密钥管理、密码运算函数*/
		/**----未测----*/
	public int SDF_GenerateKeyWithIPK_RSA(Pointer pSessionHandle, int uiIPKIndex,int uiKeyBits,byte pucKey,IntByReference puiKeyLength,PointerByReference phKeyHandle);
	public int SDF_GenerateKeyWithEPK_RSA(Pointer pSessionHandle, int uiKeyBits,RSArefPublicKeyLite.ByReference pucPublicKey,byte pucKey,IntByReference puiKeyLength,PointerByReference phKeyHandle);

	public int SDF_GenerateKeyWithKEK(Pointer pSessionHandle, int uiKeyBits,int uiAlgID,int uiKEKIndex, byte[] pucKey, IntByReference puiKeyLength, PointerByReference phKeyHandle);

	public int SDF_GenerateKeyWithIPK_ECC (Pointer pSessionHandle, int uiIPKIndex,int uiKeyBits,ECCCipher.ByReference pucKey,PointerByReference phKeyHandle);
	public int SDF_GenerateKeyWithEPK_ECC (Pointer pSessionHandle, int uiKeyBits,int  uiAlgID,ECCrefPublicKey.ByReference pucPublicKey,ECCCipher.ByReference pucKey,PointerByReference phKeyHandle);

	public int SDF_ImportKeyWithISK_RSA(Pointer pSessionHandle, int uiISKIndex,byte[] pucKey,int uiKeyLength,PointerByReference phKeyHandle);
	public int SDF_ImportKeyWithKEK(Pointer pSessionHandle, int  uiAlgID,int uiKEKIndex, byte[] pucKey, int uiKeyLength, PointerByReference phKeyHandle);

	public int SDF_ImportKeyWithISK_ECC (Pointer pSessionHandle,int uiISKIndex,ECCCipher.ByReference pucKey,PointerByReference phKeyHandle);

	public int SDF_ImportKey(Pointer pSessionHandle, byte[] pucKey, int uiKeyLength,PointerByReference phKeyHandle);

	public int SDF_DestroyKey(Pointer pSessionHandle, Pointer hKeyHandle);
	public int SDF_GetSymmKeyHandle(Pointer pSessionHandle, int uiKeyIndex, PointerByReference phKeyHandle);
	public int SDF_Encrypt(Pointer pSessionHandle,Pointer hKeyHandle,int uiAlgID,byte[] pucIV,byte[] pucData,int uiDataLength,byte[] pucEncData,IntByReference puiEncDataLength);
	public int SDF_Decrypt (Pointer pSessionHandle,Pointer hKeyHandle,int uiAlgID,byte[] pucIV,byte[] pucEncData,int  uiEncDataLength,byte[] pucData,IntByReference puiDataLength);
	public int SDF_CalculateMAC(Pointer pSessionHandle,Pointer hKeyHandle,int uiAlgID,byte[] pucIV,byte[] pucData,int uiDataLength,byte[] pucMAC,IntByReference puiMACLength);

	/*杂凑运算函数*/
	public int SDF_HashInit(Pointer pSessionHandle,int uiAlgID,ECCrefPublicKey.ByReference pucPublicKey,byte[] pucID,int uiIDLength);
	public int SDF_HashUpdate(Pointer pSessionHandle,byte[] pucData,int uiDataLength);
	public int SDF_HashFinal(Pointer pSessionHandle,byte[] pucHash,IntByReference puiHashLength);

	/*用户文件操作函数*/
	public int SDF_CreateFile(Pointer pSessionHandle,byte[] pucFileName,int uiNameLen,int uiFileSize);
	public int SDF_ReadFile(Pointer pSessionHandle,byte[] pucFileName,int uiNameLen,int uiOffset,IntByReference puiReadLength,byte[] pucBuffer);
	public int SDF_WriteFile(Pointer pSessionHandle,byte[] pucFileName,int uiNameLen,int uiOffset,int uiWriteLength,byte[] pucBuffer);
	public int SDF_DeleteFile(Pointer pSessionHandle,byte[] pucFileName,int uiNameLen);

	public int SWCSM_GenerateRSAKeyPair(Pointer pSessionHandle, int  uiKeyNumber, int  uiKeyBits);
	public int SWCSM_InputRSAKeyPair(Pointer pSessionHandle, int uiKeyNumber,RSArefPublicKeyLite.ByReference pucPublicKey, RSArefPrivateKeyLite.ByReference pucPrivateKey);
	public int SWCSM_SetPrivateKeyAccessPwd(Pointer pSessionHandle, int uiKeyIndex, byte[] pucPassword, int uiPwdLength);

	//推荐使用以下函数
	public int SWMF_GenerateRSAKeyPair(Pointer pSessionHandle, int  uiKeyNumber, int  uiKeyBits);
	public int SWMF_InputRSAKeyPair(Pointer pSessionHandle, int uiKeyNumber,RSArefPublicKeyLite.ByReference pucPublicKey, RSArefPrivateKeyLite.ByReference pucPrivateKey);
	public int SWMF_SetPrivateKeyAccessPwd(Pointer pSessionHandle, int uiKeyIndex, byte[] pucPassword, byte uiPwdLength);
	public int SWMF_GenerateKEK(Pointer pSessionHandle, int  uiKeyNumber, int  uiKeyLength);
	public int SWMF_InputKEK(Pointer pSessionHandle, int  uiKeyNumber, byte[] pucKey, int uiKeyLength);
	public int SWCSM_GenerateRSAKeyPairEx(Pointer pSessionHandle, int  uiKeyNumber, int uiKeyBits,int unPublicExponent,RSArefPublicKeyLite.ByReference pucPublicKey,RSArefPrivateKeyLite.ByReference pucPrivateKey);

}
