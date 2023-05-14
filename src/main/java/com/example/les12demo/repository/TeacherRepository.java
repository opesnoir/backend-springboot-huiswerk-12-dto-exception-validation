package com.example.les12demo.repository;

import com.example.les12demo.model.Teacher;
import org.springframework.data.repository.CrudRepository;

import java.time.LocalDate;
import java.util.Optional;

public interface TeacherRepository extends CrudRepository<Teacher, Long> {

    Iterable<Teacher> findByDobBefore(LocalDate date);

    Optional<Teacher> findByFirstNameAndLastName(String firstName, String lastName);
}
