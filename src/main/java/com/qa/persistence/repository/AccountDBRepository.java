package com.qa.persistence.repository;

import static javax.transaction.Transactional.TxType.REQUIRED;
import static javax.transaction.Transactional.TxType.SUPPORTS;

import javax.enterprise.inject.Default;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import com.qa.util.JSONUtil;

@Transactional(SUPPORTS)
@Default
public class AccountDBRepository implements AccountRepository {
	
	@PersistenceContext(unitName = "primary")
	private EntityManager manager;
	
	@Inject
	private JSONUtil util;

	@Override
	public String createAccount(String account) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getAccount(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getAllAccounts() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String updateAccount(Integer id, String account) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String deleteAccount(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}



}
