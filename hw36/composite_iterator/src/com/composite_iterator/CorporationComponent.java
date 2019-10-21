package com.composite_iterator;

public abstract class CorporationComponent implements IReport{
    private String name;
    String getName() {
        return name;
    }
    CorporationComponent(String name) {
        this.name = name;
    }
    abstract int getCountComponent();
    abstract CorporationComponent getComponent(int index);
}
