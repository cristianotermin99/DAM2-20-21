package servicios;

import dao.modelo.Autor;

import java.util.List;

public interface ServiciosAutores {
    Autor insertarAutor(Autor autor);

    List<Autor> getAutores();

    String validarAutor(Autor object);
}
