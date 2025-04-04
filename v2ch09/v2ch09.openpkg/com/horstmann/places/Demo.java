package com.horstmann.places;

import com.horstmann.util.ObjectAnalyzer;

public class Demo {
    public static void main(String[] args) throws ReflectiveOperationException {
        var belgium = new Country("Belgium", 30510);
        var analyzer = new ObjectAnalyzer();
        System.out.println(analyzer.toString(belgium));
    }
}
