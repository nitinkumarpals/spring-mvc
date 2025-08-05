package com.nitin.pal.restApi.service;

import com.nitin.pal.restApi.dto.AddStudentRequestDto;
import com.nitin.pal.restApi.dto.StudentDto;

import java.util.List;

public interface StudentService {
    List<StudentDto> getAllStudents();

    StudentDto getStudentById(Long id);

    StudentDto createNewStudent(AddStudentRequestDto addStudentRequestDto);
}
