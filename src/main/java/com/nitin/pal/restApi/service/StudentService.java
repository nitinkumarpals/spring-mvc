package com.nitin.pal.restApi.service;

import com.nitin.pal.restApi.dto.AddStudentRequestDto;
import com.nitin.pal.restApi.dto.StudentDto;

import java.util.List;
import java.util.Map;

public interface StudentService {
    List<StudentDto> getAllStudents();

    StudentDto getStudentById(Long id);

    StudentDto createNewStudent(AddStudentRequestDto addStudentRequestDto);

    void deleteStudentById(Long id);

    StudentDto updateStudent(Long id, AddStudentRequestDto addStudentRequestDto);

    StudentDto updatePartialStudent(Long id, Map<String, Object> updates);
}
