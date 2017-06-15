package com.vp.plugin.repositories;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import com.vp.plugin.connectors.businessrules.ISBVRFileConnector;
import com.vp.plugin.model.BusinessRule;

public class BusinessRulesRepositoryImpl implements BusinessRulesRepository {

	/**
	 * e.g. "c:/plugin/repo/br.txt"
	 */
	private String filepath;

	private ISBVRFileConnector sBVRFileConnector;

	@Override
	public void create(BusinessRule businessRule) {
		String content = "Hello World !!";
		try {
			Files.write(Paths.get("filepath"), content.getBytes());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	public BusinessRule read(long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void update(BusinessRule t) {
		// TODO Auto-generated method stub

	}

	@Override
	public void delete(long id) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<BusinessRule> readAll() {
		// TODO Auto-generated method stub
		return null;
	}

}
