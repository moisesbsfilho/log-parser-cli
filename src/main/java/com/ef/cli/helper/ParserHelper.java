package com.ef.cli.helper;

import com.ef.core.model.Duration;
import com.ef.core.model.Log;
import com.ef.core.model.ThresholdSearch;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public abstract class ParserHelper {

    public static LocalDateTime getEndDateByDuration(LocalDateTime startDate, Duration duration){
        LocalDateTime endDate = LocalDateTime.now();
        switch (duration){
            case DAILY:
                endDate = startDate.plusDays(1);
                break;
            case HOURLY:
                endDate = startDate.plusHours(1);
                break;
            default:
                System.out.println("Invalid duration. Please check the options passing the parameter --help.");
        }
        return endDate.minusSeconds(1);
    }

    public static List<ThresholdSearch> getThresholdSearchesFromLogMap(Map<Log, Integer> map, LocalDateTime startDate, LocalDateTime endDate, Integer threshold){
        return map.entrySet().stream().map(element -> ThresholdSearch.newBuilder()
                .withIp(element.getKey().getIp())
                .withStartDate(startDate)
                .withEndDate(endDate)
                .withThresholdActual(element.getValue())
                .withComment("Ip filtered by maximum threshold query: " + threshold)
                .build())
                .collect(Collectors.toList());
    }

}
