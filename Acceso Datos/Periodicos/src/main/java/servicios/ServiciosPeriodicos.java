package servicios;

import dao.modelo.Periodico;

import java.util.List;

public interface ServiciosPeriodicos {
    List<Periodico> getAllPeriodicos();

    int borrarPeriodico(Periodico periodico);

    int actualizarDatosPeriodico(Periodico periodico);

    int insertarPeriodico(Periodico periodico);

    String validarPeriodico(Periodico periodico);
}
