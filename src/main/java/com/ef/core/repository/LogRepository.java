package com.ef.core.repository;

import com.ef.core.business.LogFileBusiness;
import com.ef.core.factory.ConnectionFactory;
import com.ef.core.model.Log;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.ef.core.model.Log.Builder;
import static com.ef.core.model.Log.newBuilder;

public class LogRepository implements Repository<Log>{

    private static final Logger LOGGER = LoggerFactory.getLogger(LogFileBusiness.class);

    private Connection connection;

    public LogRepository() {
        this.connection = ConnectionFactory.getConnection();
    }

    public Map<Log, Integer> findByDateAndThreshold(LocalDateTime startDate, LocalDateTime endDate, Integer threshold) {

        Builder logBuilder = newBuilder();
        Map<Log, Integer> map = new HashMap<>();

        String query = "select count(l.ip) as count, l.ip from logs as l " +
                "where request_date between ? and ? " +
                "group by l.ip " +
                "having count(l.ip) > ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)){

            preparedStatement.setTimestamp(1, Timestamp.valueOf(startDate));
            preparedStatement.setTimestamp(2, Timestamp.valueOf(endDate));
            preparedStatement.setInt(3, threshold);
            ResultSet result = preparedStatement.executeQuery();
            while (result.next()){
                Log log = logBuilder
                        .withIp(result.getString("ip"))
                        .build();
                map.put(log, result.getInt("count"));
            }
            LOGGER.debug("Found {} logs records with parameters passed", map.size());
            connection.close();

        } catch (SQLException e) {
            LOGGER.error("Error on try to execute query", e);
        }
        return map;
    }

    @Override
    public Log save(Log obj) {
        throw new UnsupportedOperationException("Method is not implemented");
    }

    @Override
    public void saveAll(List<Log> list) {

        String query ="insert into logs (ip, status_code, http_method, user_agent, request_date) " +
                "values (?, ?, ?, ?, ?)";

        try (final PreparedStatement preparedStatement = connection.prepareStatement(query)){

            connection.setAutoCommit(false);

            list.stream().forEach(log -> {
                try {
                    preparedStatement.setString(1, log.getIp());
                    preparedStatement.setInt(2, log.getStatusCode());
                    preparedStatement.setString(3, log.getHttpMethod());
                    preparedStatement.setString(4, log.getUserAgent());
                    preparedStatement.setTimestamp(5, Timestamp.valueOf(log.getRequestDate()));
                    System.out.println(preparedStatement);
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
    public void update(Log obj) {
        throw new UnsupportedOperationException("Method is not implemented");
    }

    @Override
    public void delete(Long id) {
        throw new UnsupportedOperationException("Method is not implemented");
    }

    @Override
    public Log find(Long id) {
        throw new UnsupportedOperationException("Method is not implemented");
    }

    @Override
    public List<Log> findAll() {
        throw new UnsupportedOperationException("Method is not implemented");
    }

}
