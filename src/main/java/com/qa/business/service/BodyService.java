package com.qa.business.service;

public interface BodyService {
	
	public String getNextState(double timeStep);
	
	public String createBody(String body);
	
	public String updateBody(Long id, String body);
	
	public String removeBody(Long id);
	
	public String getAllBodies();
	
	public String getABody(Long id);

}
