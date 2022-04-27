package com.ijse;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import javax.json.JsonReader;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = "/customer")
public class CustomerServlet extends HttpServlet {
    JsonArrayBuilder responseArray;
    JsonObjectBuilder responseInfo;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        try {
            resp.setContentType("application/json");

            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/ThogaKade", "root",
                    "shiny1234");
            ResultSet rst = connection.prepareStatement("select * from Customer").executeQuery();

            JsonArrayBuilder allCustomers = Json.createArrayBuilder();
            JsonObjectBuilder customer = Json.createObjectBuilder();

            while (rst.next()) {
                String id = rst.getString(1);
                String name = rst.getString(2);
                String address = rst.getString(3);
                double salary = rst.getDouble(4);

                customer.add("id", id);
                customer.add("name", name);
                customer.add("address", address);
                customer.add("salary", salary);

                allCustomers.add(customer.build());

            }

            // {
            // "data": "[{},{},{},{}]",
            // "message":"Done",
            // "status":"200"
            // }

            responseArray = Json.createArrayBuilder();
            responseInfo = Json.createObjectBuilder();

            responseInfo.add("data", allCustomers.build());
            responseInfo.add("message", "Done");
            responseInfo.add("status", 200);

            responseArray.add(responseInfo.build());
            resp.getWriter().print(responseArray.build());

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            resp.sendError(500, e.getMessage());

        } catch (SQLException throwables) {
            throwables.printStackTrace();
            resp.sendError(500, throwables.getMessage());
        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("customerID");
        String name = req.getParameter("customerName");
        String address = req.getParameter("customerAddress");
        String salary = req.getParameter("customerSalary");

        try {
            resp.setContentType("application/json");

            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/ThogaKade", "root",
                    "shiny1234");

            PreparedStatement pstm = connection
                    .prepareStatement("INSERT INTO Customer (id,name,address, salary) VALUES (?,?,?,?)");

            pstm.setObject(1, id);
            pstm.setObject(2, name);
            pstm.setObject(3, address);
            pstm.setObject(4, salary);

            if (pstm.executeUpdate() > 0) {

                // {
                // "data": "[{},{},{},{}]",
                // "message":"Successfully Added",
                // "status":"200"
                // }

                responseArray = Json.createArrayBuilder();
                responseInfo = Json.createObjectBuilder();

                responseInfo.add("data", "");
                responseInfo.add("message", "Customer Saved Successfully...!");
                responseInfo.add("status", 200);

                responseArray.add(responseInfo.build());
                resp.getWriter().print(responseArray.build());
            }

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            resp.sendError(500, e.getMessage());

        } catch (SQLException throwables) {
            throwables.printStackTrace();
            resp.sendError(500, throwables.getMessage());
        }

    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        try {
            resp.setContentType("application/json");
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/ThogaKade", "root",
                    "shiny1234");

            String id = req.getParameter("CustID");
            PreparedStatement pstm = connection
                    .prepareStatement("DELETE FROM Customer WHERE id = ?");

            pstm.setObject(1, id);

            if (pstm.executeUpdate() > 0) {

                // {
                // "data": "[{},{},{},{}]",
                // "message":"Successfully Deleted",
                // "status":"200"
                // }

                responseArray = Json.createArrayBuilder();
                responseInfo = Json.createObjectBuilder();

                responseInfo.add("data", "");
                responseInfo.add("message", "Customer Deleted Successfully...!");
                responseInfo.add("status", 200);

                responseArray.add(responseInfo.build());
                resp.getWriter().print(responseArray.build());
            }

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            resp.sendError(500, e.getMessage());

        } catch (SQLException throwables) {
            throwables.printStackTrace();
            resp.sendError(500, throwables.getMessage());
        }
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            resp.setContentType("application/json");
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/ThogaKade", "root",
                    "shiny1234");

            JsonReader reader = Json.createReader(req.getReader());
            JsonObject jsonObj = reader.readObject();

            String id = jsonObj.getString("customerID");
            String name = jsonObj.getString("customerName");
            String address = jsonObj.getString("customerAddress");
            double salary = Double.valueOf(jsonObj.getString("customerSalary"));

            // System.out.println(id + " " + name + " " + address + " " + salary);

            PreparedStatement pstm = connection
                    .prepareStatement("UPDATE Customer SET name = ?, address = ?, salary = ? WHERE id = ?");

            pstm.setObject(1, name);
            pstm.setObject(2, address);
            pstm.setObject(3, salary);
            pstm.setObject(4, id);

            if (pstm.executeUpdate() > 0) {

                // {
                // "data": "[{},{},{},{}]",
                // "message":"Successfully Updated",
                // "status":"200"
                // }

                responseArray = Json.createArrayBuilder();
                responseInfo = Json.createObjectBuilder();

                responseInfo.add("data", "");
                responseInfo.add("message", "Customer Updated Successfully...!");
                responseInfo.add("status", 200);

                responseArray.add(responseInfo.build());
                resp.getWriter().print(responseArray.build());
            }

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            resp.sendError(500, e.getMessage());

        } catch (SQLException throwables) {
            throwables.printStackTrace();
            resp.sendError(500, throwables.getMessage());
        }
    }
}
