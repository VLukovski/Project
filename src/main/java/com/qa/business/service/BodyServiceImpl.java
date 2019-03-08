package com.qa.business.service;

import javax.inject.Inject;

import com.qa.persistence.repository.BodyRepository;
import com.qa.util.JSONUtil;

public class BodyServiceImpl implements BodyService {
	
	@Inject
	private BodyRepository repo;

	@Inject
	private JSONUtil util;

	@Override
	public String getNextState(double timeStep) {
		return repo.getNextState(timeStep);
	}

	@Override
	public String createBody(String body) {
		return repo.createBody(body);
	}

	@Override
	public String updateBody(Long id, String body) {
		return repo.updateBody(id, body);
	}

	@Override
	public String removeBody(Long id) {
		return repo.removeBody(id);
	}

	@Override
	public String getAllBodies() {
		return repo.getAllBodies();
	}

	@Override
	public String getABody(Long id) {
		return repo.getABody(id);
	}

}
