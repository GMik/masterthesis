package com.vp.plugin.connectors.businessrules;

import java.util.ArrayList;
import java.util.List;

import com.vp.plugin.ApplicationManager;
import com.vp.plugin.model.IBusinessRule;
import com.vp.plugin.model.IModel;
import com.vp.plugin.model.IModelElement;
import com.vp.plugin.model.factory.IModelElementFactory;

public class VPBusinessRulesConnector implements IVPBusinessRulesConnector {

	public static final String VP_MODEL_NAME_BUSINESS_RULES = "Business Rules";

	@Override
	public List<IBusinessRule> fetchVPBusinessRules() {

		List<IBusinessRule> result = new ArrayList<IBusinessRule>();
		IModelElement[] modelElemnts = ApplicationManager.instance().getProjectManager().getProject()
				.toAllLevelModelElementArray(IModelElementFactory.MODEL_TYPE_MODEL);

		for (IModelElement modelElement : modelElemnts) {
			IModel model = (IModel) modelElement;

			String kindOfModel = model.getName();
			if (kindOfModel.equals(VP_MODEL_NAME_BUSINESS_RULES)) {

				IModelElement[] businessRules = model.toChildArray();
				for (IModelElement businessRuleElement : businessRules) {
					result.add((IBusinessRule) businessRuleElement);
				}
			}
		}
		return result;
	}

}
