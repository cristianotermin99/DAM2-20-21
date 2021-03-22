package servicios;

import dao.modelo.Articulo;
import dao.modelo.Periodico;
import dao.modelo.TipoArticulo;

public class ServiciosArticulosCliente {
    public Articulo addArticulo(Articulo articulo) {
        return articulo;
    }

    public String validarArticulos(Articulo articulo) {
        return "";
    }

    public int borrarArticulo(Articulo articulo) {
        return 0;
    }

    public int actualizarArticulo(Articulo articulo) {
        return 0;
    }

    public TipoArticulo getTiposArticulos() {
        TipoArticulo tipoArticulo=null;
        return tipoArticulo;
    }

    public Articulo getAllArticulos() {
        Articulo articulo=null;
        return articulo;
    }

    public Articulo getArticulosDeUnPeriodico(int periodico) {
        Articulo articulo=null;
        return articulo;
    }


}
