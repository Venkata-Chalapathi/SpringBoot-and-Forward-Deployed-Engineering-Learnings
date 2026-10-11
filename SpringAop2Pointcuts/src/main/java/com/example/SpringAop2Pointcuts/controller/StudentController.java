package com.example.SpringAop2Pointcuts.controller;


import com.example.SpringAop2Pointcuts.dto.Student;
import com.example.SpringAop2Pointcuts.service.StudentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/students")
public class StudentController {

    private StudentService studentService;

    private StudentController(StudentService studentService){
        this.studentService = studentService;
    }

    @PostMapping
    public ResponseEntity<Student> createStudent( @RequestBody Student student) {
        return ResponseEntity.ok(studentService.createStudent(student));
    }

    @GetMapping
    public ResponseEntity<String> getStudent() {
        return ResponseEntity.ok(studentService.getStudent("All Student Data"));
    }
}
