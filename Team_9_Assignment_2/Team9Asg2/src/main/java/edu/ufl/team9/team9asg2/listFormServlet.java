package edu.ufl.team9.team9asg2;

import edu.ufl.SangavaramM.Inventorydblib.dbCode;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "listFormServlet", value = "/listForm")
public class listFormServlet extends HttpServlet {

    private dbCode o;

    public void init(ServletConfig config) throws ServletException {
        super.init(config);

        o = new dbCode();

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String eid = request.getParameter("eid");


        String message =o.getEmployeeName(eid);
        List<String> inventory = o.listInventory(eid);

        request.setAttribute("ename", message);
        request.setAttribute("eid", eid);
        request.setAttribute("inventory",inventory);
        request.getRequestDispatcher("listForm.jsp").forward(request,response);
    }
}
