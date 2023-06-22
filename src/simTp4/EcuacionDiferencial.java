package simTp4;

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
public class EcuacionDiferencial {

    private int nroSimulacion;
    private double t;
    private double D;
    private double dDdt;
    private double timas1;
    private double Dimas1;


}
