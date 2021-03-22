package servicios;

import dao.DaoProductos;
import dao.DaoUsuarios;

import java.util.List;

public class ServiciosProductos {
    public List<String> getAllProductos(){
        DaoProductos daoProductos=new DaoProductos();
        return daoProductos.listaProductos();
    }
}
