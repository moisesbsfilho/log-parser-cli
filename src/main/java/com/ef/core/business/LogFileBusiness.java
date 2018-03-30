package com.ef.core.business;

import com.ef.core.loader.FileLoader;
import com.ef.core.model.Log;
import com.ef.core.repository.Repository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public class LogFileBusiness {

    private static final Logger LOGGER = LoggerFactory.getLogger(LogFileBusiness.class);

    private FileLoader fileLoader;

    private String filePath;

    private Repository repository;

    private final String DEFAULT_SEPARATOR = "\\|";

    public LogFileBusiness(FileLoader fileLoader, String filePath, Repository repository) {
        this.fileLoader = fileLoader;
        this.filePath = filePath;
        this.repository = repository;
    }

    public boolean load(String fileSeparator) {
        LOGGER.debug("Load file process triggered with values: filePath = {}, fileSeparator = {}", filePath, fileSeparator);
        List<Log> logs = processAllLines(fileLoader.readLines(this.filePath), fileSeparator);
        repository.saveAll(logs);
        return true;
    }

    private List<Log> processAllLines(List<String> lines, final String fileSeparator) {

        LOGGER.debug("Process each lines of the file");
        final String separator = fileSeparator.isEmpty() ? DEFAULT_SEPARATOR : fileSeparator;

        final DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS");

        Function<String, Log> transformer = line -> {

            String[] data = line.split(separator);
            return Log.newBuilder()
                    .withRequestDate(LocalDateTime.parse(data[0], format))
                    .withIp(data[1])
                    .withHttpMethod(data[2].replaceAll("\"", ""))
                    .withStatusCode(Integer.valueOf(data[3]))
                    .withUserAgent(data[4].replaceAll("\"", ""))
                    .build();
        };

        return lines.stream()
                .map(transformer)
                .collect(Collectors.toList());
    }
}
