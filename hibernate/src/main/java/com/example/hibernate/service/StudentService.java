package com.example.hibernate.service;

import com.example.hibernate.model.Student;
import com.example.hibernate.repository.StudentRepository;
//import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {

    StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }


    public void createStudent(Student student) {

    }


    public Student getStudentById(Long id) {
        return null;
    }



    public void updateStudent(Student studentReq, Long id) {

    }


    public void deleteStudent(Long id) {

    }
}
