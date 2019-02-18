package com.qa.persistence.repository;

import com.qa.persistence.domain.Body;

public interface BodyRepository {

	public double getDistance(Body first, Body second);

	public void addForces(Body first, Body second);
	
	public void simulateStep(double timeStep);
	
	public String createBody(String body);
	
	public String updateBody(String body);
	
	public String removeBody(String body);
	
	public String getAllBodies();
}
