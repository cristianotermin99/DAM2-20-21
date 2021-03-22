package servicios;

import dao.modelo.Articulo;
import dao.modelo.TipoArticulo;

import java.util.List;

public interface ServiciosArticulos {
    List<Articulo> getAllArticulos();

    int borrarArticulo(Articulo articulo);

    List<TipoArticulo> getTiposArticulos();

    Articulo addArticulo(Articulo articulo);

    int actualizarArticulo(Articulo articulo);

    List<Articulo> getArticulosDeUnPeriodico(int idPeriodico);

    String validarArticulos(Articulo articulo);
}
