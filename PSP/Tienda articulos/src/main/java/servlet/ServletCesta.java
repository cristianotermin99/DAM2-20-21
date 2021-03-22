package servlet;

import servicios.ServiciosProductos;
import servicios.ServiciosUsuarios;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@WebServlet(name = "ServletCesta", urlPatterns = "/cesta")
public class ServletCesta extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        cesta(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        addProductos(request,response);
    }

    private void cesta(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        ServiciosProductos serviciosProductos=new ServiciosProductos();
        switch (request.getParameter("button")){
            case "Comprar":
                request.getSession().setAttribute("listaProductos", null);
                request.setAttribute("numList", serviciosProductos.getAllProductos());
                request.getRequestDispatcher("jsp/productos.jsp").forward(request, response);
                break;
            case "Atras":
                request.setAttribute("numList", serviciosProductos.getAllProductos());
                request.getRequestDispatcher("jsp/productos.jsp").forward(request, response);
                break;
            case "Logout":
                request.getSession().setAttribute("listaProductos",null);
                request.getSession().setAttribute("usuario",null);
                request.getRequestDispatcher("index.html").forward(request, response);
                break;
        }
    }

    private void addProductos(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<String> listaProductos = new ArrayList<>();
        if (request.getSession().getAttribute("listaProductos") != null) {
            listaProductos.addAll((List<String>) (request.getSession().getAttribute("listaProductos")));
            listaProductos.addAll(Arrays.asList(request.getParameterValues("producto")));
            request.getSession().setAttribute("listaProductos", listaProductos);
        } else {
            listaProductos.clear();
            listaProductos = Arrays.asList(request.getParameterValues("producto"));
            request.getSession().setAttribute("listaProductos", listaProductos);
        }
        request.getRequestDispatcher("jsp/cesta.jsp").forward(request, response);
    }
}
