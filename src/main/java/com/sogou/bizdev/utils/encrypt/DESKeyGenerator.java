package com.sogou.bizdev.utils.encrypt;

import java.security.Key;

import javax.crypto.spec.SecretKeySpec;

public class DESKeyGenerator extends AbstractKeyGenerator {
	private byte[] keys;
	private String algorithm;

	protected DESKeyGenerator(String key, String algorithm) {
		this.keys = key.getBytes();
		this.algorithm = algorithm;
	}
	@Override
	public Key getKey() {
		byte[] arrB = new byte[8];
		for (int i = 0; i < keys.length && i < arrB.length; i++) {
			arrB[i] = keys[i];
		}

		return new SecretKeySpec(arrB, algorithm);
	}

	@Override
	public String getAlgorithm() {
		return algorithm;
	}
	public static AbstractKeyGenerator getInstance(String key) {
		return new DESKeyGenerator(key, "DES");
	}
}
