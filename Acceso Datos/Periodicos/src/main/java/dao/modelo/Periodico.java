package dao.modelo;

import lombok.*;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.PositiveOrZero;


@Getter @Setter @AllArgsConstructor
public class Periodico {

    private int id;
    @NotBlank @NotEmpty
    private String nombre;
    @Min(0)
    private double precio;
    @NotBlank @NotEmpty
    private String director;
    @PositiveOrZero
    private int id_administrador;

    public Periodico( String nombre, double precio, String director, int id_administrador) {
        this.nombre = nombre;
        this.precio = precio;
        this.director = director;
        this.id_administrador = id_administrador;
    }

    @Override
    public String toString() {
        return  nombre + " " + precio +" "+ director;
    }
}
