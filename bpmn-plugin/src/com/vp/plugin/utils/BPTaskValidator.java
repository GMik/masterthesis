package com.vp.plugin.utils;

import java.util.Set;

public class BPTaskValidator implements IBPTaskValidator {

	@Override
	public boolean validate(String[] taskWords, Set<String> classes) {

		for (String taskWord : taskWords) {
			for (String className : classes) {
				if (taskWord.equals(className)) {
					return true;
				}
			}
		}
		return false;
	}

}
