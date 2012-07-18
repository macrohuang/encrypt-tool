package github.macrohuang.utils.test;

import github.macrohuang.utils.encrypt.AESKeyGenerator;
import github.macrohuang.utils.encrypt.DESKeyGenerator;
import github.macrohuang.utils.encrypt.EncryptUtils;
import github.macrohuang.utils.encrypt.MD5;
import github.macrohuang.utils.encrypt.SHA;

import java.util.Date;
import java.util.logging.Logger;

import org.junit.Assert;
import org.junit.Test;


public class EncryptUtilsTest {
	String msg = "This is a secret!";
	String key = "This is the key to the secret!";
	Logger logger = Logger.getLogger(EncryptUtilsTest.class.getName());

	@Test
	public void testEncryptAndDescrypt() {
		String encryptMsg = EncryptUtils.encrypt(msg, DESKeyGenerator.getInstance(key));
		logger.info("DESEncrypt msg:" + encryptMsg);
		String decryptMsg = EncryptUtils.decrypt(encryptMsg, DESKeyGenerator.getInstance(key));
		logger.info("DESDecrypt msg:" + decryptMsg);
		Assert.assertEquals(msg, decryptMsg);

		encryptMsg = EncryptUtils.encrypt(msg, AESKeyGenerator.getInstance(key));
		logger.info("AESEncrypt msg:" + encryptMsg);
		decryptMsg = EncryptUtils.decrypt(encryptMsg, AESKeyGenerator.getInstance(key));
		logger.info("AESDecrypt msg:" + decryptMsg);
		Assert.assertEquals(msg, decryptMsg);
	}

	@Test
	public void testDESEncryptAndDescrypt() {
		String encryptMsg = EncryptUtils.desEncrypt(msg, key);
		logger.info("Encrypt msg:" + encryptMsg);
		String decryptMsg = EncryptUtils.desDecrypt(encryptMsg, key);
		logger.info("Decrypt msg:" + decryptMsg);
		Assert.assertEquals(msg, decryptMsg);
	}

	@Test
	public void testOnlineBadcase() {
		logger.info(EncryptUtils.desEncrypt(String.valueOf(1898512), SHA.encode(MD5.encode("cd4ba4490f71a697c160308153553d44") + "28211")));
		logger.info(new Date(1341207987000L).toString());
	}
}
