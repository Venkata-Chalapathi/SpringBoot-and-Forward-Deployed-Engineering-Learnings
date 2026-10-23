package com.example.HibernateInternals.service;

import com.example.HibernateInternals.model.Student;
import com.example.HibernateInternals.repository.StudentRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class StudentService {

    private StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Transactional
    public void createStudent(Student student) {

        studentRepository.save(student);
    }

    @Transactional
    public Student getStudentById(Long id) {
        return studentRepository.findById(id);
    }

    @Transactional
    public void updateStudent(Student studentReq, Long id) {
        Student student = studentRepository.findById(id);

        if (student == null){
            throw new RuntimeException("Student Not Found");
        } else{
            student.setName(studentReq.getName());
            student.setEmail(studentReq.getEmail());
            student.setAge(studentReq.getAge());
        }

    }

    @Transactional
    public void deleteStudent(Long id) {
        Student student = studentRepository.findById(id);

        if (student == null){
            throw new RuntimeException("Student Not Found");
        }else {
            studentRepository.remove(student);
        }
    }
}
