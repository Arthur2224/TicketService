package org.example.DAOs;

import java.util.List;
import java.util.Optional;

public interface DAO<T> {
    Optional<T> findById(long id);
    List<T> getAll();
    void save(T t);
    void update(T t, T temp);
    void delete(T t);
}
