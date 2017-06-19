package com.vp.plugin.utils.validation.sbvr.to.dm;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.vp.plugin.connectors.businessrules.RelationshipType;
import com.vp.plugin.connectors.businessrules.SBVRFact;
import com.vp.plugin.connectors.businessrules.SBVRFileElementsContainer;
import com.vp.plugin.connectors.domainmodel.br.DomainModelSBVRRelevantElementsContainer;
import com.vp.plugin.connectors.domainmodel.br.facts.GeneralizationFact;
import com.vp.plugin.utils.validation.ValidationResult;
import com.vp.plugin.utils.validation.br.DMToSBVRValidationMessages;

public class BRGeneralizationValidator {

	private static final Map<String, RelationshipType> map = new HashMap<>();

	static {
		map.put("owns", RelationshipType.COMPOSITION);
		map.put("is owned by", RelationshipType.COMPOSITION);
		map.put("extends", RelationshipType.GENERALIZATION);
		map.put("is extended by", RelationshipType.GENERALIZATION);

	}

	public List<ValidationResult> validate(DomainModelSBVRRelevantElementsContainer domainModelElementsContainer,
			SBVRFileElementsContainer sbvrElementsContainer) {

		List<SBVRFact> sbvrGeneralizationFacts = sbvrElementsContainer.getFacts().stream()
				.filter(fact -> fact.getRelationshipType().equals(RelationshipType.GENERALIZATION))
				.collect(Collectors.toList());
		List<GeneralizationFact> dmGeneralizationFacts = domainModelElementsContainer.getGeneralizationFacts();

		return validate(sbvrGeneralizationFacts, dmGeneralizationFacts);
	}

	public List<ValidationResult> validate(List<SBVRFact> sbvrGeneralizationFacts,
			List<GeneralizationFact> dmGeneralizationFacts) {

		List<ValidationResult> validationResults = new ArrayList<ValidationResult>();

		for (GeneralizationFact dmGeneralizatioFact : dmGeneralizationFacts) {

			boolean matching = false;
			for (SBVRFact sbvrFact : sbvrGeneralizationFacts) {

				String dmSuperclass = dmGeneralizatioFact.getSuperclass().getName();
				String dmSubclass = dmGeneralizatioFact.getSubclass().getName();

				String sbvrRelationship = sbvrFact.getRelationship();
				String sbvrSuperclass = null;
				String sbvrSubclass = null;

				if ("is extended by".equals(sbvrRelationship)) {
					sbvrSuperclass = sbvrFact.getTerm1().getTerm();
					sbvrSubclass = sbvrFact.getTerm2().getTerm();

					if (dmSuperclass.equals(sbvrSuperclass) && dmSubclass.equals(sbvrSubclass)) {
						matching = true;
					}

				} else if ("extends".equals(sbvrRelationship)) {
					sbvrSubclass = sbvrFact.getTerm1().getTerm();
					sbvrSuperclass = sbvrFact.getTerm2().getTerm();

					if (dmSuperclass.equals(sbvrSuperclass) && dmSubclass.equals(sbvrSubclass)) {
						matching = true;
					}
				}
			}
			if (!matching) {
				validationResults.add(new ValidationResult(false,
						message(DMToSBVRValidationMessages.DOMAIN_MODEL_ELEMENT_HAS_NO_MATCHING_BR,
								dmGeneralizatioFact.toString())));
			}
		}
		return validationResults;
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
