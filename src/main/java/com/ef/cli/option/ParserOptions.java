package com.ef.cli.option;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class ParserOptions implements Options{

    private static ParserOptions parserOptions;

    private static Map<String, String> options;

    private ParserOptions() {
        options = new HashMap<>();
    }

    public static ParserOptions getInstance() {
        if (parserOptions == null) {
            parserOptions = new ParserOptions();
        }
        return parserOptions;
    }

    @Override
    public void addOption(String opt, String description) {
        options.put(opt, description);
    }

    @Override
    public boolean hasOption(String opt) {
        return options.entrySet().stream()
                .anyMatch(map -> map.getKey().equals(opt));

    }

    @Override
    public Optional<Map<String, String>> getOptions() {
        return Optional.of(options);
    }

}
