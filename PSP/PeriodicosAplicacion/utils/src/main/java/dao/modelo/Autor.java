package dao.modelo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.PositiveOrZero;

@Getter @Setter @AllArgsConstructor
public class Autor {
    @PositiveOrZero
    private int id;
    @NotBlank @NotEmpty
    private String nombre;
    @NotBlank @NotEmpty
    private String apellidos;

    public Autor( @NotBlank @NotEmpty String nombre, @NotBlank @NotEmpty String apellidos) {
        this.nombre = nombre;
        this.apellidos = apellidos;
    }

    @Override
    public String toString() {
        return nombre +" "+ apellidos;
    }
}
