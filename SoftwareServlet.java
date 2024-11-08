package controller;


import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.RequestDispatcher;
/*
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.RequestDispatcher;
*/

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import dao.Software;
import dao.SoftwareDAO;

//@WebServlet("/SoftwareServlet")
@SuppressWarnings("serial")
public class SoftwareServlet extends HttpServlet {
    private SoftwareDAO softwareDAO = new SoftwareDAO();

    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Software> softwares = softwareDAO.getAllSoftwares();
        request.setAttribute("softwares", softwares);
        RequestDispatcher dispatcher = request.getRequestDispatcher("ManageSoftware.jsp");
        dispatcher.forward(request, response);
    }

    
    
    

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	String action = request.getParameter("deleteSoftware");
        
   
        if (action != null) {
           
            int id = Integer.parseInt(request.getParameter("id"));
            softwareDAO.deleteSoftware(id); 
            response.sendRedirect("SoftwareServlet"); 
        } else {
           
            String name = request.getParameter("name");
            String description = request.getParameter("description");
            
            softwareDAO.addSoftware(new Software(name, description));
            
            	
            
            response.sendRedirect("SoftwareServlet"); 
        
        }
    }
}