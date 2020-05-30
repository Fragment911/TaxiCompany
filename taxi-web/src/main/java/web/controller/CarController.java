package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.view.RedirectView;
import api.entity.*;
import services.interfaces.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping(value = {"/car"})
public class CarController {
    @Autowired
    private CarService carService;
    @Autowired
    private OptionService optionService;
    @Autowired
    private CarOptionService carOptionService;
    @Autowired
    private AccountService accountService;
    @Autowired
    private OrderService orderService;

    @RequestMapping(value = {""}, method = RequestMethod.GET)
    public String getAll(Model model) {
        model.addAttribute("carList", carService.getAll());
        return "car/list";
    }

    @RequestMapping(value = {"/{id}"}, method = RequestMethod.GET)
    public String get(Model model, @PathVariable("id") Long id) {
        model.addAttribute("car", carService.get(id));
        return "car/info";
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'MODER')")
    @RequestMapping(value = {"/create"}, method = RequestMethod.GET)
    public String empty(Model model) {
        model.addAttribute("carTypeList", CarType.getAll());
        model.addAttribute("accountList", accountService.getAll());
        return "/car/create";
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'MODER')")
    @RequestMapping(value = {"/create"}, method = RequestMethod.POST)
    public String create(@Valid Car car) {
        carService.create(car);
        return "redirect:";
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'MODER')")
    @RequestMapping(value = {"/update/{id}"}, method = RequestMethod.GET)
    public String edit(Model model, @PathVariable("id") Long id) {
        model.addAttribute("car", carService.get(id));
        model.addAttribute("carTypeList", CarType.getAll());
        model.addAttribute("accountList", accountService.getAll());
        List<Option> optionList = new ArrayList<>();
        List<CarOption> carOptionList = carOptionService.getByCar(carService.get(id));
        for (CarOption carOption: carOptionService.getByCar(carService.get(id))) {
            optionList.add(optionService.get(carOption.getOption().getId()));
        }
        model.addAttribute("optionList", optionList);
        List<Option> optionList1 = new ArrayList<>();
        for (Option option: optionService.getAll()) {
            if (!carService.get(id).getOptionList().stream().map(x -> x.getId()).collect(Collectors.toList()).contains(option.getId())) {
                optionList1.add(option);
            }
        }
        model.addAttribute("optionList1", optionList1);
        return "car/update";
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'MODER')")
    @RequestMapping(value = {"/update"}, method = RequestMethod.POST)
    public String update(@Valid Car car) {
        carService.update(car);
        return "redirect:";
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'MODER')")
    @RequestMapping(value = {"/addOption/{id}/{id1}"}, method = RequestMethod.GET)
    public String addOption(Model model, @PathVariable("id") Long id, @PathVariable("id1") Long id1) {
        Car car = carService.get(id);
        Option option = optionService.get(id1);
        CarOption carOption = new CarOption(carService.get(id), optionService.get(id1));
        carOptionService.create(carOption);
        return "redirect:/car/update/{id}";
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'MODER')")
    @RequestMapping(value = {"/removeOption/{id}/{id1}"}, method = RequestMethod.GET)
    public String removeOption(Model model, @PathVariable("id") Long id, @PathVariable("id1") Long id1) {
        Car car = carService.get(id);
        Option option = optionService.get(id1);
        for (CarOption carOption: carOptionService.getByCarAndOption(carService.get(id), optionService.get(id1))) {
            carOptionService.delete(carOption.getId());
        }
        return "redirect:/car/update/{id}";
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'MODER')")
    @RequestMapping(value = {"/remove/{id}"}, method = RequestMethod.GET)
    public RedirectView delete(@PathVariable("id") Long id) {
        Car car = carService.get(id);
        if (CollectionUtils.isEmpty(car.getOrderList())) {
            carService.delete(id);
        }
        return new RedirectView("/car");
    }
}
