package com.horstmann.places;

import org.eclipse.yasson.FieldAccessStrategy;

import jakarta.json.bind.Jsonb;
import jakarta.json.bind.JsonbBuilder;
import jakarta.json.bind.JsonbConfig;

public class Demo {
    public static void main(String[] args) {
        var belgium = new Country("Belgium", 30510);

        JsonbConfig config = new JsonbConfig()
                .withPropertyVisibilityStrategy(new FieldAccessStrategy());
        Jsonb jsonb = JsonbBuilder.create(config);
        String json = jsonb.toJson(belgium);
        System.out.println(json);
    }
}
