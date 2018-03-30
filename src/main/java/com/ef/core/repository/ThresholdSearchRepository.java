package com.ef.core.repository;

import com.ef.core.factory.ConnectionFactory;
import com.ef.core.model.ThresholdSearch;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

public class ThresholdSearchRepository implements Repository<ThresholdSearch>{

    private static final Logger LOGGER = LoggerFactory.getLogger(ThresholdSearchRepository.class);

    private Connection connection;

    public ThresholdSearchRepository() {
        this.connection = ConnectionFactory.getConnection();
    }

    @Override
    public ThresholdSearch save(ThresholdSearch obj) {
        throw new UnsupportedOperationException("Method is not implemented");
    }

    @Override
    public void saveAll(List<ThresholdSearch> list) {

        String query ="insert into wallet_hub.threshold_searches (threshold_actual, ip, start_date, end_date, comment) " +
                "values (?, ?, ?, ?, ?)";

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)){

            connection.setAutoCommit(false);

            list.stream().forEach(thresholdSearch -> {
                try {
                    preparedStatement.setInt(1, thresholdSearch.getThresholdActual());
                    preparedStatement.setString(2, thresholdSearch.getIp());
                    preparedStatement.setTimestamp(3, Timestamp.valueOf(thresholdSearch.getEndDate()));
                    preparedStatement.setTimestamp(4, Timestamp.valueOf(thresholdSearch.getEndDate()));
                    preparedStatement.setString(5, thresholdSearch.getComment());
                    preparedStatement.addBatch();
                } catch (SQLException e) {
                    e.printStackTrace();
                }

            });
            preparedStatement.executeBatch();
            connection.commit();

        } catch (SQLException e) {
            LOGGER.error("Error on try to execute batch query", e);
        }
    }

    @Override
    public void update(ThresholdSearch obj) {
        throw new UnsupportedOperationException("Method is not implemented");
    }

    @Override
    public void delete(Long id) {
        throw new UnsupportedOperationException("Method is not implemented");
    }

    @Override
    public ThresholdSearch find(Long id) {
        throw new UnsupportedOperationException("Method is not implemented");
    }

    @Override
    public List<ThresholdSearch> findAll() {
        throw new UnsupportedOperationException("Method is not implemented");
    }
}
