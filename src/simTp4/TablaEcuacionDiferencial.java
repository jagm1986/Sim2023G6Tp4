package simTp4;

import java.text.DecimalFormat;
import java.util.List;
import javax.swing.table.DefaultTableModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class TablaEcuacionDiferencial extends DefaultTableModel {

    public TablaEcuacionDiferencial(List<EcuacionDiferencial> filasTabla) {
        cargarDatos(filasTabla);
    }

    private void cargarDatos(List<EcuacionDiferencial> filasTabla) {
        DecimalFormat df = new DecimalFormat("0.000");

        Object[][] matrizTabla = new Object[calcularCantidadFilas(filasTabla)][78]; // Crea la tabla de tamaño cantidadIntervalos+1X4
        int i = 0;

        for (EcuacionDiferencial simulacion : filasTabla) //Recorre los intervalos
        {

            matrizTabla[i][0] = simulacion.getNroSimulacion() + i;//Nro sim
            matrizTabla[i][1] = df.format(simulacion.getT());//t

            matrizTabla[i][2] = df.format(simulacion.getD()); //D
            matrizTabla[i][3] = df.format(simulacion.getDDdt());//dD/dt
            matrizTabla[i][4] = df.format(simulacion.getTimas1());//t(i+1)
            matrizTabla[i][5] = df.format(simulacion.getDimas1()); //D(i+1)

            i++;
        }

        String[] NombresDeColumnas = new String[]{
            "Nro", "t", "D", "dD/dt", "t(i+1)", "D(i+1)",};

        this.setDataVector(matrizTabla, NombresDeColumnas);

    }

    private Integer calcularCantidadFilas(List<EcuacionDiferencial> filasTabla) {
        Integer cantidadFilas = filasTabla.size();

        return cantidadFilas;
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return false;
    }
}
