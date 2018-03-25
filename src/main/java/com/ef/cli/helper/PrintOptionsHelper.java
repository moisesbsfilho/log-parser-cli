package com.ef.cli.helper;

import com.ef.cli.option.Options;

import java.util.Map;

public class PrintOptionsHelper {

    public void printHelper(String commandLineSyntax, Options options){
        System.out.printf("%s command line help. Please take note of parameters used by this system.\n\n", commandLineSyntax);
        System.out.printf("\t%-20s %s\n", "Options:", "Description of commands:");
        Map<String, String> optionsMap = options.getOptions().orElseThrow(() -> new IllegalStateException("Expected options not found"));
        optionsMap.entrySet().stream()
                .forEach(map -> System.out.printf("\t%-20s %s\n", map.getKey(), map.getValue()));
    }

}