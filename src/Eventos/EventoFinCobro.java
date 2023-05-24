package Eventos;

import ObjetosTemporales.Auto;
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
public class EventoFinCobro {

    private long tiempoCobro;
    private double finAtCobro;
    private int nroAuto;
    private int nroEstacionamiento;
    private Auto auto;


}
