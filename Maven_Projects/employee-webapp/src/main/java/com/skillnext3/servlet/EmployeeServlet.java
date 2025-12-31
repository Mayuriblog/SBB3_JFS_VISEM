package com.skillnext3.servlet;

import com.skillnext3.dao.EmployeeDAO;
import com.skillnext3.model.Employee;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import java.io.IOException;

public class EmployeeServlet extends HttpServlet {

    protected void doPost(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {

        String name = req.getParameter("name");
        String email = req.getParameter("email");
        double salary = Double.parseDouble(req.getParameter("salary"));

        Employee emp = new Employee();
        emp.setName(name);
        emp.setEmail(email);
        emp.setSalary(salary);

        EmployeeDAO.save(emp);

        res.sendRedirect("success.jsp");
    }
}
