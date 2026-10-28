package org.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.ResultSet;

public class Main {

    static final String URL = "jdbc:mysql://localhost:3306/jdbc";
    static final String USER = "root";
    static final String PASSWORD = "root0127";

    // Database connection
    public static Connection getConnection() throws Exception {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    // INSERT: Add student details
    public static void insertStudent(
           int id, String Name, int Age, String Gender, int AadharNo)
            throws Exception {

        Connection cn = getConnection();
        Statement stmt = cn.createStatement();

        String sql = "INSERT INTO student_table(Name, Age, Gender, AadharNo) "
                + "VALUES('" + Name + "', " + Age + ", '"
                + Gender + "', '" + AadharNo + "')";

        stmt.executeUpdate(sql);

        System.out.println("Student Details inserted successfully!");

        stmt.close();
        cn.close();
    }

    // READ: Display all student details
    public static void displayStudents() throws Exception {

        Connection cn = getConnection();
        Statement stmt = cn.createStatement();

        ResultSet rs = stmt.executeQuery(
                "SELECT * FROM student_table"
        );

        System.out.println("\n----- Student Details -----");

        while (rs.next()) {
            System.out.println(
                    "ID: " + rs.getInt("id")
                            + ", Name: " + rs.getString("Name")
                            + ", Age: " + rs.getInt("Age")
                            + ", Gender: " + rs.getString("Gender")
                            + ", AadhaarNo: " + rs.getString("AadharNo")
            );
        }

        rs.close();
        stmt.close();
        cn.close();
    }

    // UPDATE: Update student age using ID
    public static void updateStudent(int id, int Age)
            throws Exception {

        Connection cn = getConnection();
        Statement stmt = cn.createStatement();

        String sql = "UPDATE student_table SET Age = "
                + Age + " WHERE id = " + id;

        int rows = stmt.executeUpdate(sql);

        System.out.println(rows + " student record(s) updated.");

        stmt.close();
        cn.close();
    }

    // DELETE: Delete student using ID
    public static void deleteStudent(int id) throws Exception {

        Connection cn = getConnection();
        Statement stmt = cn.createStatement();

        String sql = "DELETE FROM student_table WHERE id = " + id;

        int rows = stmt.executeUpdate(sql);

        System.out.println(rows + " student record(s) deleted.");

        stmt.close();
        cn.close();
    }

    // MAIN METHOD
    public static void main(String[] args) {

        try {

            // 1. INSERT
            insertStudent(101, "chatrisha", 20, "Female", 600200100);
            insertStudent(102, "keerthi", 21, "Female", 700600600 );
            insertStudent(103, "Navya", 20, "Female", 100500400);
            insertStudent(104, "Manasa", 5, "Female", 100600700);


            // 2. READ
            System.out.println("\nBefore Updating:");
            displayStudents();

            // 3. UPDATE: Change age of student ID 1
            updateStudent(1, 23);

            System.out.println("\nAfter Updating:");
            displayStudents();

            // 4. DELETE: Delete student ID 2
            deleteStudent(2);

            System.out.println("\nAfter Deleting:");
            displayStudents();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}