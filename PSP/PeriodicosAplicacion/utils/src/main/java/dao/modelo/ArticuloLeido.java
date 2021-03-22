package dao.modelo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.PositiveOrZero;

@Getter
@Setter
@AllArgsConstructor
public class ArticuloLeido {
    @PositiveOrZero
    private int id;
    @PositiveOrZero
    private int idLector;
    @PositiveOrZero
    private int idArticulo;
    @Max(10) @Min(0)
    private int rating;

    public ArticuloLeido(@PositiveOrZero int idLector, @PositiveOrZero int idArticulo,@Max(10) @Min(0) int rating) {
        this.idLector = idLector;
        this.idArticulo = idArticulo;
        this.rating=rating;
    }


}
