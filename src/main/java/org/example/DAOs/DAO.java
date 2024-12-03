package org.example.DAOs;

import java.util.List;

public interface DAO<T> {
    T findById(Long id);
    List<T> getAll();
    void save(T t);
    void update(Long id,T t);
    void delete(Long id);
}
