package com.vp.plugin.connectors.businessrules.utils;

import java.util.Objects;

import com.vp.plugin.connectors.businessrules.SBVRFileElementsContainer;
import com.vp.plugin.connectors.businessrules.innermodel.SBVRBusinessRule;

public class BRToFactMatcher {

	public static void match(SBVRBusinessRule sbvrBusinessRule, SBVRFileElementsContainer container) {
		Objects.requireNonNull(sbvrBusinessRule);

		// SBVRLogicalOperations.NEG.getValue();
		SBVRLogicalOperations.AND.getValue();
		SBVRLogicalOperations.OR.getValue();
		SBVRLogicalOperations.IF.getValue();
		SBVRLogicalOperations.THEN.getValue();

		// if
		// (sbvrBusinessRule.getText().contains(SBVRLogicalOperations.NEG.getValue()))
		// {
		//
		// }

		if (sbvrBusinessRule.getText().contains(SBVRLogicalOperations.AND.getValue())) {
			String[] parts = sbvrBusinessRule.getText().split(SBVRLogicalOperations.AND.getValue());
			// SBVRBusinessRulePart firstPart = new SBVRBusinessRulePart();
			//
			// SBVRBusinessRulePart secondPart = new SBVRBusinessRulePart();

		}

		if (sbvrBusinessRule.getText().contains(SBVRLogicalOperations.OR.getValue())) {
			String[] parts = sbvrBusinessRule.getText().split(SBVRLogicalOperations.OR.getValue());
		}

		if (sbvrBusinessRule.getText().contains(SBVRLogicalOperations.IF.getValue())
				&& sbvrBusinessRule.getText().contains(SBVRLogicalOperations.THEN.getValue())) {

		}

		if (sbvrBusinessRule.getText().contains(SBVRLogicalOperations.IF.getValue())) {

		}

	}

}
