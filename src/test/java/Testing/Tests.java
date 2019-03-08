package Testing;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.qa.persistence.domain.Body;
import com.qa.persistence.repository.BodyDBRepository;




public class Tests {
//
//	BodyDBRepository repo;
//	Body b1;
//	Body b2;
//	Body b3;
//	Body b4;
//	
//	@Before
//	public void setup() {
//		repo = new BodyDBRepository();
//		b1 = new Body(100., 50., 5., 3., 10.);
//		b2 = new Body(70., 10., 5., 7., 13.);
//		b3 = new Body(20., 30., 2., 11., 15.);
//		b4 = new Body(30., 50., 10., 7., 17.);
//		repo.addToSystem(b1);
//		repo.addToSystem(b2);
//		repo.addToSystem(b3);
//		repo.addToSystem(b4);
//	}
//
//	@Test
//	public void test1() {
//		assertEquals(50.0, repo.getDistance(b1, b2), 0.0001);
//	}
//
//	@Test
//	public void test2() {
//		repo.addForces(b1, b2);
//		assertEquals(b1.getForceX(), -b2.getForceX(), 0.0001);
//		assertEquals((130 * (10. - 50)) / (50 * 50 * 50), b1.getForceY(), 0.0001);
//	}
//	
//	@Test
//	public void test3() {
//		repo.simulateStep(1);
//		assertEquals(105, repo.getSystem().get(0).getPosX(), 0.5);
//	}
}
