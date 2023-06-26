package simTp4;

import java.util.ArrayList;
import java.util.List;
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
public class VectorEcuacionDiferencial {

    private double h;
    private double T;
    private double C;
    private double to;
    private double Do;

    private List<EcuacionDiferencial> simulaciones;

    public void crearSimulacionesEcuacionesDiferenciales() {

        simulaciones = new ArrayList<>();

        double D = 0;
        int i = 0;
        double t = 0;
        double dD_dt = 0;
        double t_i_mas_1 = 0;
        double D_i_mas_1 = 0;

        EcuacionDiferencial ecuacionDiferencial = EcuacionDiferencial.builder()
                .timas1(t_i_mas_1)
                .Dimas1(D_i_mas_1)
                .build();

        simulaciones.add(ecuacionDiferencial);

        while (D <= this.Do) {

            D = simulaciones.get(i).getDimas1();
            t = simulaciones.get(i).getTimas1();

            dD_dt = (this.C + 0.2 * this.T + (Math.pow(t, 2)));
            t_i_mas_1 = t + this.h;
            D_i_mas_1 = D + (dD_dt * this.h);

            EcuacionDiferencial ecuacionDiferencialAux = EcuacionDiferencial.builder()
                    .t(t)
                    .D(D)
                    .dDdt(dD_dt)
                    .timas1(t_i_mas_1)
                    .Dimas1(D_i_mas_1)
                    .build();
            simulaciones.add(ecuacionDiferencialAux);

            i++;
        }
    }

    public void resetear() {
        this.to = 0;
        this.simulaciones = new ArrayList<>();

    }

}
