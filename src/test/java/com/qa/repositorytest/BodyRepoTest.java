package com.qa.repositorytest;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
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

import com.qa.persistence.domain.Body;
import com.qa.persistence.repository.BodyDBRepository;
import com.qa.util.JSONUtil;

@RunWith(MockitoJUnitRunner.class)
public class BodyRepoTest {

	private static final String MOCK_ARRAY1 = "[{\"posX\":100.0,\"posY\":50.0,\"velX\":5.0,\"velY\":3.0,\"forceX\":0.0,\"forceY\":0.0,\"mass\":10.0},{\"posX\":70.0,\"posY\":10.0,\"velX\":5.0,\"velY\":7.0,\"forceX\":0.0,\"forceY\":0.0,\"mass\":13.0},{\"posX\":20.0,\"posY\":30.0,\"velX\":2.0,\"velY\":11.0,\"forceX\":0.0,\"forceY\":0.0,\"mass\":15.0},{\"posX\":30.0,\"posY\":50.0,\"velX\":10.0,\"velY\":7.0,\"forceX\":0.0,\"forceY\":0.0,\"mass\":17.0}]";

	private static final String MOCK_OBJECT = "{\"posX\":100.0,\"posY\":50.0,\"velX\":5.0,\"velY\":3.0,\"forceX\":0.0,\"forceY\":0.0,\"mass\":10.0}";

	private static final String MOCK_BYTES = "iVBORw0KGgoAAAANSUhEUgAAAfQAAAH0CAYAAADL1t+KAAAEAElEQVR42u3coREAAAgDMfZfumg0CERy1xnetQoAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAeC6ZAwAEHQAQdABA0AEAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAADYS+YAAEEHAAQdABB0AAAAAAAAAAAAAAAAAAAAAAC45KQHAAQdABB0AEDQAUDMAEDQAQBBBwAEHQAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAADgtQZUKH+Baw8BHAAAAABJRU5ErkJggg==";
	private Body b1;
	private Body b2;
	private Body b3;
	private Body b4;
	private List<Body> bodies;

	@InjectMocks
	private BodyDBRepository repo;

	@Mock
	private EntityManager manager;

	@Mock
	private Query query;

	private JSONUtil util;

	@Before
	public void setup() {
		b1 = new Body(100., 50., 5., 3., 10.);
		b2 = new Body(70., 10., 5., 7., 13.);
		b3 = new Body(20., 30., 2., 11., 15.);
		b4 = new Body(30., 50., 10., 7., 17.);
		bodies = new ArrayList<Body>();
		bodies.add(b1);
		bodies.add(b2);
		bodies.add(b3);
		bodies.add(b4);
		repo.setManager(manager);
		util = new JSONUtil();
		repo.setUtil(util);
	}

	@Test
	public void getNextState() throws IOException {
		Mockito.when(manager.createQuery(Mockito.anyString())).thenReturn(query);
		Mockito.when(query.getResultList()).thenReturn(bodies);
		assertEquals(MOCK_BYTES, repo.getNextState(1));
	}

	@Test
	public void testGetAllBodys() {
		Mockito.when(manager.createQuery(Mockito.anyString())).thenReturn(query);
		Mockito.when(query.getResultList()).thenReturn(bodies);
		assertEquals(MOCK_ARRAY1, repo.getAllBodies());
	}

	@Test
	public void testGetBody() {
		Mockito.when(manager.find(Mockito.any(), Mockito.anyInt())).thenReturn(bodies.get(0));
		assertEquals(MOCK_OBJECT, repo.getABody(1L));
	}

	@Test
	public void testCreateBody() {
		String reply = repo.createBody(MOCK_OBJECT);
		assertEquals("{\"message\": \"body has been sucessfully added\"}", reply);
	}

//	@Test
//	public void testDeleteBodyPass() {
//		Mockito.when(manager.createQuery(Mockito.anyString()).getResultList()).thenReturn(Mockito.anyObject());
//		Mockito.when(manager.contains(Mockito.anyObject())).thenReturn(true);
//		String reply = repo.removeBodies();
//		assertEquals("{\"message\": \"all bodies have been sucessfully deleted\"}", reply);
//	}

	@Test
	public void testUpdateBodyFail() {
		String update = "{\"trainer\":\"Jordan\"}";
		Mockito.when(manager.contains(Mockito.anyObject())).thenReturn(false);
		assertEquals("{\"message\": \"this body does not exist\"}", repo.updateBody(1L, update));
	}

	@Test
	public void testUpdateBodyPass() {
		String update = "{\"trainer\":\"Jordan\"}";
		Mockito.when(manager.contains(Mockito.anyObject())).thenReturn(true);
		Mockito.when(manager.find(Mockito.any(), Mockito.anyLong())).thenReturn(bodies.get(0));
		assertEquals("{\"message\": \"body has been sucessfully updated\"}", repo.updateBody(1L, update));
	}
}
