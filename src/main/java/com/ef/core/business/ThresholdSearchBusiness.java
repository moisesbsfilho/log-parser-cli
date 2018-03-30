package com.ef.core.business;

import com.ef.core.model.ThresholdSearch;
import com.ef.core.repository.Repository;
import com.ef.core.repository.ThresholdSearchRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class ThresholdSearchBusiness {

    private static final Logger LOGGER = LoggerFactory.getLogger(ThresholdSearchBusiness.class);

    private Repository repository;

    public ThresholdSearchBusiness(Repository repository) {
        this.repository = repository;
    }

    public Boolean saveAll(List<ThresholdSearch> thresholdSearches){
        LOGGER.debug("Call repository to save list");
        new ThresholdSearchRepository().saveAll(thresholdSearches);
        return true;

    }

}
