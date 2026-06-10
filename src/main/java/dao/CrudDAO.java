package dao;

import java.util.List;
import java.util.Optional;

public interface CrudDAO<T> {
    List<T> findAll();
    Optional<T> findById(int id);
    int save(T entity);
    void update(int id, T entity);
    void delete(int id);
    boolean existsById(int id);
}
