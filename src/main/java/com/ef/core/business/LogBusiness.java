package com.ef.core.business;

import com.ef.core.model.Duration;
import com.ef.core.model.Log;
import com.ef.core.repository.LogRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

public class LogBusiness {

    private static final Logger LOGGER = LoggerFactory.getLogger(LogBusiness.class);

    private LogRepository repository;

    public LogBusiness(LogRepository repository){
        this.repository = repository;
    }

    public Map<Log, Integer> findByDateAndThreshold(LocalDateTime startDate, Duration duration, Integer threshold){
        LOGGER.debug("Find by date and threshold process triggered with values: startDate = {}, duration {}, threshold = {}", startDate, duration, threshold);
        Map<Log, Integer> map = new HashMap<>();
        switch (duration){
            case DAILY:
                map = repository.findByDateAndThreshold(startDate, startDate.plusDays(1), threshold);
                break;
            case HOURLY:
                map = repository.findByDateAndThreshold(startDate, startDate.plusHours(1), threshold);
                break;
            default:
                System.out.println("Invalid duration. Please check the options passing the parameter --help.");
        }
        return map;
    }


}
