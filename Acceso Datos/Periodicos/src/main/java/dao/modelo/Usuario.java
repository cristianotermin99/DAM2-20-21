package dao.modelo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import javax.validation.constraints.*;

@Getter @Setter @AllArgsConstructor
public class Usuario {
    @PositiveOrZero
    private int idUsuario;
    @NotEmpty @NotBlank
    private final String user;
    @NotEmpty @NotBlank
    private final String password;
    @Min(0) @Max(1) @Digits(integer = 1, fraction = 0)
    private final int primeraVez;
    @NotEmpty @NotBlank
    private final String mail;
    private final TipoUsuario tipoUsuario;

    public Usuario(String user, String password, String mail, TipoUsuario tipoUsuario) {
        this.user = user;
        this.password = password;
        this.primeraVez = 1;
        this.mail = mail;
        this.tipoUsuario = tipoUsuario;
    }

    @Override
    public String toString() {
        return  idUsuario +" "+ user +" "+ mail + " "+tipoUsuario+" ";
    }
}
