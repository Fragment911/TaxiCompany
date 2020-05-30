package dao.implementations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import dao.interfaces.BaseDAO;

import java.util.ArrayList;
import java.util.List;

abstract class BaseDAOImpl<T, TRepository extends CrudRepository<T, Long>> implements BaseDAO<T> {
    @Autowired
    TRepository tRepository;

    public void create(T t) {
        tRepository.save(t);
    }

    public T get(Long id) {
        return tRepository.findOne(id);
    }

    public List<T> getAll() {
        List<T> tList = new ArrayList<>();
        for (T t: tRepository.findAll()) {
            tList.add(t);
        }
        return tList;
    }

    public void update(T t) {
        tRepository.save(t);
    }

    public void delete(Long id) {
        tRepository.delete(id);
    }
}
