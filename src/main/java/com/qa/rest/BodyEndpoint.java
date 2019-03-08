package com.qa.rest;

import java.io.IOException;

import javax.inject.Inject;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import com.qa.business.service.BodyService;

@Path("/body")
public class BodyEndpoint {

	@Inject
	private BodyService service;
	
	@Path("/getNextState/{timeStep}")
	@GET
	@Produces({ "application/json" })
	public String getNextState(@PathParam("timeStep") Integer timeStep) throws IOException {
		return service.getNextState(timeStep);
	}

	@Path("/createBody")
	@POST
	@Produces({ "application/json" })
	public String createBody(String body) {
		return service.createBody(body);
	}

	@Path("/getBody/{id}")
	@GET
	@Produces({ "application/json" })
	public String getBody(@PathParam("id") Long id) {
		return service.getABody(id);
	}

	@Path("/getAllBodies")
	@GET
	@Produces({ "application/json" })
	public String getAllBodies() {
		return service.getAllBodies();
	}

	@Path("/updateBody/{id}")
	@PUT
	@Produces({ "application/json" })
	public String updateBody(@PathParam("id") Long id, String body) {
		return service.updateBody(id, body);
	}

	@Path("/deleteBody/{id}")
	@DELETE
	@Produces({ "application/json" })
	public String removeBody(@PathParam("id") Long id) {
		return service.removeBody(id);
	}
}