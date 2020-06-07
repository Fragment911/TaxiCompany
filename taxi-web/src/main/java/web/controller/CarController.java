package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import api.entity.*;
import api.interfaces.*;

import javax.validation.Valid;

@Controller
@RequestMapping("car")
public class CarController {
    @Autowired
    private CarService carService;
    @Autowired
    private OptionService optionService;
    @Autowired
    private CarOptionService carOptionService;
    @Autowired
    private AccountService accountService;

    @GetMapping("")
    public String getAll(Model model) {
        model.addAttribute("carList", carService.getAll());
        return "car/list";
    }

    @GetMapping("{id}")
    public String get(Model model, @PathVariable("id") Long id) {
        model.addAttribute("car", carService.get(id));
        model.addAttribute("carOptionList", optionService.getOptionListByCar(carService.get(id)));
        return "car/info";
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'MODER')")
    @GetMapping("create")
    public String empty(Model model) {
        model.addAttribute("carTypeList", CarType.getAll());
        model.addAttribute("driverList", accountService.getFreeDriverList());
        return "/car/create";
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'MODER')")
    @PostMapping("create")
    public String create(@Valid Car car) {
        carService.create(car);
        return "redirect:";
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'MODER')")
    @GetMapping("update/{id}")
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
    @PostMapping("update")
    public String update(@Valid Car car) {
        carService.update(car);
        return "redirect:";
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'MODER')")
    @GetMapping("addOption/{car_id}/{option_id}")
    public String addOption(Model model, @PathVariable("car_id") Long carId, @PathVariable("option_id") Long optionid) {
        carOptionService.create(carId, optionid);
        return "redirect:/car/update/{car_id}";
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'MODER')")
    @GetMapping("removeOption/{car_id}/{option_id}")
    public String removeOption(Model model, @PathVariable("car_id") Long carId, @PathVariable("option_id") Long optionid) {
        carOptionService.delete(carId, optionid);
        return "redirect:/car/update/{car_id}";
    }
}
