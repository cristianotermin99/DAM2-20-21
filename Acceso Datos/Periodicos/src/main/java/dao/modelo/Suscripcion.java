package dao.modelo;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.PositiveOrZero;
import java.time.LocalDate;

@Getter @Setter  @ToString @AllArgsConstructor
public class Suscripcion {
    @PositiveOrZero
    private int idSuscripcion;
    @PositiveOrZero
    private int idLector;
    @PositiveOrZero
    private int idPeriodico;
    @PastOrPresent
    private LocalDate fechaInicio;
    @FutureOrPresent
    private LocalDate fechaBaja;

    public Suscripcion( @PositiveOrZero int idLector, @PositiveOrZero int idPeriodico, @PastOrPresent LocalDate fechaInicio, @FutureOrPresent LocalDate fechaBaja) {

        this.idLector = idLector;
        this.idPeriodico = idPeriodico;
        this.fechaInicio = fechaInicio;
        this.fechaBaja = fechaBaja;
    }

    public Suscripcion(@PositiveOrZero  int idSuscripcion, @PositiveOrZero int idLector, @PositiveOrZero int idPeriodico, @PastOrPresent LocalDate fechaInicio) {
        this.idSuscripcion = idSuscripcion;
        this.idLector = idLector;
        this.idPeriodico = idPeriodico;
        this.fechaInicio = fechaInicio;
    }

    @Override
    public String toString() {
        return "Suscripcion{" +
                "idSuscripcion=" + idSuscripcion +
                ", idLector=" + idLector +
                ", idPeriodico=" + idPeriodico +
                ", fechaInicio=" + fechaInicio +
                ", fechaBaja=" + fechaBaja +
                '}';
    }
}
