package com.superronjon.sudoku;

import java.math.BigInteger;

public class CheckCounter
{
	private BigInteger count;
	private final boolean shouldCount;

	public CheckCounter(boolean shouldCount) {
		this.shouldCount = shouldCount;
		if(this.shouldCount) {
			count = new BigInteger("0");
		}
		else {
			count = new BigInteger("-1");
		}
	}

	public void add(int toAdd) {
		this.count = count.add(new BigInteger(Integer.toString(toAdd)));
	}

	public void addOne() {
		add(1);
	}

	public String toString() {
		return count.toString();
	}

	public boolean getShouldCount() {
		return shouldCount;
	}

	public BigInteger getCount() {
		return count;
	}
}
