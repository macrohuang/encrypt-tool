/**
 * 
 */
package com.sogou.bizdev.utils.encrypt;

/**
 * @author shizw
 */
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public final class MD5 {
	public final static String encode(String s) {
		char hexDigits[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
				'a', 'b', 'c', 'd', 'e', 'f' };
		try {
			byte[] strTemp = s.getBytes();
			MessageDigest mdTemp = MessageDigest.getInstance("MD5");
			mdTemp.update(strTemp);
			byte[] md = mdTemp.digest();
			int j = md.length;
			char str[] = new char[j * 2];
			int k = 0;
			for (int i = 0; i < j; i++) {
				byte byte0 = md[i];
				str[k++] = hexDigits[byte0 >>> 4 & 0xf];
				str[k++] = hexDigits[byte0 & 0xf];
			}
			return new String(str);
		} catch (NoSuchAlgorithmException e) {
			return null;
		}
	}
	
	public static void main(String[] args){
		System.out.println(encode("22222222"));

		String salt = "39a7fb7d-67d0-4c28-974a-e8ca913a9b42";
		String s = "1234567890-_qwertyuiopasdfghjklzxcvbnmQWERTYUIOPASDFGHJKLZXCVBNM";
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < s.length(); i++) {
			sb.append((char) (s.charAt(i) ^ salt.charAt(i % salt.length())));
		}
		System.out.println("Encrib: " + sb.toString());

		StringBuilder sb2 = new StringBuilder();
		for (int i = 0; i < sb.length(); i++) {
			sb2.append((char) (sb.charAt(i) ^ salt.charAt(i % salt.length())));
		}
		System.out.println("Origin:" + s);
		System.out.println("Describ: " + sb2.toString());
	}
}
