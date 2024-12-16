package org.example.DAOs;

import java.util.List;

public interface DAO<T> {
    T findById(Long id);
    List<T> getAll();
    T save(T t);
    void update(T t);
    void delete(Long id);
}
