package com.ef;

import com.ef.cli.ParserCli;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Parser {

    private static final Logger LOGGER = LoggerFactory.getLogger(Parser.class);

    public static void main(String[] args) {

        LOGGER.debug(">>>>>> Starting ParcerCli command line" );
        System.out.println("Starting process parameters...");
        ParserCli.start();

        if (args.length < 1) {
            System.out.println("Illegal numbers of arguments. Please check the options passing the parameter --help");
        } else {
            ParserCli.parse(args);
        }

    }

}
