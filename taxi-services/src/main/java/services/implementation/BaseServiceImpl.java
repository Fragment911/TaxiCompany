package services.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import dao.interfaces.BaseDAO;

import java.util.List;

class BaseServiceImpl<T, TDAO extends BaseDAO<T>> {
    @Autowired
    TDAO tDAO;

    public void create(T t) {
        tDAO.create(t);
    }

    public T get(Long id) {
        return tDAO.get(id);
    }

    public List<T> getAll() {
        return tDAO.getAll();
    }

    public void update(T t) {
        tDAO.update(t);
    }

    public void delete(Long id) {
        tDAO.delete(id);
    }
}
