package com.composite_iterator;

public class Main {
    public static void main(String[] args) {
        Corporation ms = new Corporation("MS");

        SubDivision hh = new SubDivision("HH"),
                market = new SubDivision("Market"),
                development = new SubDivision("Development");
        //HH, Market, Development
        market.addDepartment(new Department("USA"));
        market.addDepartment(new Department("UK"));
        market.addDepartment(new Department("EU"));
        //Game, Office, OS
        development.addDepartment(new Department("Game"));
        development.addDepartment(new Department("Office"));
        development.addDepartment(new Department("OS"));

        ms.addCorporationComponent(hh);
        ms.addCorporationComponent(market);
        ms.addCorporationComponent(development);

        System.out.println(ms.getReport());

        System.out.println("Iterator for " + ms.getName());
        for (CorporationComponent c : ms) {
            System.out.println("element: " + c.getName());
        }
    }
}

