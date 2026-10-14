package in.gvc;

import in.gvc.repository.StudentRepository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {

        StudentRepository studentRepository = new StudentRepository();

        studentRepository.createStudent();

    }
}