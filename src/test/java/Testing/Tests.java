package Testing;

import org.junit.Test;

import com.qa.persistence.domain.Body;

import junit.framework.TestCase;

public class Tests extends TestCase {
	@Test
	public void test1() {
		Body b1 = new Body(100, 50, 5, 3, 10);
		Body b2 = new Body(70, 10, 5, 7, 13);
		assertEquals(50.0, b1.getDistance(b2));
		assertEquals(b1.getDistance(b2), b2.getDistance(b1));
	}

	@Test
	public void test2() {
		Body b1 = new Body(100, 50, 5, 3, 10);
		Body b2 = new Body(70, 10, 5, 7, 13);
		b1.addForces(b2);
		assertEquals(b1.getFx(), -b2.getFx());
		assertEquals((130 * (b2.getY() - b1.getY())) / (50 * 50 * 50), b1.getFy());
	}
}
