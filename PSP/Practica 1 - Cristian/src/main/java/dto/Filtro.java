package dto;


import lombok.Data;

@Data
public class Filtro {
    private final String titulo;
    private final String[] cabeceras;
    private final int columnas;
    private final int inferior;
    private final int superior;
}
