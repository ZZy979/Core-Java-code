package com.horstmann.greetsvc.internal;

import com.horstmann.greetsvc.GreeterService;

import java.util.Locale;
import java.util.Map;

public class GermanGreeter implements GreeterService {
    private Map<String, String> dictionary;

    public GermanGreeter(Map<String, String> dictionary) {
        this.dictionary = dictionary;
    }

    @Override
    public String greet(String subject) {
        return "Hallo, " + dictionary.getOrDefault(subject, subject) + "!";
    }

    @Override
    public Locale getLocale() {
        return Locale.GERMAN;
    }
}
