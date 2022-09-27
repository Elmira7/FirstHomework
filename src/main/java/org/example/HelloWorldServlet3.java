package org.example;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebInitParam;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

@ContentServlet(resourcePath = "/WEB-INF/resources/content_preview/hello_world_3_preview.html")
@WebServlet(name = "Content_HelloWorldServlet3", urlPatterns = "/content/hello3")
public class HelloWorldServlet3 extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/cafe/cafe_3.html").forward(request, response);
    }
}
