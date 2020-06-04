package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;
import api.entity.Option;
import api.interfaces.OptionService;

import javax.validation.Valid;

@Controller
@RequestMapping(value = {"option"}) // todo поубирать слэши
public class OptionController {
    @Autowired
    private OptionService optionService;

    @GetMapping(value = {""})
    public String getAll(Model model) {
        model.addAttribute("optionList", optionService.getAll());
        return "option/list";
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'MODER')")
    @GetMapping(value = {"create"})
    public String empty() {
        return "/option/create";
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'MODER')")
    @PostMapping(value = {"create"})
    public String create(@Valid Option option) {
        optionService.create(option);
        return "redirect:";
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'MODER')")
    @GetMapping(value = {"update/{id}"})
    public String edit(Model model, @PathVariable("id") Long id) {
        model.addAttribute("option", optionService.get(id));
        return "option/update";
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'MODER')")
    @PostMapping(value = {"update"})
    public String update(@Valid Option option) {
        optionService.update(option);
        return "redirect:";
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'MODER')")
    @GetMapping(value = {"remove/{id}"})
    public RedirectView delete(@PathVariable("id") Long id) {
        optionService.delete(id);
        return new RedirectView("/option");
    }
}