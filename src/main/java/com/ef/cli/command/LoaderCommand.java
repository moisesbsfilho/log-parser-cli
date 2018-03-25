package com.ef.cli.command;

public class LoaderCommand implements ParserCommand{

    private String filePath;

    public LoaderCommand(String filePath) {
        this.filePath = filePath;
    }

    @Override
    public void execute() {

    }

}
