package dao.implementations;

import api.entity.Option;
import dao.interfaces.OptionDAO;
import dao.repository.OptionRepository;
import org.springframework.stereotype.Repository;

@Repository
public class OptionDAOImpl extends BaseDAOImpl<Option, OptionRepository> implements OptionDAO {
}
