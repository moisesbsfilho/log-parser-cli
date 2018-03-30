package com.ef.core.business;

import com.ef.core.model.Log;
import com.ef.core.repository.LogRepository;
import com.ef.core.repository.Repository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDateTime;
import java.util.Map;

public class LogBusiness {

    private static final Logger LOGGER = LoggerFactory.getLogger(LogBusiness.class);

    private Repository repository;

    public LogBusiness(Repository repository){
        this.repository = repository;
    }

    public Map<Log, Integer> findByDateAndThreshold(LocalDateTime startDate, LocalDateTime endDate, Integer threshold){
        LOGGER.debug("Find by date and threshold process triggered with values: startDate = {}, endDate = {}, threshold = {}", startDate, endDate, threshold);
        Map<Log, Integer> map = new LogRepository().findByDateAndThreshold(startDate, endDate, threshold);
        return map;
    }

}
