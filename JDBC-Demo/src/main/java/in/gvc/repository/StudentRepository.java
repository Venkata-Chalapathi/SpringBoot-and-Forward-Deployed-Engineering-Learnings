package in.gvc.repository;


import in.gvc.model.Student;

import java.sql.*;

public class StudentRepository {

    String url = "jdbc:mysql://localhost:3306/student_db";
    String username = "root";
    String password = "192211150@Mysql";

    public void createStudent() {

        try {
            Connection connection = DriverManager.getConnection(url, username, password);

            Statement statement = connection.createStatement();

            String sql = " INSERT INTO students (name, email, age) " +
                    "VALUES ( 'Venkat', 'venkat@gmail.com', 22 ) ";

            int result = statement.executeUpdate(sql);

            if(result == 1){
                System.out.println("Created student successfully");
            }else {
                System.out.println("Creation Failed");
            }

            connection.close();

        }catch (SQLException e) {
            System.out.println("DB Connection Failed");
            e.printStackTrace();
        }

    }

    public void updateStudent() {

        try {
            Connection connection = DriverManager.getConnection(url, username, password);

            Statement statement = connection.createStatement();

            String sql = " UPDATE students SET age = 24 WHERE id = 1";

            int result = statement.executeUpdate(sql);

            if(result == 1){
                System.out.println("Updated student successfully");
            }else {
                System.out.println("Update Failed");
            }

            connection.close();

        }catch (SQLException e) {
            System.out.println("DB Connection Failed");
            e.printStackTrace();
        }

    }

    public void deleteStudent() {

        try {
            Connection connection = DriverManager.getConnection(url, username, password);

            Statement statement = connection.createStatement();

            String sql = " DELETE FROM students WHERE id = 1 ";

            int result = statement.executeUpdate(sql);

            if(result == 1){
                System.out.println("Deleted student successfully");
            }else {
                System.out.println("Deletion Failed");
            }

            connection.close();

        }catch (SQLException e) {
            System.out.println("DB Connection Failed");
            e.printStackTrace();
        }

    }

    public void getStudentById() {

        try {
            Connection connection = DriverManager.getConnection(url, username, password);

            Statement statement = connection.createStatement();

            String sql = " SELECT id, name, email, age FROM students WHERE id = 2";

            ResultSet resultSet = statement.executeQuery(sql);
            resultSet.next();
            Student student = mapRow(resultSet);
            System.out.println(student);
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
