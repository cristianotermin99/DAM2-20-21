package servicios.implServicios;

import dao.DaoSuscripciones;
import dao.implDao.DaoSuscripcionesImpl;
import dao.modelo.Lector;
import dao.modelo.Suscripcion;
import servicios.ServiciosSuscripciones;

import javax.inject.Inject;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.List;
import java.util.stream.Collectors;

public class ServiciosSuscripcionesImpl implements ServiciosSuscripciones {
    @Inject
    DaoSuscripciones daoSuscripciones;

    @Override
    public List<Suscripcion> getSuscripcionesLector(Lector lector) {
        return daoSuscripciones.getSuscripcionesDeUnLector(lector);
    }

    @Override
    public int insertarSuscripcion(Suscripcion suscripcion){
        return daoSuscripciones.insertarSuscripcion(suscripcion);
    }


    @Override
    public int desuscribirse(Suscripcion suscripcion) {
        return daoSuscripciones.deleteSuscripcion(suscripcion);
    }

    @Override
    public String validarSuscripcion(Suscripcion suscripcion) {
        ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
        Validator validator = validatorFactory.getValidator();

        return validator.validate(suscripcion)
                .stream()
                .map(filtroConstraintViolation -> filtroConstraintViolation.getMessage())
                .collect(Collectors.joining(", "));
    }
}
