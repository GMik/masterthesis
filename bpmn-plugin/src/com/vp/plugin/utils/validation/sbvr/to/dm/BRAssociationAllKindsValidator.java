package com.vp.plugin.utils.validation.sbvr.to.dm;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.lang.StringUtils;

import com.vp.plugin.connectors.businessrules.RelationshipType;
import com.vp.plugin.connectors.businessrules.SBVRFact;
import com.vp.plugin.connectors.businessrules.SBVRFileElementsContainer;
import com.vp.plugin.connectors.businessrules.SBVRTerm;
import com.vp.plugin.connectors.domainmodel.br.DomainModelSBVRRelevantElementsContainer;
import com.vp.plugin.connectors.domainmodel.br.facts.AggregationKind;
import com.vp.plugin.connectors.domainmodel.br.facts.RelationshipFact;
import com.vp.plugin.connectors.domainmodel.br.terms.Term;
import com.vp.plugin.utils.validation.ValidationResult;
import com.vp.plugin.utils.validation.br.DMToSBVRValidationMessages;

public class BRAssociationAllKindsValidator {

	public List<ValidationResult> validate(DomainModelSBVRRelevantElementsContainer domainModelElementsContainer,
			SBVRFileElementsContainer sbvrElementsContainer) {
		List<ValidationResult> validationResults = new ArrayList<>();

		List<RelationshipFact> domainModelAssociationFacts = domainModelElementsContainer.getRelationshipFacts()
				.stream().filter(fact -> fact.getAggregationKind().equals(AggregationKind.ASSOCIATION))
				.collect(Collectors.toList());
		List<SBVRFact> sbvrAssociationFacts = sbvrElementsContainer.getFacts().stream()
				.filter(fact -> fact.getRelationshipType().equals(RelationshipType.ASSOCIATION))
				.collect(Collectors.toList());
		validationResults.addAll(validateAssiciations(sbvrAssociationFacts, domainModelAssociationFacts));

		List<RelationshipFact> domainModelCompositionFacts = domainModelElementsContainer.getRelationshipFacts()
				.stream().filter(fact -> fact.getAggregationKind().equals(AggregationKind.COMPOSITION))
				.collect(Collectors.toList());
		List<SBVRFact> sbvrCompositionFacts = sbvrElementsContainer.getFacts().stream()
				.filter(fact -> fact.getRelationshipType().equals(RelationshipType.COMPOSITION))
				.collect(Collectors.toList());
		validationResults.addAll(validateCompositions(sbvrCompositionFacts, domainModelCompositionFacts));

		return validationResults;
	}

	public List<ValidationResult> validateCompositions(List<SBVRFact> sbvrCompositionFacts,
			List<RelationshipFact> domainModelCompositionFacts) {
		return validate(domainModelCompositionFacts, sbvrCompositionFacts);

	}

	public List<ValidationResult> validateAssiciations(List<SBVRFact> sbvrAssociationFacts,
			List<RelationshipFact> domainModelAssociationFacts) {
		return validate(domainModelAssociationFacts, sbvrAssociationFacts);
	}

	private List<ValidationResult> validate(List<RelationshipFact> domainModelFacts, List<SBVRFact> sbvrFacts) {

		List<ValidationResult> validationResults = new ArrayList<>();

		for (RelationshipFact associationFact : domainModelFacts) {

			boolean matching = false;
			for (SBVRFact sbvrFact : sbvrFacts) {

				Term dm_term1 = associationFact.getLeftTerm();
				Term dm_term2 = associationFact.getRightTerm();
				String dm_verb = associationFact.getVerb();

				String sbvr_association = sbvrFact.getRelationship();
				SBVRTerm sbvr_term1 = sbvrFact.getTerm1();
				SBVRTerm sbvr_term2 = sbvrFact.getTerm2();

				if (dm_verb != null && dm_verb.equals(sbvr_association)) {
					if (termsAreEquals(dm_term1, sbvr_term1) && termsAreEquals(dm_term2, sbvr_term2)
							&& rolesAreEquals(sbvrFact.getTerm1Role(), sbvrFact.getTerm2Role(),
									associationFact.getLeftTermRole(), associationFact.getRightTermRole())) {
						matching = true;
						break;

					} else if ((termsAreEquals(dm_term1, sbvr_term2) && termsAreEquals(dm_term2, sbvr_term1))
							&& rolesAreEquals(sbvrFact.getTerm1Role(), sbvrFact.getTerm2Role(),
									associationFact.getLeftTermRole(), associationFact.getRightTermRole())) {
						matching = true;
						break;

					}
				}
			}
			if (!matching) {
				validationResults.add(new ValidationResult(false,
						message(DMToSBVRValidationMessages.DOMAIN_MODEL_ELEMENT_HAS_NO_MATCHING_BR,
								associationFact.toString())));
			}
		}
		return validationResults;
	}

	private boolean termsAreEquals(Term domainModelTerm, SBVRTerm sbvrTerm) {
		return domainModelTerm.getName().equals(sbvrTerm.getTerm());
	}

	private boolean rolesAreEquals(String sbvrLeftRole, String sbvrRightRole, String dmLeftRole, String dmRightRole) {
		return StringUtils.equals(sbvrLeftRole, dmLeftRole) && StringUtils.equals(sbvrRightRole, dmRightRole);
	}

	private String message(String message, String... params) {
		String result = new String(message);
		int position = 0;
		for (String param : params) {
			result = result.replace("{" + (position++) + "}", param);
		}
		return result;
	}

}
