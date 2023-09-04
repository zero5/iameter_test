package iameter;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;

interface PrintWriterInterface {
    void println(String s);
}

@WebServlet(name = "_01_FunctionalDataFlow", value = "/_01_FunctionalDataFlow")
public class _01_FunctionalDataFlow extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        final PrintWriter responseWriter = response.getWriter();

        String parm1 = request.getParameter("parm1");

        // True positive
        responseWriter.println(parm1);

        // False negative
        // Analyzers that do not interpret the execution flow based on functional data flows will not report a vulnerability
        PrintWriterInterface responseWriter3 = (s) -> responseWriter.println(s);
        responseWriter3.println(parm1);
    }


}
