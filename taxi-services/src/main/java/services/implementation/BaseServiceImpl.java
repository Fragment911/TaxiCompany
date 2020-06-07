package services.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

class BaseServiceImpl<T, TRepository extends JpaRepository<T, Long>> {
    @Autowired
    TRepository tRepository;

    public void create(T t) {
        tRepository.save(t);
    }

    public T get(Long id) {
        return tRepository.findOne(id);
    }

    public List<T> getAll() {
        return tRepository.findAll();
    }

    public void update(T t) {
        tRepository.save(t);
    }

    public void delete(Long id) {
        tRepository.delete(id);
    }
}
