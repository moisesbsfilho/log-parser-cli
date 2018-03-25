package com.ef.cli.parser;

import com.ef.cli.CommandLine;
import com.ef.cli.option.ParserOptions;

public interface CommandLineParser {

    CommandLine parse(ParserOptions options, String[] args);

}
