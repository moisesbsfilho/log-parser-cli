package com.ef.cli.helper;

import com.ef.cli.option.Options;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

public class PrintOptionsHelper {

    private static final Logger LOGGER = LoggerFactory.getLogger(PrintOptionsHelper.class);

    public void printHelper(String commandLineSyntax, Options options){
        LOGGER.debug("Printing command line options in console");
        System.out.printf("%s command line help. Please take note of parameters used by this system.\n\n", commandLineSyntax);
        System.out.printf("\t%-20s %s\n", "Options:", "Description of commands:");
        Map<String, String> optionsMap = options.getOptions().orElseThrow(() -> new IllegalStateException("Expected options not found"));
        optionsMap.entrySet().stream()
                .forEach(map -> System.out.printf("\t%-20s %s\n", map.getKey(), map.getValue()));
    }

}