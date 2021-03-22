package servicios;

import dao.modelo.Lector;
import dao.modelo.Suscripcion;

import java.util.List;

public interface ServiciosSuscripciones {
    List<Suscripcion> getSuscripcionesLector(Lector lector);

    int insertarSuscripcion(Suscripcion suscripcion);

    int desuscribirse(Suscripcion suscripcion);

    String validarSuscripcion(Suscripcion suscripcion);
}
