package com.example.demo;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/putServlet")
public class PutServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        String sid = request.getParameter("number");
        int number = Integer.parseInt(sid);

        String brand = request.getParameter("brand");
        String model = request.getParameter("model");

        CarBase carBase = new CarBase();
        carBase.setNumber(number);
        carBase.setBrand(brand);
        carBase.setColor(model);
        carBase.setModel(request.getParameter("color"));

        int status = CarBaseRepository.update(carBase);

        if (status > 0) {
            response.sendRedirect("viewServlet");
        } else {
            out.println("Sorry! unable to update record");
        }
        out.close();
    }
}
