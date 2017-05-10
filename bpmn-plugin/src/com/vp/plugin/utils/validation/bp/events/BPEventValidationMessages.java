package com.vp.plugin.utils.validation.bp.events;

public class BPEventValidationMessages {

	public static final String EVENT_FORMAT_DOES_NOT_MEET_STRUCTURE_REQUIREMENTS = "Event: {0} element doest not meet structure (Class + state from its state machine) requirements.";

	public static final String CLASS_DOES_NOT_EXIST = "Error occured during validation of BPM element. Class: {0} for selected event does not exist in domain model or is not currently used in any model. ";

	public static final String CLASS_EXIST_BUT_WITHOUT_DEFINED_STATE_MACHINE = "Error occured during validation of BPM element. Class: {0} for selected event exists, but without defined state machine or with state machine, which currently does not contain any state.";

	public static final String CLASS_EXIST_BUT_STATE_MACHINE_WITHOUT_GIVEN_STATE = "Error occured during validation of BPM element. Class {0} for selected event exists with defined state machine, but it unfortunately does not contain selected state: {1}";

	public static final String SUCCESS = "For given event state machine class: {0} with given state: {1} was found.";

	public static final String EVENT_ELEMENT_DOES_NOT_HAVE_ANY_PARENT_MODEL = "Error occured during validation of BPM element. Given element doest not have any parent model.";

	public static final String SUCCESS_BUT_VERIFICATION_NEEDED = "For given event state machine class: {0} with given state: {1} was found, but more than one state machine for a given class was created in this project. Please verify this information.";

}
