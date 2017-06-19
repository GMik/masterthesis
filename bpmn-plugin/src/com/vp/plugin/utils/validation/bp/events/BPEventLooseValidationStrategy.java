package com.vp.plugin.utils.validation.bp.events;

import com.vp.plugin.model.IModelElement;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

public class BPEventLooseValidationStrategy implements IBPEventValidationStrategy {

	@Override
	public BPEventValidationResult validate(IModelElement modelElement) {
		throw new NotImplementedException();
	}

}
