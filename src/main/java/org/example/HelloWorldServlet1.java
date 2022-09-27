package org.example;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebInitParam;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

@ContentServlet(resourcePath = "/WEB-INF/resources/content_preview/hello_world_1_preview.html")
@WebServlet(name = "Content_HelloWorldServlet1", urlPatterns = "/content/hello1")
public class HelloWorldServlet1 extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        request.getRequestDispatcher("/WEB-INF/cafe/cafe_1.html").forward(request, response);
    }
}