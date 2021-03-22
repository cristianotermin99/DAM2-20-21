package dao;

import dao.modelo.Periodico;

import java.util.List;

public interface DaoPeriodicos {
    List<Periodico> getAllPeriodicos();

    int deletePeriodico(Periodico periodico);

    int actualizarDatosPeriodico(Periodico periodico);

    int insertarPeriodico(Periodico periodico);
}
