package servicios.implServicios;

import dao.DaoArticulosLeidos;
import dao.implDao.DaoArticulosLeidosImpl;
import dao.modelo.ArticuloLeido;
import servicios.ServiciosArticulosLeidos;

import javax.inject.Inject;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.stream.Collectors;

public class ServiciosArticulosLeidosImpl implements ServiciosArticulosLeidos {
@Inject
DaoArticulosLeidos daoArticulosLeidos;
    @Override
    public int leerArticulo(ArticuloLeido articuloLeido){
        return daoArticulosLeidos.leerArticulo0(articuloLeido);
    }

    @Override
    public int comprobarArticuloLeido(ArticuloLeido articuloLeido){
        return daoArticulosLeidos.comprobarArticuloLeido(articuloLeido);
    }

    @Override
    public int actualizarRatingArticulo(ArticuloLeido articuloLeido){
        return daoArticulosLeidos.actualizarRatingArticulo(articuloLeido);
    }

    @Override
    public ArticuloLeido getArticuloLeido(ArticuloLeido articuloLeido){
        return daoArticulosLeidos.getArticuloLeido(articuloLeido);
    }

    @Override
    public String validarArticuloLeido(ArticuloLeido object){
        ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
        Validator validator = validatorFactory.getValidator();

        return  validator.validate(object)
                .stream()
                .map(filtroConstraintViolation -> filtroConstraintViolation.getMessage())
                .collect(Collectors.joining(", "));

    }
}
