package dao;

import dao.modelo.Lector;
import dao.modelo.Usuario;

import java.time.LocalDate;

public interface DaoLectores {
    Lector getLector(Usuario user);

    int actualizarDatosLector(Usuario user, String nombre, LocalDate date);

    Lector addLector(Lector lector, String contraseña);

    int deleteLector(Lector usuario);
}
