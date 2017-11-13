package com.security.hsm_jna;

import java.io.File;

import com.sun.jna.Library;
import com.sun.jna.Native;
import com.sun.jna.Pointer;
import com.sun.jna.ptr.IntByReference;
import com.sun.jna.ptr.PointerByReference;

public interface SJJ_HSM extends Library{

	/*常量定义*/
	public static final int SGD_TRUE	=	0x00000001;
	public static final int SGD_FALSE	=	0x00000000;

	/*算法标识*/
	public static final int SGD_SM1_ECB	 =	0x00000101;
	public static final int SGD_SM1_CBC	 =	0x00000102;
	public static final int SGD_SM1_CFB	 =	0x00000104;
	public static final int SGD_SM1_OFB	 =	0x00000108;
	public static final int SGD_SM1_MAC	 =	0x00000110;
	public static final int SGD_SM1_CTR	 =	0x00000120;

	public static final int SGD_SSF33_ECB = 0x00000201;
	public static final int SGD_SSF33_CBC =	0x00000202;
	public static final int SGD_SSF33_CFB =	0x00000204;
	public static final int SGD_SSF33_OFB =	0x00000208;
	public static final int SGD_SSF33_MAC =	0x00000210;
	public static final int SGD_SSF33_CTR =	0x00000220;

	public static final int SGD_SM4_ECB	=	0x00002001;
	public static final int SGD_SM4_CBC	=	0x00002002;
	public static final int SGD_SM4_CFB	=	0x00002004;
	public static final int SGD_SM4_OFB	=	0x00002008;
	public static final int SGD_SM4_MAC	=	0x00002010;
	public static final int SGD_SM4_CTR	=	0x00002020;

	public static final int SGD_AES_ECB	=	0x00000401;
	public static final int SGD_AES_CBC	=	0x00000402;
	public static final int SGD_AES_CFB	=	0x00000404;
	public static final int SGD_AES_OFB	=	0x00000408;
	public static final int SGD_AES_MAC	=	0x00000410;
	public static final int SGD_AES_CTR	=	0x00000420;

	public static final int SGD_3DES_ECB =	0x00000801;
	public static final int SGD_3DES_CBC =	0x00000802;
	public static final int SGD_3DES_CFB =	0x00000804;
	public static final int SGD_3DES_OFB =	0x00000808;
	public static final int SGD_3DES_MAC =	0x00000810;
	public static final int SGD_3DES_CTR =	0x00000820;

	public static final int SGD_SM7_ECB	 =	0x00008001;
	public static final int SGD_SM7_CBC	 =	0x00008002;
	public static final int SGD_SM7_CFB	 =	0x00008004;
	public static final int SGD_SM7_OFB	 =	0x00008008;
	public static final int SGD_SM7_MAC	 =	0x00008010;
	public static final int SGD_SM7_CTR	 =	0x00008020;

	public static final int SGD_RSA		 =	0x00010000;
	public static final int SGD_RSA_SIGN =	0x00010100;
	public static final int SGD_RSA_ENC	 =	0x00010200;

	public static final int SGD_SM2 	=	0x00020000;
	public static final int SGD_SM2_1	=	0x00020100 ;//椭圆曲线签名算法
	public static final int SGD_SM2_2	=	0x00020200 ;//椭圆曲线密钥交换协议
	public static final int SGD_SM2_3	=	0x00020400 ;//椭圆曲线加密算法

	public static final int SGD_DSA		=	0x00040000;
	public static final int SGD_ECDSA	=	0x00080000;

	public static final int SGD_SM3		=	0x00000001;
	public static final int SGD_SHA1	=	0x00000002;
	public static final int SGD_SHA256	=	0x00000004;
	public static final int SGD_SHA512	=	0x00000008;
	public static final int SGD_SHA384	=	0x00000010;
	public static final int SGD_SHA224	=	0x00000020;
	public static final int SGD_MD5		=	0x00000080;

	public static final int PBOC_ARQC_LENGTH   =  8;
	public static final int PBOC_ARPC_LENGTH   =  8;
	public static final int PBOC_MAC_LENGTH    =  8;

	public static final int MAC_ANSI_X9_19     =  1;
	public static final int MAC_ANSI_X9_9      =  2;
	public static final int MAC_PBOC           =  3;

	//补丁模式
	public static final int NO_PADDING		   =  0;
	public static final int PADDING_PBOC	   =  1;

	//密钥类型定义
	public static final int KEY_TYPE_KEK		  =	1;
	public static final int KEY_TYPE_INT_RSA_KEY  = 152; //不可导出RSA密钥对
	public static final int KEY_TYPE_LMK          = 153 ;//本地主密钥
	public static final int KEY_TYPE_TRANSFER_KEY = 154; //传输密钥
	public static final int KEY_TYPE_WORK_KEY     = 157; //工作主密钥
	public static final int KEY_TYPE_TK_ENC       = 155; //ZEK, 3031
	public static final int KEY_TYPE_TK_MAC       = 156; //ZAK, 2627
	public static final int KEY_TYPE_MDK_AC       = 158; //MK-AC, 2829, 工作主密钥-应用密钥
	public static final int KEY_TYPE_MDK_ENC      = 159; //MK-SMC, 2829, 工作主密钥-加密密钥
	public static final int KEY_TYPE_MDK_MAC      = 160; //MK-SMI, 2829, 工作主密钥-MAC密钥
	public static final int KEY_TYPE_MDK_KMU      = 161; //2829, 卡控制密钥
	public static final int KEY_TYPE_ZMK		  =	201;
	public static final int KEY_TYPE_ZPK		  =	202;
	public static final int KEY_TYPE_PVK		  =	203;
	public static final int KEY_TYPE_TPK		  =	204;
	public static final int KEY_TYPE_TMK		  =	205;
	public static final int KEY_TYPE_TAK		  =	206;
	public static final int KEY_TYPE_ZAK		  =	208;
	public static final int KEY_TYPE_TEK		  =	209;
	public static final int KEY_TYPE_ZEK		  =	230;
	public static final int KEY_TYPE_ZTK		  =	231;
	public static final int KEY_TYPE_WWK		  =	232;
	public static final int KEY_TYPE_BDK		  =	233;
	public static final int KEY_TYPE_ZDK		  =	233;
	public static final int KEY_TYPE_ZNK		  =	235;
	public static final int KEY_TYPE_WK_AC		  =	KEY_TYPE_MDK_AC;
	public static final int KEY_TYPE_WK_ENC		  =	KEY_TYPE_MDK_ENC;
	public static final int KEY_TYPE_WK_MAC		  =	KEY_TYPE_MDK_MAC;
	public static final int KEY_TYPE_WK_KMU		  =	KEY_TYPE_MDK_KMU;

	public static final int KEY_TYPE_SM2		=	0x00010000;
	public static final int KEY_TYPE_RSA		=	0x00020000;


	//酒类防伪密管系统密钥类型定义
	//一级分散密钥
	public static final int KEY_TYPE_SAM_MAIN_MK     =  0x01010101; //SAM主控主密钥
	public static final int KEY_TYPE_SAM_MAIN_ADFK   =  0x02010101; //SAM应用主控主密钥
	public static final int KEY_TYPE_READER_MAIN_MK  =  0x03010102; //读写器鉴别主密钥
	public static final int KEY_TYPE_TAGS_MAIN       =  0x03020102; //标签鉴别通用主密钥
	public static final int KEY_TYPE_TK              =  0x01010103; //传输密钥
	//二级分散密钥
	public static final int KEY_TYPE_SAM_MK         =   0x01010201; //SAM主控密钥
	public static final int KEY_TYPE_SAM_ADFK       =   0x02010201; //SAM应用主控密钥
	public static final int KEY_TYPE_READER_MK      =   0x03010202; //读写器鉴别密钥
	public static final int KEY_TYPE_TAGS_SUB       =   0x03020202; //标签鉴别专用主密钥
	public static final int KEY_TYPE_SAM_INIT_KEY   =   0x03010101; //SAM厂商密钥（初始密钥）
	//三级分散密钥
	public static final int KEY_TYPE_TAGS_KEY       =   0x03020302; //标签鉴别密钥


	/*标准错误码定义*/
	public static final int SDR_OK					= 0x0;						 /*成功*/
	public static final int SDR_BASE				= 0x01000000;
	public static final int SDR_UNKNOWERR			= (SDR_BASE + 0x00000001);	  /*未知错误*/
	public static final int SDR_NOTSUPPORT			= (SDR_BASE + 0x00000002);    /*不支持*/
	public static final int SDR_COMMFAIL			= (SDR_BASE + 0x00000003);    /*通信错误*/
	public static final int SDR_HARDFAIL			= (SDR_BASE + 0x00000004);    /*硬件错误*/
	public static final int SDR_OPENDEVICE			= (SDR_BASE + 0x00000005);    /*打开设备错误*/
	public static final int SDR_OPENSESSION			= (SDR_BASE + 0x00000006);    /*打开会话句柄错误*/
	public static final int SDR_PARDENY				= (SDR_BASE + 0x00000007);    /*权限不满足*/
	public static final int SDR_KEYNOTEXIST			= (SDR_BASE + 0x00000008);    /*密钥不存在*/
	public static final int SDR_ALGNOTSUPPORT		= (SDR_BASE + 0x00000009);    /*不支持的算法*/
	public static final int SDR_ALGMODNOTSUPPORT	= (SDR_BASE + 0x0000000A);    /*不支持的算法模式*/
	public static final int SDR_PKOPERR				= (SDR_BASE + 0x0000000B);    /*公钥运算错误*/
	public static final int SDR_SKOPERR				= (SDR_BASE + 0x0000000C);    /*私钥运算错误*/
	public static final int SDR_SIGNERR				= (SDR_BASE + 0x0000000D);    /*签名错误*/
	public static final int SDR_VERIFYERR			= (SDR_BASE + 0x0000000E);    /*验证错误*/
	public static final int SDR_SYMOPERR			= (SDR_BASE + 0x0000000F);    /*对称运算错误*/
	public static final int SDR_STEPERR				= (SDR_BASE + 0x00000010);    /*步骤错误*/
	public static final int SDR_FILESIZEERR			= (SDR_BASE + 0x00000011);    /*文件大小错误*/
	public static final int SDR_FILENOEXIST			= (SDR_BASE + 0x00000012);    /*文件不存在*/
	public static final int SDR_FILEOFSERR			= (SDR_BASE + 0x00000013);    /*文件操作偏移量错误*/
	public static final int SDR_KEYTYPEERR			= (SDR_BASE + 0x00000014);    /*密钥类型错误*/
	public static final int SDR_KEYERR				= (SDR_BASE + 0x00000015);    /*密钥错误*/

	/*扩展错误码*/
	public static final int SWR_BASE				= (SDR_BASE + 0x00010000);	/*自定义错误码基础值*/
	public static final int SWR_INVALID_USER		= (SWR_BASE + 0x00000001);	/*无效的用户名*/
	public static final int SWR_INVALID_AUTHENCODE	= (SWR_BASE + 0x00000002);	/*无效的授权码*/
	public static final int SWR_PROTOCOL_VER_ERR	= (SWR_BASE + 0x00000003);	/*不支持的协议版本*/
	public static final int SWR_INVALID_COMMAND		= (SWR_BASE + 0x00000004);	/*错误的命令字*/
	public static final int SWR_INVALID_PACKAGE		= (SWR_BASE + 0x00000005);	/*错误的数据包格式*/
	public static final int SWR_INVALID_PARAMETERS  = (SWR_BASE + 0x00000005);	/*参数错误*/
	public static final int SWR_FILE_ALREADY_EXIST	= (SWR_BASE + 0x00000006);	/*已存在同名文件*/

	public static final int SWR_SOCKET_ERR_BASE	 =	(SWR_BASE + 0x00000100);	/*用于检查是否是SOCKET错误*/
	public static final int SWR_SOCKET_TIMEOUT	 =	(SWR_BASE + 0x00000100);	/*超时错误*/
	public static final int SWR_CONNECT_ERR		 =	(SWR_BASE + 0x00000101);	/*连接服务器错误*/
	public static final int SWR_SET_SOCKOPT_ERR	 =	(SWR_BASE + 0x00000102);	/*设置Socket参数错误*/
	public static final int SWR_SOCKET_SEND_ERR	 =	(SWR_BASE + 0x00000104);	/*发送LOGINRequest错误*/
	public static final int SWR_SOCKET_RECV_ERR	 =	(SWR_BASE + 0x00000105);	/*发送LOGINRequest错误*/
	public static final int SWR_SOCKET_RECV_0	 =	(SWR_BASE + 0x00000106);	/*发送LOGINRequest错误*/

	public static final int SWR_NO_AVAILABLE_HSM = (SWR_BASE + 0x00000201);			/*没有可用的加密机*/
	public static final int SWR_NO_AVAILABLE_CSM = (SWR_BASE + 0x00000202);			/*加密机内没有可用的加密模块*/
	public static final int SWR_CONFIG_ERR		 = (SWR_BASE + 0x00000301);			/*配置文件错误*/

	public static final int SWR_CARD_BASE        = (SDR_BASE + 0x00020000); 		/*密码卡错误码*/
	public static final int SDR_BUFFER_TOO_SMALL = (SWR_CARD_BASE + 0x00000101); 	/*接收参数的缓存区太小*/
	public static final int SDR_DATA_PAD		 = (SWR_CARD_BASE + 0x00000102); 	/*数据没有按正确格式填充，或解密得到的脱密数据不符合填充格式*/
	public static final int SDR_DATA_SIZE		 = (SWR_CARD_BASE + 0x00000103); 	/*明文或密文长度不符合相应的算法要求*/
	public static final int SDR_CRYPTO_NOT_INIT	 = (SWR_CARD_BASE + 0x00000104); 	/*步骤错误*/

	public static final int SWR_MANAGEMENT_DENY	  =	 (SWR_CARD_BASE + 0x00001001);	//管理权限不满足
	public static final int SWR_OPERATION_DENY	  =	 (SWR_CARD_BASE + 0x00001002);  //操作权限不满足
	public static final int SWR_DEVICE_STATUS_ERR =  (SWR_CARD_BASE + 0x00001003);  //当前设备状态不满足现有操作

	public static final int SWR_LOGIN_ERR        =   (SWR_CARD_BASE + 0x00001011);  //登录失败
	public static final int SWR_USERID_ERR       =   (SWR_CARD_BASE + 0x00001012);  //用户ID数目/号码错误
	public static final int SWR_PARAMENT_ERR     =   (SWR_CARD_BASE + 0x00001013);  //参数错误
	public static final int SWR_KEYTYPEERR		 =	 (SWR_CARD_BASE + 0x00000020);	//密钥类型错误

	SJJ_HSM instanseHSM = (SJJ_HSM) Native.loadLibrary("."+File.separator+"swsjf", SJJ_HSM.class);

	/*设备管理类函数*/
	public int SJF_OpenDevice(PointerByReference phDeviceHandle);
	public int SJF_OpenDeviceWithPath(byte[] pcCfgPath, PointerByReference phDeviceHandle);
	public int SJF_CloseDevice(Pointer hDeviceHandle);

	public int SJF_OpenSession(Pointer hDeviceHandle,PointerByReference phSessionHandle);
	public int SJF_CloseSession(Pointer hSessionHandle);

	/* 密钥管理函数  */
	public int SJF_GetKeyInfo(Pointer hSessionHandle, int uiKeyType,int uiKeyIndex,IntByReference puiKeyLength);
	public int SJF_GenerateKeyPair(Pointer hSessionHandle,int uiKeyAlg,int uiKeyIndex,int uiKeyBits,int uiExponent,int uiMechanism,byte[] pbPublicKey,IntByReference puiPublicKeyLength,byte[] pbPrivateKey,IntByReference puiPrivateKeyLength);
	public int SJF_GenerateKey(Pointer hSessionHandle,    int uiKeyIndex,int uiKeyType,int uiKeyLength, int uiAlgID,int uiKEKType,int uiKEKIndex,byte[] pbKEKCipher,int uiKEKLength,byte[] pbKeyCipherByKEK,byte[] pbKeyCipherByLMK);
	public int SJF_ExportPublicKey(Pointer hSessionHandle,int uiAlgID,int uiKeyIndex,byte[] pbPublicKey,IntByReference puiPublicKeyLength);

	public int SJF_ImportKey(Pointer hSessionHandle,int uiKeyType,int uiIndex,byte[] pbKeyCipher,int uiKeyLength);
	public int SJF_ExportKey(Pointer hSessionHandle,int uiKeyType,int uiIndex,byte[] pbKeyCipher, IntByReference puiKeyLength);

	public int SJF_TransKey_KEK2LMK(Pointer hSessionHandle,int uiAlgID,int uiKEKType,int uiKEKIndex,byte[] pbKEKCipher,int uiKEKLength,int uiKeyType,byte[] pbKeyCipherByKEK,int uiKeyLength,byte[] pbKeyCipherByLMK);
	public int SJF_TransKey_LMK2KEK(Pointer hSessionHandle,int uiAlgID,int uiKEKType,int uiKEKIndex,byte[] pbKEKCipher,int uiKEKLength,int uiKeyType,byte[] pbKeyCipherByLMK,int uiKeyLength,byte[] pbKeyCipherByKEK);

	public int SJF_DeriveKey(	 Pointer hSessionHandle,int uiAlgID,int uiMDKIndex,int uiMDKType,byte[] pbMDKCipher,int uiMDKLength,int uiTKIndex,    int uiTKType,    byte[] pbTKCipher,    int uiTKLength,    byte[] pbReferenceData,int uiRefDataLength,int uiDeriveTimes,int uiDivKeyLength,byte[] pbDivKeyByTK,byte[] pbDivKeyByLMK);
	public int SJF_DeriveKeyEx(Pointer hSessionHandle,int uiAlgID,int uiMDKIndex,int uiMDKType,byte[] pbMDKCipher,int uiMDKLength,int uiEncKeyIndex,int uiEncKeyType,byte[] pbEncKeyCipher,int uiEncKeyLength,byte[] pbMDKRef,       int uiMDKRefLength, int uiMDKDeriveTimes,byte[] pbEncKeyRef,int uiEncKeyRefLength,int uiEncKeyDeriveTimes,byte[] pbKeyHeader,int uiKeyHeaderLen,byte[] pbDivKeyByEncKey,byte[] puiDevKeyByEncKeyLen);

	public int SJF_GenerateRandom(Pointer hSessionHandle, int  uiLength, byte[] pucRandom);

	//v1.2.5扩展密钥管理函数
	public int SJF_ImportKeyByComponent(Pointer hSessionHandle,int uiKeyType,int uiIndex,int uiCount,int uiComponentLen,byte[] pbComponent1,byte[] pbComponent2,byte[] pbComponent3,byte[] pbComponent4,byte[] pbComponent5,byte[] pbComponent6,byte[] pbComponent7,byte[] pbComponent8,byte[] pbComponent9,byte[] pbKeyCipher,IntByReference puiKeyLength);
	public int SJF_DeriveAndEncKey(Pointer hSessionHandle,int uiAlgID,int uiPadMode,int uiMDKIndex,int uiMDKType,byte[] pbMDKCipher,int uiMDKLength,int uiEncKeyIndex,int uiEncKeyType,byte[] pbEncKeyCipher, int uiEncKeyLength,
								   byte[] pbMDKRef,int uiMDKRefLength,int uiMDKDeriveTimes,byte[] pbEncKeyRef,int uiEncKeyRefLength,int uiEncKeyDeriveTimes,byte[] pbKeyHeader,int uiKeyHeaderLen,byte[] pbMACHeader,int uiMACHeaderLen,byte[] pbMACIV,int uiMACIVLen,
								   byte[] pbDivKeyByEncKey,IntByReference puiDevKeyByEncKeyLen,byte[] pbMAC,IntByReference puiMACLen);
	public int SJF_ImportKeyByKEK(Pointer hSessionHandle, int uiKeyType,int uiIndex,byte[] pbKeyCipher,int uiKeyLength,int uiEncKeyType,int uiEncKeyIndex,byte[] pbEncKeyCipher,int uiEncKeyLength);

	//v1.2.6扩展函数
	public int SJF_GenerateKCV(Pointer hSessionHandle, int uiKeyType,int uiKeyIndex,int uiAlgID,byte[] pucKCV,IntByReference puiKCVLength);

	//v1.2.6.1扩展
	public int SJF_DeleteKey(Pointer hSessionHandle,int uiKeyType,int uiIndex);

	//v1.2.6.2
	public int SJF_DeriveAndEncKeyEx(Pointer hSessionHandle,	int uiAlgID, int uiPadMode,
									 int uiMDKIndex, int uiMDKType, byte[] pbMDKCipher, int uiMDKLength,
									 int uiEncKeyIndex,  int uiEncKeyType, byte[] pbEncKeyCipher, int uiEncKeyLength,
									 byte[] pbMDKRef, int uiMDKRefLength, int uiMDKDeriveTimes,
									 byte[] pbEncKeyRef, int uiEncKeyRefLength, int uiEncKeyDeriveTimes,
									 byte[] pbKeyHeader, int uiKeyHeaderLen, byte[] pbKeyTails, int uiKeyTailsLen,
									 byte[] pbMACHeader, int uiMACHeaderLen,
									 byte[] pbMACIV, int uiMACIVLen,
									 byte[] pbDivKeyByEncKey, IntByReference puiDevKeyByEncKeyLen,
									 byte[] pbMAC, IntByReference puiMACLen);
	//v1.2.7扩展
	public int SJF_GenerateExtKeyPair(Pointer hSessionHandle,int uiAlgID,int uiKeyBits,int uiExponent, int uiTkAlg,int uiPadMode,int uiTKType,int uiTkIndex,byte[] pbTkCipher, int uiTkLength, byte[] pbPublicKey,IntByReference puiPublicKeyLength,byte[] pbPrivateKey,IntByReference puiPrivateKeyLength);
	public int SJF_DeriveKeyWithKCV(Pointer hSessionHandle,int uiDivAlgID,int uiEncAlgID,int uiKCVAlgID,int uiPadMode,int uiMDKIndex,int uiMDKType,byte[] pbMDKCipher,int uiMDKLength,int uiTKIndex,int uiTKType,byte[] pbTKCipher,int uiTKLength,byte[] pbReferenceData,int uiRefDataLength,int uiDeriveTimes,int uiDivKeyLength,byte[] pbDivKeyByTK,IntByReference puiDivKeyByTKLength,byte[] pbDivKeyByLMK,byte[] pbKCV);


	/* 非对称密钥运算函数 */
	public int SJF_RSAPrivateKeyOpt(Pointer hSessionHandle,int uiKeyIndex,int uiMechanism,byte[] pbPrivateKey,int uiPrivateKeyLength,byte[] pbInData,int uiInLength,byte[] pbOutData,IntByReference puiOutLength);
	public int SJF_RSAPublicKeyOpt(Pointer hSessionHandle,int uiKeyIndex,byte[] pbPublicKey,int uiPublicKeyLength,byte[] pbInData,int uiInLength,byte[] pbOutData,IntByReference puiOutLength);

	public int SJF_SM2PrivateKeySign(Pointer hSessionHandle, int uiKeyIndex,int uiMechanism,byte[] pbPrivateKey,int uiPrivateKeyLength,byte[] pbInData,int uiInLength,byte[] pbSignature,IntByReference puiSignatureLength);
	public int SJF_SM2PublicKeyVerify(Pointer hSessionHandle,int uiKeyIndex,byte[] pbPublicKey,int uiPublicKeyLength,byte[] pbInData,int uiInLength,byte[] pbSignature,int uiSignatureLength);

	public int SJF_SM2PublicKeyEncrypt(Pointer hSessionHandle, int uiKeyIndex,byte[] pbPublicKey,int uiPublicKeyLength,byte[] pbInData,int uiInLength,byte[] pbCipher,IntByReference puiCipherLength);
	public int SJF_SM2PrivateKeyDecrypt(Pointer hSessionHandle,int uiKeyIndex,int uiMechanism,byte[] pbPrivateKey,int uiPrivateKeyLength,byte[] pbCipher,int uiCipherLength,byte[] pbOutData,IntByReference puiOutLength);

	/* 杂凑运算函数 */
	public int SJF_HashInit(Pointer hSessionHandle,int uiAlgID,byte[] pbSM2PublicKey,int uiPublicKeyLength,byte[] pucID,int uiIDLength);
	public int SJF_HashUpdate(Pointer hSessionHandle,byte[] pbInData,int uiInLength);
	public int SJF_HashFinal(Pointer hSessionHandle,byte[] pbHash,IntByReference puiHashLength);

	/* 对称密钥运算函数  */
	public int SJF_Encrypt(Pointer hSessionHandle,int uiAlgID,int uiMechanism,int uiKeyIndex,int uiKeyType,byte[] pbKey,int uiKeyLength,byte[] pbReferenceData,int uiRefDataLength,int uiDeriveTimes,byte[] pbIV,int uiIVLength,byte[] pbInData,int uiInLength,byte[] pbOutData,IntByReference puiOutLength);
	public int SJF_Decrypt(Pointer hSessionHandle,int uiAlgID,int uiMechanism,int uiKeyIndex,int uiKeyType,byte[] pbKey,int uiKeyLength,byte[] pbReferenceData,int uiRefDataLength,int uiDeriveTimes,byte[] pbIV,int uiIVLength,byte[] pbInData,int uiInLength,byte[] pbOutData,IntByReference puiOutLength);

	public int SJF_GenerateMAC(Pointer hSessionHandle, int uiAlgID,    int uiMechanism,int uiKeyIndex,int uiKeyType,byte[] pbKey,int uiKeyLength,byte[] pbReferenceData,int uiRefDataLength,int uiDeriveTimes,byte[] pbIV,    int uiIVLength,byte[] pbInData,int uiInLength,byte[] pbMAC,IntByReference puiMACLength);
	public int SJF_GenerateHMAC(Pointer hSessionHandle,int uiHashAlgID,int uiDivAlgID, int uiKeyIndex,int uiKeyType,byte[] pbKey,int uiKeyLength,byte[] pbReferenceData,int uiRefDataLength,int uiDeriveTimes,byte[] pbInData,int uiInLength,byte[] pbHMAC,IntByReference puiHMACLength);

	/* 银联交易函数*/
	public int SJF_TransPIN(Pointer hSessionHandle,int uiSrcKeyType,int uiSrcKeyIndex,byte[] pbSrckeyCipher,int uiSrcKeyLength,int uiSrcPINBlockFormat,byte[] pbSrcPINBlock,int uiSrcPINBlockLength,int uiDestKeyType,int uiDestKeyIndex,byte[] pbDestkeyCipher,int uiDestKeyLength,int uiDestPINBlockFormat,byte[] pbPAN,int uiPANLength,byte[] pbDestPINBlock,IntByReference puiDestPINBlockLength);
	public int SJF_VerifyARQCandGenARPC(Pointer hSessionHandle,int uiFlag,int uiAlgID,int uiKeyIndex,int uiKeyType,byte[] pbKeyCipher,int uiKeyLength,byte[] pbReference,int uiRefDataLength,byte[] pbTransData,int uiTransDataLength,byte pbATC,byte pbARQC,byte pbARC,byte pbARPC);

	//新增函数
	public int SJF_Decrypt_Ex(Pointer hSessionHandle, int uiAlgID, int uiMechanism, int uiKeyIndex,int uiKeyType, byte[] pbKey, int uiKeyLength,byte[] pbReferenceData, int uiRefDataLength,int uiDeriveTimes, byte[] pbIV, int uiIVLength,byte[] pbInData, int uiInLength,byte[] pbOutData,IntByReference puiOutLength);
	public int SJF_Encrypt_Ex(Pointer hSessionHandle, int uiAlgID, int uiMechanism, int uiKeyIndex,int uiKeyType, byte[] pbKey, int uiKeyLength,byte[] pbReferenceData, int uiRefDataLength,int uiDeriveTimes, byte[] pbIV, int uiIVLength,byte[] pbInData, int uiInLength,byte[] pbOutData,IntByReference puiOutLength);

}
