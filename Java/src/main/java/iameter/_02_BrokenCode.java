package iameter;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "_02_BrokenCode", value = "/_02_BrokenCode")
public class _02_BrokenCode extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // For analyzers that require compilable code, delete or comment out this fragment
        /*/region
        String argument = "harmless value";

        UnknownType.Property1 = parm1;
        UnknownType.Property2 = UnknownType.Property1;
        UnknownType.Property3 = cond1;

        if (UnknownType.Property3 == null) {
            argument = UnknownType.Property2;
        }

        // False positive
        // Analyzers that ignore code that cannot be compiled will report a vulnerability
        responseWriter.print(argument);

        //endregion
        */


    }

}
