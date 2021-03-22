package servicios;


import dto.Filtro;
import dto.FiltroException;

import java.util.Arrays;
import java.util.stream.Collectors;

public class Validador {


    public Filtro validarFiltro(String titulo, String [] cabeceras, String columnas,String inferior,String superior,String stripped)
            throws FiltroException
    {
        String error = null;
        var inferiorInterno = 0;
        var superiorInterno=0;
        Filtro f = null;
        String color="";

        if ( (cabeceras==null) || (columnas==null) )
        {
            error ="Titulo o cabecera o columnas no se admiten nulos";
        }
        if (inferior!= null && !inferior.isBlank())
        {
            if (!inferior.chars().allMatch(Character::isDigit))
                error = "Inferior debe ser numero";
            else
                inferiorInterno = Integer.parseInt(inferior);
        }
        else
            inferiorInterno=0;

        if (superior!= null && !superior.isBlank())
        {
            if (!superior.chars().allMatch(Character::isDigit))
                error = "Inferior debe ser numero";
            else
                superiorInterno = Integer.parseInt(superior);
        }
        if (error == null) {
            long numeroCabeceras = Arrays.stream(cabeceras)
                    .filter(s -> !s.isBlank()).count();
            int columna = Integer.parseInt(columnas);

            if (numeroCabeceras != columna) {
                error = "Cabceras y columnas no son iguales";
            }
        }
        if (stripped!=null){
            color="#FFFF00";
        }
        if (error == null)
        {

           f = new Filtro(titulo == null ? "":titulo,
                   Arrays.stream(cabeceras)
                   .filter(s -> !s.isBlank()).toArray(String[]::new),Integer.parseInt(columnas)
           ,inferiorInterno,superiorInterno,color);
        }
        else
        {
            throw new FiltroException(error);
        }
        return f;

    }
}
