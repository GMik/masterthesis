package com.vp.plugin.utils.validation.bp.dataobjects;

import java.util.Set;

import com.vp.plugin.connectors.domainmodel.IVPDomainModelConnector;
import com.vp.plugin.connectors.domainmodel.VPDomainModelConnector;
import com.vp.plugin.exceptions.ValidationException;
import com.vp.plugin.model.IClass;
import com.vp.plugin.model.IModelElement;
import com.vp.plugin.utils.modelelements.ModelElementUtil;
import com.vp.plugin.utils.validation.ValidationResult;

public class BPObjectStandardValidationStrategy implements IBPObjectValidationStrategy {

	@Override
	public ValidationResult validate(IModelElement modelElement) throws ValidationException {
		String[] elementWords = ModelElementUtil.fetchWords(modelElement);
		if (elementWords.length != 1) {
			return new ValidationResult(false,
					message(IBPDataObjectValidationMessages.DATA_OBJECT_FORMAT_DOES_NOT_MEET_STRUCTURE_REQUIREMENTS,
							modelElement.getName()));
		} else {
			String givenClass = elementWords[0].trim();
			IVPDomainModelConnector domainModelConnector = new VPDomainModelConnector();
			Set<IClass> classes = domainModelConnector.fetchClassesWithGivenName(givenClass);

			if (classes.isEmpty()) {
				return new ValidationResult(true,
						message(IBPDataObjectValidationMessages.CLASS_NOT_DEFINED, givenClass));
			} else if (classes.size() == 1) {
				return new ValidationResult(true, message(IBPDataObjectValidationMessages.SUCCESS, givenClass));
			} else {
				return new ValidationResult(true,
						message(IBPDataObjectValidationMessages.SUCCESS_BUT_VERIFICATION_NEEDED, givenClass));
			}
		}
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
