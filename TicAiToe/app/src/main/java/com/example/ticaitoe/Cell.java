package com.example.ticaitoe;


public class Cell {
    private final int i;
    private final int j;

    public final int getI() {
        return this.i;
    }

    public final int getJ() {
        return this.j;
    }

    public Cell(int i, int j) {
        this.i = i;
        this.j = j;
    }
}
