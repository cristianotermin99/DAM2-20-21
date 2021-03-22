package filters;

import dto.Usuario;
import servicios.ServiciosUsuarios;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@WebFilter(filterName = "FilterLogin", urlPatterns={"/login", "/cesta", "/producto"})
public class FilterLogin implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        ServiciosUsuarios serviciosUsuarios=new ServiciosUsuarios();
        String userName = (request.getParameter("usuario"));
        String password = (request.getParameter("password"));
        Usuario usuarioValidar=new Usuario(userName,password);

        if (((HttpServletRequest) request).getServletPath().equals("/login")){
            if (serviciosUsuarios.validarUsuario(usuarioValidar).isEmpty()){
                chain.doFilter(request,response);
            }else{
                request.getRequestDispatcher("html/error.html").forward(request,response);
            }
        }else if(((HttpServletRequest) request).getServletPath().equals("/cesta")){
            chain.doFilter(request,response);
        }else{
            chain.doFilter(request,response);
        }
    }

    @Override
    public void destroy() {

    }
}
