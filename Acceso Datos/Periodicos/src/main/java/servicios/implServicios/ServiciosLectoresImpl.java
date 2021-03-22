package servicios.implServicios;

import dao.DaoLectores;
import dao.implDao.DaoLectoresImpl;
import dao.modelo.Lector;
import dao.modelo.Usuario;
import servicios.ServiciosLectores;

import javax.inject.Inject;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.time.LocalDate;
import java.util.stream.Collectors;

public class ServiciosLectoresImpl implements ServiciosLectores {

@Inject
DaoLectores daoLectores;

    @Override
    public Lector getLector(Usuario u)
    {
        return daoLectores.getLector(u);
    }

   @Override
   public Lector addLector(Lector lector) {
        String contraseñaHashed = ServiciosLectores.bytesToHex(lector.getPassword());
        return daoLectores.addLector(lector, contraseñaHashed);
    }

    @Override
    public int actualizarDatosLector(Usuario user, String nombre, LocalDate date){
        return daoLectores.actualizarDatosLector(user,nombre,date);
    }


    @Override
    public String validarLector(Lector object){
        ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
        Validator validator = validatorFactory.getValidator();

        return  validator.validate(object)
                .stream()
                .map(filtroConstraintViolation -> filtroConstraintViolation.getMessage())
                .collect(Collectors.joining(", "));

    }

}
