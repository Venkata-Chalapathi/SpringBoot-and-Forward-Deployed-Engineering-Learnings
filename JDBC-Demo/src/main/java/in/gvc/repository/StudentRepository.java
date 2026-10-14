package in.gvc.repository;


import in.gvc.model.Student;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StudentRepository {

    String url = "jdbc:mysql://localhost:3306/student_db";
    String username = "root";
    String password = "192211150@Mysql";

    public void createStudent(Student student) {

        String sql = """
                            INSERT INTO students(name, email, age)
                            VALUES (?, ?, ?)
                         """;


        try (
                Connection connection = DriverManager.getConnection(url, username, password);
                PreparedStatement preparedStatement = connection.prepareStatement(sql);
                ) {

            preparedStatement.setString(1, student.getName());
            preparedStatement.setString(2, student.getEmail());
            preparedStatement.setInt(3, student.getAge());

            int rowAffected = preparedStatement.executeUpdate();

            if(rowAffected == 1){
                System.out.println("Created student successfully");
            }else {
                System.out.println("Creation Failed");
            }

        }catch (SQLException e) {
            System.out.println("DB Connection Failed");
            e.printStackTrace();
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

        try (
                Connection connection = DriverManager.getConnection(url, username, password);

                PreparedStatement preparedStatement = connection.prepareStatement(sql);
                ) {

            preparedStatement.setString(1, student.getName());
            preparedStatement.setString(2, student.getEmail());
            preparedStatement.setInt(3, student.getAge());
            preparedStatement.setLong(4, id);

            int rowAffected = preparedStatement.executeUpdate();

            if(rowAffected == 1){
                System.out.println("Updated student successfully");
            }else {
                System.out.println("Update Failed");
            }

        }catch (SQLException e) {
            System.out.println("DB Connection Failed");
            e.printStackTrace();
        }

    }

    public void deleteStudent(Long id) {

        String sql = """ 
                DELETE FROM students WHERE id = ? 
                """;

        try (
                Connection connection = DriverManager.getConnection(url, username, password);

                PreparedStatement preparedStatement = connection.prepareStatement(sql);
                ) {

            preparedStatement.setLong(1, id);

            int rowAffected = preparedStatement.executeUpdate();

            if(rowAffected == 1){
                System.out.println("Deleted student successfully");
            }else {
                System.out.println("Deletion Failed");
            }

        }catch (SQLException e) {
            System.out.println("DB Connection Failed");
            e.printStackTrace();
        }

    }

    public void getStudentById(Long id) {

        String sql = """ 
                SELECT id, name, email, age FROM students WHERE id = ?
                """;

        try (
                Connection connection = DriverManager.getConnection(url, username, password);
                PreparedStatement preparedStatement = connection.prepareStatement(sql);
                ){

            preparedStatement.setLong(1, id);

            try (
                    ResultSet resultSet = preparedStatement.executeQuery(sql);
                    ){
                if(resultSet.next()){
                    Student student = mapRow(resultSet);
                    System.out.println(student);
                }
            }

        }catch (SQLException e) {
            System.out.println("DB Connection Failed");
            e.printStackTrace();
        }
    }

    public void getAllStudents() {

        String sql = """ 
                SELECT id, name, email, age FROM students
                """;

        try (
                Connection connection = DriverManager.getConnection(url, username, password);
                PreparedStatement preparedStatement = connection.prepareStatement(sql);
        ){

            try (
                    ResultSet resultSet = preparedStatement.executeQuery();
            ){
                List<Student> studentList = new ArrayList<>();
                while (resultSet.next()){
                    Student student = mapRow(resultSet);
                    studentList.add(student);
                    System.out.println(student);
                }
            }

        }catch (SQLException e) {
            System.out.println("DB Connection Failed");
            e.printStackTrace();
        }
    }

    public void completeCRUD() {

        try {
            Connection connection = DriverManager.getConnection(url, username, password);

            Statement statement = connection.createStatement();

            String sql = " SELECT id, name, email, age FROM students WHERE id = 2";

            boolean result = statement.execute(sql);

            if(result){
                ResultSet resultSet = statement.getResultSet();
            }else {
                int rowAffected = statement.getUpdateCount();
            }

            connection.close();

        }catch (SQLException e) {
            System.out.println("DB Connection Failed");
            e.printStackTrace();
        }
    }

    private Student mapRow(ResultSet resultSet) throws SQLException {

        Student student = new Student();

        student.setId(resultSet.getLong("id"));
        student.setName(resultSet.getString("name"));
        student.setEmail(resultSet.getString("email"));
        student.setAge(resultSet.getInt("age"));

        return student;
    }

}
