package com.qa.repositorytest;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import com.qa.persistence.domain.Body;
import com.qa.persistence.repository.BodyPhysics;




public class PhysicsTest {

	Body b1;
	Body b2;
	Body b3;
	Body b4;
	List<Body> bodies;
	
	@Before
	public void setup() {
		b1 = new Body(100., 50., 5., 3., 10.);
		b2 = new Body(70., 10., 5., 7., 13.);
		b3 = new Body(20., 30., 2., 11., 15.);
		b4 = new Body(30., 50., 10., 7., 17.);
		bodies = new ArrayList<Body>();
		bodies.add(b1);
		bodies.add(b2);
		bodies.add(b3);
		bodies.add(b4);
	}

	@Test
	public void addForcesTest() {
		BodyPhysics.addForces(b1, b2);
		assertEquals(b1.getForceX(), -b2.getForceX(), 0.01);
		assertEquals((130 * (10. - 50)) / (50 * 50 * 50), b1.getForceY(), 0.01);
	}
	
	@Test
	public void simulateStepTest() {
		BodyPhysics.simulateStep(bodies, 1);
		assertEquals(105, bodies.get(0).getPosX(), 0.5);
	}
}
