package dao.modelo;

import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.PositiveOrZero;

@Getter @Setter @AllArgsConstructor
public class Articulo {
    @PositiveOrZero
    private int idArticulo;
    @NotEmpty @NotBlank
    private String titular;
    @NotEmpty @NotBlank
    private String descripcion;
    @PositiveOrZero
    private int idPeriodico;
    @PositiveOrZero
    private int idTipoArticulo;
    @PositiveOrZero
    private int idAutor;


    public Articulo(String titular, String descripcion, int idPeriodico, int idTipoArticulo, int idAutor) {
        this.titular = titular;
        this.descripcion = descripcion;
        this.idPeriodico = idPeriodico;
        this.idTipoArticulo = idTipoArticulo;
        this.idAutor = idAutor;
    }

    @Override
    public String toString() {
        return titular + '\'' +
                ", descripcion='" + descripcion + '\'' +
                '}';
    }
}
