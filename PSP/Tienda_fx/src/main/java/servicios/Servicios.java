package servicios;

import modelo.Usuario;

import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.stream.Collectors;

public class Servicios {
    public String validarUsuario(Usuario object){
        ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
        Validator validator = validatorFactory.getValidator();

        return  validator.validate(object)
                .stream()
                .map(filtroConstraintViolation -> filtroConstraintViolation.getMessage())
                .collect(Collectors.joining(", "));

    }
}
