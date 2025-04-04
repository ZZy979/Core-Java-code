package com.horstmann.greet.internal;

import com.horstmann.greet.Greeter;

public class GreeterImpl implements Greeter {
    @Override
    public String greet(String subject) {
        return "Hello, " + subject + "!";
    }
}
