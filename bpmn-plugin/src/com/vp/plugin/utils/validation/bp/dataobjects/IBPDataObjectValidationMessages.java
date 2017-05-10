package com.vp.plugin.utils.validation.bp.dataobjects;

public class IBPDataObjectValidationMessages {
	public static final String DATA_OBJECT_FORMAT_DOES_NOT_MEET_STRUCTURE_REQUIREMENTS = "Error occured during validation of Data Object: {0} element doest not meet structure requirements (should contain only one word with class name)";

	public static final String SUCCESS = "Success - the class: {0} is defined in domain model for this Data Object";

	public static final String CLASS_NOT_DEFINED = "Error occured during validation of Data Object: {0}. The class is not defined in domain model.";

	public static final String SUCCESS_BUT_VERIFICATION_NEEDED = "For given Data Object: {0} a class in domain model was found, but with more than one occurence. Please verify this information.";

}
