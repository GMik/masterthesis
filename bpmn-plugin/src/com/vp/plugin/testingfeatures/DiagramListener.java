package com.vp.plugin.testingfeatures;

import com.vp.plugin.ApplicationManager;
import com.vp.plugin.ViewManager;
import com.vp.plugin.diagram.IBusinessProcessDiagramUIModel;
import com.vp.plugin.diagram.IDiagramElement;
import com.vp.plugin.diagram.IDiagramListener;
import com.vp.plugin.diagram.IDiagramUIModel;
import com.vp.plugin.diagram.IStateDiagramUIModel;
import com.vp.plugin.exceptions.ValidationException;
import com.vp.plugin.model.IBPDataObject;
import com.vp.plugin.model.IBPEndEvent;
import com.vp.plugin.model.IBPIntermediateEvent;
import com.vp.plugin.model.IBPStartEvent;
import com.vp.plugin.model.IModelElement;
import com.vp.plugin.utils.validation.ValidationResult;
import com.vp.plugin.utils.validation.bp.dataobjects.BPDataObjectValidator;
import com.vp.plugin.utils.validation.bp.dataobjects.BPObjectStandardValidationStrategy;
import com.vp.plugin.utils.validation.bp.events.BPEventRestrictiveValidationStrategy;
import com.vp.plugin.utils.validation.bp.events.BPEventValidator;

public class DiagramListener implements IDiagramListener {

	ViewManager _viewManager = ApplicationManager.instance().getViewManager();

	private static BPEventValidator eventValidator;

	private static BPDataObjectValidator bpObjectValidator;

	public DiagramListener() {
	}

	static {
		eventValidator = new BPEventValidator(new BPEventRestrictiveValidationStrategy());
		bpObjectValidator = new BPDataObjectValidator(new BPObjectStandardValidationStrategy());
	}

	@Override
	public void diagramElementAdded(IDiagramUIModel arg0, IDiagramElement arg1) {
		System.out.println("===> DiagramListener.diagramElementAdded(...)");
		_viewManager.showMessage("Diagram Element " + arg1.getModelElement().getName() + " Added");
	}

	@Override
	public void diagramElementRemoved(IDiagramUIModel arg0, IDiagramElement arg1) {
		System.out.println("===> DiagramListener.diagramElementRemoved(...)");
		_viewManager.showMessage("Diagram Element " + arg1.getModelElement().getName() + " Removed");

	}

	@Override
	public void diagramUIModelLoaded(IDiagramUIModel arg0) {
		System.out.println("===> DiagramListener.diagramUIModelLoaded(...)");
		_viewManager.showMessage("Diagram " + arg0.getType() + " : " + arg0.getName() + " Loaded");

	}

	private IDiagramElement retrieveSelectedElement() {
		try {
			return ApplicationManager.instance().getDiagramManager().getActiveDiagram().getSelectedDiagramElement()[0];
		} catch (Throwable t) {
			return null;
		}
	}

	private ValidationResult eventValidation(IModelElement selectedElement) {

		ValidationResult validationResult = null;

		// if (selectedElement instanceof IBPTask) {
		// IVPDomainModelConnector domainModelConnector = new
		// VPDomainModelConnector();
		// String[] taskWords = ModelElementUtil.fetchWords(selectedElement);
		// if (taskWords != null) {
		// for (String tw : taskWords) {
		// System.out.println(tw);
		// }
		// }
		if (selectedElement instanceof IBPStartEvent)

		{

			/**
			 * TODO - REFACTOR!
			 */

			try {
				validationResult = eventValidator.validate((IBPStartEvent) selectedElement);
			} catch (ValidationException e) {

				System.out.println("########### validation exception - IBPStartEvent");
				/**
				 * TODO
				 */
			}

		} else if (selectedElement instanceof IBPIntermediateEvent) {

			try {
				validationResult = eventValidator.validate((IBPIntermediateEvent) selectedElement);
			} catch (ValidationException e) {

				System.out.println("########### validation exception - IBPIntermediateEvent");
				/**
				 * TODO
				 */
			}

		} else if (selectedElement instanceof IBPEndEvent) {

			try {
				validationResult = eventValidator.validate((IBPEndEvent) selectedElement);
			} catch (ValidationException e) {
				System.out.println("########### validation exception - IBPEndEvent");/**
																						 * TODO
																						 */
			}
		}
		return validationResult;
	}

	private boolean instanceOfBPEvent(IModelElement selectedElement) {
		return selectedElement instanceof IBPEndEvent || selectedElement instanceof IBPStartEvent
				|| selectedElement instanceof IBPIntermediateEvent;
	}

	private boolean instanceOfBPObject(IModelElement selectedElement) {
		return selectedElement instanceof IBPDataObject;
	}

	// TODO- chain of responsibility?
	@Override
	public void diagramUIModelPropertyChanged(IDiagramUIModel diagramUIModel, String arg1, Object arg2, Object arg3) {

		System.out.println("===> DiagramListener.diagramUIModelPropertyChanged(...)");

		IModelElement selectedElement = retrieveSelectedElement() == null ? null
				: retrieveSelectedElement().getMetaModelElement();
		ValidationResult validationResult = null;

		if (selectedElement == null) {
			return;
		}

		if (isBusinessProcessDiagramUIModel(diagramUIModel)) {

			validationResult = instanceOfBPEvent(selectedElement) ? eventValidation(selectedElement) : validationResult;
			validationResult = instanceOfBPObject(selectedElement) ? bpObjectValidation(selectedElement)
					: validationResult;

		} else if (isStateDiagramUIModel(diagramUIModel)) {

		}

		if (validationResult != null) {
			System.out.println("--------- VALIDATION MESSAGE: " + validationResult.getMessage());

		}

	}

	private ValidationResult bpObjectValidation(IModelElement selectedElement) {
		try {
			return bpObjectValidator.validate(selectedElement);
		} catch (ValidationException e) {
			System.out.println("########### validation exception - IBPDataObject");
			/**
			 * TODO
			 */
			return null;
		}
	}

	@Override
	public void diagramUIModelRenamed(IDiagramUIModel arg0) {
		System.out.println("===> DiagramListener.diagramUIModelRenamed(...)");
		System.out.println("Diagram " + arg0.getType() + " : " + arg0.getName() + " Renamed");

	}

	private boolean isBusinessProcessDiagramUIModel(IDiagramUIModel diagramUIModel) {
		return (diagramUIModel != null && diagramUIModel instanceof IBusinessProcessDiagramUIModel);
	}

	private boolean isStateDiagramUIModel(IDiagramUIModel diagramUIModel) {
		return (diagramUIModel != null && diagramUIModel instanceof IStateDiagramUIModel);
	}

}
