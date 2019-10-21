package com.composite_iterator;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Spliterator;
import java.util.function.Consumer;

public class Corporation implements IReport, Iterator<CorporationComponent>, Iterable<CorporationComponent> {
    private String name;
    private ArrayList<CorporationComponent> components = new ArrayList<>();

    String getName() {
        return name;
    }

    Corporation(String name) {
        this.name = name;
    }

    void addCorporationComponent(CorporationComponent component) {
        components.add(component);
    }

    @Override
    public String getReport() {
        StringBuilder report = new StringBuilder();
        report.append("Отчет компании ".toUpperCase()).append(getName()).append("\n");
        for (CorporationComponent c : components) {
            report.append(c.getReport());
        }
        return report.toString();
    }

    private int counter;

    private int getComponentCount() {
        int result = 0;
        for (CorporationComponent c : components) {
            result += c.getCountComponent() + 1;
        }
        return result;
    }

    @Override
    public boolean hasNext() {
        return counter < getComponentCount();
    }

    @Override
    public CorporationComponent next() {
        return getComponentByIndex(counter);
    }

    @Override
    public void remove() {
        counter = 0;
    }

    @Override
    public void forEachRemaining(Consumer<? super CorporationComponent> action) { }

    private CorporationComponent getComponentByIndex(int index){
        counter++;
        int count = 0;
        for (int i = 0; i < components.size(); i++) {
            if(count == index) return components.get(i);
            for (int j = 0; j < components.get(i).getCountComponent(); j++) {
                count++;
                if(count == index) return components.get(i).getComponent(j);
            }
            count++;
        }
        return  null;
    }

    @Override
    public Iterator<CorporationComponent> iterator() {
        return this;
    }

    @Override
    public void forEach(Consumer<? super CorporationComponent> action) {

    }

    @Override
    public Spliterator<CorporationComponent> spliterator() {
        return null;
    }
}
