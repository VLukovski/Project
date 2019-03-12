package com.qa.servicetest;

import static org.junit.Assert.assertEquals;

import java.io.IOException;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import com.qa.business.service.BodyServiceImpl;
import com.qa.persistence.repository.BodyRepository;

@RunWith(MockitoJUnitRunner.class)
public class BodyServiceTest {

	private static final String MOCK_VALUE = "test_value";

	@InjectMocks
	private BodyServiceImpl service;

	@Mock
	private BodyRepository repo;

	@Before
	public void setup() {
		service.setRepository(repo);
	}

	@Test
	public void test1() {
		Mockito.when(repo.getAllBodies()).thenReturn(MOCK_VALUE);
		assertEquals(MOCK_VALUE, service.getAllBodies());
	}

	@Test
	public void test2() {
		Mockito.when(repo.getABody(Mockito.anyLong())).thenReturn(MOCK_VALUE);
		assertEquals(MOCK_VALUE, service.getABody(Mockito.anyLong()));
	}

	@Test
	public void test3() {
		Mockito.when(repo.createBody(Mockito.anyString())).thenReturn(MOCK_VALUE);
		assertEquals(MOCK_VALUE, service.createBody(Mockito.anyString()));
	}

	@Test
	public void test4() {
		Mockito.when(repo.updateBody(Mockito.anyLong(), Mockito.anyString())).thenReturn(MOCK_VALUE);
		assertEquals(MOCK_VALUE, service.updateBody(Mockito.anyLong(), Mockito.anyString()));
	}

	@Test
	public void test5() {
		Mockito.when(repo.removeBodies()).thenReturn(MOCK_VALUE);
		assertEquals(MOCK_VALUE, service.removeBodies());
	}

	@Test
	public void test6() throws IOException {
		Mockito.when(repo.getNextState(Mockito.anyInt())).thenReturn(MOCK_VALUE);
		assertEquals(MOCK_VALUE, service.getNextState(Mockito.anyInt()));
	}
}
