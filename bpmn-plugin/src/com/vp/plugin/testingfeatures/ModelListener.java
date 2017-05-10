package com.vp.plugin.testingfeatures;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import com.vp.plugin.ApplicationManager;
import com.vp.plugin.ViewManager;
import com.vp.plugin.model.IBPTask;

public class ModelListener implements PropertyChangeListener {

	ViewManager _viewManager = ApplicationManager.instance().getViewManager();

	public ModelListener() {
	}

	@Override
	public void propertyChange(PropertyChangeEvent evt) {

		// System.out.println("ZZZZZZZZZZZZZZZZZZZZZZ");
		//
		// Object changeSource = evt.getSource();
		// if (changeSource instanceof IModelElement) {
		// IModelElement model = (IModelElement) changeSource;
		// _viewManager.showMessage("Model Element " + model.getModelType() + "
		// : " + model.getName() + " modified.");
		// _viewManager.showMessage("AAAAAAAAAAAAA");
		// _viewManager.showConfirmDialog(_viewManager.getRootFrame(), new
		// Object());
		// _viewManager.showInternalConfirmDialog(null, "Some string 1");
		// _viewManager.showInternalMessageDialog(null, "Some string 2");
		// _viewManager.showMessageDialog(null, "some string 3");
		// }

		System.out.println("===> ModelListener.propertyChange(...)");

		Object changeSource = evt.getSource();
		if (changeSource instanceof IBPTask) {
			IBPTask task = (IBPTask) changeSource;
			String taskName = task.getName();
			_viewManager.showMessage("Task changed:  " + taskName);
		}

	}

}
