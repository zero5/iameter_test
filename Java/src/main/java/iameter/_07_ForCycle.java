package iameter;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "_07_ForCycle", value = "/_07_ForCycle")
public class _07_ForCycle extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        final PrintWriter responseWriter = response.getWriter();
        String parm1 = request.getParameter("parm1");

        // True positive
        responseWriter.println(parm1);

        Integer sum = 0;
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 15; j++) {
                sum += i + j;
            }
        }

        if (sum != 1725) {
            // False positive
            // Analyzers that approximate or ignore the cycle interpretation will report a vulnerability
            responseWriter.print(parm1);
        }
    }

}
