package dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.*;

@Getter @Setter @AllArgsConstructor @ToString
public class Usuario {

    @NotEmpty
    @NotBlank
    private final String user;
    @NotEmpty @NotBlank
    private final String password;



    public Usuario(String user, String password, String mail) {
        this.user = user;
        this.password = password;
    }
}
