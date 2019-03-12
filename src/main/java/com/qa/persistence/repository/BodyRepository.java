package com.qa.persistence.repository;

import java.io.IOException;

public interface BodyRepository {
	
	public String getNextState(double timeStep) throws IOException;
	
	public String createBody(String body);
	
	public String updateBody(Long id, String body);
	
	public String removeBodies();
	
	public String getAllBodies();
	
	public String getABody(Long id);
}
