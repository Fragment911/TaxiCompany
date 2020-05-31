package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.view.RedirectView;
import api.entity.Option;
import services.interfaces.OptionService;

import javax.validation.Valid;

@Controller
@RequestMapping(value = {"/option"})
public class OptionController {
    @Autowired
    private OptionService optionService;

    @RequestMapping(value = {""}, method = RequestMethod.GET)
    public String getAll(Model model) {
        model.addAttribute("optionList", optionService.getAll());
        return "option/list";
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'MODER')")
    @RequestMapping(value = {"/create"}, method = RequestMethod.GET)
    public String empty() {
        return "/option/create";
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'MODER')")
    @RequestMapping(value = {"/create"}, method = RequestMethod.POST)
    public String create(@Valid Option option) {
        optionService.create(option);
        return "redirect:";
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'MODER')")
    @RequestMapping(value = {"/update/{id}"}, method = RequestMethod.GET)
    public String edit(Model model, @PathVariable("id") Long id) {
        model.addAttribute("option", optionService.get(id));
        return "option/update";
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'MODER')")
    @RequestMapping(value = {"/update"}, method = RequestMethod.POST)
    public String update(@Valid Option option) {
        optionService.update(option);
        return "redirect:";
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'MODER')")
    @RequestMapping(value = {"/remove/{id}"}, method = RequestMethod.GET)
    public RedirectView delete(@PathVariable("id") Long id) {
        optionService.delete(id);
        return new RedirectView("/option");
    }
}