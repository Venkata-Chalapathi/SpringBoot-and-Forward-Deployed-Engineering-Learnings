package com.example.Spring_JDBC2.repository;


import com.example.Spring_JDBC2.model.Student;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class StudentRepository {

    private JdbcTemplate jdbcTemplate;

    public StudentRepository(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }

    private StudentRowMapper studentRowMapper = new StudentRowMapper();

    private RowMapper<Student> rowMapper = new BeanPropertyRowMapper<>(Student.class);

    public void createStudent(Student student) {

        String sql = """
                            INSERT INTO students(name, email, age)
                            VALUES (?, ?, ?)
                         """;

        int rowAffected = jdbcTemplate.update(sql, student.getName(),
                            student.getEmail(), student.getAge());

        if(rowAffected == 1){
            System.out.println("Created student successfully");
        }else {
            System.out.println("Creation Failed");
        }


    }

    public void updateStudent(Student student, Long id) {

        String sql = """
                    UPDATE students
                    SET name = ?,
                        email = ?,
                        age = ?
                    WHERE id = ?
                    """;

        int rowAffected = jdbcTemplate.update(sql, student.getName(),
                student.getEmail(), student.getAge(), id);

        if(rowAffected == 1){
            System.out.println("Updated student successfully");
        }else {
            System.out.println("Update Failed");
        }

    }

    public void deleteStudent(Long id) {

        String sql = """ 
                DELETE FROM students WHERE id = ? 
                """;

        int rowAffected = jdbcTemplate.update(sql, id);

        if(rowAffected == 1){
            System.out.println("Deleted student successfully");
        }else {
            System.out.println("Deletion Failed");
        }
    }

    public Student getStudentById(Long id) {

        String sql = """ 
                SELECT id, name, email, age FROM students WHERE id = ?
                """;

        return jdbcTemplate.queryForObject(sql, rowMapper, id);
    }

    public List<Student> getAllStudents() {

        String sql = """ 
                SELECT id, name, email, age FROM students
                """;

        List<Student> studentList = jdbcTemplate.query( sql, rowMapper );
        return studentList;
    }



}
