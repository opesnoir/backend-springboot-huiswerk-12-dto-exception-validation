package com.example.les12demo.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

public class TeacherDto {
    public Long id;

    @NotBlank
    public String firstName;

    @Size(min=3,max=128, message = "grootte tussen 3 en 128")
    public String lastName;

    @Past
    public LocalDate dob;

    @Size(max = 10000)
    public int salary;

}
