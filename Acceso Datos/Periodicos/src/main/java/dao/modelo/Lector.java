package dao.modelo;

import lombok.*;

import javax.validation.constraints.*;
import java.time.LocalDate;

@Getter
@Setter
public class Lector extends Usuario {
    private int idLector;
    private final String nombre;
    private final LocalDate birth;

    public Lector(@PositiveOrZero int idUsuario,
                  @NotEmpty @NotBlank String user,
                  @NotEmpty @NotBlank String password,
                  @Min(0) @Max(1) @Digits(integer = 1, fraction = 0) int primeraVez,
                  @NotEmpty @NotBlank String mail,
                  TipoUsuario tipoUsuario,
                  int idLector,
                  String nombre,
                  LocalDate birth) {
        super(idUsuario, user, password, primeraVez, mail, tipoUsuario);
        this.idLector = idLector;
        this.nombre = nombre;
        this.birth = birth;
    }
    public Lector(@NotEmpty @NotBlank String user,
                  @NotEmpty @NotBlank String password,
                  @NotEmpty @NotBlank String mail,
                  TipoUsuario tipoUsuario,
                  String nombre,
                  LocalDate birth) {
        super(user, password, mail, tipoUsuario);
        this.nombre = nombre;
        this.birth = birth;
    }

    @Override
    public String toString() {
        return nombre+" "+birth+" "+super.toString();
    }
}
