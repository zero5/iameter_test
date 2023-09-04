package iameter;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;

@WebServlet(name = "_04_StandardEncoders", value = "/_04_StandardEncoders")
public class _04_StandardEncoders extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        final PrintWriter responseWriter = response.getWriter();
        String parm1 = request.getParameter("parm1");

        // True positive
        responseWriter.println(parm1);

        // False positive
        // Analyzers that do not take into account the semantics of standard filtering functions will report a vulnerability
        responseWriter.print(URLEncoder.encode(parm1, "UTF-8"));
    }
}
