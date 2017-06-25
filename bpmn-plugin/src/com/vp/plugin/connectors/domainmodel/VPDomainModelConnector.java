package com.vp.plugin.connectors.domainmodel;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.vp.plugin.ApplicationManager;
import com.vp.plugin.ProjectManager;
import com.vp.plugin.connectors.domainmodel.br.DomainModelSBVRRelevantElementsContainer;
import com.vp.plugin.connectors.domainmodel.br.TermsAndClassFacts;
import com.vp.plugin.connectors.domainmodel.br.facts.AggregationKind;
import com.vp.plugin.connectors.domainmodel.br.facts.ClassFact;
import com.vp.plugin.connectors.domainmodel.br.facts.Fact;
import com.vp.plugin.connectors.domainmodel.br.facts.GeneralizationFact;
import com.vp.plugin.connectors.domainmodel.br.facts.RelationshipFact;
import com.vp.plugin.connectors.domainmodel.br.facts.RelationshipFact.RelationshipFactBuilder;
import com.vp.plugin.connectors.domainmodel.br.facts.StateFact;
import com.vp.plugin.connectors.domainmodel.br.terms.Term;
import com.vp.plugin.model.IActor;
import com.vp.plugin.model.IAssociation;
import com.vp.plugin.model.IAssociationEnd;
import com.vp.plugin.model.IAttribute;
import com.vp.plugin.model.IBPDataStore;
import com.vp.plugin.model.IBPLane;
import com.vp.plugin.model.IBPPool;
import com.vp.plugin.model.IClass;
import com.vp.plugin.model.IGeneralization;
import com.vp.plugin.model.IModelElement;
import com.vp.plugin.model.IState2;
import com.vp.plugin.model.factory.IModelElementFactory;

public class VPDomainModelConnector implements IVPDomainModelConnector {

	public List<String> fetchPackages() {
		List<String> packages = new ArrayList<>();

		IModelElement[] elements = ApplicationManager.instance().getProjectManager().getProject()
				.toAllLevelModelElementArray(IModelElementFactory.MODEL_TYPE_PACKAGE);

		for (IModelElement element : elements) {
			packages.add(element.getName());
		}
		return packages;
	}

	public List<IBPDataStore> fetchDatastores() {
		List<IBPDataStore> datastores = new ArrayList<>();

		IModelElement[] elements = ApplicationManager.instance().getProjectManager().getProject()
				.toAllLevelModelElementArray(IModelElementFactory.MODEL_TYPE_BP_DATA_STORE);

		for (IModelElement element : elements) {
			IBPDataStore ds = (IBPDataStore) element;
			datastores.add(ds);
		}
		return datastores;
	}

	public List<IBPLane> fetchLanes() {
		List<IBPLane> lanes = new ArrayList<>();

		IModelElement[] elements = ApplicationManager.instance().getProjectManager().getProject()
				.toAllLevelModelElementArray(IModelElementFactory.MODEL_TYPE_BP_LANE);

		for (IModelElement element : elements) {
			IBPLane lane = (IBPLane) element;
			lanes.add(lane);
		}
		return lanes;
	}

	public List<IBPPool> fetchPools() {
		List<IBPPool> pools = new ArrayList<>();

		IModelElement[] elements = ApplicationManager.instance().getProjectManager().getProject()
				.toAllLevelModelElementArray(IModelElementFactory.MODEL_TYPE_BP_POOL);

		for (IModelElement element : elements) {
			IBPPool pool = (IBPPool) element;
			pools.add(pool);
		}
		return pools;
	}

	public List<IActor> fetchActors() {

		List<IActor> actors = new ArrayList<>();

		IModelElement[] elements = ApplicationManager.instance().getProjectManager().getProject()
				.toAllLevelModelElementArray(IModelElementFactory.MODEL_TYPE_ACTOR);

		for (IModelElement element : elements) {
			IActor actor = (IActor) element;
			actors.add(actor);
		}

		return actors;
	}

	@Override
	public List<StateMachine> fetchStateMachines() {
		List<StateMachine> stateMachines = new ArrayList<>();
		Map<IClass, List<IState2>> classesWithStates = fetchClassesWithStates();
		for (Map.Entry<IClass, List<IState2>> entry : classesWithStates.entrySet()) {
			stateMachines.add(new StateMachine(entry.getKey().getId(), entry.getKey(), entry.getValue()));
		}
		return stateMachines;
	}

	private List<StateFact> fetchStateFacts() {
		List<StateFact> stateFacts = new ArrayList<>();
		List<StateMachine> stateMachines = fetchStateMachines();
		for (StateMachine sm : stateMachines) {
			for (IState2 state : sm.getStates()) {
				StateFact fact = new StateFact(new Term(sm.getForClass().getName()), state.getName());
				stateFacts.add(fact);
			}
		}
		return stateFacts;
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

	@Override
	public Set<IClass> fetchClasses() {
		Set<IClass> result = new HashSet<>();

		IModelElement[] classes = ApplicationManager.instance().getProjectManager().getProject()
				.toAllLevelModelElementArray(IModelElementFactory.MODEL_TYPE_CLASS);

		for (IModelElement _class : classes) {
			result.add((IClass) _class);
		}
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

	@Override
	public DomainModelSBVRRelevantElementsContainer fetchSBVRRelevantElements() {
		DomainModelSBVRRelevantElementsContainer container = new DomainModelSBVRRelevantElementsContainer();

		List<RelationshipFact> relationshipFacts = fetchRelationshipFacts();
		List<GeneralizationFact> generalizationFacts = fetchGeneralizationFacts();
		TermsAndClassFacts termsAndClassFacts = fetchTermsAndClassFacts();
		List<StateFact> stateFacts = fetchStateFacts();

		container.setRelationshipFacts(relationshipFacts);
		container.setGeneralizationFacts(generalizationFacts);
		container.setTerms(termsAndClassFacts.terms);
		container.setClassFacts(termsAndClassFacts.classFacts);
		container.setStateFacts(stateFacts);

		return container;
	}

	@Override
	public List<Term> fetchTerms() {

		Set<Term> terms = new HashSet<>();

		Set<IClass> classes = fetchClasses();
		for (IClass clazz : classes) {
			terms.add(new Term(clazz.getName()));
		}

		return new ArrayList<Term>(terms);
	}

	@Override
	public List<Fact> fetchFacts() {

		List<Fact> facts = new ArrayList<>();

		List<ClassFact> classFacts = fetchClassFacts();
		List<RelationshipFact> relationshipFacts = fetchRelationshipFacts();

		facts.addAll(classFacts);
		facts.addAll(relationshipFacts);

		return facts;
	}

	private List<ClassFact> fetchClassFacts() {
		return fetchTermsAndClassFacts().classFacts;
	}

	private List<GeneralizationFact> fetchGeneralizationFacts() {

		IModelElement[] generalizations = ApplicationManager.instance().getProjectManager().getProject()
				.toAllLevelModelElementArray(IModelElementFactory.MODEL_TYPE_GENERALIZATION);

		List<GeneralizationFact> generalizationFacts = new ArrayList<>();

		for (IModelElement elem : generalizations) {
			IGeneralization generalization = (IGeneralization) elem;
			IModelElement subclass = generalization.getTo(); // subclass
			IModelElement superclass = generalization.getFrom(); // superclass
			GeneralizationFact generalizationFact = new GeneralizationFact(new Term(superclass.getName()),
					generalization.getName(), new Term(subclass.getName()));

			generalizationFacts.add(generalizationFact);

		}
		return generalizationFacts;

	}

	private AggregationKind retrieveAggregationKind(String left, String right) {
		if (left.equals(AggregationKind.COMPOSITION.getValue())
				|| right.equals(AggregationKind.COMPOSITION.getValue())) {
			return AggregationKind.COMPOSITION;
		}
		if (left.equals(AggregationKind.AGGREGATION.getValue())
				|| right.equals(AggregationKind.AGGREGATION.getValue())) {
			return AggregationKind.AGGREGATION;
		}
		return AggregationKind.ASSOCIATION;
	}

	private List<RelationshipFact> fetchRelationshipFacts() {

		ProjectManager projectManager = ApplicationManager.instance().getProjectManager();
		IModelElement[] associations = projectManager.getProject()
				.toAllLevelModelElementArray(IModelElementFactory.MODEL_TYPE_ASSOCIATION);

		List<RelationshipFact> relationshipFacts = new ArrayList<>();

		for (IModelElement elem : associations) {

			IAssociation association = (IAssociation) elem;

			IAssociationEnd associationEndFrom = (IAssociationEnd) association.getFromEnd();
			IAssociationEnd associationEndTo = (IAssociationEnd) association.getToEnd();

			//
			String fromRole = associationEndFrom.getName();
			String toRole = associationEndTo.getName();
			//
			AggregationKind aggregationKind = retrieveAggregationKind(associationEndFrom.getAggregationKind(),
					associationEndTo.getAggregationKind());

			try {
				String leftClass = associationEndFrom.getEndRelationship().getTo().getName();
				String rightClass = associationEndFrom.getEndRelationship().getFrom().getName();

				String leftMultiplicity = associationEndFrom.getMultiplicity();
				String rightMultiplicity = associationEndTo.getMultiplicity();

				RelationshipFact relationshipFact = new RelationshipFactBuilder().leftTerm(new Term(leftClass))
						.leftRole(fromRole).rightRole(toRole).leftMultiplicity(leftMultiplicity)
						.verb(association.getName()).rightMultiplicity(rightMultiplicity)
						.rightTerm(new Term(rightClass)).aggregationKind(aggregationKind).build();

				relationshipFacts.add(relationshipFact);
			} catch (NullPointerException e) {
				// do nothing
			}

		}

		return relationshipFacts;

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

	private TermsAndClassFacts fetchTermsAndClassFacts() {

		TermsAndClassFacts termsAndClassFacts = new TermsAndClassFacts();

		Set<IClass> classes = fetchClasses();
		for (IClass clazz : classes) {

			// Terms - class names
			Term term = new Term(clazz.getName());
			termsAndClassFacts.terms.add(term);

			// Facts - class name + has + attribute
			Iterator iterator = clazz.attributeIterator();
			while (iterator.hasNext()) {
				IAttribute element = (IAttribute) iterator.next();
				String property = element.getName();
				ClassFact classFact = new ClassFact(term, property);
				termsAndClassFacts.classFacts.add(classFact);
			}
		}

		return termsAndClassFacts;

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

	private Map<IClass, List<IState2>> fetchClassesWithStates() {

		Map<String, List<IState2>> classesIdsToStates = fetchClassesWithStatesHelpMethod();
		Map<IClass, List<IState2>> classesToStates = new HashMap<>();

		for (Map.Entry<String, List<IState2>> entry : classesIdsToStates.entrySet()) {

			classesToStates.put((IClass) ApplicationManager.instance().getProjectManager().getProject()
					.getModelElementById(entry.getKey()), entry.getValue());
		}

		return classesToStates;

	}

}
