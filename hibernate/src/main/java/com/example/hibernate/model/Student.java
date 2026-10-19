package com.example.hibernate.model;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Table(name = "studentTable")
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(
            name = "student-name",
            nullable = false,
            length = 100
    )
    private String name;

    @Column(
            unique = true,
            nullable = false,
            length = 150,
            insertable = true,
            updatable = true
    )
    private String email;

    private int age;

    @ElementCollection
    @CollectionTable(
            name = "student_skills",
            joinColumns = @JoinColumn(name = "student_id")
    )
    private Set<String> skills;

//    @Embedded
//    @AttributeOverrides({
//            @AttributeOverride(
//                    name = "houseNo",
//                    column = @Column(name = "current_houseNo")
//            ),
//            @AttributeOverride(
//                    name = "street",
//                    column = @Column(name = "current_street")
//            ),
//            @AttributeOverride(
//                    name = "city",
//                    column = @Column(name = "current_city")
//            ),
//            @AttributeOverride(
//                    name = "state",
//                    column = @Column(name = "current_state")
//            ),
//            @AttributeOverride(
//                    name = "pincode",
//                    column = @Column(name = "current_pinCode")
//            )
//    })
//    private Address currentAddress;
//
//    @Embedded
//    @AttributeOverrides({
//            @AttributeOverride(
//                    name = "houseNo",
//                    column = @Column(name = "permanent_house_no")
//            ),
//            @AttributeOverride(
//                    name = "street",
//                    column = @Column(name = "permanent_street")
//            ),
//            @AttributeOverride(
//                    name = "city",
//                    column = @Column(name = "permanent_city")
//            ),
//            @AttributeOverride(
//                    name = "state",
//                    column = @Column(name = "permanent_state")
//            ),
//            @AttributeOverride(
//                    name = "pincode",
//                    column = @Column(name = "permanent_pincode")
//            )
//    })
//    private Address permenantAddress;
//
//
//

//    @Column(precision = 5, scale = 2)
//    private BigDecimal percentage;
//
//    private LocalDateTime createdAt;
//
//    @Enumerated(EnumType.STRING)
//    private StudentStatus status;
//
//    @Convert(converter = BooleanToStringConverter.class)
//    private Boolean isMonitor;
//
//    private LocalDate dateOfBirth;
//
//    @Transient
//    private String displayName;


    public Student(Long id, String name, String email, int age, Set<String> skills) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.age = age;
        this.skills = skills;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Set<String> getSkills() {
        return skills;
    }

    public void setSkills(Set<String> skills) {
        this.skills = skills;
    }
}
