package servlet;

import servicios.ServiciosProductos;
import servicios.ServiciosUsuarios;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "ServletLogin", urlPatterns = {"/login"})
public class ServletLogin extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        hacerLogin(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        hacerLogin(request, response);
    }

    private void hacerLogin(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ServiciosUsuarios serviciosUsuarios=new ServiciosUsuarios();
        ServiciosProductos serviciosProductos=new ServiciosProductos();
        if (serviciosUsuarios.comprobarUsuario(request.getParameter("usuario"),
                request.getParameter("password"))!=null){
            request.getSession().setAttribute("usuario",request.getParameter("usuario"));
            request.setAttribute("numList", serviciosProductos.getAllProductos());
            request.getRequestDispatcher("jsp/productos.jsp").forward(request,response);
        }else{
            request.getRequestDispatcher("jsp/error.html").forward(request,response);
        }



    }
}
