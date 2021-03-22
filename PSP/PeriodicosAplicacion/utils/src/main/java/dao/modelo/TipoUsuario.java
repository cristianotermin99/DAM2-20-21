package dao.modelo;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.PositiveOrZero;

@Getter @Setter
public class TipoUsuario {
    @PositiveOrZero
    private final int idTipoUsuario;
    @NotEmpty @NotBlank
    private final String tipo;
    @NotEmpty @NotBlank
    private final String descripcion;

    public TipoUsuario(int idTipoUsuario, String tipo, String descripcion) {
        this.idTipoUsuario = idTipoUsuario;
        this.tipo = tipo;
        this.descripcion = descripcion;
    }

    @Override
    public String toString() {
        return tipo ;
    }
}
