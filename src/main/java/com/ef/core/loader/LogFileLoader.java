package com.ef.core.loader;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class LogFileLoader implements FileLoader {

    private static final Logger LOGGER = LoggerFactory.getLogger(LogFileLoader.class);

    @Override
    public List<String> readLines(String path) {
        List<String> lines = new ArrayList<>();
        try (Stream<String> stream = Files.lines(Paths.get(path))) {
            lines = stream
                        .filter(line -> !line.isEmpty())
                        .collect(Collectors.toList());
        } catch (IOException e) {
            LOGGER.error("Error on try to load file", e);
        }
        return lines;
    }

}
