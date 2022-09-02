package com.example.carShop;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/saveServlet")
public class SaveServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        response.setContentType("text/plain");
        response.setCharacterEncoding("UTF-8");

        PrintWriter out = response.getWriter();

        String name = request.getParameter("name");
        String price = request.getParameter("price");
        String availability = request.getParameter("availability");

        com.example.carShop.Carshop carshop = new com.example.carShop.Carshop();

        carshop.setName(name);
        carshop.setPrice(price);
        carshop.setAvailability(availability);

        //out.println(employee.toString());
        //out.println(EmployeeRepository.getConnection());

        int status = com.example.carShop.CarshopRepository.save(carshop);
        //out.println(status);

        if (status > 0) {
            out.print("Record saved successfully!");
        } else {
            out.println("Sorry! unable to save record");
        }
        out.close();
    }
}