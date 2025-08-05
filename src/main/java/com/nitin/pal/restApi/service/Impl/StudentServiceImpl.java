package com.nitin.pal.restApi.service.Impl;

import com.nitin.pal.restApi.dto.AddStudentRequestDto;
import com.nitin.pal.restApi.dto.StudentDto;
import com.nitin.pal.restApi.entity.Student;
import com.nitin.pal.restApi.repository.StudentRepository;
import com.nitin.pal.restApi.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService {
    private final StudentRepository studentRepository;
    private final ModelMapper modelMapper;

    @Override
    public List<StudentDto> getAllStudents() {
        List<Student> students = studentRepository.findAll();
        return students.stream().map(student -> modelMapper.map(student, StudentDto.class)).toList();
    }

    @Override
    public StudentDto getStudentById(Long id) {
        Student student = studentRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Student not found with ID: " + id));
        return modelMapper.map(student, StudentDto.class);
    }

    @Override
    public StudentDto createNewStudent(AddStudentRequestDto addStudentRequestDto) {
        Student newStudent = modelMapper.map(addStudentRequestDto, Student.class);
        Student student = studentRepository.save(newStudent);
        return modelMapper.map(student, StudentDto.class);
    }

    @Override
    public void deleteStudentById(Long id) {
        if (!studentRepository.existsById(id)) {
            throw new IllegalArgumentException("Student does not exist by id: " + id);
        }
        studentRepository.deleteById(id);
    }

    @Override
    public StudentDto updateStudent(Long id, AddStudentRequestDto addStudentRequestDto) {
        Student student = studentRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Student not found with ID: " + id));
        // Map fields from AddStudentRequestDto to the existing student entity
        // This updates the student object with new values without creating a new instance
        modelMapper.map(addStudentRequestDto,student);
        student = studentRepository.save(student);
        // Convert the updated Student entity into a StudentDto for the response
        return modelMapper.map(student,StudentDto.class);
    }

    @Override
    public StudentDto updatePartialStudent(Long id, Map<String, Object> updates) {
        Student student = studentRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Student not found with ID: " + id));
        updates.forEach((field, value) -> {
            switch (field) {
                case "name":
                    student.setName((String) value);
                    break;
                case "email":
                    student.setEmail((String) value);
                    break;
                default:
                    throw new IllegalArgumentException("Field is not supported");
            }
        });
        Student savedStudent = studentRepository.save(student);
        return modelMapper.map(savedStudent, StudentDto.class);
    }
}
