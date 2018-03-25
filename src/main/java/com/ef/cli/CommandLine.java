package com.ef.cli;

import com.ef.cli.option.Options;

import java.util.Map;

public class CommandLine {

    private Options options;

    private Map<String, String> parametersMap;

    public CommandLine(Options options, Map<String, String> parametersMap) {
        this.options = options;
        this.parametersMap = parametersMap;
    }


    public boolean hasOption(String option) {
        return options.hasOption(option) && parametersMap.entrySet().stream().anyMatch(map -> map.getKey().equals(option));
    }

    public Object getValue(String option) {
        return parametersMap.entrySet().stream()
                .filter(map -> map.getKey().equals(option))
                .map(Map.Entry::getValue)
                .findFirst();
    }
}
