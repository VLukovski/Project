package com.qa.rest;

import javax.inject.Inject;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import com.qa.business.service.AccountService;

@Path("/account")
public class AccountEndpoint {

	@Inject
	private AccountService service;

	@Path("/createAccount")
	@POST
	@Produces({ "application/json" })
	public String createAccount(String account) {
		return service.createAccount(account);
	}

	@Path("/getAccount/{id}")
	@GET
	@Produces({ "application/json" })
	public String getAccount(@PathParam("id") Integer id) {
		return service.getAccount(id);
	}

	@Path("/getAllAccounts")
	@GET
	@Produces({ "application/json" })
	public String getAllAccounts() {
		return service.getAllAccounts();
	}

	@Path("/updateAccount/{id}")
	@PUT
	@Produces({ "application/json" })
	public String updateAccount(@PathParam("id") Integer id, String account) {
		return service.updateAccount(id, account);
	}

	@Path("/deleteAccount/{id}")
	@DELETE
	@Produces({ "application/json" })
	public String deleteAccount(@PathParam("id") Integer id) {
		return service.deleteAccount(id);
	}
	
	public void setService(AccountService service) {
		this.service = service;
	}
}
