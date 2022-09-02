package com.example.carShop;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/isDeleted")
public class isDeleted extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String sid = request.getParameter("code");
        int code = Integer.parseInt(sid);
        com.example.carShop.CarshopRepository.isDeleted(code);
        response.sendRedirect("viewServlet");
    }
}
