package com.qa.persistence.repository;

import java.util.List;

import com.qa.persistence.domain.Body;

public class BodyPhysics {
	static double dist;
	
	public static void addForces(Body first, Body second) {
		double G = 1;
		dist = Math.sqrt(
				Math.pow((first.getPosX() - second.getPosX()), 2) + Math.pow((first.getPosY() - second.getPosY()), 2)) + 1;
		double force = G * (first.getMass() * second.getMass()) / (dist * dist);
		second.addForceX(force * (first.getPosX() - second.getPosX()) / dist);
		second.addForceY(force * (first.getPosY() - second.getPosY()) / dist);
		first.addForceX(force * (second.getPosX() - first.getPosX()) / dist);
		first.addForceY(force * (second.getPosY() - first.getPosY()) / dist);

	}
	
	public static void simulateStep(List<Body> system, double timeStep) {
		for (int i = 0; i < system.size(); i++) {
			system.get(i).setForceX(0);
			system.get(i).setForceY(0);
		}

		for (int i = 0; i < system.size() - 1; i++) {
			for (int j = i + 1; j < system.size(); j++) {
				addForces(system.get(i), system.get(j));
			}
		}

		for (int i = 0; i < system.size(); i++) {
			system.get(i).addVelX(timeStep * system.get(i).getForceX() / system.get(i).getMass());
			system.get(i).addVelY(timeStep * system.get(i).getForceY() / system.get(i).getMass());
			system.get(i).addPosX(timeStep * system.get(i).getVelX());
			system.get(i).addPosY(timeStep * system.get(i).getVelY());
			if (Math.abs(system.get(i).getPosX()) >= 250) {
				system.get(i).setVelX(-system.get(i).getVelX());
			}
			if (Math.abs(system.get(i).getPosY()) >= 250) {
				system.get(i).setVelY(-system.get(i).getVelY());
			}
		}
	}
}
