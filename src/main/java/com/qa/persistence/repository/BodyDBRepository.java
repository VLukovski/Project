package com.qa.persistence.repository;

import static javax.transaction.Transactional.TxType.REQUIRED;
import static javax.transaction.Transactional.TxType.SUPPORTS;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Collection;
import java.util.List;

import javax.imageio.ImageIO;
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
	public String getNextState(double timeStep) throws IOException {
		List<Body> system = (ArrayList<Body>) manager.createQuery("SELECT b FROM Body b").getResultList();
		BodyPhysics.simulateStep(system, timeStep);

		BufferedImage image = new BufferedImage(500, 500, BufferedImage.TYPE_INT_ARGB);
		Graphics2D gfx = image.createGraphics();
		gfx.translate(250, 250);
		gfx.setBackground(new Color(0, 0, 0));
		gfx.setColor(new Color(0, 0, 255));
		for (Body body : system) {
			gfx.fillOval((int) Math.round(body.getPosX()), (int) Math.round(body.getPosY()), 2, 2);
		}
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		ImageIO.write(image, "jpg", outputStream);
		return Base64.getEncoder().encodeToString(outputStream.toByteArray());
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
		Body aBody = util.getObjectForJson(body, Body.class);
		if (manager.contains(manager.find(Body.class, id))) {
			Body dbBody = manager.find(Body.class, id);
			// Mostly useless for this case
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

	public void setUtil(JSONUtil util) {
		this.util = util;
	}

	public void setManager(EntityManager manager) {
		this.manager = manager;
	}

}
