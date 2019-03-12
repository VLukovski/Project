package com.qa.persistence.repository;

public interface AccountRepository {

	public String createAccount(String account);

	public String getAccount(Long id);

	public String getAllAccounts();

	public String updateAccount(Long id, String account);

	public String deleteAccount(Long id);
	
	public Boolean checkAccount(String input);

}
