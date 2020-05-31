package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
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

    @RequestMapping(value = {""}, method = RequestMethod.GET)
    public String getAll(Model model) {
        model.addAttribute("carList", carService.getAll());
        return "car/list";
    }

    @RequestMapping(value = {"/{id}"}, method = RequestMethod.GET)
    public String get(Model model, @PathVariable("id") Long id) {
        model.addAttribute("car", carService.get(id));
        model.addAttribute("carOptionList", optionService.getOptionListByCar(carService.get(id)));
        return "car/info";
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'MODER')")
    @RequestMapping(value = {"/create"}, method = RequestMethod.GET)
    public String empty(Model model) {
        model.addAttribute("carTypeList", CarType.getAll());
        model.addAttribute("driverList", accountService.getFreeDriverList());
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
        Car car = carService.get(id);
        model.addAttribute("car", car);
        model.addAttribute("carTypeList", CarType.getAll());
        model.addAttribute("driverList", accountService.getFreeDriverList());
        model.addAttribute("carOptionList", optionService.getOptionListByCar(car));
        model.addAttribute("otherOptionList", optionService.getOtherOptionList(car));
        return "car/update";
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'MODER')")
    @RequestMapping(value = {"/update"}, method = RequestMethod.POST)
    public String update(@Valid Car car) {
        carService.update(car);
        return "redirect:";
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'MODER')")
    @RequestMapping(value = {"/addOption/{car_id}/{option_id}"}, method = RequestMethod.GET)
    public String addOption(Model model, @PathVariable("car_id") Long carId, @PathVariable("option_id") Long optionid) {
        carOptionService.create(carId, optionid);
        return "redirect:/car/update/{car_id}";
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'MODER')")
    @RequestMapping(value = {"/removeOption/{car_id}/{option_id}"}, method = RequestMethod.GET)
    public String removeOption(Model model, @PathVariable("car_id") Long carId, @PathVariable("option_id") Long optionid) {
        carOptionService.delete(carId, optionid);
        return "redirect:/car/update/{car_id}";
    }
}
