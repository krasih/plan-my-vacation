package com.example.planmyvacation.web;

import com.example.planmyvacation.model.dto.CareerDTO;
import com.example.planmyvacation.service.CareerService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/api/careers")
public class CareerController {

    private final CareerService careerService;

    public CareerController(CareerService careerService) {

        this.careerService = careerService;
    }

    @ModelAttribute("careersData")
    public CareerDTO allCareers() { return new CareerDTO(); }

    @ModelAttribute("careerData")
    public CareerDTO aCareer() { return new CareerDTO(); }


    @GetMapping(value = "" )
    public String findAll(Model model) {

        model.addAttribute("careersData", careerService.findAll());

        return "jobs";
    }

    @GetMapping(value = "", headers = "hx-request=true" )
    public String findAllHTMX(Model model) {

        model.addAttribute("careersData", careerService.findAll());

        return "fragments/careers :: careers";
    }

    @GetMapping("/{id}")
    public String findById( @PathVariable("id") Long id, Model model ) {

        model.addAttribute("careerData", careerService.findById( id ));

        return "fragments/modals :: editCareerFragment";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") Long id) {

        careerService.delete( id );

        return "redirect:/api/careers";
    }

    @PostMapping("/{id}")
    public String create(
            @Valid CareerDTO careerDTO,
            BindingResult bindingResult,
            RedirectAttributes rAtt) {

        if(bindingResult.hasErrors()){
            rAtt.addFlashAttribute("careerData", careerDTO);
            rAtt.addFlashAttribute("org.springframework.validation.BindingResult.careerData", bindingResult);
            return "redirect:/api/careers/add";
        }

        careerService.create(careerDTO);

        return "redirect:/api/careers";
    }

    @PutMapping("/{id}")
    public String update( Model model,
            @PathVariable("id") Long id,
            @Valid CareerDTO careerDTO,
            BindingResult bindingResult,
            RedirectAttributes rAtt) {

        if(bindingResult.hasErrors()){
            rAtt.addFlashAttribute("careerData", careerDTO);
            rAtt.addFlashAttribute("org.springframework.validation.BindingResult.careerData", bindingResult);
            return "redirect:/api/careers";
        }

        careerService.update(id, careerDTO);

        model.addAttribute("careersData", careerService.findAll());

        return "fragments/careers :: careers";
    }
}
