package iameter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringReader;

import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import javax.xml.parsers.*;

@WebServlet(name = "_09_XXE", value = "/_09_XXE")
public class _09_XXE extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        final PrintWriter responseWriter = response.getWriter();
        InputSource parm1 = new InputSource(new StringReader(request.getParameter("parm1")));

        // True positive
        responseWriter.println(request.getParameter("parm1"));

        DocumentBuilderFactory builderFactory;
        DocumentBuilder documentBuilder;

        try {
            // Finding a vulnerability depends on the parser settings
            builderFactory = DocumentBuilderFactory.newInstance();
            builderFactory.setFeature("http://apache.org/xml/features/disallow-doctype-decl", true);
            documentBuilder = builderFactory.newDocumentBuilder();
            documentBuilder.parse(parm1); //False positive

            builderFactory = DocumentBuilderFactory.newInstance();
            builderFactory.setFeature("http://xml.org/sax/features/external-parameter-entities", false);
            documentBuilder = builderFactory.newDocumentBuilder();
            documentBuilder.parse(parm1); //True positive

        } catch (SAXException e) {
            e.printStackTrace();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }

        responseWriter.print("Done!");
    }
}
