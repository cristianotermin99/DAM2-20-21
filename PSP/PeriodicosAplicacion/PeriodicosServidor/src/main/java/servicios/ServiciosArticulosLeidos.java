package servicios;


import dao.modelo.ArticuloLeido;

public interface ServiciosArticulosLeidos {
    int leerArticulo(ArticuloLeido articuloLeido);

    int comprobarArticuloLeido(ArticuloLeido articuloLeido);

    int actualizarRatingArticulo(ArticuloLeido articuloLeido);

    ArticuloLeido getArticuloLeido(ArticuloLeido articuloLeido);

    String validarArticuloLeido(ArticuloLeido object);
}
