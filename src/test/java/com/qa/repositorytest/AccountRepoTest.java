package com.qa.repositorytest;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import com.qa.persistence.domain.Account;
import com.qa.persistence.repository.AccountDBRepository;
import com.qa.util.JSONUtil;

@RunWith(MockitoJUnitRunner.class)
public class AccountRepoTest {

	private static final String MOCK_ARRAY1 = "[{\"uniqueKey\":1337,\"login\":\"Matt\",\"password\":\"admin\"},{\"uniqueKey\":1111,\"login\":\"Jordan\",\"password\":\"weapon\"}]";

	private static final String MOCK_OBJECT = "{\"uniqueKey\":1337,\"login\":\"Matt\",\"password\":\"admin\"}";

	@InjectMocks
	private AccountDBRepository repo;

	@Mock
	private EntityManager manager;

	@Mock
	private Query query;

	private JSONUtil util;

	private List<Account> accounts;

	@Before
	public void setup() {
		accounts = new ArrayList<Account>();
		accounts.add(new Account(1337, "Matt", "admin"));
		accounts.add(new Account(1111, "Jordan", "weapon"));
		repo.setManager(manager);
		util = new JSONUtil();
		repo.setUtil(util);
	}

	@Test
	public void testGetAllAccounts() {
		Mockito.when(manager.createQuery(Mockito.anyString())).thenReturn(query);
		Mockito.when(query.getResultList()).thenReturn(accounts);
		assertEquals(MOCK_ARRAY1, repo.getAllAccounts());
	}

//	@Test
//	public void testGetAccount() {
//		Mockito.when(manager.find(Mockito.any(), Mockito.anyInt())).thenReturn(accounts.get(0));
//		assertEquals(MOCK_OBJECT, repo.getAccount(1));
//	}

	@Test
	public void testCreateAccount() {
		String reply = repo.createAccount(MOCK_OBJECT);
		assertEquals("{\"message\": \"account has been sucessfully added\"}", reply);
	}

	@Test
	public void testDeleteAccountPass() {
		Mockito.when(manager.contains(Mockito.anyObject())).thenReturn(true);
		String reply = repo.deleteAccount(1L);
		assertEquals("{\"message\": \"account has been sucessfully deleted\"}", reply);
	}

	@Test
	public void testDeleteAccountFail() {
		Mockito.when(manager.contains(Mockito.anyObject())).thenReturn(false);
		String reply = repo.deleteAccount(0L);
		assertEquals("{\"message\": \"this account does not exist\"}", reply);
	}

	@Test
	public void testUpdateAccountFail() {
		String update = "{\"login\":\"Jordan\"}";
		Mockito.when(manager.contains(Mockito.anyObject())).thenReturn(false);
		assertEquals("{\"message\": \"this account does not exist\"}", repo.updateAccount(1L, update));
	}

	@Test
	public void testUpdateAccountPass() {
		String update = "{\"login\":\"Jordan\"}";
		Mockito.when(manager.contains(Mockito.anyObject())).thenReturn(true);
		Mockito.when(manager.find(Mockito.any(), Mockito.anyLong())).thenReturn(accounts.get(0));
		assertEquals("{\"message\": \"account has been sucessfully updated\"}", repo.updateAccount(1L, update));
	}
}
