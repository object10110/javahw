package com.composite_iterator;

public class Department extends CorporationComponent {
    Department(String name) {
        super(name);
    }

    @Override
    int getCountComponent() {
        return 0;
    }

    @Override
    CorporationComponent getComponent(int index) {
        return null;
    }

    @Override
    public String getReport() {
        return "Отчет департамента " + getName();
    }
}
