package com.example.webproject;

import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@Log
public class PatientController {
    @Autowired
    CustomValidationController customValidationController;

    @InitBinder
    protected void initBinder(final WebDataBinder binder){
        binder.addValidators(customValidationController);
    }

    @GetMapping("/")
    public String getIndex(Model model){
        return "index";
    }

    @GetMapping("/patients")
    String jspPage(Model model) {
        return "patients";
    }

    @Bean
    public MessageSource messageSource() {
        ReloadableResourceBundleMessageSource messageSource
                = new ReloadableResourceBundleMessageSource();

        messageSource.setBasename("classpath:messages");
        messageSource.setDefaultEncoding("UTF-8");
        return messageSource;
    }

    @Bean
    public LocalValidatorFactoryBean getValidator() {
        LocalValidatorFactoryBean bean = new LocalValidatorFactoryBean();
        bean.setValidationMessageSource(messageSource());
        return bean;
    }

    @GetMapping("/addPatient")
    public String getPatientCreatePage(Model model){
        model.addAttribute("patient", new Patient());
        return "addPatient";
    }
    @PostMapping("/")
    public String createPatient(@Valid @ModelAttribute("patient") Patient patient, BindingResult bindingResult){
        if (bindingResult.hasErrors()) {
            return "addPatient";
        }else{
            log.info("Patient Created");
            return "results";
        }

    }
}