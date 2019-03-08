package com.qa.business.service;

import java.io.IOException;

public interface BodyService {
	
	public String getNextState(double timeStep) throws IOException;
	
	public String createBody(String body);
	
	public String updateBody(Long id, String body);
	
	public String removeBody(Long id);
	
	public String getAllBodies();
	
	public String getABody(Long id);

}
