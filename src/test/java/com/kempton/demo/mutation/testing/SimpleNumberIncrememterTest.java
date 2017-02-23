package com.kempton.demo.mutation.testing;

import static org.easymock.EasyMock.createStrictMock;
import static org.easymock.EasyMock.expect;
import static org.easymock.EasyMock.replay;
import static org.easymock.EasyMock.verify;
import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class SimpleNumberIncrememterTest {

	@Test
	public void testNoIncrementer() {
		SimpleNumber number = new SimpleNumber(1);
		SimpleNumber sameNumber = number.increment(null);
		/* Uncomment this line to ensure the mutation testing passed */
//		assertEquals(number, sameNumber);
	}

	@Test
	public void testIncrementer() {
		SimpleNumber number = new SimpleNumber(-1);

		Incrememter mockIncrememter = createStrictMock(Incrememter.class);

		SimpleNumber expectedIncrementedNumber = new SimpleNumber(2);
		expect(mockIncrememter.increment(number)).andReturn(expectedIncrementedNumber);

		replay(mockIncrememter);

		SimpleNumber actualIncrementedNumber = number.increment(mockIncrememter);

		verify(mockIncrememter);

		/* Uncomment this line to ensure the mutation testing passed */
//		assertEquals(expectedIncrementedNumber, actualIncrementedNumber);
	}
}
