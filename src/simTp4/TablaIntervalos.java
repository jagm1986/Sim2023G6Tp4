package simTp4;

import Eventos.EventoFinEstacionamiento;
import ObjetosPermanentes.Sector;
import ObjetosTemporales.Auto;
import java.text.DecimalFormat;
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

    private static final DecimalFormat df = new DecimalFormat("0.000");
    private double cantidadAutosNoIngresadosTotal;
    private String recaudacionTotal;
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
            matrizTabla[i][16] = simulacion.getCajaCobro() != null ? simulacion.getCajaCobro().getEstadoCaja() : "";
            matrizTabla[i][17] = simulacion.getCajaCobro() != null ? simulacion.getCajaCobro().getCola() : 0;
            matrizTabla[i][18] = simulacion.getVariablesEstadisticas().getRecaudacion();
            matrizTabla[i][19] = simulacion.getVariablesEstadisticas().getCantidadAutosNoIngresados();
            matrizTabla[i][20] = df.format(simulacion.getVariablesEstadisticas().getPorcentajeUtilizacionPlaya());

            matrizTabla[i][21] = df.format(acumuladorRecaudacion);
            matrizTabla[i][22] = df.format(acumuladorPorcUtilizacionPlaya);
            matrizTabla[i][23] = acumuladorCantidadAutosNoIngresados;
            matrizTabla[i][24] = simulacion.getCantidadOcupados();

            i = iAnteriorAlLoop;
            /* for (Map.Entry<Integer, String> entry : simulacion.getAutosTotales().entrySet()) {

                i++;
                matrizTabla[i][25] = entry.getKey() + ": " + entry.getValue();
                
            }
             */

            for (Auto autoAMostrar : simulacion.getAutosTotalesList()) {
                i++;
                matrizTabla[i][25] = "PATENTE: " + autoAMostrar.getId() + ": " + autoAMostrar.getEstadoAuto();
            }

            i = iAnteriorAlLoop + 1;

            int j = simulacion.getAutosTotalesList().size() > 15 ? simulacion.getAutosTotalesList().size() : 15;
            i = i + j;
        }

        this.recaudacionTotal = df.format(acumuladorRecaudacion);
        this.cantidadAutosNoIngresadosTotal = acumuladorCantidadAutosNoIngresados;
        this.procentajeUtilizacionPlaya = acumuladorPorcUtilizacionPlaya * 100 / filasTabla.get(filasTabla.size() - 1).getReloj();

        String[] NombresDeColumnas = new String[]{"Nro", "Reloj", "Evento", "RndTC",
            "Tipo_Coche", "Precio", "Rnd_Min", "Minutos", "Rnd_Llegada_Auto",
            "TpoELLg", "Proximo_Auto", "Nro_FinEst", "Fin_Estacionamiento", "Nro_Sector",
            "Estado_Sector", "Fin_Cobro", "Caja_Estado", "Caja_Cola", "Recaudacion", "Autos_No_Ingresados", "Porc_Utilizacion",
            "Recaudacion_Total", "Porc_UtilizacionTotal", "Autos_No_Ingresados_AC", "Cantidad_Sectores_Ocupados", "Autos"
        };

        this.setDataVector(matrizTabla, NombresDeColumnas);

    }

    private Integer calcularCantidadFilas(List<PlayaEstacionamiento> filasTabla) {
        Integer cantidadFilas = filasTabla.size();
        for (PlayaEstacionamiento sim : filasTabla) {
            cantidadFilas += (sim.getEventosFinEstacionamiento().size()) + 4 + sim.getAutosTotalesList().size();
        }
        return cantidadFilas;
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return false;
    }
}
