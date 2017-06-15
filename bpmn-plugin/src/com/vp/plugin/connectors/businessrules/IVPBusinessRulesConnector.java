package com.vp.plugin.connectors.businessrules;

import java.util.List;

import com.vp.plugin.model.IBusinessRule;

public interface IVPBusinessRulesConnector {

	List<IBusinessRule> fetchVPBusinessRules();
}
