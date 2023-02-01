package com.aptech.myblog;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebInitParam;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

//@WebServlet(urlPatterns = {"/home", "*.html"}, initParams = {@WebInitParam(name = "productName", value = "My blog")})
@WebServlet(urlPatterns = {"/home", "*.do"}, name = "Main")
public class MainServlet extends HttpServlet {

    String productName = "";

    String connectionString = "";

    @Override
    public void init() throws ServletException {
        productName = getInitParameter("productName");
        connectionString = getServletContext().getInitParameter("connectionString");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        resp.setContentType("text/xml");
        String name = req.getParameter("name");
        resp.getWriter().write(String.format("<application>" +
                "<msg>Hello %s</msg>" +
                "<msg>Hello World Main Servlet</msg>" +
                "<product>%s</product>" +
                "<connectionString>%s</connectionString>" +
                "</application>", name, productName, connectionString));
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");

        if(name != null && name != ""){
            resp.getWriter().write("Hello " + name);
        }else {
            resp.sendRedirect("index.html");
        }
    }
}
