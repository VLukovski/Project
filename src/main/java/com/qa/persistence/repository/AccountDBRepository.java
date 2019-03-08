package com.qa.persistence.repository;

import static javax.transaction.Transactional.TxType.REQUIRED;
import static javax.transaction.Transactional.TxType.SUPPORTS;

import java.util.Collection;

import javax.enterprise.inject.Default;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import com.qa.persistence.domain.Account;
import com.qa.util.JSONUtil;

@Transactional(SUPPORTS)
@Default
public class AccountDBRepository implements AccountRepository {

	@PersistenceContext(unitName = "primary")
	private EntityManager manager;

	@Inject
	private JSONUtil util;

	@Override
	@Transactional(REQUIRED)
	public String createAccount(String account) {
		Account anAccount = util.getObjectForJson(account, Account.class);
		manager.persist(anAccount);
		return "{\"message\": \"account has been sucessfully added\"}";
	}

	@Override
	public String getAccount(Integer id) {
		return util.getJsonForObject(manager.find(Account.class, id));
	}

	@Override
	public String getAllAccounts() {
		return util
				.getJsonForObject((Collection<Account>) manager.createQuery("SELECT a FROM Account a").getResultList());
	}

	@Override
	@Transactional(REQUIRED)
	public String updateAccount(Integer id, String account) {
		Account anAccount = util.getObjectForJson(account, Account.class);
		if (manager.contains(manager.find(Account.class, id))) {
			if (anAccount.getLogin() != null) {
				manager.find(Account.class, id).setLogin(anAccount.getLogin());
			}
			if (anAccount.getPassword() != null) {
				manager.find(Account.class, id).setPassword(anAccount.getPassword());
			}
			if (anAccount.getUniqueKey() != null) {
				manager.find(Account.class, id).setUniqueKey(anAccount.getUniqueKey());
			}
			return "{\"message\": \"account has been sucessfully updated\"}";
		}
		return "{\"message\": \"this account does not exist\"}";
	}

	@Override
	@Transactional(REQUIRED)
	public String deleteAccount(Integer id) {
		if (manager.contains(manager.find(Account.class, id))) {
			manager.remove(manager.find(Account.class, id));
			return "{\"message\": \"account has been sucessfully deleted\"}";
		}
		return "{\"message\": \"this account does not exist\"}";
	}

}
