package com.algorithms.util;

public class Counter {

    private int value;

    public Counter(int v) {
        this.value = v;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public void increment() {
        this.value++;
    }
}
