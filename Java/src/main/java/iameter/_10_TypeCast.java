package iameter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;

import static java.lang.Integer.parseInt;

@WebServlet(name = "_10_TypeCast", value = "/_10_TypeCast")
public class _10_TypeCast extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        final PrintWriter responseWriter = response.getWriter();
        String parm1 = request.getParameter("parm1");

        // True positive
        responseWriter.print(parm1);

        // False positive
        // Analyzers that do not take into account that the parameter is converted to a number will report a vulnerability
        responseWriter.print(parseInt(parm1));
    }

}
