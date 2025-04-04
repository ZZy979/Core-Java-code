package com.horstmann.greetsvc.internal;

import com.horstmann.greetsvc.GreeterService;

import java.util.Locale;

public class FrenchGreeter implements GreeterService {
    @Override
    public String greet(String subject) {
        return "Bonjour " + subject;
    }

    @Override
    public Locale getLocale() {
        return Locale.FRENCH;
    }
}
