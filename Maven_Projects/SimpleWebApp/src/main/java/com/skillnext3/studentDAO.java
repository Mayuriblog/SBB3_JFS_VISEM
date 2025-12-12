package com.skillnext1;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StudentDAO {

    private Connection getConnection() throws Exception {
        String url = "jdbc:mysql://localhost:3306/skillnext_db";
        String username = "root";
        String password = "root";   // change if needed
        return DriverManager.getConnection(url, username, password);
    }

    // INSERT
    public void insert(Student s) throws Exception {
        Connection con = getConnection();
        String query = "INSERT INTO student(name, sem, dept) VALUES (?, ?, ?)";
        PreparedStatement ps = con.prepareStatement(query);
        ps.setString(1, s.getName());
        ps.setInt(2, s.getSem());
        ps.setString(3, s.getDept());
        ps.executeUpdate();
        con.close();
    }

    // UPDATE
    public void update(Student s) throws Exception {
        Connection con = getConnection();
        String sql = "UPDATE student SET name=?, sem=?, dept=? WHERE id=?";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1, s.getName());
        ps.setInt(2, s.getSem());
        ps.setString(3, s.getDept());
        ps.setInt(4, s.getId());
        ps.executeUpdate();
        con.close();
    }

    // DELETE
    public void delete(int id) throws Exception {
        Connection con = getConnection();
        String sql = "DELETE FROM student WHERE id=?";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setInt(1, id);
        ps.executeUpdate();
        con.close();
    }

    // SELECT ALL
    public List<Student> selectAll() throws Exception {
        Connection con = getConnection();
        String sql = "SELECT * FROM student";
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery(sql);

        List<Student> list = new ArrayList<>();

        while (rs.next()) {
            Student s = new Student(
                rs.getInt("id"),
                rs.getString("name"),
                rs.getInt("sem"),
                rs.getString("dept")
            );
            list.add(s);
        }

        con.close();
        return list;
    }
}
