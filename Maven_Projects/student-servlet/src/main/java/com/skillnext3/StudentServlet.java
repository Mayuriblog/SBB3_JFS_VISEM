package com.skillnext3;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.sql.*;

public class StudentServlet extends HttpServlet {

    protected void doPost(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {

        String name = req.getParameter("name");
        int sem = Integer.parseInt(req.getParameter("sem"));
        String dept = req.getParameter("dept");

        res.setContentType("text/html");
        PrintWriter out = res.getWriter();

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            Connection con = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/skillnext3_db",
                "root",
                "root"
            );

            PreparedStatement ps =
                con.prepareStatement("insert into student(name,sem,dept) values(?,?,?)");

            ps.setString(1, name);
            ps.setInt(2, sem);
            ps.setString(3, dept);

            ps.executeUpdate();

            out.println("<h3>Student Registered Successfully</h3>");

            con.close();

        } catch (Exception e) {
            out.println(e);
        }
    }
}
