package com.vp.plugin.testingfeatures;

import com.vp.plugin.ApplicationManager;
import com.vp.plugin.ViewManager;
import com.vp.plugin.model.IModelElement;
import com.vp.plugin.model.IProject;
import com.vp.plugin.model.IProjectModelListener;
import com.vp.plugin.sample.reloadclasses.ReloadClassesPlugin;

public class ProjectModelListener implements IProjectModelListener {

	ViewManager _viewManager = ApplicationManager.instance().getViewManager();

	@Override
	public void modelAdded(IProject arg0, IModelElement arg1) {
		System.out.println("===> ProjectModelListener.modelAdded(...)");
		_viewManager.showMessage("Model Element " + arg1.getName() + " added");
		arg1.addPropertyChangeListener(ReloadClassesPlugin.MODEL_LISTENER);
	}

	@Override
	public void modelRemoved(IProject arg0, IModelElement arg1) {
		System.out.println("===> ProjectModelListener.modelRemoved(...)");
		_viewManager.showMessage("Model Element " + arg1.getName() + " removed");

	}

}
