package com.composite_iterator;

import java.util.ArrayList;

public class SubDivision extends CorporationComponent {
    private ArrayList<CorporationComponent> components = new ArrayList<>();
    SubDivision(String name) {
        super(name);
    }

    @Override
    int getCountComponent() {
        return components.size();
    }

    @Override
    CorporationComponent getComponent(int index) {
        return components.get(index);
    }

    @Override
    public String getReport() {
        StringBuilder report = new StringBuilder();
        report.append("Отчет подразделения ")
                .append(getName())
                .append(":\n");
        for (CorporationComponent c:components) {
            report.append("\t")
                    .append(c.getReport())
                    .append("\n");
        }
        return report.toString();
    }
    void addDepartment(CorporationComponent component) {
        components.add(component);
    }
}
