package servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@WebServlet(name = "ServletProductos", urlPatterns = {"/producto"})
public class ServletProductos extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        cargarProductos(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        cargarProductos(request, response);
    }

    private void cargarProductos(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        var queHaces=request.getParameter("accionProductosCesta");
        List<String> cesta = (List) request.getSession().getAttribute("listaProductos");
        if (cesta == null) {
            cesta = new ArrayList<>();
        }
        if (request.getParameterValues("producto") != null) {
            cesta.addAll(Arrays.asList(request.getParameterValues("producto")));
        }

        request.getSession().setAttribute("listaProductos", cesta);
        switch (queHaces){
            case "add":
                response.getWriter().print(request.getSession().getAttribute("listaProductos"));
                break;
            case "cargar":
                response.getWriter().print(request.getSession().getAttribute("numList"));
                break;
        }

    }
}
