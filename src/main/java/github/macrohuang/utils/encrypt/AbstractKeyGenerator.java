package github.macrohuang.utils.encrypt;

import java.security.Key;

public abstract class AbstractKeyGenerator {
	public abstract Key getKey();

	public abstract String getAlgorithm();

}
