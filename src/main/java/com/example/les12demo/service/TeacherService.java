package com.example.les12demo.service;

import org.springframework.stereotype.Service;
import com.example.les12demo.exception.ResourceNotFoundException;
import com.example.les12demo.model.Teacher;
import com.example.les12demo.repository.TeacherRepository;
import com.example.les12demo.dto.TeacherDto;


@Service // De annotatie @Service vertelt Spring dat deze klasse een servicecomponent is.
public class TeacherService {

    private final TeacherRepository repos; // De final variabele repos wordt geïnjecteerd in de constructor.

    public TeacherService(TeacherRepository repos) {
        this.repos = repos; // De constructor slaat de geïnjecteerde repository op in een variabele.
    }

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

    public Long createTeacher(TeacherDto teacherDto) {
        // Een nieuwe Teacher wordt gemaakt en gevuld met de gegevens uit de TeacherDto.
        Teacher teacher = new Teacher();
        teacher.setFirstName(teacherDto.firstName);
        teacher.setLastName(teacherDto.lastName);
        teacher.setDob(teacherDto.dob);

        // De nieuwe Teacher wordt opgeslagen in de repository.
        repos.save(teacher);

        return teacher.getId(); // Het ID van de opgeslagen Teacher wordt geretourneerd.
    }
}
