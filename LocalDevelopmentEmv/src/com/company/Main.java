package com.company;

import java.util.Set;

public class Main {

    public static void main(String[] args) {
	// write your code here
//        System.out.printf("This is the classpath: %s %n", System.getProperty("java.class.path"));

        Set<String> propNames = System.getProperties().stringPropertyNames();
//        for (String propertyName: propNames) {
//            System.out.printf("%s is %s %n", propertyName, System.getProperty(propertyName));
//        }

        propNames.forEach((p) -> System.out.println(p));
    }
}
