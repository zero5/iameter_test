package iameter;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "_08_StringBuilderSemantics", value = "/_08_StringBuilderSemantics")
public class _08_StringBuilderSemantics extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        final PrintWriter responseWriter = response.getWriter();
        String parm1 = request.getParameter("parm1");
        StringBuilder sb = new StringBuilder();

        // True positive
        responseWriter.println(parm1);

        String cond1 = "ZmFsc2U=";// "false" in Base64 encoding
        sb.append(cond1);
        if ("true".equals(sb.toString())) {
            // False positive
            // Analyzers that do not interpret the semantics of standard library types will report a vulnerability
            responseWriter.print(parm1);
        }
    }
}
