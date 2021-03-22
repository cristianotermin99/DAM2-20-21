package dao;

import dao.modelo.Lector;
import dao.modelo.Suscripcion;

import java.util.List;

public interface DaoSuscripciones {
    int insertarSuscripcion(Suscripcion suscripcion);

    int deleteSuscripcion(Suscripcion suscripcion);

    List<Suscripcion> getSuscripcionesDeUnLector(Lector lector);
}
