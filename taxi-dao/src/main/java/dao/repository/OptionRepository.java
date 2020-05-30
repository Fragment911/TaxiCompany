package dao.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import api.entity.Option;

@Repository
public interface OptionRepository extends JpaRepository<Option, Long> {

}
