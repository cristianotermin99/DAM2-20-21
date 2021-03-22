package dao;

import dao.modelo.Usuario;

import java.util.List;

public interface DaoUsuarios {
    Usuario comprobarUsuario(String userNombre, String password);

    int actualizarContraseña(Usuario user, String contraseñaNueva);

    int actualizarDatosUsuario(Usuario user, String userName, String mail);

    Usuario insertarUsuario(Usuario user, String contraseña);

    List<Usuario> getAllUsers();

    int deleteUser(Usuario usuario);

    List<Usuario> getAdmins();
}
