package org.samarskii.model;

import java.util.Arrays;
import java.util.List;

public enum Gender {
    Male,
    Female,
    Other;

    public static List<Gender> list()
    {
        return Arrays.asList(values());
    }

    public static Gender parse(String value)
    {
        Gender gender = null;

        for(Gender test : list())
        {
            if(test.name().equalsIgnoreCase(value))
            {
                gender = test;
                break;
            }
        }
        return gender;
    }
}
