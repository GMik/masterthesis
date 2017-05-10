package com.vp.plugin.sample.reloadclasses;

import com.vp.plugin.ApplicationManager;
import com.vp.plugin.VPPlugin;
import com.vp.plugin.VPPluginInfo;
import com.vp.plugin.model.IProject;
import com.vp.plugin.testingfeatures.DiagramListener;
import com.vp.plugin.testingfeatures.ModelListener;
import com.vp.plugin.testingfeatures.ProjectDiagramListener;
import com.vp.plugin.testingfeatures.ProjectListener;
import com.vp.plugin.testingfeatures.ProjectModelListener;

public class ReloadClassesPlugin implements VPPlugin {

	public static ModelListener MODEL_LISTENER = new ModelListener();
	public static DiagramListener DIAGRAM_LISTENER = new DiagramListener();
	public static ProjectModelListener PROJECT_MODEL_LISTENER = new ProjectModelListener();
	public static ProjectDiagramListener PROJECT_DIAGRAM_LISTENER = new ProjectDiagramListener();

	public void loaded(VPPluginInfo info) {
		ProjectListener projectListener = new ProjectListener();
		IProject project = ApplicationManager.instance().getProjectManager().getProject();
		project.addProjectListener(projectListener);

	}

	@Override
	public void unloaded() {
	}

}
