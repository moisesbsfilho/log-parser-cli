package com.ef.core.repository;

import java.util.List;

public interface Repository<T> {

    T save(T obj);

    void saveAll(List<T> list);

    void update(T obj);

    void delete(Long id);

    T find(Long id);

    List<T> findAll();

}
