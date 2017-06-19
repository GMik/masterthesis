package com.vp.plugin.connectors.businessrules;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import com.vp.plugin.utils.validation.ValidationResult;
import com.vp.plugin.utils.validation.br.BRValidationMessages;

public class SBVRFileConnector implements ISBVRFileConnector {

	private final String filePath = "C:\\Users\\Grzegorz\\AppData\\Roaming\\VisualParadigm\\plugins\\bpmn-plugin\\src\\testdata\\sbvrdata.txt";;

	@Override
	public SBVRFileElementsContainer loadSBVRData() throws IOException {
		SBVRFileElementsContainer container = new SBVRFileElementsContainer();

		Map<String, SBVRTerm> terms = loadTerms();
		fillContainerWithTerms(terms, container);
		loadAndfillContainerWithAttributeTerms(terms, container);

		BufferedReader bufferedReader = new BufferedReader(new FileReader(filePath));
		try {

			String line = bufferedReader.readLine();
			int lineNumber = 1;
			while (line != null) {

				if (line.isEmpty()) {
					line = bufferedReader.readLine();
					continue;
				}

				String[] lineData = line.split(SBVRFileConstants.TYPE_AND_DATA_SEPARATOR);

				String type = lineData[SBVRFileConstants.TYPE_POSITION];
				String data = lineData[SBVRFileConstants.DATA_POSITION];
				String[] splittedData = data.split(SBVRFileConstants.DATA_SEPARATOR);

				if (data.equals("Task;is owned by;Story")) {
					System.out.println();
				}

				switch (type) {
				case SBVRFileConstants.TYPE_TERM:
					// do nothing - previously done
					break;
				case SBVRFileConstants.TYPE_CLASS_ATTRIBUTE_TERM:
					// do nothing - previously done
					break;
				case SBVRFileConstants.TYPE_FACT: {
					String term1 = splittedData[0];
					String term1Role = null;
					if (term1.contains(" as ")) {
						String[] term1AndRole = term1.split(" as ");
						term1 = term1AndRole[0];
						term1Role = term1AndRole[1];
					}

					String term2 = splittedData[2];
					String term2Role = null;
					if (term2.contains(" as ")) {
						String[] term2AndRole = term2.split(" as ");
						term2 = term2AndRole[0];
						term2Role = term2AndRole[1];
					}

					String relationship = splittedData[1];

					SBVRTerm sbvrTerm1 = terms.get(term1);
					SBVRTerm sbvrTerm2 = terms.get(term2);

					if (sbvrTerm1 == null || sbvrTerm2 == null) {
						container.add(new ValidationResult(false,
								message(BRValidationMessages.FACT_IS_BASED_ON_TERM_WHICH_DOES_NOT_EXIST,
										sbvrTerm1.getTerm(), sbvrTerm2.getTerm())));
					} else {
						RelationshipType relationshipType = RelationshipSelector.retrieveFrom(relationship);

						container.addFact(new SBVRFact(sbvrTerm1, term1Role, relationship, relationshipType, sbvrTerm2,
								term2Role, retrieveWords(splittedData)));
					}

					break;
				}

				case SBVRFileConstants.TYPE_CLASS_CHARACTERISTIC_FACT: {

					String term1 = splittedData[0];
					String relation = splittedData[1];
					String term2 = splittedData[2];

					SBVRClassCharacteristicTerm classCharacteristicTerm = findAttributeTerm(container, term1, term2);

					if (classCharacteristicTerm == null) {
						container.add(new ValidationResult(false,
								message(BRValidationMessages.ATTRIBUTE_FACT_INCORRECT, term1, term2, relation)));
					}

					// term1 has term2(attr)
					else if (classCharacteristicTerm.getTerm().getTerm().equals(term1)
							&& ClassAttributeRelationship.HAS.getValue().equals(relation)
							&& classCharacteristicTerm.getCharacteristicTerm().equals(term2)) {

						SBVRClassCharacteristicFact attributeFact = new SBVRClassCharacteristicFact(
								classCharacteristicTerm, ClassAttributeRelationship.HAS, null,
								CharacteristicKindOf.ATTRIBUTE, retrieveWords(splittedData));
						container.addAttributeFact(attributeFact);
					}
					// term1(attr) is property of term2
					else if (classCharacteristicTerm.getCharacteristicTerm().equals(term1)
							&& ClassAttributeRelationship.IS_PROPERTY_OF.getValue().equals(relation)
							&& classCharacteristicTerm.getTerm().getTerm().equals(term2)) {
						SBVRClassCharacteristicFact attributeFact = new SBVRClassCharacteristicFact(
								classCharacteristicTerm, ClassAttributeRelationship.IS_PROPERTY_OF, null,
								CharacteristicKindOf.ATTRIBUTE, retrieveWords(splittedData));
						container.addAttributeFact(attributeFact);
					}

					// term1 has state term2(attr)
					else if (classCharacteristicTerm.getTerm().getTerm().equals(term1)
							&& ClassStateRelationship.HAS_STATE.getValue().equals(relation)
							&& classCharacteristicTerm.getCharacteristicTerm().equals(term2)) {

						SBVRClassCharacteristicFact stateFact = new SBVRClassCharacteristicFact(classCharacteristicTerm,
								null, ClassStateRelationship.HAS_STATE, CharacteristicKindOf.STATE,
								retrieveWords(splittedData));
						container.addAttributeFact(stateFact);
					}
					// term1(attr) is state of term2
					else if (classCharacteristicTerm.getCharacteristicTerm().equals(term1)
							&& ClassStateRelationship.IS_STATE_OF.getValue().equals(relation)
							&& classCharacteristicTerm.getTerm().getTerm().equals(term2)) {
						SBVRClassCharacteristicFact stateFact = new SBVRClassCharacteristicFact(classCharacteristicTerm,
								null, ClassStateRelationship.IS_STATE_OF, CharacteristicKindOf.STATE,
								retrieveWords(splittedData));
						container.addAttributeFact(stateFact);
					}

					else {
						container.add(new ValidationResult(false,
								message(BRValidationMessages.ATTRIBUTE_FACT_INCORRECT, term1, term2, relation)));
					}

				}
					break;
				case SBVRFileConstants.TYPE_BUSINESS_RULE:

					String text = data;
					Object matchingFact = retrieveFact(text, container);

					SBVRBusinessRulePart businessRulePart = null;
					if (matchingFact instanceof SBVRFact) {
						businessRulePart = new SBVRBusinessRulePart((SBVRFact) matchingFact, null);
						SBVRBusinessRule businessRule = new SBVRBusinessRule(text,
								Collections.singletonList(businessRulePart));
						container.add(businessRule);

						break;
					}

					else if (matchingFact instanceof SBVRClassCharacteristicFact) {
						businessRulePart = new SBVRBusinessRulePart(null, (SBVRClassCharacteristicFact) matchingFact);
						SBVRBusinessRule businessRule = new SBVRBusinessRule(text,
								Collections.singletonList(businessRulePart));
						container.add(businessRule);

						break;
					} else {
						container.add(new ValidationResult(false,
								message(BRValidationMessages.BR_IS_NOT_BASES_ON_A_FACT, text)));
					}

					break;
				}
				lineNumber++;
				line = bufferedReader.readLine();
			}

		} finally {
			bufferedReader.close();
		}

		return container;
	}

	private String[] retrieveWords(String[] array) {
		List<String> temp = new ArrayList<>();
		for (String s : array) {
			String[] partWords = s.split(" ");
			for (String pw : partWords) {
				temp.add(pw);
			}
		}
		return temp.toArray(new String[temp.size()]);
	}

	private Object retrieveFact(String text, SBVRFileElementsContainer container) {

		if (text.equals("Task is owned by Story")) {
			System.out.println();
		}

		String[] textSplitted = text.split(" ");

		for (SBVRFact fact : container.getFacts()) {

			String[] factWords = fact.getFactWords();

			// must be equal to factWords length
			int matchingWordsCounter = 0;
			if (factWords.length <= textSplitted.length) {
				int factWordsIndex = 0;
				for (int i = 0; i < textSplitted.length; i++) {
					if (factWords[factWordsIndex].equals(textSplitted[i])) {
						matchingWordsCounter++;
						factWordsIndex++;
					}
				}
			}
			if (matchingWordsCounter == factWords.length) {
				return fact;
			}
		}

		for (SBVRClassCharacteristicFact characteristicFact : container.getCharacteristicFacts()) {
			String[] factWords = characteristicFact.getFactWords();
			int matchingWordsCounter = 0;
			if (factWords.length <= textSplitted.length) {
				int factWordsIndex = 0;
				for (int i = 0; i < textSplitted.length; i++) {
					if (factWords[factWordsIndex].equals(textSplitted[i])) {
						matchingWordsCounter++;
						factWordsIndex++;
					}
				}
			}
			if (matchingWordsCounter == factWords.length) {
				return characteristicFact;
			}
		}

		return null;

	}

	private SBVRClassCharacteristicTerm findAttributeTerm(SBVRFileElementsContainer container, String possibleTerm1,
			String possibleTerm2) {

		for (int i = 0; i < container.getCharacteristicTerms().size(); i++) {

			SBVRClassCharacteristicTerm _attributeTerm = container.getCharacteristicTerms().get(i);

			if (_attributeTerm.getTerm().getTerm().equals(possibleTerm1)
					&& _attributeTerm.getCharacteristicTerm().equals(possibleTerm2)) {
				return _attributeTerm;
			}

			if (_attributeTerm.getTerm().getTerm().equals(possibleTerm2)
					&& _attributeTerm.getCharacteristicTerm().equals(possibleTerm1)) {
				return _attributeTerm;
			}
		}
		return null;
	}

	private SBVRClassCharacteristicTerm createClassAttributeTerm(String attribute, SBVRTerm term,
			CharacteristicKindOf characteristicKindOf) {
		return new SBVRClassCharacteristicTerm(term, attribute, characteristicKindOf);
	}

	private String message(String message, String... params) {
		String result = new String(message);
		int position = 0;
		for (String param : params) {
			result = result.replace("{" + (position++) + "}", param);
		}
		return result;
	}

	private Map<String, SBVRTerm> loadTerms() throws IOException {

		Set<SBVRTerm> terms = new HashSet<>();
		BufferedReader bufferedReader = new BufferedReader(new FileReader(filePath));
		try {

			String line = bufferedReader.readLine();
			while (line != null) {

				if (line.isEmpty()) {
					line = bufferedReader.readLine();
					continue;

				}

				String[] lineData = line.split(SBVRFileConstants.TYPE_AND_DATA_SEPARATOR);
				String type = lineData[SBVRFileConstants.TYPE_POSITION];

				if (SBVRFileConstants.TYPE_TERM.equals(type)) {
					String data = lineData[SBVRFileConstants.DATA_POSITION];
					terms.add(new SBVRTerm(data));
				}

				line = bufferedReader.readLine();
			}
		} finally {
			bufferedReader.close();
		}

		Map<String, SBVRTerm> termsMap = new HashMap<>();
		for (SBVRTerm sbvrTerm : terms) {
			termsMap.put(sbvrTerm.getTerm(), sbvrTerm);
		}
		return termsMap;
	}

	private void loadAndfillContainerWithAttributeTerms(Map<String, SBVRTerm> terms,
			SBVRFileElementsContainer container) throws IOException {

		BufferedReader bufferedReader = new BufferedReader(new FileReader(filePath));
		try {

			String line = bufferedReader.readLine();
			while (line != null) {

				if (line.isEmpty()) {
					line = bufferedReader.readLine();
					continue;
				}

				String[] lineData = line.split(SBVRFileConstants.TYPE_AND_DATA_SEPARATOR);
				String type = lineData[SBVRFileConstants.TYPE_POSITION];
				String data = lineData[SBVRFileConstants.DATA_POSITION];
				String[] splittedData = data.split(SBVRFileConstants.DATA_SEPARATOR);

				if (SBVRFileConstants.TYPE_CLASS_ATTRIBUTE_TERM.equals(type)) {
					String term = splittedData[0];
					String characteristic = splittedData[1];
					SBVRTerm sbvrTerm = terms.get(term);
					if (sbvrTerm == null) {
						container.add(new ValidationResult(false,
								message(BRValidationMessages.CLASS_ATTR_OR_STATE_TERM_IS_NOT_BASED_ON_TERM,
										characteristic, term)));
					} else {
						container.addAttributeTerm(
								createClassAttributeTerm(characteristic, sbvrTerm, CharacteristicKindOf.ATTRIBUTE));
					}
				}

				if (SBVRFileConstants.TYPE_CLASS_STATE_TERM.equals(type)) {
					String term = splittedData[0];
					String characteristic = splittedData[1];
					SBVRTerm sbvrTerm = terms.get(term);
					if (sbvrTerm == null) {
						container.add(new ValidationResult(false,
								message(BRValidationMessages.CLASS_ATTR_OR_STATE_TERM_IS_NOT_BASED_ON_TERM,
										characteristic, term)));
					} else {
						container.addAttributeTerm(
								createClassAttributeTerm(characteristic, sbvrTerm, CharacteristicKindOf.STATE));
					}
				}

				line = bufferedReader.readLine();
			}
		} finally {
			bufferedReader.close();
		}

	}

	private void fillContainerWithTerms(Map<String, SBVRTerm> terms, SBVRFileElementsContainer container) {
		for (Entry<String, SBVRTerm> entry : terms.entrySet()) {
			container.addTerm(entry.getValue());
		}
	}
}
