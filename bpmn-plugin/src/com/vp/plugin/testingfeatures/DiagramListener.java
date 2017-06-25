package com.vp.plugin.testingfeatures;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.vp.plugin.ApplicationManager;
import com.vp.plugin.ViewManager;
import com.vp.plugin.connectors.businessrules.SBVRFileConnector;
import com.vp.plugin.connectors.businessrules.SBVRFileElementsContainer;
import com.vp.plugin.connectors.domainmodel.StateMachine;
import com.vp.plugin.connectors.domainmodel.VPDomainModelConnector;
import com.vp.plugin.connectors.domainmodel.br.DomainModelSBVRRelevantElementsContainer;
import com.vp.plugin.diagram.IBusinessProcessDiagramUIModel;
import com.vp.plugin.diagram.IClassDiagramUIModel;
import com.vp.plugin.diagram.IDiagramElement;
import com.vp.plugin.diagram.IDiagramListener;
import com.vp.plugin.diagram.IDiagramUIModel;
import com.vp.plugin.diagram.IStateDiagramUIModel;
import com.vp.plugin.model.IActor;
import com.vp.plugin.model.IAssociation;
import com.vp.plugin.model.IAssociationEnd;
import com.vp.plugin.model.IBPDataInput;
import com.vp.plugin.model.IBPDataObject;
import com.vp.plugin.model.IBPDataObjectState;
import com.vp.plugin.model.IBPDataOutput;
import com.vp.plugin.model.IBPDataStore;
import com.vp.plugin.model.IBPEndEvent;
import com.vp.plugin.model.IBPGateway;
import com.vp.plugin.model.IBPIntermediateEvent;
import com.vp.plugin.model.IBPLane;
import com.vp.plugin.model.IBPPool;
import com.vp.plugin.model.IBPStartEvent;
import com.vp.plugin.model.IBusinessRule;
import com.vp.plugin.model.IBusinessRuleAssociation;
import com.vp.plugin.model.IBusinessRuleGroup;
import com.vp.plugin.model.IClass;
import com.vp.plugin.model.IGeneralization;
import com.vp.plugin.model.IModel;
import com.vp.plugin.model.IModelElement;
import com.vp.plugin.model.IState2;
import com.vp.plugin.model.factory.IModelElementFactory;
import com.vp.plugin.utils.validation.BPValidationMessages;
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

	private static VPDomainModelConnector domainModelConnector;

	ViewManager _viewManager = ApplicationManager.instance().getViewManager();

	public DiagramListener() {
	}

	static {
		eventValidator = new BPEventValidator(new BPEventRestrictiveValidationStrategy());
		bpObjectValidator = new BPDataObjectValidator(new BPObjectStandardValidationStrategy());
		domainModelConnector = new VPDomainModelConnector();
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

	private LocalDateTime lastChange = LocalDateTime.now();

	@Override
	public void diagramUIModelPropertyChanged(IDiagramUIModel diagramUIModel, String arg1, Object arg2, Object arg3) {

		LocalDateTime now = LocalDateTime.now();
		long difference = ChronoUnit.SECONDS.between(lastChange, now);

		lastChange = now;
		if (difference < 1) {
			return;
		}

		IModelElement selectedElement = retrieveSelectedElement() == null ? null
				: retrieveSelectedElement().getMetaModelElement();
		if (selectedElement == null) {
			return;
		}

		if (isClassDiagramUIModel(diagramUIModel)) {
			SBVRToDomainModelValidator validator = initializeBVRToDomainModelValidator();
			validateClassDiagramElements(selectedElement, validator);
		}

		// if (isBusinessProcessDiagramUIModel(diagramUIModel)) {
		// validateBPMNElements(selectedElement);
		// }

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

		// -- new

		// DATA OBJECT
		if (isBPMNDataObject(selectedElement)) {
			ValidationResult result = validateBPMN_Data(selectedElement);
			if (result != null) {
				_viewManager.showMessage(result.getMessage());
			}
		}

		// DATA INPUT
		if (isBPMNDataInput(selectedElement)) {
			ValidationResult result = validateBPMN_Data(selectedElement);
			if (result != null) {
				_viewManager.showMessage(result.getMessage());
			}
		}

		// DATA OUTPUT
		if (isBPMNDataOutput(selectedElement)) {
			ValidationResult result = validateBPMN_Data(selectedElement);
			if (result != null) {
				_viewManager.showMessage(result.getMessage());
			}
		}

		// DATA STORE
		if (isBPMNDataStore(selectedElement)) {
			ValidationResult result = validateDatastore(selectedElement);
			if (result != null) {
				_viewManager.showMessage(result.getMessage());
			}
		}

		// POOL
		if (isBPMNPool(selectedElement)) {
			ValidationResult result = validateBPMNPool(selectedElement.getName());
			if (result != null) {
				_viewManager.showMessage(result.getMessage());
			}
		}

		// LANE
		if (isBPMNLane(selectedElement)) {
			ValidationResult result = validateBPMNLane(selectedElement.getName());
			if (result != null) {
				_viewManager.showMessage(result.getMessage());
			}
		}

	}

	private boolean statesContains(StateMachine sm, String uistate) {
		for (IState2 state : sm.getStates()) {
			if (state.getName().equals(uistate)) {
				return true;
			}
		}
		return false;

	}

	private ValidationResult validateDatastore(IModelElement object) {
		String className = object.getName();
		Set<IClass> classes = domainModelConnector.fetchClassesWithGivenName(className);

		if (classes.isEmpty()) {
			return new ValidationResult(false,
					message("Warning: class with given name {0} is not defined for datastore.", className));
		} else {
			return null;
		}

	}

	private ValidationResult validateBPMN_Data(IModelElement object) {

		String content = object.getName();
		String className = null;
		String state = null;

		if (content.contains(" ") && content.contains("[") && content.contains("]")) {
			String[] parts = content.split(" ");
			className = parts[0];
			state = parts[1].substring(1, parts[1].length() - 1);

			Set<IClass> classes = domainModelConnector.fetchClassesWithGivenName(className);

			if (classes.isEmpty()) {
				return new ValidationResult(false,
						message("Warning: class with given name {0} is not defined.", className));
				// nie ma klasy dla className - MESSAGE
			} else {
				List<StateMachine> smachines = domainModelConnector.fetchStateMachinesFor(className);

				if (smachines.isEmpty()) {
					return new ValidationResult(false, message(
							"Warning: class with given name {0} is defined, but without state machine.", className));

					// ........................
				} else {

					if (statesContains(smachines.get(0), state)) {
						return null;
					} else {
						return new ValidationResult(false,
								message("Warning: class with given name {0} is defined with state machine, but state machine does not have any state like {1} defined.",
										className, state));
					}

				}
			}

		} else {
			className = content;

			Set<IClass> classes = domainModelConnector.fetchClassesWithGivenName(className);

			if (classes.isEmpty()) {
				return new ValidationResult(false,
						message("Warning: class with given name {0} is not defined.", className));
			} else {
				List<StateMachine> smachines = domainModelConnector.fetchStateMachinesFor(className);

				if (smachines.isEmpty()) {
					return null;
				} else {
					return new ValidationResult(false,
							message("Warning: class with given name {0} is defined with state machine.", className));
				}
			}

		}

	}

	private String message(String message, String... params) {
		String result = new String(message);
		int position = 0;
		for (String param : params) {
			result = result.replace("{" + (position++) + "}", param);
		}
		return result;
	}

	private ValidationResult validateBPMNPool(String name) {
		Set<IClass> classes = domainModelConnector.fetchClassesWithGivenName(name);
		List<String> packages = domainModelConnector.fetchPackages();

		boolean isCorrect = !packages.stream().filter(p -> name.equals(p)).collect(Collectors.toList()).isEmpty()
				|| !classes.isEmpty();
		return isCorrect ? null
				: new ValidationResult(false, message(BPValidationMessages.FOR_GIVEN_POOL_PACKAGE_NOT_DEFINED, name));
	}

	private ValidationResult validateBPMNLane(String name) {
		List<IActor> actors = domainModelConnector.fetchActors();
		Set<IClass> classes = domainModelConnector.fetchClassesWithGivenName(name);

		boolean isCorrect = !actors.stream().filter(a -> name.equals(a.getName())).collect(Collectors.toList())
				.isEmpty() || !classes.isEmpty();

		return isCorrect ? null
				: new ValidationResult(false,
						message(BPValidationMessages.FOR_GIVEN_LANE_ACTOR_OR_CLASS_NOT_DEFINED, name));
	}

	private ValidationResult validateBPMNDatastore(String name) {
		List<IActor> actors = domainModelConnector.fetchActors();
		Set<IClass> classes = domainModelConnector.fetchClassesWithGivenName(name);

		boolean isCorrect = !actors.stream().filter(a -> name.equals(a.getName())).collect(Collectors.toList())
				.isEmpty() || !classes.isEmpty();

		return isCorrect ? null
				: new ValidationResult(false,
						message(BPValidationMessages.FOR_GIVEN_DATASTORE_ACTOR_OR_CLASS_NOT_DEFINED, name));
	}

	private boolean isBPMNDataObject(IModelElement selectedElement) {
		return selectedElement instanceof IBPDataObject;
	}

	private boolean isBPMNGateway(IModelElement selectedElement) {
		return selectedElement instanceof IBPGateway;
	}

	private boolean isBPMNPool(IModelElement selectedElement) {
		return selectedElement instanceof IBPPool;
	}

	private boolean isBPMNLane(IModelElement selectedElement) {
		return selectedElement instanceof IBPLane;
	}

	private boolean isBPMNDataStore(IModelElement selectedElement) {
		return selectedElement instanceof IBPDataStore;
	}

	private boolean isBPMNDataInput(IModelElement selectedElement) {
		return selectedElement instanceof IBPDataInput;
	}

	private boolean isBPMNDataOutput(IModelElement selectedElement) {
		return selectedElement instanceof IBPDataOutput;
	}

	private boolean isBPMNDataObjectState(IModelElement selectedElement) {
		return selectedElement instanceof IBPDataObjectState;
	}

	// private boolean

	private void validateBusinessRule(IBusinessRule businessRule, SBVRToBusinessRulesValidator validator) {

		String ownId = businessRule.getName();
		ValidationResult validationResult = validator.validateBusinessRule(ownId);

		if (validationResult != null) {
			_viewManager.showMessage(validationResult.getMessage());
		}

	}

	private List<ValidationResult> validateClassName(String name) {
		Set<IClass> classes = domainModelConnector.fetchClassesWithGivenName(name);
		List<ValidationResult> results = new ArrayList<>();
		if (classes.isEmpty()) {
			results.add(new ValidationResult(false,
					message("Given class: {0} has no referencing term in SBVR rules", name)));
		}

		return results;
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
