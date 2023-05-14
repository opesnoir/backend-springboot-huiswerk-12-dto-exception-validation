package com.example.les12demo.controller;

import com.example.les12demo.dto.TeacherDto;
import com.example.les12demo.exception.DuplicateNameException;
import com.example.les12demo.model.Teacher;
import com.example.les12demo.repository.TeacherRepository;
import com.example.les12demo.service.TeacherService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;


@RestController
@RequestMapping("/teachers")
public class TeacherController {

    private final TeacherRepository teacherRepository; // De final variabele teacherRepository wordt geïnjecteerd in de constructor.
    private final TeacherService teacherService;

    public TeacherController(TeacherRepository teacherRepository, TeacherService teacherService) {
        this.teacherRepository = teacherRepository;
        this.teacherService = teacherService;
    }

    // Endpoint om een lijst van leraren op te halen
    @GetMapping
    public ResponseEntity<Iterable<Teacher>> getTeachers(){
        Iterable<Teacher> teachers = teacherRepository.findAll();
        return new ResponseEntity<>(teachers, HttpStatus.OK);
    }

    // Endpoint om een nieuwe leraar toe te voegen
    @PostMapping
    public ResponseEntity<Object> createTeacher(@Valid @RequestBody TeacherDto teacherDto, BindingResult br) throws DuplicateNameException {
        if (br.hasFieldErrors()) {
            // Als er fouten zijn in de validatie, geef een foutmelding terug met een lijst van de gevonden fouten
            StringBuilder sb = new StringBuilder();
            for (FieldError fe : br.getFieldErrors()) {
                sb.append(fe.getField()).append(": ");
                sb.append(fe.getDefaultMessage());
                sb.append("\n");
            }
            return ResponseEntity.badRequest().body(sb.toString());
        } else {
            // Als de validatie goed is, maak een nieuwe leraar aan en geef de ID van de nieuwe leraar terug
            Long newId = teacherService.createTeacher(teacherDto);

            // Stuur een 'created' status terug en voeg de URI van de nieuwe resource toe aan de headers van het response
            URI uri = URI.create(ServletUriComponentsBuilder
                    .fromCurrentRequest().path("/" + newId).toUriString());
            return ResponseEntity.created(uri).body(newId);
        }
    }
}

