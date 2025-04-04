package com.horstmann.greetsvc.internal;

import com.horstmann.greetsvc.GreeterService;

import java.util.HashMap;
import java.util.Map;

public class GermanGreeterFactory {
    public static GreeterService provider() {
        Map<String, String> dictionary = new HashMap<>();
        dictionary.put("World", "Welt");
        dictionary.put("Modular World", "Modulare Welt");
        return new GermanGreeter(dictionary);
    }
}
