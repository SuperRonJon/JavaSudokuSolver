package com.superronjon.sudoku;

public class CheckCounter
{
	private Long count;
	private final boolean shouldCount;

	public CheckCounter(boolean shouldCount) {
		this.shouldCount = shouldCount;
		if(this.shouldCount) {
			count = 0L;
		}
		else {
			count = -1L;
		}
	}

	public void add(int toAdd) {
		if(this.shouldCount) {
			this.count += toAdd;
		}
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

	public Long getCount() {
		return count;
	}
}
