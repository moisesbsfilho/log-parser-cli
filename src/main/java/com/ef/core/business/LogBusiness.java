package com.ef.core.business;

import com.ef.core.model.Duration;
import com.ef.core.model.Log;
import com.ef.core.model.ThresholdSearch;
import com.ef.core.repository.LogRepository;
import com.ef.core.repository.Repository;
import com.ef.core.repository.ThresholdSearchRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class LogBusiness {

    private static final Logger LOGGER = LoggerFactory.getLogger(LogBusiness.class);

    private Repository repository;

    public LogBusiness(Repository repository){
        this.repository = repository;
    }

    public Map<Log, Integer> findByDateAndThreshold(LocalDateTime startDate, Duration duration, Integer threshold){

        LOGGER.debug("Find by date and threshold process triggered with values: startDate = {}, duration {}, threshold = {}", startDate, duration, threshold);
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

        Map<Log, Integer> map = new LogRepository().findByDateAndThreshold(startDate, endDate, threshold);
        storeThresholdResults(map, startDate, endDate, threshold);
        return map;
    }

    private List<ThresholdSearch> storeThresholdResults(Map<Log, Integer> map, LocalDateTime startDate, LocalDateTime endDate, Integer threshold){

        List<ThresholdSearch> thresholdSearches = map.entrySet().stream().map(element -> ThresholdSearch.newBuilder()
                .withIp(element.getKey().getIp())
                .withStartDate(startDate)
                .withEndDate(endDate)
                .withThresholdActual(element.getValue())
                .withComment("Ip filtered by maximum threshold query: " + threshold)
                .build())
                .collect(Collectors.toList());

        new ThresholdSearchRepository().saveAll(thresholdSearches);

        return thresholdSearches;

    }


}
