package com.example.webproject;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import java.time.LocalDate;

@Data
public class Patient {
    @NotEmpty(message = "{firstName.notempty}")
    private String firstName;
    @NotEmpty(message = "{surname.notempty}")
    private String surname;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate registerDate;
    @Min(value = 12, message = "{age.min}")
    private int age;
    @NotEmpty(message = "{email.notempty}")
    private String email;
    @Pattern(regexp = "[0-9\\s]{12}",message = "{phone.pattern}")
    private String phone;
}