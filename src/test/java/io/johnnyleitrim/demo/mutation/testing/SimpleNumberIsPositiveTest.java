package io.johnnyleitrim.demo.mutation.testing;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.Test;

public class SimpleNumberIsPositiveTest {

	@Test
	public void testPositive() {
		SimpleNumber number = new SimpleNumber(1);
		assertTrue(number.isPositive());
	}

	@Test
	public void testNegative() {
		SimpleNumber number = new SimpleNumber(-1);
		assertFalse(number.isPositive());
	}

	/*
	 * Uncomment this test to ensure the mutation testing passes.
	 * It tests the boundary condition of isPositive
	 */
//	@Test
//	public void testBoundary() {
//		SimpleNumber number = new SimpleNumber(0);
//		assertTrue(number.isPositive());
//	}
}
