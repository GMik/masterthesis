package com.vp.plugin.connectors.domainmodel;

import java.util.List;
import java.util.Set;

import com.vp.plugin.model.IClass;

public interface IVPDomainModelConnector {

	Set<IClass> fetchClasses();

	// Set<String> fetchStatesFor(String classifier);

	// Map<IClass, List<IState2>> fetchClassesWithStates();

	// Map<IClass, List<IState2>> fetchClassesWithStatesFor(String className);

	List<StateMachine> fetchStateMachines();

	List<StateMachine> fetchStateMachinesFor(String classname);

	Set<IClass> fetchClassesWithGivenName(String classname);
}
