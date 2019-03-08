package com.qa.persistence.repository;

import static javax.transaction.Transactional.TxType.REQUIRED;
import static javax.transaction.Transactional.TxType.SUPPORTS;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import com.qa.persistence.domain.Body;
import com.qa.util.JSONUtil;

@Transactional(SUPPORTS)
public class BodyDBRepository implements BodyRepository {

	@PersistenceContext(unitName = "primary")
	private EntityManager manager;

	@Inject
	private JSONUtil util;

	@Override
	@Transactional(REQUIRED)
	public String getNextState(double timeStep) {
		List<Body> system = (ArrayList<Body>) manager.createQuery("SELECT b FROM Body b").getResultList();
		BodyPhysics.simulateStep(system, timeStep);
		String image = "";
		return image;
	}

	@Override
	@Transactional(REQUIRED)
	public String createBody(String body) {
		Body aBody = util.getObjectForJson(body, Body.class);
		manager.persist(aBody);
		return "{\"message\": \"body has been sucessfully added\"}";
	}

	@Override
	@Transactional(REQUIRED)
	public String updateBody(Long id, String body) {
		Body anBody = util.getObjectForJson(body, Body.class);
		if (manager.contains(manager.find(Body.class, id))) {
			// BIG TODO
			return "{\"message\": \"body has been sucessfully updated\"}";
		}
		return "{\"message\": \"this body does not exist\"}";
	}

	@Override
	@Transactional(REQUIRED)
	public String removeBody(Long id) {
		if (manager.contains(manager.find(Body.class, id))) {
			manager.remove(manager.find(Body.class, id));
			return "{\"message\": \"body has been sucessfully deleted\"}";
		}
		return "{\"message\": \"this body does not exist\"}";
	}

	@Override
	public String getAllBodies() {
		return util.getJsonForObject((Collection<Body>) manager.createQuery("SELECT b FROM Body b").getResultList());
	}

	@Override
	public String getABody(Long id) {
		return util.getJsonForObject(manager.find(Body.class, id));
	}
}
