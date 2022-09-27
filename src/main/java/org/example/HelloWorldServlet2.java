package org.example;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

@ContentServlet(resourcePath = "/WEB-INF/resources/content_preview/hello_world_2_preview.html")
@WebServlet(name = "Content_HelloWorldServlet2", urlPatterns = "/content/hello2")
public class HelloWorldServlet2 extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        request.getRequestDispatcher("/WEB-INF/cafe/cafe_2.html").forward(request, response);

    }
}