package com.kempton.demo.mutation.testing;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class SimpleNumberMultiplyIfOnesTest {

	@Test
	public void testOnes() {
		SimpleNumber number = new SimpleNumber(1);
		SimpleNumber otherNumber = new SimpleNumber(1);

		SimpleNumber newNumber = number.multiplyIfOnes(otherNumber);

		assertEquals(1, newNumber.getValue());
	}

	@Test
	public void testNoOnes() {
		SimpleNumber number = new SimpleNumber(2);
		SimpleNumber otherNumber = new SimpleNumber(3);

		SimpleNumber newNumber = number.multiplyIfOnes(otherNumber);

		assertEquals(2, newNumber.getValue());
		assertEquals(3, otherNumber.getValue());
	}

	@Test
	public void testOnlyOneOne() {
		SimpleNumber number = new SimpleNumber(1);
		SimpleNumber otherNumber = new SimpleNumber(2);

		SimpleNumber newNumber = number.multiplyIfOnes(otherNumber);

		assertEquals(1, newNumber.getValue());
		assertEquals(2, otherNumber.getValue());

		/* Test the numbers the other way around */
		number = new SimpleNumber(2);
		otherNumber = new SimpleNumber(1);

		newNumber = number.multiplyIfOnes(otherNumber);

		assertEquals(2, newNumber.getValue());
		assertEquals(1, otherNumber.getValue());
	}

}
