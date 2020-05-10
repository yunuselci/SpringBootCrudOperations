package com.example.webproject;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import java.time.LocalDate;

@Data
@Entity
public class Patient {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @NotEmpty(message = "{firstName.notempty}")
    @Column(name = "firstName")
    private String firstName;
    @NotEmpty(message = "{surname.notempty}")
    @Column(name = "surname")
    private String surname;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @JsonFormat(pattern = "dd/MM/yyyy")
    @Column(name = "registerDate")
    private LocalDate registerDate;
    @Min(value = 12, message = "{age.min}")
    @Column(name = "age")
    private int age;
    @NotEmpty(message = "{email.notempty}")
    @Column(name = "email")
    private String email;
    @Pattern(regexp = "[0-9\\s]{12}",message = "{phone.pattern}")
    @Column(name = "phone")
    private String phone;

    public Patient() {
    }
    public Patient(String firstName,String
                   surname,LocalDate registerDate,
                   Integer age,String email,String phone) {
        this.firstName = firstName;
        this.surname = surname;
        this.registerDate = registerDate;
        this.age = age;
        this.email = email;
        this.phone = phone;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public LocalDate getRegisterDate() {
        return registerDate;
    }

    public void setRegisterDate(LocalDate registerDate) {
        this.registerDate = registerDate;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}