package servicios;

import dao.modelo.Lector;
import dao.modelo.Usuario;

import java.time.LocalDate;

public class ServiciosLectoresCliente {
    public String validarLector(Lector lector) {
        return"";
    }

    public int actualizarDatosLector(Usuario usuario, String nombrePropio, LocalDate fechaNacimiento) {
        return 0;
    }

    public Lector getLector(Usuario usuario) {

        return null;
    }

    public Lector addLector(Lector lector) {
        return lector;
    }
}
