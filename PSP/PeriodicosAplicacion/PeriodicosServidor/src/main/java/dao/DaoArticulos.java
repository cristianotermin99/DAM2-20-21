package dao;

import dao.modelo.Articulo;
import dao.modelo.TipoArticulo;

import java.util.List;

public interface DaoArticulos {
    List<Articulo> getAllArticulos();

    int deleteArticulo(Articulo articulo);

    Articulo insertarArticulo(Articulo articulo);

    int actualizarArticulo(Articulo articulo);

    List<TipoArticulo> getTiposArticulos();

    List<Articulo> getArticulosDeUnPeriodico(int idPeriodico);
}
