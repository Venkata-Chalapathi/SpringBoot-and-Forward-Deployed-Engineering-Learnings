package com.example.HibernateInternals.repository;

import com.example.HibernateInternals.model.Student;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

@Repository
public class StudentRepository {

    @PersistenceContext
    private EntityManager entityManager;

    // CREATE
    public void save(Student student) {
        entityManager.persist(student);
    }
    // READ BY VALUE
    public Student findById(Long id){
        return entityManager.find(Student.class, id);
    }
    // DELETE
    public void remove(Student student){
        entityManager.remove(student);
    }
}
