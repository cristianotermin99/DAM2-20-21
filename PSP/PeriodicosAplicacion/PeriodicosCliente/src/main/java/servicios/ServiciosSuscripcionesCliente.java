package servicios;

import dao.modelo.Lector;
import dao.modelo.Suscripcion;

public class ServiciosSuscripcionesCliente {
    public String validarSuscripcion(Suscripcion suscripcion) {
        return "";
    }

    public int insertarSuscripcion(Suscripcion suscripcion) {
        return 0;
    }

    public int desuscribirse(Suscripcion suscripcion) {
        return 0;
    }

    public Suscripcion getSuscripcionesLector(Lector lector) {
        Suscripcion suscripcion=new Suscripcion();
        return suscripcion;
    }
}
