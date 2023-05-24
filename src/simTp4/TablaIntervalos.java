package simTp4;

import Eventos.EventoFinEstacionamiento;
import ObjetosPermanentes.Sector;
import ObjetosTemporales.Auto;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.DefaultTableModel;
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
public class TablaIntervalos extends DefaultTableModel {

    private double cantidadAutosNoIngresadosTotal;
    private double recaudacionTotal;
    private double procentajeUtilizacionPlaya;

    public TablaIntervalos(List<PlayaEstacionamiento> filasTabla) {
        cargarDatos(filasTabla);
    }

    private void cargarDatos(List<PlayaEstacionamiento> filasTabla) {
        Object[][] matrizTabla = new Object[calcularCantidadFilas(filasTabla)][28]; // Crea la tabla de tamaño cantidadIntervalos+1X4
        int i = 0;

        //Acumuladores totales
        double acumuladorRecaudacion = 0, acumuladorCantidadAutosNoIngresados = 0, acumuladorPorcUtilizacionPlaya = 0;

        for (PlayaEstacionamiento simulacion : filasTabla) //Recorre los intervalos
        {
            //Acumuladores por simulacion
            long cantAutosNoIngresasXSimulacion = 0;

            matrizTabla[i][0] = simulacion.getNroSimulacion();//Nro sim
            matrizTabla[i][1] = simulacion.getReloj();//reloj

            matrizTabla[i][2] = simulacion.getEventoName();
            matrizTabla[i][3] = simulacion.getRndTipoCoche();
            matrizTabla[i][4] = simulacion.getTipoCoche();
            matrizTabla[i][5] = simulacion.getPrecio();
            matrizTabla[i][6] = simulacion.getRndMinutosEstacionamiento();
            matrizTabla[i][7] = simulacion.getMinutos();

            matrizTabla[i][8] = simulacion.getEventoLLegadaAuto().getRnd();
            matrizTabla[i][9] = simulacion.getEventoLLegadaAuto().getTiempoEntreLlegadas();
            matrizTabla[i][10] = simulacion.getEventoLLegadaAuto().getProximoAuto();

            int iAnteriorAlLoop = i;
            for (EventoFinEstacionamiento finEstacionamiento : simulacion.getEventosFinEstacionamiento()) {
                i++;
                int j = finEstacionamiento.getNro();
                matrizTabla[i][11] = j;
                matrizTabla[i][12] = finEstacionamiento.getFinEstacionamiento();

            }

            i = iAnteriorAlLoop;
            for (Sector sector : simulacion.getSectores()) {
                i++;
                int j = sector.getId();
                matrizTabla[i][13] = j;
                matrizTabla[i][14] = sector.getEstadoSector();

            }

            i = iAnteriorAlLoop;
            acumuladorRecaudacion += simulacion.getVariablesEstadisticas().getRecaudacion();
            acumuladorPorcUtilizacionPlaya += simulacion.getVariablesEstadisticas().getPorcentajeUtilizacionPlaya();
            acumuladorCantidadAutosNoIngresados += simulacion.getVariablesEstadisticas().getCantidadAutosNoIngresados();

            matrizTabla[i][15] = simulacion.getEventoFinCobro() != null ? simulacion.getEventoFinCobro().getFinAtCobro() : 0;
            matrizTabla[i][16] = simulacion.getCajaCobro() != null ? simulacion.getCajaCobro().getEstadoCaja(): "";
            matrizTabla[i][17] = simulacion.getCajaCobro() != null ? simulacion.getCajaCobro().getCola(): 0;
            matrizTabla[i][18] = simulacion.getVariablesEstadisticas().getRecaudacion();
            matrizTabla[i][19] = simulacion.getVariablesEstadisticas().getCantidadAutosNoIngresados();
            matrizTabla[i][20] = simulacion.getVariablesEstadisticas().getPorcentajeUtilizacionPlaya();

            matrizTabla[i][21] = acumuladorRecaudacion;
            matrizTabla[i][22] = acumuladorPorcUtilizacionPlaya;
            matrizTabla[i][23] = acumuladorCantidadAutosNoIngresados;
            matrizTabla[i][24] = simulacion.getCantidadOcupados();

            i = i + 13;
        }

        this.recaudacionTotal = acumuladorRecaudacion;
        this.cantidadAutosNoIngresadosTotal = acumuladorCantidadAutosNoIngresados;
        this.procentajeUtilizacionPlaya = acumuladorPorcUtilizacionPlaya * 100 / filasTabla.get(filasTabla.size() - 1).getReloj();

        String[] NombresDeColumnas = new String[]{"Nro", "Reloj", "Evento", "RndTC",
            "Tipo_Coche", "Precio", "Rnd Minutos", "Minutos", "Rnd Llegada Auto",
            "TpoELLg", "Proximo_Auto", "Nro_FinEst", "FinEstacionamiento", "Nro Sector",
            "Estado Sector", "Fin_Cobro", "Caja-Estado", "Caja-Cola", "Recaudacion", "Autos No Ingresados", "PorUtilizacion",
            "RecaudacionTotal", "PorUtilizacionTotal", "ANIAC", "Cantidad Ocupados"
        };

        this.setDataVector(matrizTabla, NombresDeColumnas);
    }

    private Integer calcularCantidadFilas(List<PlayaEstacionamiento> filasTabla) {
        Integer cantidadFilas = filasTabla.size();
        for (PlayaEstacionamiento sim : filasTabla) {
            cantidadFilas += (sim.getEventosFinEstacionamiento().size()) + 1;
        }
        return cantidadFilas;
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return false;
    }
}
