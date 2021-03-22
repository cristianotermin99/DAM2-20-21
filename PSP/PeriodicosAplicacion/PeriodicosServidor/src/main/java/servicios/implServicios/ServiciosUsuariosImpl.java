package servicios.implServicios;


import dao.DaoLectores;
import dao.DaoUsuarios;
import dao.modelo.Lector;
import dao.modelo.Usuario;
import lombok.SneakyThrows;
import servicios.ServiciosUsuarios;

import javax.inject.Inject;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.List;
import java.util.stream.Collectors;

public class ServiciosUsuariosImpl implements ServiciosUsuarios {

    @Inject
    DaoUsuarios daoUsuarios;
    @Inject
    DaoLectores daoLectores;

    @Override
    @SneakyThrows
    public Usuario comprobarUsuario(String usuario, String password) {
        String contraseñaHashed = ServiciosUsuarios.bytesToHex(password);
        return daoUsuarios.comprobarUsuario(usuario, contraseñaHashed);
    }

    @Override
    public int cambiarContraseña(Usuario usuario, String nuevaPassword) {
        return daoUsuarios.actualizarContraseña(usuario, ServiciosUsuarios.bytesToHex(nuevaPassword));
    }

    @Override
    public int actualizarDatos(Usuario usuario, String userName, String mail) {
        return daoUsuarios.actualizarDatosUsuario(usuario, userName, mail);
    }

    @Override
    public Usuario insertarAdmin(Usuario usuario) {
        String contraseñaHashed = ServiciosUsuarios.bytesToHex(usuario.getPassword());
        return daoUsuarios.insertarUsuario(usuario, contraseñaHashed);
    }

    @Override
    public List<Usuario> getAllUsers() {
        return daoUsuarios.getAllUsers();
    }

    @Override
    public int deleteUser(Usuario usuario) {

        if (usuario.getTipoUsuario().getIdTipoUsuario() == 3) {
            Lector lector=daoLectores.getLector(usuario);
            return daoLectores.deleteLector(lector);
        } else if (usuario.getTipoUsuario().getIdTipoUsuario() == 2){
            return daoUsuarios.deleteUser(usuario);
        }else{
            return 3;
        }
    }

    @Override
    public List<Usuario> getAdmins() {
        return daoUsuarios.getAdmins();
    }

    @Override
    public String validarUsuario(Usuario usuario) {
        ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
        Validator validator = validatorFactory.getValidator();

        return validator.validate(usuario)
                .stream()
                .map(filtroConstraintViolation -> filtroConstraintViolation.getMessage())
                .collect(Collectors.joining(", "));

    }


}
