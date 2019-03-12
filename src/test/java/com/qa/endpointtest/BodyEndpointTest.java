package com.qa.endpointtest;

import static org.junit.Assert.assertEquals;

import java.io.IOException;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import com.qa.business.service.BodyService;
import com.qa.rest.BodyEndpoint;

@RunWith(MockitoJUnitRunner.class)
public class BodyEndpointTest {

	private static final String MOCK_VALUE = "test_value";

	@InjectMocks
	private BodyEndpoint endpoint;

	@Mock
	private BodyService service;

	@Before
	public void setup() {
		endpoint.setService(service);
	}

	@Test
	public void test1() {
		Mockito.when(service.getAllBodies()).thenReturn(MOCK_VALUE);
		assertEquals(MOCK_VALUE, endpoint.getAllBodies());
	}

	@Test
	public void test2() {
		Mockito.when(service.getABody(Mockito.anyLong())).thenReturn(MOCK_VALUE);
		assertEquals(MOCK_VALUE, endpoint.getABody(Mockito.anyLong()));
	}

	@Test
	public void test3() {
		Mockito.when(service.createBody(Mockito.anyString())).thenReturn(MOCK_VALUE);
		assertEquals(MOCK_VALUE, endpoint.createBody(Mockito.anyString()));
	}

	@Test
	public void test4() {
		Mockito.when(service.updateBody(Mockito.anyLong(), Mockito.anyString())).thenReturn(MOCK_VALUE);
		assertEquals(MOCK_VALUE, endpoint.updateBody(Mockito.anyLong(), Mockito.anyString()));
	}

	@Test
	public void test5() {
		Mockito.when(service.removeBodies()).thenReturn(MOCK_VALUE);
		assertEquals(MOCK_VALUE, endpoint.removeBodies());
	}
	
	@Test
	public void test6() throws IOException {
		Mockito.when(service.getNextState(Mockito.anyInt())).thenReturn(MOCK_VALUE);
		assertEquals(MOCK_VALUE, endpoint.getNextState(Mockito.anyInt()));
	}
}
