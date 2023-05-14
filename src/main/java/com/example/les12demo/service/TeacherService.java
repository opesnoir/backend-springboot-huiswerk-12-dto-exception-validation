package com.example.les12demo.service;

import com.example.les12demo.exception.DuplicateNameException;
import org.springframework.stereotype.Service;
import com.example.les12demo.exception.ResourceNotFoundException;
import com.example.les12demo.model.Teacher;
import com.example.les12demo.repository.TeacherRepository;
import com.example.les12demo.dto.TeacherDto;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service // De annotatie @Service vertelt Spring dat deze klasse een servicecomponent is.
public class TeacherService {

    //variabel
    private final TeacherRepository repos; // De final variabele repos wordt geïnjecteerd in de constructor.

    //constructor
    public TeacherService(TeacherRepository repos) {
        this.repos = repos;
    }


    // een enkele teacher ophalen
    public TeacherDto getTeacher(Long id) {
        // Hier wordt de Teacher met de gegeven id gezocht in de repository.
        // Als de Teacher niet gevonden wordt, wordt er een ResourceNotFoundException gegooid.
        Teacher t = repos.findById(id).orElseThrow(() -> new ResourceNotFoundException("Teacher not found"));

        // Een nieuwe TeacherDto wordt gemaakt en gevuld met de gegevens van de gevonden Teacher.
        TeacherDto teacherDto = new TeacherDto();
        teacherDto.id = t.getId();
        teacherDto.firstName = t.getFirstName();
        teacherDto.lastName = t.getLastName();
        teacherDto.dob = t.getDob();

        return teacherDto; // De gevulde TeacherDto wordt geretourneerd.
    }

    public List<TeacherDto> findAllTeachers() {
        List<Teacher> teachers = (List<Teacher>) repos.findAll();
        List<TeacherDto> teacherDtos = new ArrayList<>();

        for (Teacher t : teachers) {
            TeacherDto teacherDto = new TeacherDto();
            teacherDto.id = t.getId();
            teacherDto.firstName = t.getFirstName();
            teacherDto.lastName = t.getLastName();
            teacherDto.dob = t.getDob();
            teacherDtos.add(teacherDto);
        }

        return teacherDtos;
    }

    public Long createTeacher(TeacherDto teacherDto) throws DuplicateNameException {
        // Een nieuwe Teacher wordt gemaakt en gevuld met de gegevens uit de TeacherDto.
        Optional<Teacher> teacherOptional = repos.findByFirstNameAndLastName(teacherDto.firstName, teacherDto.lastName);
        if (teacherOptional.isPresent()) {
            throw new DuplicateNameException("Er bestaat al een docent met dezelfde naam");
        } else {
            Teacher teacher = new Teacher();
            teacher.setFirstName(teacherDto.firstName);
            teacher.setLastName(teacherDto.lastName);
            teacher.setDob(teacherDto.dob);

            // De nieuwe Teacher wordt opgeslagen in de repository.
            repos.save(teacher);

            return teacher.getId(); // Het ID van de opgeslagen Teacher wordt geretourneerd.
        }
    }
}
