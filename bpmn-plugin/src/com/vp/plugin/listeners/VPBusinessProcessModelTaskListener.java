package com.vp.plugin.listeners;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import com.vp.plugin.model.IBPTask;

public class VPBusinessProcessModelTaskListener implements PropertyChangeListener {

	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		Object changeSource = evt.getSource();
		if (changeSource instanceof IBPTask) {
			IBPTask task = (IBPTask) changeSource;
			String taskName = task.getName();

		}
	}

}
