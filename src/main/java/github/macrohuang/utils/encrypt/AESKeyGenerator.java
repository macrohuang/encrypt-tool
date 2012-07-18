package github.macrohuang.utils.encrypt;

import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

import javax.crypto.KeyGenerator;

public class AESKeyGenerator extends AbstractKeyGenerator {
	private byte[] keys;
	private String algorithm;
	private int length;

	protected AESKeyGenerator(String key, String algorithm) {
		this(key, algorithm, 128);
	}

	protected AESKeyGenerator(String key, String algorithm, int length) {
		this.keys = key.getBytes();
		this.algorithm = algorithm;
		this.length = length;
	}
	@Override
	public Key getKey() {
		try {
			KeyGenerator keyGenerator = KeyGenerator.getInstance(algorithm);
			keyGenerator.init(length, new SecureRandom(keys));
			return keyGenerator.generateKey();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public String getAlgorithm() {
		return algorithm;
	}

	public static AbstractKeyGenerator getInstance(String key) {
		return new AESKeyGenerator(key, "AES");
	}

	public static AbstractKeyGenerator getInstance(String key, int keyLength) {
		return new AESKeyGenerator(key, "AES", keyLength);
	}
}
