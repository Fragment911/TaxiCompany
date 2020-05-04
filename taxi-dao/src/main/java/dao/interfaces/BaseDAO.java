package dao.interfaces;

import java.util.List;

public interface BaseDAO<T> {
    List<T> getAll();
    T get(Long id);
    void create(T t);
    void update(T t);
    void delete(Long id);
}
