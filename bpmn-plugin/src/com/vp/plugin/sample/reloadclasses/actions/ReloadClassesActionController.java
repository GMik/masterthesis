package com.vp.plugin.sample.reloadclasses.actions;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

import com.vp.plugin.ApplicationManager;
import com.vp.plugin.action.VPAction;
import com.vp.plugin.action.VPActionController;

public class ReloadClassesActionController implements VPActionController {

	@Override
	public void performAction(VPAction aAction) {

		// SAMPLE PLUGIN

		// e.g. C:\Program Files\Eclipse\Workspace\MyProject\classes
		File lSourceClassesFolder = new File(
				"C:\\Users\\Grzegorz\\Desktop\\ec ws\\com.vp_.plugin.sample.reloadclasses1 (1)\\com.vp.plugin.sample.reloadclasses\\classes");
		// e.g. C:\Program Files\Visual Paradigm for
		// UML\plugins\com.vp.plugin.sample.reloadclasses\classes
		File lPluginClassesFolder = new File(
				"C:\\Users\\Grzegorz\\AppData\\Roaming\\VisualParadigm\\plugins\\com.vp.plugin.sample.reloadclasses\\classes");

		copy(lSourceClassesFolder, lPluginClassesFolder);
		// BPMN PLUGIN

		/*
		 * // e.g. C:\Program Files\Eclipse\Workspace\MyProject\classes File
		 * lSourceClassesFolder2 = new File(
		 * "D:\\Studies\\Mgr III\\Praca dyplomowa - repo\\masterthesis\\target\\classes"
		 * ); // e.g. C:\Program Files\Visual Paradigm for //
		 * UML\plugins\com.vp.plugin.sample.reloadclasses\classes
		 * 
		 * File lPluginClassesFolder2 = new File(
		 * "C:\\Users\\Grzegorz\\AppData\\Roaming\\VisualParadigm\\plugins\\com.pwr.bpmnplugin\\classes"
		 * );
		 * 
		 * copy(lSourceClassesFolder2, lPluginClassesFolder2);
		 * 
		 * // e.g. com.vp.plugin.sample.reloadclasses
		 * 
		 */
		// ApplicationManager.instance().reloadPluginClasses("VPBpmnPlugin");

		ApplicationManager.instance().reloadPluginClasses("com.vp.plugin.sample.reloadclasses");
	}

	@Override
	public void update(VPAction aAction) {
	}

	private void copy(File aFrom, File aTo) {
		if (aFrom.isDirectory()) {
			if (!aTo.exists()) {
				aTo.mkdir();
			}

			File[] lFiles = aFrom.listFiles();
			if (lFiles != null) {
				for (File lFile : lFiles) {
					copy(lFile, new File(aTo, lFile.getName()));
				}
			}
		} else {

			try {
				InputStream lIs = new FileInputStream(aFrom);
				try {
					OutputStream lOs = new FileOutputStream(aTo);
					try {
						byte[] lData = new byte[10240];
						int lDataLength = lIs.read(lData);
						while (lDataLength > -1) {
							lOs.write(lData, 0, lDataLength);

							lDataLength = lIs.read(lData);
						}
					} finally {
						lOs.close();
					}
				} finally {
					lIs.close();
				}
			} catch (Exception lE) {
			}

		}

	}

}
