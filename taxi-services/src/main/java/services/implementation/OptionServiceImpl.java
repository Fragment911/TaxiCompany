package services.implementation;

import org.springframework.stereotype.Service;
import api.entity.Option;
import dao.interfaces.OptionDAO;
import services.interfaces.OptionService;

@Service
public class OptionServiceImpl extends BaseServiceImpl<Option, OptionDAO> implements OptionService {
}
