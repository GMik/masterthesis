package com.vp.plugin.utils;

import java.util.Set;

public interface IBPTaskValidator {

	boolean validate(String[] taskWords, Set<String> classes);
}
