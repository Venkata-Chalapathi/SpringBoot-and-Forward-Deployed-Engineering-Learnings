package in.gvc.repository;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class StudentRepository {

    String url = "jdbc:mysql://localhost:3306/student_db";
    String username = "root";
    String password = "192211150@Mysql";

    public void createStudent() {

        try {
            Connection connection = DriverManager.getConnection(url, username, password);

            Statement statement = connection.createStatement();

            String sql = " INSERT INTO students (name, email, age) " +
                    "VALUES ( 'Chalapathi', 'chala@gmail.com', 22 ) ";

            int result = statement.executeUpdate(sql);

            if(result == 1){
                System.out.println("Created student successfully");
            }else {
                System.out.println("Creation Failed");
            }

            connection.close();

        }catch (SQLException e) {
            System.out.println("DB Connection Falied");
            e.printStackTrace();
        }

    }
}
