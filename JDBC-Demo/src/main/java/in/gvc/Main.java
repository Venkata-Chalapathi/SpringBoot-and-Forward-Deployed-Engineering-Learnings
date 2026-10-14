package in.gvc;

import in.gvc.model.Student;
import in.gvc.repository.StudentRepository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {

        StudentRepository studentRepository = new StudentRepository();

//        studentRepository.createStudent(new Student("kala", "kala@gmail.com", 98));
//        studentRepository.updateStudent();
//        studentRepository.deleteStudent();
//        studentRepository.getStudentById();
        studentRepository.getAllStudents();

    }
}