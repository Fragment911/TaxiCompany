package services.implementation;

import api.entity.Option;
import dao.interfaces.OptionDAO;
import org.springframework.stereotype.Service;
import services.interfaces.OptionService;

@Service
public class OptionServiceImpl extends BaseServiceImpl<Option, OptionDAO> implements OptionService {
}
