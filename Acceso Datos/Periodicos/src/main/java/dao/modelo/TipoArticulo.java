package dao.modelo;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class TipoArticulo {
    private int id;
    private String tipo;

    public TipoArticulo(int id, String tipo) {
        this.id = id;
        this.tipo = tipo;
    }

    @Override
    public String toString() {
        return  tipo;
    }
}
