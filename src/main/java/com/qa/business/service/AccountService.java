package com.qa.business.service;

public interface AccountService {

	public String createAccount(String account);

	public String getAccount(Integer id);

	public String getAllAccounts();

	public String updateAccount(Integer id, String account);

	public String deleteAccount(Integer id);

}
