package servicios.implServicios;

import dao.DaoArticulos;
import dao.modelo.Articulo;
import dao.modelo.TipoArticulo;
import servicios.ServiciosArticulos;

import javax.inject.Inject;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.List;
import java.util.stream.Collectors;

public class ServiciosArticulosImpl implements ServiciosArticulos {
    @Inject
    DaoArticulos daoArticulos;

    @Override
    public List<Articulo> getAllArticulos() {
        return daoArticulos.getAllArticulos();
    }

    @Override
    public int borrarArticulo(Articulo articulo) {
        return daoArticulos.deleteArticulo(articulo);
    }

    @Override
    public List<TipoArticulo> getTiposArticulos() {
        return daoArticulos.getTiposArticulos();
    }

    @Override
    public Articulo addArticulo(Articulo articulo) {
        return daoArticulos.insertarArticulo(articulo);
    }

    @Override
    public int actualizarArticulo(Articulo articulo) {
        return daoArticulos.actualizarArticulo(articulo);
    }

    @Override
    public List<Articulo> getArticulosDeUnPeriodico(int idPeriodico) {
        return daoArticulos.getArticulosDeUnPeriodico(idPeriodico);
    }

    @Override
    public String validarArticulos(Articulo articulo) {
        ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
        Validator validator = validatorFactory.getValidator();

        return validator.validate(articulo)
                .stream()
                .map(filtroConstraintViolation -> filtroConstraintViolation.getMessage())
                .collect(Collectors.joining(", "));
    }

}
