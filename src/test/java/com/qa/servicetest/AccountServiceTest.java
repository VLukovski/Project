package com.qa.servicetest;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import com.qa.business.service.AccountServiceImpl;
import com.qa.persistence.repository.AccountRepository;

@RunWith(MockitoJUnitRunner.class)
public class AccountServiceTest {

	private static final String MOCK_VALUE = "test_value";

	@InjectMocks
	private AccountServiceImpl service;

	@Mock
	private AccountRepository repo;

	@Before
	public void setup() {
		service.setRepository(repo);
	}

	@Test
	public void test1() {
		Mockito.when(repo.getAllAccounts()).thenReturn(MOCK_VALUE);
		assertEquals(MOCK_VALUE, service.getAllAccounts());
	}

	@Test
	public void test2() {
		Mockito.when(repo.getAccount(Mockito.anyInt())).thenReturn(MOCK_VALUE);
		assertEquals(MOCK_VALUE, service.getAccount(Mockito.anyInt()));
	}

	@Test
	public void test3() {
		Mockito.when(repo.createAccount(Mockito.anyString())).thenReturn(MOCK_VALUE);
		assertEquals(MOCK_VALUE, service.createAccount(Mockito.anyString()));
	}

	@Test
	public void test4() {
		Mockito.when(repo.updateAccount(Mockito.anyInt(), Mockito.anyString())).thenReturn(MOCK_VALUE);
		assertEquals(MOCK_VALUE, service.updateAccount(Mockito.anyInt(), Mockito.anyString()));
	}

	@Test
	public void test5() {
		Mockito.when(repo.deleteAccount(Mockito.anyInt())).thenReturn(MOCK_VALUE);
		assertEquals(MOCK_VALUE, service.deleteAccount(Mockito.anyInt()));
	}
}
