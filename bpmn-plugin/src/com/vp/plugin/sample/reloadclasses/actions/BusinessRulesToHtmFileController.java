package com.vp.plugin.sample.reloadclasses.actions;

import java.awt.Component;

import com.vp.plugin.ApplicationManager;
import com.vp.plugin.ProjectManager;
import com.vp.plugin.ViewManager;
import com.vp.plugin.action.VPAction;
import com.vp.plugin.action.VPActionController;
import com.vp.plugin.model.IClass;
import com.vp.plugin.model.IModelElement;
import com.vp.plugin.model.factory.IModelElementFactory;

public class BusinessRulesToHtmFileController implements VPActionController {

	public void getXzzzz() {
		// ProjectManager projectManager =
		// ApplicationManager.instance().getProjectManager();
		ProjectManager projectManager = ApplicationManager.instance().getProjectManager();
		IModelElement[] classes = projectManager.getProject()
				.toModelElementArray(IModelElementFactory.MODEL_TYPE_CLASS);
		for (IModelElement _class : classes) {
			IClass _iclass = (IClass) _class;
			System.out.println(_iclass.getName());

		}

		AllVpModelsChecker.checkAllModels();
	}

	//
	// StringBuilder contentBuilder = new StringBuilder();
	//
	// IModelElement[] models = projectManager.getProject()
	// .toModelElementArray(IModelElementFactory.MODEL_TYPE_BUSINESS_RULE);
	//
	// IModelElement[] models2 = projectManager.getProject()
	// .toModelElementArray(IModelElementFactory.MODEL_TYPE_BP_BUSINESS_RULE_TASK);
	//
	// IModelElement[] models3 = projectManager.getProject()
	// .toModelElementArray(IModelElementFactory.MODEL_TYPE_BP_IMPL_BUSINESS_RULE);
	//
	// IModelElement[] models4 = projectManager.getProject()
	// .toModelElementArray(IModelElementFactory.MODEL_TYPE_BP_RULE);
	//
	// IModelElement[] structuredClasses = projectManager.getProject()
	// .toModelElementArray(IModelElementFactory.MODEL_TYPE_STRUCTURED_CLASS);
	//
	// IModelElement[] glossary = projectManager.getProject()
	// .toModelElementArray(IModelElementFactory.MODEL_TYPE_STRUCTURED_CLASS);
	//
	// System.out.println("MODEL_TYPE_BUSINESS_RULE - " +
	// structuredClasses.length);
	// System.out.println("MODEL_TYPE_BUSINESS_RULE - " + models.length);
	// System.out.println("MODEL_TYPE_BP_BUSINESS_RULE_TASK - " +
	// models2.length);
	// System.out.println("MODEL_TYPE_BP_IMPL_BUSINESS_RULE - " +
	// models3.length);
	// System.out.println("MODEL_TYPE_BP_RULE - " + models4.length);
	// System.out.println("MODEL_TYPE_GLOSSARY - " + glossary.length);
	//
	// contentBuilder.append(System.lineSeparator());
	// contentBuilder.append("MODEL_TYPE_BUSINESS_RULE - " + models.length);
	// contentBuilder.append("####################");
	// contentBuilder.append(System.lineSeparator());
	// contentBuilder.append("MODEL_TYPE_BP_BUSINESS_RULE_TASK - " +
	// models2.length);
	// contentBuilder.append("####################");
	// contentBuilder.append(System.lineSeparator());
	// contentBuilder.append("MODEL_TYPE_BP_IMPL_BUSINESS_RULE - " +
	// models3.length);
	// contentBuilder.append("####################");
	// contentBuilder.append(System.lineSeparator());
	// contentBuilder.append("MODEL_TYPE_BP_RULE - " + models4.length);
	// contentBuilder.append("####################");
	// contentBuilder.append(System.lineSeparator());
	// contentBuilder.append("NUMBER OF BUSINESS RULES: " + models.length);
	// contentBuilder.append(System.lineSeparator());
	// contentBuilder.append(System.lineSeparator());
	// contentBuilder.append(
	// projectManager.getProject().toModelElementArray(IModelElementFactory.MODEL_TYPE_GLOSSARY).length);
	// contentBuilder.append(System.lineSeparator());
	//
	// // System.out.println("-- all ? " +
	// // projectManager.getProject().toModelElementArray().length);
	//
	// for (IModelElement el :
	// projectManager.getProject().toModelElementArray()) {
	//
	// if (el.getName().equals("Business Rules")) {
	// // for(IDiagramElement del : el.getDiagramElements()){
	// // System.out.println("-- > " + del.get);
	// // }
	// for (IModelElement ch : el.toChildArray()) {
	// System.out.println("-- > " + ch.getName());
	// }
	//
	// System.out.println("here");
	// System.out.println(el.getDescription());
	// System.out.println(el.getModelType());
	// }
	//
	// if (el.getName().equals("Exercise")) {
	//
	// System.out.println(el);
	// }
	// // System.out.println(el.getName());
	// }
	//
	// contentBuilder.append(System.lineSeparator());
	// contentBuilder
	// .append(projectManager.getProject().toModelElementArray(IModelElementFactory.MODEL_TYPE_CLASS).length);
	// contentBuilder.append(System.lineSeparator());
	//
	// IModelElement[] classes = projectManager.getProject()
	// .toModelElementArray(IModelElementFactory.MODEL_TYPE_BP_RULE);
	//
	// for (IModelElement classX : classes) {
	// IClass ic = (IClass) classX;
	// System.out.println(ic.getName());
	// }
	//
	// // MODEL_TYPE_MODEL !!!!!!!!!!!!!!!!!!!!!!
	//
	// try {
	// for (int i = 0; i < models.length; i++) {
	// IModelElement modelElement = models[i];
	// IBusinessRule businessRule = (IBusinessRule) modelElement;
	// contentBuilder.append((getRuleString(businessRule)));
	// contentBuilder.append(System.lineSeparator());
	// //
	// contentBuilder.append(businessRule.getEnforcementLevel().getDescription());
	// // // ???
	// }
	// } catch (Throwable t) {
	//
	// }
	//
	// String s = contentBuilder.toString();
	// // System.out.println(s);
	// return s == null || s.isEmpty() ? "zzzz" : s;

	public void performAction(VPAction action) {
		// get the view manager and the parent component for modal the dialog.
		ViewManager viewManager = ApplicationManager.instance().getViewManager();
		Component parentFrame = viewManager.getRootFrame();

		getXzzzz();

		// // popup a file chooser for choosing the output file
		// JFileChooser fileChooser = viewManager.createJFileChooser();
		// fileChooser.setFileFilter(new FileFilter() {
		//
		// public String getDescription() {
		// return "*.htm";
		// }
		//
		// public boolean accept(File file) {
		// return file.isDirectory() ||
		// file.getName().toLowerCase().endsWith(".htm");
		// }
		//
		// });
		// fileChooser.showSaveDialog(parentFrame);
		// File file = fileChooser.getSelectedFile();
		//
		// if (file != null && !file.isDirectory()) {
		// String htmlContent = getBusinessRules();
		// String result = "";

		// // Retrieve all use cases from project
		// ProjectManager projectManager =
		// ApplicationManager.instance().getProjectManager();
		// IModelElement[] models = projectManager.getProject()
		// .toModelElementArray(IModelElementFactory.MODEL_TYPE_BUSINESS_RULE);
		//
		// // Retrieve an HTML string of flow of events info from every use
		// // case
		// for (int i = 0; i < models.length; i++) {
		// IModelElement modelElement = models[i];
		// IUseCase useCase = (IUseCase) modelElement;
		// htmlContent += generate(useCase);
		// }

		// write to file

		// try {
		// FileWriter writer = new
		// FileWriter(fileChooser.getSelectedFile());
		// System.out.println(file.getAbsolutePath());
		// writer.write(htmlContent);
		// writer.close();
		// result = "Success! HTML generated to " + file.getAbsolutePath();
		// } catch (IOException e) {
		// }
		//
		// // show the generation result
		// viewManager.showMessageDialog(parentFrame, result);
		// }
	}

	public void update(VPAction action) {
		// TODO Auto-generated method stub
	}

	// public String generate(IUseCase useCase) {
	// String content = "";
	//
	// // Retrieve flow of events sets from use case. Each IStepContainer is a
	// // set of flow of
	// // events
	// IStepContainer[] stepContainers = useCase.toStepContainerArray();
	// for (int i = 0; i < stepContainers.length; i++) {
	// IStepContainer stepContainer = stepContainers[i];
	//
	// // Print the name of use case and flow of events to HTML string
	// content += "<table border=\"1\" width=\"500\"><tr><th>" +
	// useCase.getName() + " - "
	// + stepContainer.getName() + "</th></tr>";
	//
	// // Print the flow of events content to HTML string
	// IStep[] stepArray = stepContainer.toStepArray();
	// for (int j = 0; j < stepArray.length; j++) {
	// IStep step = stepArray[j];
	// content += "<tr><td>" + (j + 1) + ". " + step.getName() + "</td></tr>";
	// }
	// content += "</table><br>";
	// }
	//
	// return content;
	//
	// }

}
