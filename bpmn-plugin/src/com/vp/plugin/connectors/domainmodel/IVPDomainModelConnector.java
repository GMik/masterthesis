package com.vp.plugin.connectors.domainmodel;

import java.util.List;
import java.util.Set;

import com.vp.plugin.connectors.domainmodel.br.DomainModelSBVRRelevantElementsContainer;
import com.vp.plugin.connectors.domainmodel.br.facts.Fact;
import com.vp.plugin.connectors.domainmodel.br.terms.Term;
import com.vp.plugin.model.IClass;

public interface IVPDomainModelConnector {

	// Domain model - BPMN integrity
	Set<IClass> fetchClasses();

	Set<IClass> fetchClassesWithGivenName(String classname);

	List<StateMachine> fetchStateMachines();

	List<StateMachine> fetchStateMachinesFor(String classname);

	// Domain model - SBVR integrity
	List<Term> fetchTerms();

	List<Fact> fetchFacts();

	DomainModelSBVRRelevantElementsContainer fetchSBVRRelevantElements();

}
