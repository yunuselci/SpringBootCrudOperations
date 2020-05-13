package com.example.webproject;

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
@RequestMapping("/patients/")
public class PatientController {
    private final PatientRepository patientRepository;

    @Autowired
    public PatientController(PatientRepository patientRepository){
        this.patientRepository = patientRepository;
    }


    @Autowired
    CustomValidationController customValidationController;

    @InitBinder
    protected void initBinder(final WebDataBinder binder){
        binder.addValidators(customValidationController);
    }

    @GetMapping("patientlist")
    public String getPatientList(Model model){
        return "patientList";
    }

    @GetMapping("/")
    public String getIndex() {
        return "index";
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

    @GetMapping("signup")
    public String getPatientCreatePage(Model model){
        model.addAttribute("patient", new Patient());
        return "addPatient";
    }

    @GetMapping("list")
    public String showUpdateForm(Model model) {
        model.addAttribute("patients", patientRepository.findAll());
        return "patientList";
    }

    @GetMapping("edit/{id}")
    public String showUpdateForm(@PathVariable("id") long id, Model model) {
        Patient patient = patientRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid patient Id:" + id));
        model.addAttribute("patient", patient);
        return "updatePatient";
    }
    @PostMapping("update/{id}")
    public String updatePatient(@PathVariable("id") long id, @Valid Patient patient, BindingResult result,
                                Model model) {
        if (result.hasErrors()) {
            patient.setId(id);
            return "updatePatient";
        }

        patientRepository.save(patient);
        model.addAttribute("patients", patientRepository.findAll());
        return "patientList";
    }


    @PostMapping("add")
    public String createPatient(@Valid @ModelAttribute("patient") Patient patient, BindingResult bindingResult){
        if (bindingResult.hasErrors()) {
            return "addPatient";
        }else{
            patientRepository.save(patient);
            return "redirect:list";
        }

    }

    @GetMapping("delete/{id}")
    public String deletePatient(@PathVariable("id") long id, Model model) {
        Patient patient = patientRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid Patient Id:" + id));
        patientRepository.delete(patient);
        model.addAttribute("patients", patientRepository.findAll());
        return "patientList";
    }
}