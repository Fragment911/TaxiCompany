package services.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import dao.interfaces.BaseDAO;

import java.util.ArrayList;
import java.util.List;

class BaseServiceImpl<T, TDAO extends BaseDAO<T>> {
    @Autowired
    TDAO tDAO;

    @Transactional
    public void create(T t) {
        tDAO.create(t);
    }

    @Transactional
    public T get(Long id) {
        T t;
        t = tDAO.get(id);
        return t;
    }

    @Transactional
    public List<T> getAll() {
        List<T> tList = new ArrayList<>();
        for (T t: tDAO.getAll()) {
            tList.add(t);
        }
        return tList;
    }

    @Transactional
    public void update(T t) {
        tDAO.update(t);
    }

    @Transactional
    public void delete(Long id) {
        tDAO.delete(id);
    }
}
