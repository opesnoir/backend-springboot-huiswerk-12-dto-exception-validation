package com.example.les12demo.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.time.LocalDate;

@Entity
@Table(name="teachers")
public class Teacher {
    //variabelen (waaronder primary key variabel) opslaan

    //primary key initiëren
    @Id
    @GeneratedValue
    private Long id;
    //overige variabelen initiëren
    private String firstName;
    private String lastName;
    private LocalDate dob;
    private int salary;


    //constructors toevoegen
    public Teacher(){

    }

    public Teacher(Long id, String firstName, String lastName, LocalDate dob, int salary) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.dob = dob;
        this.salary = salary;
    }

    //getters en setters toevoegen
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public LocalDate getDob() {
        return dob;
    }

    public void setDob(LocalDate dob) {
        this.dob = dob;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }
}
