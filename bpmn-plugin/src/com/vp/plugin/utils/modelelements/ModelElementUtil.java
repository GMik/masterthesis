package com.vp.plugin.utils.modelelements;

import org.apache.commons.lang.StringUtils;

import com.vp.plugin.model.IModelElement;

public class ModelElementUtil {

	public static String[] fetchWords(IModelElement modelElement) {
		return modelElement == null ? null : StringUtils.normalizeSpace(modelElement.getName()).split(" ");
	}

}
