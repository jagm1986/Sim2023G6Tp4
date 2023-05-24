package VariablesEstadisticas;

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
public class VariablesEstadisticas {

    private int id;
    private double recaudacion;
    private double cantidadAutosNoIngresados;
    private double cantidadAutosNoIngresadosAC;
    private double porcentajeUtilizacionPlaya;
    private double porcentajeUtilizacionPlataAC;

}
