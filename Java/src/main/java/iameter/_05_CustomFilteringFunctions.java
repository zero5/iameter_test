package iameter;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;

class CustomFilter {
    static String filter(String s) {
        return s.replace("<","").replace(">","");
    }
}

@WebServlet(name = "_05_CustomFilteringFunctions", value = "/_05_CustomFilteringFunctions")
public class _05_CustomFilteringFunctions extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        final PrintWriter responseWriter = response.getWriter();
        String parm1 = request.getParameter("parm1");

        // True positive
        responseWriter.println(parm1);

        // False positive
        // Analyzers that do not take into account the semantics of custom filtering functions will report a vulnerability
        // (CustomFilter.Filter implements the logic `s.Replace("<", string.Empty).Replace(">", string.Empty)`)
        responseWriter.print(CustomFilter.filter(parm1));
    }
}
