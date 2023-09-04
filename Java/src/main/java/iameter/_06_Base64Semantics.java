package iameter;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.binary.StringUtils;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "_06_Base64Semantics", value = "/_06_Base64Semantics")
public class _06_Base64Semantics extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        final PrintWriter responseWriter = response.getWriter();
        String parm1 = request.getParameter("parm1");

        // True positive
        responseWriter.println(parm1);

        String cond1 = "ZmFsc2U=";// "false" in Base64 encoding
        if ("true".equals(StringUtils.newStringUtf8(Base64.decodeBase64(cond1)))) {
            // False positive
            // Analyzers that do not take into account the semantics of standard encoding functions will report a vulnerability
            responseWriter.print(parm1);
        }
    }

}
