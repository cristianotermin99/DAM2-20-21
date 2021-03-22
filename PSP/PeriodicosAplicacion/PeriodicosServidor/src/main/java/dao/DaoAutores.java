package dao;

import dao.modelo.Autor;

import java.util.List;

public interface DaoAutores {
    Autor insertarAutor(Autor autor);

    List<Autor> getAutores();
}
