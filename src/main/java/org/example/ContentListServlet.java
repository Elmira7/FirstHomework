package org.example;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@WebServlet(name = "HelloWorldServlet", urlPatterns = "/content/list")
public class ContentListServlet extends HttpServlet {

    private static class ContentHolder {
        private final String previewResource;
        private final String mapping;

        public ContentHolder(String previewResource, String mapping) {
            this.previewResource = previewResource;
            this.mapping = mapping;
        }

        public String getPreviewResource() {
            return previewResource;
        }

        public String getMapping() {
            return mapping;
        }
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        Map<String, ? extends ServletRegistration> servletRegistrationMap = request.getServletContext().getServletRegistrations();

        List<? extends ServletRegistration> contentRegistrations = servletRegistrationMap.values().stream()
                .filter(this::checkIfContentUnitInstance)
                .collect(Collectors.toList());

        List<ContentHolder> contentHolders = contentRegistrations.stream()
                .map(reg -> new ContentHolder(
                        getResourcePath(reg),
                        reg.getMappings().stream().findAny().orElseThrow(() ->
                                new RuntimeException("Servlet mappings not found"))
                )).collect(Collectors.toList());

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println("<html>");
        out.println("<head>");
        out.println("<title>There are content list!</title>");
        out.println("</head>");
        out.println("<body>");
        out.println("<h1>Список столовых:</h1>");
        out.println("<br>");
        out.println("<style>");
        out.println(" body{");
        out.println("background: url(\"/img_4.png\");");
        out.println("background-size: cover;");
        out.println("}");
        out.println("</style>");

        contentHolders.forEach(contentHolder -> {
            out.println("<p>" + readResource(contentHolder.getPreviewResource()) + "</p> ");
            out.println("<a href=\"" + contentHolder.getMapping() + "\">узнать больше</a>");
            out.println("<p></p>");
            out.println("<br>");
        });

        out.println("</body>");
        out.println("</html>");
    }

    private String readResource(String resourcePath) {
        try {
            return new String(getServletContext().getResourceAsStream(resourcePath).readAllBytes());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    private <M extends ServletRegistration> String getResourcePath(M servletReg) {
        return  getServletClass(servletReg).getAnnotation(ContentServlet.class).resourcePath();
    }

    private <M extends ServletRegistration> boolean checkIfContentUnitInstance(M servletReg) {
        return  getServletClass(servletReg).isAnnotationPresent(ContentServlet.class);
    }

    private <M extends ServletRegistration> Class<?> getServletClass(M servletReg) {

        String className = servletReg.getClassName();
        Class<?> servletClass;
        try {
            servletClass = this.getClass().getClassLoader().loadClass(className);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return servletClass;
    }
}