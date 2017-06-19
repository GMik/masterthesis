package com.vp.plugin.utils.validation.bp.events;

import com.vp.plugin.diagram.IDiagramElement;
import com.vp.plugin.model.IBPEndEvent;
import com.vp.plugin.model.IBPIntermediateEvent;
import com.vp.plugin.model.IBPStartEvent;
import com.vp.plugin.model.IModelElement;
import com.vp.plugin.utils.validation.ValidationResult;

public class BPEventValidator {

	private IBPEventValidationStrategy validationStrategy;

	public BPEventValidator(IBPEventValidationStrategy validationStrategy) {
		this.validationStrategy = validationStrategy;
	}

	public ValidationResult validate(IBPStartEvent startEvent) {
		return validateX(startEvent);
	}

	public ValidationResult validate(IBPIntermediateEvent intermediateEvent) {
		return validateX(intermediateEvent);
	}

	public ValidationResult validate(IBPEndEvent endEvent) {
		return validateX(endEvent);
	}

	public IBPEventValidationStrategy getValidationStrategy() {
		return validationStrategy;
	}

	public void setValidationStrategy(IBPEventValidationStrategy validationStrategy) {
		this.validationStrategy = validationStrategy;
	}

	private ValidationResult validateX(IModelElement modelElement) {
		return validationStrategy.validate(modelElement);
	}

	private static IModelElement retrieveModelElementFrom(IDiagramElement diagramElement) {
		return diagramElement.getMetaModelElement();
	}

}
