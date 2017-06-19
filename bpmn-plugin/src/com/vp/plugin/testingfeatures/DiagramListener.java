package com.vp.plugin.testingfeatures;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.vp.plugin.ApplicationManager;
import com.vp.plugin.ViewManager;
import com.vp.plugin.connectors.businessrules.SBVRFileConnector;
import com.vp.plugin.connectors.businessrules.SBVRFileElementsContainer;
import com.vp.plugin.connectors.domainmodel.VPDomainModelConnector;
import com.vp.plugin.connectors.domainmodel.br.DomainModelSBVRRelevantElementsContainer;
import com.vp.plugin.diagram.IBusinessProcessDiagramUIModel;
import com.vp.plugin.diagram.IClassDiagramUIModel;
import com.vp.plugin.diagram.IDiagramElement;
import com.vp.plugin.diagram.IDiagramListener;
import com.vp.plugin.diagram.IDiagramUIModel;
import com.vp.plugin.diagram.IStateDiagramUIModel;
import com.vp.plugin.model.IAssociation;
import com.vp.plugin.model.IAssociationEnd;
import com.vp.plugin.model.IBPDataObject;
import com.vp.plugin.model.IBPEndEvent;
import com.vp.plugin.model.IBPIntermediateEvent;
import com.vp.plugin.model.IBPStartEvent;
import com.vp.plugin.model.IBusinessRule;
import com.vp.plugin.model.IBusinessRuleAssociation;
import com.vp.plugin.model.IBusinessRuleGroup;
import com.vp.plugin.model.IClass;
import com.vp.plugin.model.IGeneralization;
import com.vp.plugin.model.IModel;
import com.vp.plugin.model.IModelElement;
import com.vp.plugin.model.factory.IModelElementFactory;
import com.vp.plugin.utils.validation.ValidationResult;
import com.vp.plugin.utils.validation.bp.dataobjects.BPDataObjectValidator;
import com.vp.plugin.utils.validation.bp.dataobjects.BPObjectStandardValidationStrategy;
import com.vp.plugin.utils.validation.bp.events.BPEventRestrictiveValidationStrategy;
import com.vp.plugin.utils.validation.bp.events.BPEventValidator;
import com.vp.plugin.utils.validation.sbvr.to.dm.SBVRToBusinessRulesValidator;
import com.vp.plugin.utils.validation.sbvr.to.dm.SBVRToDomainModelValidator;

public class DiagramListener implements IDiagramListener {

	private static BPEventValidator eventValidator;

	private static BPDataObjectValidator bpObjectValidator;

	ViewManager _viewManager = ApplicationManager.instance().getViewManager();

	public DiagramListener() {
	}

	static {
		eventValidator = new BPEventValidator(new BPEventRestrictiveValidationStrategy());
		bpObjectValidator = new BPDataObjectValidator(new BPObjectStandardValidationStrategy());
	}

	@Override
	public void diagramUIModelRenamed(IDiagramUIModel arg0) {

		System.out.println();
		// do nothing
	}

	@Override
	public void diagramElementAdded(IDiagramUIModel arg0, IDiagramElement arg1) {

		System.out.println();
		// do nothing
	}

	@Override
	public void diagramElementRemoved(IDiagramUIModel arg0, IDiagramElement arg1) {

		System.out.println();
		// do nothing

	}

	@Override
	public void diagramUIModelLoaded(IDiagramUIModel arg0) {

		System.out.println();
		// do nothing

	}

	private void clearBusinessRules() {

		IModelElement[] models = ApplicationManager.instance().getProjectManager().getProject()
				.toAllLevelModelElementArray(IModelElementFactory.MODEL_TYPE_MODEL);

		for (IModelElement elem : models) {
			IModel m = (IModel) elem;

			String kindOfModel = m.getName();

			if (kindOfModel.equals("Business Rules")) {
				IModelElement[] businessRules = m.toChildArray();

				for (IModelElement br : businessRules) {
					m.removeChild(br);
				}
			}
		}

	}

	@Override
	public void diagramUIModelPropertyChanged(IDiagramUIModel diagramUIModel, String arg1, Object arg2, Object arg3) {

		IModelElement selectedElement = retrieveSelectedElement() == null ? null
				: retrieveSelectedElement().getMetaModelElement();
		if (selectedElement == null) {
			return;
		}

		if (isClassDiagramUIModel(diagramUIModel)) {
			SBVRToDomainModelValidator validator = initializeBVRToDomainModelValidator();
			validateClassDiagramElements(selectedElement, validator);
		}

		if (isBusinessProcessDiagramUIModel(diagramUIModel)) {
			validateBPMNElements(selectedElement);
		}

		if (isStateDiagramUIModel(diagramUIModel)) {
			SBVRToDomainModelValidator validator = initializeBVRToDomainModelValidator();
			validateStateMachineElements(selectedElement, validator);
		}

		if (isBusinessRule(selectedElement)) {

			// if created
			IBusinessRule businessRule = (IBusinessRule) selectedElement;
			businessRule.setUserID("");

			SBVRToBusinessRulesValidator validator = initializeSBVRToBusinessRulesValidator();
			validateBusinessRule(businessRule, validator);
		}

	}

	private void validateBusinessRule(IBusinessRule businessRule, SBVRToBusinessRulesValidator validator) {

		String ownId = businessRule.getName();
		ValidationResult validationResult = validator.validateBusinessRule(ownId);

		if (validationResult != null) {
			_viewManager.showMessage(validationResult.getMessage());
		}

	}

	private void validateClassDiagramElements(IModelElement selectedElement, SBVRToDomainModelValidator validator) {

		List<ValidationResult> validationResults = new ArrayList<>();

		validationResults.addAll(validator.validateClassAttributes());
		if (isClass(selectedElement)) {
			validationResults.addAll(validator.validateAssociations());
			validationResults.addAll(validator.validateCompositions());
			validationResults.addAll(validator.validateGeneralizations());
		}

		if (isAssociation(selectedElement) || isAssociationEnd(selectedElement)
				|| isAssociation(selectedElement.getParent())) {
			validationResults.addAll(validator.validateAssociations());
			validationResults.addAll(validator.validateCompositions());
		}

		if (isGeneralization(selectedElement)) {
			validationResults.addAll(validator.validateGeneralizations());
		}

		List<ValidationResult> validationResultForSelectedElement = validationResults.stream()
				.filter(result -> result.getMessage().contains(selectedElement.getName())).collect(Collectors.toList());

		for (ValidationResult result : validationResultForSelectedElement) {
			_viewManager.showMessage(result.getMessage());
		}
	}

	private boolean isBusinessRule(IModelElement selectedElement) {
		return selectedElement instanceof IBusinessRule;
	}

	private boolean isBusinessRuleGroup(IModelElement selectedElement) {
		return selectedElement instanceof IBusinessRuleGroup;
	}

	private boolean isBusinessRuleAssociation(IModelElement selectedElement) {
		return selectedElement instanceof IBusinessRuleAssociation;
	}

	private void validateBPMNElements(IModelElement selectedElement) {
		ValidationResult validationResult = null;

		validationResult = instanceOfBPEvent(selectedElement) ? validateEvent(selectedElement) : validationResult;
		validationResult = instanceOfBPObject(selectedElement) ? validateBPObject(selectedElement) : validationResult;

		if (validationResult != null) {
			_viewManager.showMessage(validationResult.getMessage());
		}
	}

	private void validateStateMachineElements(IModelElement selectedElement, SBVRToDomainModelValidator validator) {

		List<ValidationResult> validationResults = validator.validateClassStates();

		List<ValidationResult> validationResultForSelectedElement = validationResults.stream()
				.filter(result -> result.getMessage().contains(selectedElement.getName())).collect(Collectors.toList());

		for (ValidationResult result : validationResultForSelectedElement) {
			_viewManager.showMessage(result.getMessage());
		}
	}

	private ValidationResult validateEvent(IModelElement selectedElement) {

		ValidationResult validationResult = null;

		if (selectedElement instanceof IBPStartEvent) {
			validationResult = eventValidator.validate((IBPStartEvent) selectedElement);
		} else if (selectedElement instanceof IBPIntermediateEvent) {
			validationResult = eventValidator.validate((IBPIntermediateEvent) selectedElement);

		} else if (selectedElement instanceof IBPEndEvent) {
			validationResult = eventValidator.validate((IBPEndEvent) selectedElement);
		}
		return validationResult;

	}

	private SBVRToBusinessRulesValidator initializeSBVRToBusinessRulesValidator() {
		SBVRFileConnector sbvrConnector = new SBVRFileConnector();
		SBVRFileElementsContainer sbvrContainer = null;
		try {
			sbvrContainer = sbvrConnector.loadSBVRData();
		} catch (IOException e) {

		}
		return new SBVRToBusinessRulesValidator(sbvrContainer);
	}

	private SBVRToDomainModelValidator initializeBVRToDomainModelValidator() {
		VPDomainModelConnector connector = new VPDomainModelConnector();
		SBVRFileConnector sbvrConnector = new SBVRFileConnector();

		DomainModelSBVRRelevantElementsContainer dmContainer = connector.fetchSBVRRelevantElements();
		SBVRFileElementsContainer sbvrContainer = null;
		try {
			sbvrContainer = sbvrConnector.loadSBVRData();
		} catch (IOException e) {

		}

		return new SBVRToDomainModelValidator(dmContainer, sbvrContainer);
	}

	private ValidationResult validateBPObject(IModelElement selectedElement) {
		return bpObjectValidator.validate(selectedElement);
	}

	private IDiagramElement retrieveSelectedElement() {
		try {
			return ApplicationManager.instance().getDiagramManager().getActiveDiagram().getSelectedDiagramElement()[0];
		} catch (Throwable t) {
			return null;
		}
	}

	private boolean isGeneralization(IModelElement selectedElement) {
		return selectedElement instanceof IGeneralization;
	}

	private boolean isAssociation(IModelElement selectedElement) {
		return selectedElement instanceof IAssociation;
	}

	private boolean isAssociationEnd(IModelElement selectedElement) {
		return selectedElement instanceof IAssociationEnd;
	}

	private boolean isClass(IModelElement selectedElement) {
		return selectedElement instanceof IClass;
	}

	private boolean instanceOfBPEvent(IModelElement selectedElement) {
		return selectedElement instanceof IBPEndEvent || selectedElement instanceof IBPStartEvent
				|| selectedElement instanceof IBPIntermediateEvent;
	}

	private boolean instanceOfBPObject(IModelElement selectedElement) {
		return selectedElement instanceof IBPDataObject;
	}

	private boolean isBusinessProcessDiagramUIModel(IDiagramUIModel diagramUIModel) {
		return (diagramUIModel != null && diagramUIModel instanceof IBusinessProcessDiagramUIModel);
	}

	private boolean isClassDiagramUIModel(IDiagramUIModel diagramUIModel) {
		return (diagramUIModel != null && diagramUIModel instanceof IClassDiagramUIModel);
	}

	private boolean isStateDiagramUIModel(IDiagramUIModel diagramUIModel) {
		return (diagramUIModel != null && diagramUIModel instanceof IStateDiagramUIModel);
	}

}
