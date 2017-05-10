package com.vp.plugin.connectors.domainmodel;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.vp.plugin.ApplicationManager;
import com.vp.plugin.ProjectManager;
import com.vp.plugin.model.IClass;
import com.vp.plugin.model.IModelElement;
import com.vp.plugin.model.IState2;
import com.vp.plugin.model.factory.IModelElementFactory;

public class VPDomainModelConnector implements IVPDomainModelConnector {

	// TODO: fetch not only from Stat2, but also from Class

	@Override
	public List<StateMachine> fetchStateMachines() {
		List<StateMachine> stateMachines = new ArrayList<>();
		Map<IClass, List<IState2>> classesWithStates = fetchClassesWithStates();
		for (Map.Entry<IClass, List<IState2>> entry : classesWithStates.entrySet()) {
			stateMachines.add(new StateMachine(entry.getKey().getId(), entry.getKey(), entry.getValue()));
		}
		return stateMachines;
	}

	@Override
	public List<StateMachine> fetchStateMachinesFor(String classname) {
		List<StateMachine> stateMachines = new ArrayList<>();
		Map<IClass, List<IState2>> classesWithStates = fetchClassesWithStatesFor(classname);
		for (Map.Entry<IClass, List<IState2>> entry : classesWithStates.entrySet()) {
			stateMachines.add(new StateMachine(entry.getKey().getId(), entry.getKey(), entry.getValue()));
		}
		return stateMachines;
	}

	private Map<IClass, List<IState2>> fetchClassesWithStates() {

		Map<String, List<IState2>> classesIdsToStates = fetchClassesWithStatesHelpMethod();
		Map<IClass, List<IState2>> classesToStates = new HashMap<>();

		for (Map.Entry<String, List<IState2>> entry : classesIdsToStates.entrySet()) {

			classesToStates.put((IClass) ApplicationManager.instance().getProjectManager().getProject()
					.getModelElementById(entry.getKey()), entry.getValue());
		}

		return classesToStates;

	}

	private Map<IClass, List<IState2>> fetchClassesWithStatesFor(String className) {
		Map<IClass, List<IState2>> classesToStates = fetchClassesWithStates();
		Map<IClass, List<IState2>> classesToStatesForClass = new HashMap<>();

		for (Map.Entry<IClass, List<IState2>> entry : classesToStates.entrySet()) {
			if (entry.getKey().getName().equals(className)) {
				classesToStatesForClass.put(entry.getKey(), entry.getValue());
			}
		}
		return classesToStatesForClass;
	}

	@Override
	public Set<IClass> fetchClasses() {
		Set<IClass> result = new HashSet<>();

		IModelElement[] classes = ApplicationManager.instance().getProjectManager().getProject()
				.toAllLevelModelElementArray(IModelElementFactory.MODEL_TYPE_CLASS);

		for (IModelElement _class : classes) {
			result.add((IClass) _class);
		}

		// result.addAll(fetchAllClassesFromPackages());
		// result.addAll(fetchClassesFromModel());
		// result.addAll(fetchClassesFromElements());
		return result;
	}

	@Override
	public Set<IClass> fetchClassesWithGivenName(String classname) {
		Set<IClass> allClasses = fetchClasses();
		Set<IClass> result = new HashSet<>();
		for (IClass _class : allClasses) {
			if (_class.getName().equals(classname)) {
				result.add(_class);
			}
		}
		return result;

	}

	private Map<String, List<IState2>> fetchClassesWithStatesHelpMethod() {
		Map<String, List<IState2>> classesIdsToStates = new HashMap<>();
		IModelElement[] states = ApplicationManager.instance().getProjectManager().getProject()
				.toAllLevelModelElementArray(IModelElementFactory.MODEL_TYPE_STATE2);
		for (IModelElement element : states) {
			IState2 state = (IState2) element;
			if (state.getParent() != null) {
				if (classesIdsToStates.containsKey(state.getParent().getId())) {
					classesIdsToStates.get(state.getParent().getId()).add(state);
				} else {
					classesIdsToStates.put(state.getParent().getId(), new ArrayList<>(Arrays.asList(state)));
				}
			}
		}
		return classesIdsToStates;

	}

	private List<IClass> fetchClassesFrom(String modelType) {
		List<IClass> result = new ArrayList<>();
		ProjectManager projectManager = ApplicationManager.instance().getProjectManager();
		IModelElement[] elements = projectManager.getProject().toModelElementArray(modelType);
		for (IModelElement element : elements) {
			if (element instanceof IClass) {
				result.add((IClass) element);
			}
		}
		return result;
	}

	private List<IClass> fetchClassesFromElements() {
		return fetchClassesFrom(IModelElementFactory.MODEL_TYPE_CLASS);
	}

	private List<IClass> fetchClassesFromModel() {
		return fetchClassesFrom(IModelElementFactory.MODEL_TYPE_MODEL);
	}

	private List<IClass> fetchAllClassesFromPackages() {
		IModelElement[] modelElements = ApplicationManager.instance().getProjectManager().getProject()
				.toModelElementArray(IModelElementFactory.MODEL_TYPE_PACKAGE);
		List<IClass> classesAccumulator = new ArrayList<>();
		for (IModelElement modelElement : modelElements) {
			fetchAllClassesFromPackagesHelp(modelElement, classesAccumulator);
		}
		return classesAccumulator;
	}

	private static void fetchAllClassesFromPackagesHelp(IModelElement modelElement, List<IClass> accumulator) {
		for (IModelElement childElement : modelElement.toChildArray()) {
			if (childElement instanceof IClass) {

				accumulator.add((IClass) childElement);
			}
			fetchAllClassesFromPackagesHelp(childElement, accumulator);
		}
	}

}
