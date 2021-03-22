package servicios.implServicios;

import dao.DaoAutores;
import dao.implDao.DaoAutoresImpl;
import dao.modelo.Autor;
import servicios.ServiciosAutores;

import javax.inject.Inject;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.List;
import java.util.stream.Collectors;

public class ServiciosAutoresImpl implements ServiciosAutores {
@Inject
DaoAutores daoAutores;
    @Override
    public Autor insertarAutor(Autor autor){
        return daoAutores.insertarAutor(autor);
    }

    @Override
    public List<Autor> getAutores(){
        return daoAutores.getAutores();
    }

    @Override
    public String validarAutor(Autor object){
        ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
        Validator validator = validatorFactory.getValidator();

        return  validator.validate(object)
                .stream()
                .map(filtroConstraintViolation -> filtroConstraintViolation.getMessage())
                .collect(Collectors.joining(", "));

    }
}
