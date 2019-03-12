package com.qa.business.service;

import javax.inject.Inject;

import com.qa.persistence.repository.AccountRepository;
import com.qa.util.JSONUtil;

public class AccountServiceImpl implements AccountService {

	@Inject
	private AccountRepository repo;

	@Inject
	private JSONUtil util;

	@Override
	public String createAccount(String account) {
		return repo.createAccount(account);
	}

	@Override
	public String getAccount(Long id) {
		return repo.getAccount(id);
	}

	@Override
	public String getAllAccounts() {
		return repo.getAllAccounts();
	}

	@Override
	public String updateAccount(Long id, String account) {
		return repo.updateAccount(id, account);
	}

	@Override
	public String deleteAccount(Long id) {
		return repo.deleteAccount(id);
	}

	public void setRepository(AccountRepository repo) {
		this.repo = repo;
	}

	@Override
	public Boolean checkAccount(String input) {
		return repo.checkAccount(input);
	}
}
