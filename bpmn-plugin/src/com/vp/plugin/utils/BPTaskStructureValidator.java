package com.vp.plugin.utils;

import java.util.Set;

public class BPTaskStructureValidator implements IBPTaskValidator {

	public static final int DOMAIN_SPECIFIC_WORD_POSITION = 0;

	@Override
	public boolean validate(String[] taskWords, Set<String> classes) {

		if (taskWords == null || taskWords.length != 2) {
			return false;
		}
		for (String className : classes) {
			if (taskWords[DOMAIN_SPECIFIC_WORD_POSITION].equals(className)) {
				return true;
			}
		}
		return false;
	}

}
