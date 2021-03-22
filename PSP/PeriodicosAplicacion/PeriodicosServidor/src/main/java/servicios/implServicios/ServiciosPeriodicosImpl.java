package servicios.implServicios;

import dao.DaoPeriodicos;
import dao.modelo.Periodico;
import servicios.ServiciosPeriodicos;

import javax.inject.Inject;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.List;
import java.util.stream.Collectors;

public class ServiciosPeriodicosImpl implements ServiciosPeriodicos {
    @Inject
    DaoPeriodicos daoPeriodicos;

    @Override
    public List<Periodico> getAllPeriodicos(){
        return daoPeriodicos.getAllPeriodicos();
    }

    @Override
    public int borrarPeriodico(Periodico periodico){
        return daoPeriodicos.deletePeriodico(periodico);
    }

    @Override
    public int actualizarDatosPeriodico(Periodico periodico){
        return daoPeriodicos.actualizarDatosPeriodico(periodico);
    }

    @Override
    public int insertarPeriodico(Periodico periodico){
        return daoPeriodicos.insertarPeriodico(periodico);
    }

    @Override
    public String validarPeriodico(Periodico periodico) {
        ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
        Validator validator = validatorFactory.getValidator();

        return validator.validate(periodico)
                .stream()
                .map(filtroConstraintViolation -> filtroConstraintViolation.getMessage())
                .collect(Collectors.joining(", "));
    }
}
