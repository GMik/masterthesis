package com.vp.plugin.utils.validation.bp.dataobjects;

import com.vp.plugin.model.IModelElement;
import com.vp.plugin.utils.validation.ValidationResult;

public class BPDataObjectValidator {
	private IBPObjectValidationStrategy validationStrategy;

	public BPDataObjectValidator(IBPObjectValidationStrategy validationStrategy) {
		this.validationStrategy = validationStrategy;
	}

	public IBPObjectValidationStrategy getValidationStrategy() {
		return validationStrategy;
	}

	public void setValidationStrategy(IBPObjectValidationStrategy validationStrategy) {
		this.validationStrategy = validationStrategy;
	}

	public ValidationResult validate(IModelElement modelElement) {
		return validationStrategy.validate(modelElement);
	}

}
