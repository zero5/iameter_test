package iameter;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "_03_UnreacableCode", value = "/_03_UnreacableCode")
public class _03_UnreachableCode extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        final PrintWriter responseWriter = response.getWriter();
        String parm1 = request.getParameter("parm1");

        // True positive
        responseWriter.println(parm1);

        // False positive
        // Analyzers that do not take into account reachability of execution paths will report a vulnerability
        String cond1 = "ZmFsc2U=";// "false" in Base64 encoding
        if (cond1 == null) {
            responseWriter.print(parm1);
        }

    }


}
