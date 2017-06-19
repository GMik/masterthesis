package com.vp.plugin.utils.validation.sbvr.to.dm;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.vp.plugin.connectors.businessrules.CharacteristicKindOf;
import com.vp.plugin.connectors.businessrules.RelationshipType;
import com.vp.plugin.connectors.businessrules.SBVRClassCharacteristicFact;
import com.vp.plugin.connectors.businessrules.SBVRFact;
import com.vp.plugin.connectors.businessrules.SBVRFileElementsContainer;
import com.vp.plugin.connectors.domainmodel.br.DomainModelSBVRRelevantElementsContainer;
import com.vp.plugin.connectors.domainmodel.br.facts.AggregationKind;
import com.vp.plugin.connectors.domainmodel.br.facts.ClassFact;
import com.vp.plugin.connectors.domainmodel.br.facts.GeneralizationFact;
import com.vp.plugin.connectors.domainmodel.br.facts.RelationshipFact;
import com.vp.plugin.connectors.domainmodel.br.facts.StateFact;
import com.vp.plugin.utils.validation.ValidationResult;

public class SBVRToDomainModelValidator {

	// data
	private DomainModelSBVRRelevantElementsContainer domainModelElementsContainer;
	private SBVRFileElementsContainer sbvrElementsContainer;

	// validators
	private BRAssociationAllKindsValidator associationValidator = new BRAssociationAllKindsValidator();
	private BRClassValidator classValidator = new BRClassValidator();
	private BRGeneralizationValidator generalizationValidator = new BRGeneralizationValidator();

	public SBVRToDomainModelValidator(DomainModelSBVRRelevantElementsContainer domainModelElementsContainer,
			SBVRFileElementsContainer sbvrElementsContainer) {
		this.domainModelElementsContainer = domainModelElementsContainer;
		this.sbvrElementsContainer = sbvrElementsContainer;
	}

	/**
	 * Validates class attributes
	 * 
	 * @return validation results
	 */
	public List<ValidationResult> validateClassAttributes() {

		List<ValidationResult> validationResults = new ArrayList<>();

		List<ClassFact> domainModelClassFacts = domainModelElementsContainer.getClassFacts();
		List<SBVRClassCharacteristicFact> sbvrClassFacts = sbvrElementsContainer.getCharacteristicFacts().stream()
				.filter(fact -> fact.getCharacteristicKinfOf().equals(CharacteristicKindOf.ATTRIBUTE))
				.collect(Collectors.toList());
		validationResults.addAll(classValidator.validateAttributes(domainModelClassFacts, sbvrClassFacts));

		return validationResults;

	}

	/**
	 * Validates class states
	 * 
	 * @return validation results
	 */
	public List<ValidationResult> validateClassStates() {

		List<ValidationResult> validationResults = new ArrayList<>();

		List<StateFact> domainModelStateFacts = domainModelElementsContainer.getStateFacts();
		List<SBVRClassCharacteristicFact> sbvrStateFacts = sbvrElementsContainer.getCharacteristicFacts().stream()
				.filter(fact -> fact.getCharacteristicKinfOf().equals(CharacteristicKindOf.STATE))
				.collect(Collectors.toList());
		validationResults.addAll(classValidator.validateStates(domainModelStateFacts, sbvrStateFacts));

		return validationResults;

	}

	/**
	 * Validates class associations and roles.
	 * 
	 * @return validation results
	 */
	public List<ValidationResult> validateAssociations() {

		List<RelationshipFact> domainModelAssociationFacts = domainModelElementsContainer.getRelationshipFacts()
				.stream().filter(fact -> fact.getAggregationKind().equals(AggregationKind.ASSOCIATION))
				.collect(Collectors.toList());
		List<SBVRFact> sbvrAssociationFacts = sbvrElementsContainer.getFacts().stream()
				.filter(fact -> fact.getRelationshipType().equals(RelationshipType.ASSOCIATION))
				.collect(Collectors.toList());

		return associationValidator.validateAssiciations(sbvrAssociationFacts, domainModelAssociationFacts);
	}

	/**
	 * Validates class compositions and roles.
	 * 
	 * @return validation results
	 */
	public List<ValidationResult> validateCompositions() {

		List<RelationshipFact> domainModelCompositionFacts = domainModelElementsContainer.getRelationshipFacts()
				.stream().filter(fact -> fact.getAggregationKind().equals(AggregationKind.COMPOSITION))
				.collect(Collectors.toList());
		List<SBVRFact> sbvrCompositionFacts = sbvrElementsContainer.getFacts().stream()
				.filter(fact -> fact.getRelationshipType().equals(RelationshipType.COMPOSITION))
				.collect(Collectors.toList());

		return associationValidator.validateCompositions(sbvrCompositionFacts, domainModelCompositionFacts);
	}

	/**
	 * Validates class generalizations and roles.
	 * 
	 * @return validation results
	 */
	public List<ValidationResult> validateGeneralizations() {

		List<SBVRFact> sbvrGeneralizationFacts = sbvrElementsContainer.getFacts().stream()
				.filter(fact -> fact.getRelationshipType().equals(RelationshipType.GENERALIZATION))
				.collect(Collectors.toList());
		List<GeneralizationFact> dmGeneralizationFacts = domainModelElementsContainer.getGeneralizationFacts();

		return generalizationValidator.validate(sbvrGeneralizationFacts, dmGeneralizationFacts);
	}

	// public void validate() {
	//
	// List<ValidationResult> validationResults = new ArrayList<>();
	//
	// /**
	// * validate roles, associations, compositions, (not supported yet -
	// * aggregations)
	// */
	// List<ValidationResult> assoc_validationResults =
	// associationValidator.validate(domainModelElementsContainer,
	// sbvrElementsContainer);
	//
	// /**
	// * validate class attributes and states
	// */
	// List<ValidationResult> class_validationResults =
	// classValidator.validate(domainModelElementsContainer,
	// sbvrElementsContainer);
	//
	// /**
	// * validate generalization relationships
	// */
	// List<ValidationResult> gener_validationResults =
	// generalizationValidator.validate(domainModelElementsContainer,
	// sbvrElementsContainer);
	//
	// /**
	// * validate associations roles
	// */
	//
	// // List<ValidationResult> roles_validationResults =
	// // rolesValidator.validate(domainModelElementsContainer,
	// // sbvrElementsContainer);
	// System.out.println("zz");
	//
	// }

}
