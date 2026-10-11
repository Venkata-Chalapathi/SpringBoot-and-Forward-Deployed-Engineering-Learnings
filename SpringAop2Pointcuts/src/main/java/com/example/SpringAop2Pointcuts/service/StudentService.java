package com.example.SpringAop2Pointcuts.service;

import com.example.SpringAop2Pointcuts.dto.Student;
import org.springframework.stereotype.Service;

@Service
public class StudentService {

    public Student createStudent(Student student) {

        System.out.println("Student saved");
        return student;
    }

    public String getStudent(String s) {
        System.out.println(s);
        return s;
    }
}
