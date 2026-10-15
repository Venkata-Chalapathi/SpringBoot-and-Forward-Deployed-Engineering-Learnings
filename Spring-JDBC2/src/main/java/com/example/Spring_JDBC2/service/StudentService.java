package com.example.Spring_JDBC2.service;

import com.example.Spring_JDBC2.model.Student;
import com.example.Spring_JDBC2.repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {

    StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public void createStudent(Student student) {
        studentRepository.createStudent(student);
    }

    public List<Student> getAllStudents() {
        return studentRepository.getAllStudents();
    }

    public Student getStudentById(Long id) {
        return studentRepository.getStudentById(id);
    }

    public void updateStudent(Student student, Long id) {
        studentRepository.updateStudent(student, id);
    }

    public void deleteStudent(Long id) {
        studentRepository.deleteStudent(id);
    }
}
