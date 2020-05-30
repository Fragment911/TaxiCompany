package dao.implementations;

import org.springframework.stereotype.Repository;
import api.entity.Option;
import dao.interfaces.OptionDAO;
import dao.repository.OptionRepository;

@Repository
public class OptionDAOImpl extends BaseDAOImpl<Option, OptionRepository> implements OptionDAO {
}
