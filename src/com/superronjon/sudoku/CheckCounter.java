package com.superronjon.sudoku;

import java.math.BigInteger;

public class CheckCounter
{
	private BigInteger count;
	private boolean shouldCount;

	public CheckCounter() {
		count = new BigInteger("-1");
		shouldCount = false;
	}

	public void setShouldCount(boolean shouldCount)
	{
		this.shouldCount = shouldCount;
		if(this.shouldCount && count.intValue() == -1) {
			count = new BigInteger("0");
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
