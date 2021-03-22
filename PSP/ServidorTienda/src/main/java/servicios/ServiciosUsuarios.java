package servicios;

import dao.DaoUsuarios;
import dto.Usuario;

import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.stream.Collectors;

public class ServiciosUsuarios {

    public String validarUsuario(Object object) {
        ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
        Validator validator = validatorFactory.getValidator();

        return validator.validate(object)
                .stream()
                .map(filtroConstraintViolation -> filtroConstraintViolation.getMessage())
                .collect(Collectors.joining(", "));
    }

    public Usuario comprobarUsuario(String user, String password) {
        DaoUsuarios daoUsuarios = new DaoUsuarios();
        return daoUsuarios.comprobarUsuario(user, password);
    }
}
