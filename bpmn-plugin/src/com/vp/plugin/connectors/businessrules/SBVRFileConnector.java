package com.vp.plugin.connectors.businessrules;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.vp.plugin.utils.validation.ValidationResult;
import com.vp.plugin.utils.validation.br.BRValidationMessages;

public class SBVRFileConnector implements ISBVRFileConnector {

	private final String filePath = "c://lines.txt";;

	private Map<String, SBVRTerm> loadTerms() throws IOException {

		// TODO: with only one stream
		Set<SBVRTerm> terms = new HashSet<>();
		Stream<String> stream = Files.lines(Paths.get(filePath));
		terms = stream.filter(line -> line.startsWith(SBVRFileConstants.TYPE_TERM)).map(n -> new SBVRTerm(n))
				.collect(Collectors.toSet());
		stream.close();

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
				String[] lineData = line.split(SBVRFileConstants.TYPE_AND_DATA_SEPARATOR);
				String type = lineData[SBVRFileConstants.TYPE_POSITION];
				String data = lineData[SBVRFileConstants.DATA_POSITION];
				String[] splittedData = data.split(SBVRFileConstants.DATA_SEPARATOR);

				if (SBVRFileConstants.TYPE_CLASS_ATTRIBUTE_TERM.equals(type)) {
					String term = splittedData[0];
					String attribute = splittedData[1];
					SBVRTerm sbvrTerm = terms.get(term);
					if (sbvrTerm == null) {
						container.add(new ValidationResult(false,
								message(BRValidationMessages.CLASS_ATTR_TERM_IS_NOT_BASED_ON_TERM, attribute, term)));
					} else {
						container.addAttributeTerm(createClassAttributeTerm(attribute, sbvrTerm));
					}
				}
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

				String[] lineData = line.split(SBVRFileConstants.TYPE_AND_DATA_SEPARATOR);

				String type = lineData[SBVRFileConstants.TYPE_POSITION];
				String data = lineData[SBVRFileConstants.DATA_POSITION];
				String[] splittedData = data.split(SBVRFileConstants.DATA_SEPARATOR);

				switch (type) {
				case SBVRFileConstants.TYPE_TERM:
					// do nothing - previously done
					break;
				case SBVRFileConstants.TYPE_CLASS_ATTRIBUTE_TERM:
					// do nothing - previously done
					break;
				case SBVRFileConstants.TYPE_FACT: {
					String term1 = splittedData[0];
					String association = splittedData[1];
					String term2 = splittedData[2];

					SBVRTerm sbvrTerm1 = terms.get(term1);
					SBVRTerm sbvrTerm2 = terms.get(term2);

					if (sbvrTerm1 == null || sbvrTerm2 == null) {
						container.add(new ValidationResult(false,
								message(BRValidationMessages.FACT_IS_BASED_ON_TERM_WHICH_DOES_NOT_EXIST,
										sbvrTerm1.getTerm(), sbvrTerm2.getTerm())));
					} else {
						container.addFact(new SBVRFact(sbvrTerm1, association, sbvrTerm2));
					}

					break;
				}

				case SBVRFileConstants.TYPE_CLASS_ATTRIBUTE_FACT: {

					String term1 = splittedData[0];
					String relation = splittedData[1];
					String term2 = splittedData[2];

					SBVRClassAttributeTerm classAttributeTerm = findAttributeTerm(container, term1, term2);

					if (classAttributeTerm == null) {
						container.add(new ValidationResult(false,
								message(BRValidationMessages.ATTRIBUTE_FACT_INCORRECT, term1, term2, relation)));
					}
					// term1 has term2(attr)
					else if (classAttributeTerm.getTerm().getTerm().equals(term1)
							&& ClassAttributeRelationship.HAS.getValue().equals(relation)
							&& classAttributeTerm.getAttributeTerm().equals(term2)) {

						// TODO
					}
					// term1(attr) is property of term2
					else if (classAttributeTerm.getAttributeTerm().equals(term1)
							&& ClassAttributeRelationship.IS_PROPERTY_OF.getValue().equals(relation)
							&& classAttributeTerm.getTerm().getTerm().equals(term2)) {

						// TODO
					} else {
						container.add(new ValidationResult(false,
								message(BRValidationMessages.ATTRIBUTE_FACT_INCORRECT, term1, term2, relation)));
					}

				}
					break;
				case SBVRFileConstants.TYPE_BUSINESS_RULE:

					break;
				}
				lineNumber++;
			}

		} finally {
			bufferedReader.close();
		}

		return container;
	}

	private SBVRClassAttributeTerm findAttributeTerm(SBVRFileElementsContainer container, String possibleTerm1,
			String possibleTerm2) {

		for (int i = 0; i < container.getAttributeTerms().size(); i++) {

			SBVRClassAttributeTerm _attributeTerm = container.getAttributeTerms().get(i);

			if (_attributeTerm.getTerm().getTerm().equals(possibleTerm1)
					&& _attributeTerm.getAttributeTerm().equals(possibleTerm2)) {
				return _attributeTerm;
			}

			if (_attributeTerm.getTerm().getTerm().equals(possibleTerm2)
					&& _attributeTerm.getAttributeTerm().equals(possibleTerm1)) {
				return _attributeTerm;
			}
		}
		return null;
	}

	private SBVRClassAttributeTerm createClassAttributeTerm(String attribute, SBVRTerm term) {
		return new SBVRClassAttributeTerm(term, attribute);
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
