package com.vp.plugin.utils.validation.bp.events;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.vp.plugin.connectors.domainmodel.IVPDomainModelConnector;
import com.vp.plugin.connectors.domainmodel.StateMachine;
import com.vp.plugin.connectors.domainmodel.VPDomainModelConnector;
import com.vp.plugin.model.IBPEndEvent;
import com.vp.plugin.model.IBPIntermediateEvent;
import com.vp.plugin.model.IBPStartEvent;
import com.vp.plugin.model.IModelElement;
import com.vp.plugin.model.IState2;
import com.vp.plugin.utils.modelelements.ModelElementUtil;

public class BPEventRestrictiveValidationStrategy implements IBPEventValidationStrategy {

	/**
	 * TODO - REFACTOR!!!
	 */
	@Override
	public BPEventValidationResult validate(IModelElement diagramElement) {
		String[] eventWords = ModelElementUtil.fetchWords(diagramElement);
		String statePart = statePart(eventWords);
		// if (diagramElement.getParent() == null) {
		// return new BPEventValidationResult(false,
		// BPEventValidationMessages.EVENT_ELEMENT_DOES_NOT_HAVE_ANY_PARENT_MODEL);
		// }

		// 0. less than dwo words in event.
		if (eventWords.length < 2) {
			return new BPEventValidationResult(false,
					message(BPEventValidationMessages.EVENT_FORMAT_DOES_NOT_MEET_STRUCTURE_REQUIREMENTS,
							diagramElement.getName()));
		}

		String givenClassName = eventWords[0];
		IVPDomainModelConnector domainModelConnector = new VPDomainModelConnector();
		List<StateMachine> classesStateMachines = domainModelConnector.fetchStateMachinesFor(givenClassName);

		// 1. given class does not exist
		if (classesStateMachines.size() == 0) {
			return new BPEventValidationResult(false,
					message(BPEventValidationMessages.CLASS_DOES_NOT_EXIST, givenClassName));
		} else if (classesStateMachines.size() == 1) {

			List<IState2> states = classesStateMachines.get(0).getStates();
			if (states.isEmpty()) {
				// 2.0 exist ONE class, but without defined state machine with
				// given state
				return new BPEventValidationResult(false, message(
						BPEventValidationMessages.CLASS_EXIST_BUT_WITHOUT_DEFINED_STATE_MACHINE, givenClassName));
			} else {
				String[] eventStateWords = Arrays.copyOfRange(eventWords, 1, eventWords.length);

				for (IState2 state : states) {
					String[] stateWords = ModelElementUtil.fetchWords(state);
					if (stateWords.length != eventStateWords.length) {
						continue;
					}

					boolean areEquals = true;
					for (int i = 0; i < stateWords.length; i++) {
						if (!stateWords[i].equals(eventStateWords[i])) {
							areEquals = false;
						}
					}

					// 2.1 SUCCESS - There is only one state machine with
					// defined (given) state
					if (areEquals) {
						return new BPEventValidationResult(true,
								message(BPEventValidationMessages.SUCCESS, givenClassName, statePart));
					}
				}

				// 2.2 There is class with state machine, but without given
				// state
				return new BPEventValidationResult(false,
						message(BPEventValidationMessages.CLASS_EXIST_BUT_STATE_MACHINE_WITHOUT_GIVEN_STATE,
								givenClassName, statePart));

			}

		} else {

			List<StateMachine> stateMachinesWithStateOfAName = new ArrayList<>();
			for (StateMachine stateMachine : classesStateMachines) {
				if (stateMachine.containsStateWithWords(eventWords)) {
					stateMachinesWithStateOfAName.add(stateMachine);
				}
			}

			if (stateMachinesWithStateOfAName.isEmpty()) {
				return new BPEventValidationResult(false, message(
						BPEventValidationMessages.CLASS_EXIST_BUT_STATE_MACHINE_WITHOUT_GIVEN_STATE, givenClassName));
			} else {
				return new BPEventValidationResult(true,
						message(BPEventValidationMessages.SUCCESS_BUT_VERIFICATION_NEEDED, givenClassName, statePart));
			}

		}

	}

	private List<IModelElement> parentModelEvents(IModelElement diagramElement) {
		IModelElement[] diagramElements = diagramElement.getParent().toChildArray();
		List<IModelElement> diagramElementsWhichAreTypeOfEvent = new ArrayList<>();
		for (IModelElement element : diagramElements) {
			if (element instanceof IBPStartEvent || element instanceof IBPEndEvent
					|| element instanceof IBPIntermediateEvent) {
				diagramElementsWhichAreTypeOfEvent.add(element);
			}
		}
		return diagramElementsWhichAreTypeOfEvent;
	}

	private String message(String message, String... params) {
		String result = new String(message);
		int position = 0;
		for (String param : params) {
			result = result.replace("{" + (position++) + "}", param);
		}
		return result;
	}

	public String statePart(String eventWords[]) {
		if (eventWords == null || eventWords.length == 1) {
			return "";
		} else {
			StringBuilder contentBuilder = new StringBuilder();
			for (int i = 1; i < eventWords.length; i++) {
				contentBuilder.append(eventWords[i]).append(" ");
			}
			return contentBuilder.toString().trim();
		}
	}

	public static void main(String[] a) {
		String s = StringUtils.normalizeSpace(" Something   is  done  ");

		System.out.println(s);

	}

}
