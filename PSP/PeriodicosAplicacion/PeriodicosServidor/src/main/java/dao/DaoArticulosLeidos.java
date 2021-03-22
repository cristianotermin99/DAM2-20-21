package dao;

import dao.modelo.ArticuloLeido;

public interface DaoArticulosLeidos {
    int leerArticulo0(ArticuloLeido articuloLeido);

    int comprobarArticuloLeido(ArticuloLeido articuloLeido);

    ArticuloLeido getArticuloLeido(ArticuloLeido articuloLeido);

    int actualizarRatingArticulo(ArticuloLeido articuloLeido);
}
