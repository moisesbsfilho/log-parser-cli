package com.ef.cli.printer;

import com.ef.core.model.ThresholdSearch;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class ThresholdResultsPrinter implements Printer<ThresholdSearch>{

    private static final Logger LOGGER = LoggerFactory.getLogger(ThresholdResultsPrinter.class);

    private List<ThresholdSearch> list;

    private Integer threshold;

    public ThresholdResultsPrinter(List<ThresholdSearch> list, Integer threshold) {
        this.list = list;
        this.threshold = threshold;
    }

    @Override
    public void print() {
        LOGGER.debug("Printing threshold results in console");
        System.out.printf("Found the results below for threshold > %s.\n\n", threshold);
        System.out.printf("\t%-20s %s\n", "Ip:", "Total threshold:");
        list.stream()
                .forEach(thresholdSearch -> System.out.printf("\t%-20s %s\n", thresholdSearch.getIp(), thresholdSearch.getThresholdActual()));
    }
}
