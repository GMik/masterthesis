package com.vp.plugin.connectors.domainmodel;

import java.util.ArrayList;
import java.util.List;

import com.vp.plugin.model.IClass;
import com.vp.plugin.model.IState2;
import com.vp.plugin.utils.modelelements.ModelElementUtil;

public class StateMachine {

	private String id;

	private IClass forClass;

	private List<IState2> states;

	public StateMachine(String id, IClass forClass, List<IState2> states) {
		this.id = id;
		this.forClass = forClass;
		this.states = states;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public IClass getForClass() {
		return forClass;
	}

	public void setForClass(IClass forClass) {
		this.forClass = forClass;
	}

	public List<IState2> getStates() {
		return states;
	}

	public void setStates(List<IState2> states) {
		this.states = states;
	}

	public List<String[]> getStatesNames() {
		List<String[]> names = new ArrayList<>();
		if (this.states != null) {
			for (IState2 state : states) {
				names.add(ModelElementUtil.fetchWords(state));
			}
		}
		return names;
	}

	public boolean containsStateWithWords(String[] stateWords) {
		List<String[]> statesNames = getStatesNames();
		for (String[] nameWords : statesNames) {
			if (nameWords.length != stateWords.length) {
				continue;
			}
			for (int i = 0; i < nameWords.length; i++) {
				if (!nameWords[i].equals(stateWords[i])) {
					break;
				}
			}
			return true;
		}
		return false;
	}

}
