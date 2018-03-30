package com.ef.cli.printer;

import com.ef.cli.option.Options;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Map;

public class OptionsPrinter implements Printer<Options>{

    private static final Logger LOGGER = LoggerFactory.getLogger(OptionsPrinter.class);

    private String commandLineSyntax;

    private Options options;

    public OptionsPrinter(String commandLineSyntax, Options options) {
        this.commandLineSyntax = commandLineSyntax;
        this.options = options;
    }

    public void print(){
        LOGGER.debug("Printing command line options in console");
        System.out.printf("%s command line help. Please take note of parameters used by this system.\n\n", commandLineSyntax);
        System.out.printf("\t%-20s %s\n", "Options:", "Description of commands:");
        Map<String, String> optionsMap = options.getOptions().orElseThrow(() -> new IllegalStateException("Expected options not found"));
        optionsMap.entrySet().stream()
                .forEach(map -> System.out.printf("\t%-20s %s\n", map.getKey(), map.getValue()));
        System.out.printf("\n");
    }

}