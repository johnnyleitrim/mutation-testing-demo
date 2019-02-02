package io.johnnyleitrim.demo.mutation.testing;

public class SimpleNumber {

	private final int value;

	public SimpleNumber(int value) {
		this.value = value;
	}

	public int getValue() {
		return value;
	}

	public boolean isPositive() {
		return value >= 0;
	}

	public SimpleNumber increment(Incrememter incrememter) {
		if (incrememter != null) {
			return incrememter.increment(this);
		}
		return this;
	}

	/* I think we can all agree this is a silly method, but here to demonstrate equivalent mutations */
	public SimpleNumber multiplyIfOnes(SimpleNumber otherNumber) {
		if (value == 1 && otherNumber.value == 1) {
			return new SimpleNumber(value * otherNumber.value);
		}
		return this;
	}
}
