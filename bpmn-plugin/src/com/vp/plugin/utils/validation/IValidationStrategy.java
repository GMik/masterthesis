package com.vp.plugin.utils.validation;

import com.vp.plugin.exceptions.ValidationException;
import com.vp.plugin.model.IModelElement;

public interface IValidationStrategy {
	ValidationResult validate(IModelElement modelElement) throws ValidationException;
}
