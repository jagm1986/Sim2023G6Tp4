package simTp4;

import enums.TipoCoche;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Probabilidad {

    private int minutos;
    private TipoCoche tipoCoche;
    private int precio;
    private double probabilidad;
    private double probabilidadAC;



}
