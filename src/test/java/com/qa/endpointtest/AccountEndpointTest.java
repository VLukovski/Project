package com.qa.endpointtest;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import com.qa.business.service.AccountService;
import com.qa.rest.AccountEndpoint;

@RunWith(MockitoJUnitRunner.class)
public class AccountEndpointTest {

	private static final String MOCK_VALUE = "test_value";

	@InjectMocks
	private AccountEndpoint endpoint;

	@Mock
	private AccountService service;

	@Before
	public void setup() {
		endpoint.setService(service);
	}

	@Test
	public void test1() {
		Mockito.when(service.getAllAccounts()).thenReturn(MOCK_VALUE);
		assertEquals(MOCK_VALUE, endpoint.getAllAccounts());
	}

	@Test
	public void test2() {
		Mockito.when(service.getAccount(Mockito.anyInt())).thenReturn(MOCK_VALUE);
		assertEquals(MOCK_VALUE, endpoint.getAccount(Mockito.anyInt()));
	}

	@Test
	public void test3() {
		Mockito.when(service.createAccount(Mockito.anyString())).thenReturn(MOCK_VALUE);
		assertEquals(MOCK_VALUE, endpoint.createAccount(Mockito.anyString()));
	}

	@Test
	public void test4() {
		Mockito.when(service.updateAccount(Mockito.anyInt(), Mockito.anyString())).thenReturn(MOCK_VALUE);
		assertEquals(MOCK_VALUE, endpoint.updateAccount(Mockito.anyInt(), Mockito.anyString()));
	}

	@Test
	public void test5() {
		Mockito.when(service.deleteAccount(Mockito.anyInt())).thenReturn(MOCK_VALUE);
		assertEquals(MOCK_VALUE, endpoint.deleteAccount(Mockito.anyInt()));
	}
}
