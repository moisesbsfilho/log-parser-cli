package com.ef;

import com.ef.cli.ParserCli;

public class Parser {

    public static void main(String[] args) {
        ParserCli.start();

        if (args.length < 1) {
            System.out.println("Illegal numbers of arguments. Please check the options passing the parameter --help");
        } else {
            ParserCli.parse(args);
        }

    }

}
