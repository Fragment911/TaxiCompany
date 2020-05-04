package services.interfaces;

import java.util.List;

public interface BaseService<T> {
    List<T> getAll();
    T get(Long id);
    void create(T t);
    void update(T t);
    void delete(Long id);
}
