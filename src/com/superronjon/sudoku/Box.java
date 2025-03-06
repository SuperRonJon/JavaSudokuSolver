package com.superronjon.sudoku;

public class Box {
    private int value;
    private boolean isDefault;

    public Box() {
        this.value = -1;
        this.isDefault = false;
    }

    public Box(int v) {
        this.value = v;
        this.isDefault = true;
    }

    public int getValue() {
        return this.value;
    }

    public void setValue(int v) {
        this.value = v;
    }

    public void clear() {
        if(!this.isDefault) {
            this.value = -1;
        }
    }

    public boolean isEmpty() {
        return this.value == -1;
    }
}
