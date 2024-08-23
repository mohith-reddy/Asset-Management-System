package edu.ufl.team9.team9asg2;

import edu.ufl.SangavaramM.Inventorydblib.dbCode;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "reassignComputerServlet", value = "/reassignComputer")
public class reassignComputerServlet extends HttpServlet {

    private dbCode o;


    public void init(ServletConfig config) throws ServletException {

        o = new dbCode();

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String eid = request.getParameter("eid");
        String oid = request.getParameter("oid");
        String eqid = request.getParameter("eqid");

        String output = o.reassignEquipment(eid, oid, eqid);
/*
        try (java.io.PrintWriter out = response.getWriter()) {
            out.println("<!DOCTYPE html>");
            out.println("<html lang=\"en\">");
            out.println("<head>");
            out.println("    <meta charset=\"UTF-8\">");
            out.println("    <title>Assigning Computer</title>");
            out.println("    <style>");
            out.println("        body {");
            out.println("            font-family: Arial, sans-serif;");
            out.println("            margin: 40px;");
            out.println("            background-color: #f4f4f4;");
            out.println("        }");
            out.println("        h1 {");
            out.println("            color: #333;");
            out.println("        }");
            out.println("        input[type=\"submit\"] {");
            out.println("            padding: 12px 24px;");
            out.println("            border-radius: 5px;");
            out.println("            border: none;");
            out.println("            background-color: #007bff;");
            out.println("            color: #fff;");
            out.println("            cursor: pointer;");
            out.println("            margin-top: 10px;");
            out.println("            font-size: 16px;");
            out.println("        }");
            out.println("        input[type=\"submit\"]:hover {");
            out.println("            background-color: #0056b3;");
            out.println("        }");
            out.println("    </style>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>" + output + "</h1>");
            out.println("<form action=\"index.jsp\">");
            out.println("    <input type=\"submit\" value=\"Back\">");
            out.println("</form>");
            out.println("</body>");
            out.println("</html>");
        }

*/
        request.setAttribute("output",output);
        request.getRequestDispatcher("assignComputer.jsp").forward(request,response);
    }
}
