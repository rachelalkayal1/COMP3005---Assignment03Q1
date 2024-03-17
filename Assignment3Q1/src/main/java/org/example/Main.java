package org.example;


import java.sql.*;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    static Connection connection;

    public static void main(String[] args) {
        //TIP Press <shortcut actionId="ShowIntentionActions"/> with your caret at the highlighted text
        // to see how IntelliJ IDEA suggests fixing it.
        String url = "jdbc:postgresql://localhost:5432/Assignment 3 Database";
        String user = "postgres";
        String password = "Rachilio22!";

        try {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection(url, user, password);
            if (connection != null) {
                System.out.println("Connected to the database! :)");
            } else {
                System.out.println("Failed to connect to the database :(");
            }

            getAllStudents();
            addStudent("Rachel", "Al Kayal", "rachelalkayal@cmail.carleton.ca", Date.valueOf("2022-05-09"));
            getAllStudents();
            updateStudentEmail(9, "newEmail@gmail.com");
            getAllStudents();
            deleteStudent(9);

        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public static void getAllStudents() {
        try {
            Statement statement = connection.createStatement();
            statement.executeQuery("SELECT * FROM students");
            ResultSet result = statement.getResultSet();
            while (result.next()) {
                System.out.println(result.getInt("student_id"));
                System.out.println(result.getString("first_name"));
                System.out.println(result.getString("last_name"));
                System.out.println(result.getString("email"));
                System.out.println(result.getDate("enrollment_date"));
                System.out.println("_________________________________________________________");
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    static void addStudent(String first_name, String last_name, String email, Date enrollment_date){
        try{
            PreparedStatement statement = connection.prepareStatement("INSERT INTO students (first_name, last_name, email, enrollment_date) VALUES (?, ?, ?, ?)");
            statement.setString(1, first_name);
            statement.setString(2, last_name);
            statement.setString(3, email);
            statement.setDate(4, enrollment_date);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    static void updateStudentEmail(Integer student_id, String newEmail){

        try{
            PreparedStatement statement = connection.prepareStatement("UPDATE students SET email = ? WHERE student_id = ?");
            statement.setString(1, newEmail);
            statement.setInt(2, student_id);
            statement.executeUpdate();
        }catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    static void deleteStudent(Integer student_id){
        try{
            PreparedStatement statement = connection.prepareStatement("DELETE FROM students WHERE student_id = ?");
            statement.setInt(1, student_id);
            statement.executeUpdate();
        }catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}