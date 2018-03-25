package com.ef.cli.parser;

import com.ef.cli.CommandLine;
import com.ef.cli.option.ParserOptions;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class DefaultParse implements CommandLineParser{

    @Override
    public CommandLine parse(ParserOptions options, String[] args) {
        List<String> parameters = Arrays.asList(args);
        Map<String, String> parametersMap = parameters.stream()
                .collect(Collectors.toMap(element -> element.split("=")[0], element -> element.split("=").length > 1 ? element.split("=")[1] : ""));
        return new CommandLine(options, parametersMap);
    }
}
