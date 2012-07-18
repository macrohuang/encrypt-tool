package github.macrohuang.utils.encrypt;

import java.security.Key;
import java.security.NoSuchAlgorithmException;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;


public final class EncryptUtils {

	public static String byteArray2HexStr(byte[] b) {
		// 每个byte用两个ascii字符才能表示，所以字符串的长度是数组长度的两倍
		StringBuffer sb = new StringBuffer(b.length * 2);
		for (int i = 0; i < b.length; i++) {
			int one = b[i];
			// 把负数转换为正数
			while (one < 0) {
				one += 256;
			}
			// 小于0F的数需要在前面补0
			if (one < 16) {
				sb.append("0");
			}
			sb.append(Integer.toString(one, 16));
		}
		return sb.toString();
	}

	public static byte[] str2ByteArray(String s) {
		// 两个ascii字符表示一个字节，所以字节数组长度是字符串长度除以2
		byte[] finalBytes = new byte[s.getBytes().length / 2];
		for (int i = 0; i < finalBytes.length; i++) {
			finalBytes[i] = (byte) Integer.parseInt(s.substring(i * 2, i * 2 + 2), 16);
		}
		return finalBytes;
	}

	public static String encrypt(String msg, AbstractKeyGenerator keyGenerator) {
		try {
			Cipher cipher = Cipher.getInstance(keyGenerator.getAlgorithm());
			cipher.init(Cipher.ENCRYPT_MODE, keyGenerator.getKey());
			return byteArray2HexStr(cipher.doFinal(msg.getBytes()));
		} catch (Exception e) {
			throw new RuntimeException("Encrypt exception." + e.getMessage());
		}
	}

	public static String decrypt(String encryptMsg, AbstractKeyGenerator keyGenerator) {
		try {
			Cipher cipher = Cipher.getInstance(keyGenerator.getAlgorithm());
			cipher.init(Cipher.DECRYPT_MODE, keyGenerator.getKey());
			return new String(cipher.doFinal(str2ByteArray(encryptMsg)));
		} catch (Exception e) {
			throw new RuntimeException("Decrypt exception." + e.getMessage());
		}
	}

	private static Key getKey(byte[] keyBytes, String algorithm, int keyByteLength) throws NoSuchAlgorithmException {
		byte[] arrB = new byte[keyByteLength];
		for (int i = 0; i < keyBytes.length && i < arrB.length; i++) {
			arrB[i] = keyBytes[i];
		}

		return new SecretKeySpec(arrB, algorithm);
	}

	public static String desEncrypt(String msg, String key) {
		try {
			Cipher cipher = Cipher.getInstance("DES");
			cipher.init(Cipher.ENCRYPT_MODE, getKey(key.getBytes(), "DES", 8));
			return byteArray2HexStr(cipher.doFinal(msg.getBytes()));
		} catch (Exception e) {
			throw new RuntimeException("DES Encrypt exception." + e.getMessage());
		}
	}

	public static String desDecrypt(String encryptMsg, String key) {
		try {
			Cipher cipher = Cipher.getInstance("DES");
			cipher.init(Cipher.DECRYPT_MODE, getKey(key.getBytes(), "DES", 8));
			return new String(cipher.doFinal(str2ByteArray(encryptMsg)));
		} catch (Exception e) {
			throw new RuntimeException("DES Encrypt exception." + e.getMessage());
		}
	}

	public static void main(String[] args) {
		String msg = "35124";
		// String key =
		// "This is secret key.weuijvhaksdh34ASDFERbdfgASD$%^#$%@#423VDFSDFH%^$%#DFBertWE$%#$GNRT#$5@#$%GFUERTAFgsdfger78808klj,bvzmxcZBasdhfgaqwe5t3487dflvabserk23958fgssefwer==-e58737ufjneHHDGFywhjGJGJKHCKED8(hHGdhjdfJ#)(*^%#!@&(MJNHVffderyUVXCZXER";
		String key = "j^#$vhDaksdh34ASDFERbdfgAS$%%@#423VDFSDFH%^$%#DFBertWE$%#$GNRT#$5@#$%GFUERTAFgsdfger78808klj,bvzmxcZBasdhfgaqwe5t3487dflvabserk23958fgssefwer==-e58737ufjneHHDGFywhjGJGJKHCKED8(hHGdhjdfJ#)(*^%#!@&(MJNHVffderyUVXCZXER";
		String encryptMsg = desEncrypt(msg, key);
		System.out.println(encryptMsg);
		System.out.println(desDecrypt(encryptMsg, key));
		System.out.println(desDecrypt("c49c40b0ab341b60", key));
	}
}
