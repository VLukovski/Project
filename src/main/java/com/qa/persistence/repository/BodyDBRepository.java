package com.qa.persistence.repository;

import java.util.ArrayList;
import java.util.List;

import com.qa.persistence.domain.Body;

public class BodyDBRepository implements BodyRepository {
	
	List<Body> system = new ArrayList<Body>();
	Long time = 0L;
	
	public double getDistance(Body first, Body second) {
		return Math.sqrt(
				Math.pow((first.getPosX() - second.getPosX()), 2) + Math.pow((first.getPosY() - second.getPosY()), 2));
	}

	public void addForces(Body first, Body second) {
		double G = 1;
		double dist = getDistance(first, second);
		double force = G * (first.getMass() * second.getMass()) / (dist * dist);
		second.addForceX(force * (first.getPosX() - second.getPosX()) / dist);
		second.addForceY(force * (first.getPosY() - second.getPosY()) / dist);
		first.addForceX(force * (second.getPosX() - first.getPosX()) / dist);
		first.addForceY(force * (second.getPosY() - first.getPosY()) / dist);
	}
	
	public List<Body> getSystem() {
		return system;
	}

	public void addToSystem(Body body) {
		system.add(body);
	}

	public void simulateStep(double timeStep) {
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
			System.out.println("Particle " + i + " Position X/Y " + system.get(i).getPosX() + "/" + system.get(i).getPosY() + " Force sum X/Y " + system.get(i).getForceX() + "/" + system.get(i).getForceY());
		}	
		System.out.println("\n");
	}

	public String createBody(String body) {
		// TODO Auto-generated method stub
		return null;
	}

	public String updateBody(String body) {
		// TODO Auto-generated method stub
		return null;
	}

	public String removeBody(String body) {
		// TODO Auto-generated method stub
		return null;
	}

	public String getAllBodies() {
		// TODO Auto-generated method stub
		return null;
	}
}
