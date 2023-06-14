package simTp4;

import Eventos.EventoFinEstacionamiento;
import ObjetosPermanentes.Sector;
import ObjetosTemporales.Auto;
import enums.EstadoAuto;
import java.text.DecimalFormat;
import java.util.List;
import java.util.Map;
import javax.swing.table.DefaultTableModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class TablaIntervalos extends DefaultTableModel {

    private static final DecimalFormat df = new DecimalFormat("0.000");

    public TablaIntervalos(List<PlayaEstacionamiento> filasTabla) {
        cargarDatos(filasTabla);
    }

    private void cargarDatos(List<PlayaEstacionamiento> filasTabla) {
        Object[][] matrizTabla = new Object[calcularCantidadFilas(filasTabla)][78]; // Crea la tabla de tamaño cantidadIntervalos+1X4
        int i = 0;
       

        for (PlayaEstacionamiento simulacion : filasTabla) //Recorre los intervalos
        {

            matrizTabla[i][0] = simulacion.getNroSimulacion();//Nro sim
            matrizTabla[i][1] = simulacion.getReloj();//Reloj

            matrizTabla[i][2] = simulacion.getEventoName(); //Evento
            matrizTabla[i][3] = simulacion.getRndTipoCoche();//RND
            matrizTabla[i][4] = simulacion.getTipoCoche();//Tipo Coche
            matrizTabla[i][5] = simulacion.getPrecio(); //Precio
            matrizTabla[i][6] = simulacion.getRndMinutosEstacionamiento(); //RND
            matrizTabla[i][7] = simulacion.getMinutos(); //Minutos

            //Evento Llegada auto
            matrizTabla[i][8] = simulacion.getEventoLLegadaAuto().getRnd(); //RND
            matrizTabla[i][9] = simulacion.getEventoLLegadaAuto().getTiempoEntreLlegadas();//Tiempo entre llegadas de auto
            matrizTabla[i][10] = simulacion.getEventoLLegadaAuto().getProximoAuto();//Proximo auto

            //Evento Fin Estacionamiento
            int iAnteriorAlLoop = i;
            int filas = 10;
            for (EventoFinEstacionamiento finEstacionamiento : simulacion.getEventosFinEstacionamiento()) {
              //  i++;
               // filas ++;
               // int j = finEstacionamiento.getNro();
               // matrizTabla[i][filas] = j; //Nro estacionamieno
                filas ++;
                matrizTabla[i][filas] = finEstacionamiento.getFinEstacionamiento(); //Fin estacionamiento

            }

           // i = iAnteriorAlLoop;
            for (Sector sector : simulacion.getSectores()) {
              //  i++;
               // filas ++;
                //int j = sector.getId();
                //matrizTabla[i][filas] = j; //Nro Sector
                filas ++;
                matrizTabla[i][filas] = sector.getEstadoSector(); //Estado sector

            }

           // i = iAnteriorAlLoop;

            filas ++;
            matrizTabla[i][filas] = simulacion.getEventoFinCobro() != null ? simulacion.getEventoFinCobro().getFinAtCobro() : 0; //Fin Cobro
            filas ++;
            matrizTabla[i][filas] = simulacion.getCajaCobro().getEstadoCaja(); //Estado caja
            filas ++;
            matrizTabla[i][filas] = simulacion.getCajaCobro().getCola(); //cola caja
            filas ++;
            matrizTabla[i][filas] = simulacion.getVariablesEstadisticas().getRecaudacion(); //Recaudacion individual
            filas ++;
            matrizTabla[i][filas] = simulacion.getVariablesEstadisticas().getCantidadAutosNoIngresados(); //Cantidad de autos no ingresados
            filas ++;
            matrizTabla[i][filas] = df.format(simulacion.getVariablesEstadisticas().getPorcentajeUtilizacionPlaya()); // Porcentaje de utilizacion individual

            filas ++;
            matrizTabla[i][filas] = df.format(simulacion.getVariablesEstadisticas().getRecaudacionAC()); //Recaudacion total
            filas ++;
            matrizTabla[i][filas] = df.format(simulacion.getVariablesEstadisticas().getPorcentajeUtilizacionPlataAC()); // Porcentaje utilizacion playa total
            filas ++;
            matrizTabla[i][filas] = simulacion.getVariablesEstadisticas().getCantidadAutosNoIngresadosAC(); //Cantidad autos no ingresados total
            filas ++;
            matrizTabla[i][filas] = simulacion.getCantidadOcupados(); //Cantidad autos ocupados

           // i = iAnteriorAlLoop;
            for (Map.Entry<Integer, Auto> entry : simulacion.getAutosTotales().entrySet()) {

             //   i++;
                filas ++;
                matrizTabla[i][filas] = "PATENTE: " + entry.getKey() ; //Auto
                filas ++;
                matrizTabla[i][filas] = entry.getValue().getEstadoAuto() ;//Estado
                filas ++;
                matrizTabla[i][filas] = entry.getValue().getPrecioXMinutos();//PrecioXMinutos

            }

            //i = iAnteriorAlLoop + 1;

           // int j = simulacion.getAutosTotales().size() > 15 ? simulacion.getAutosTotales().size() : 15;
           // i = i + j;
           i ++;
        }

        String[] NombresDeColumnas = new String[]{
            "Nro", "Reloj", "Evento", "Rnd", "Tipo_Coche", "Precio", "Rnd", "Minutos",
            
            "Rnd_Llegada_Auto", "TpoELLg", "Proximo_Auto",
            
            "Fin_Estacionamiento_1",
            "Fin_Estacionamiento_2",
            "Fin_Estacionamiento_3",
            "Fin_Estacionamiento_4",
            "Fin_Estacionamiento_5",
            "Fin_Estacionamiento_6",
            "Fin_Estacionamiento_7",
            "Fin_Estacionamiento_8",
            "Fin_Estacionamiento_9",
            "Fin_Estacionamiento_10",
            "Fin_Estacionamiento_11",
            
            "Estado_Sector_1",
            "Estado_Sector_2",
            "Estado_Sector_3",
            "Estado_Sector_4",
            "Estado_Sector_5",
            "Estado_Sector_6",
            "Estado_Sector_7",
            "Estado_Sector_8",
            "Estado_Sector_9",
            "Estado_Sector_10",
            
            "Fin_Cobro", "Caja_Estado", "Caja_Cola",
            
            "Recaudacion", "Autos_No_Ingresados", "Porc_Utilizacion",
          
            "Recaudacion_Total", "Porc_UtilizacionTotal", "Autos_No_Ingresados_AC", "Cantidad_Sectores_Ocupados", 
            
            "Auto", "Estado", "PrecioXMinutos", "Auto","Estado", "PrecioXMinutos",
            "Auto","Estado", "PrecioXMinutos","Auto","Estado", "PrecioXMinutos",
            "Auto","Estado", "PrecioXMinutos","Auto","Estado", "PrecioXMinutos",
            "Auto","Estado", "PrecioXMinutos","Auto","Estado", "PrecioXMinutos",
            "Auto","Estado", "PrecioXMinutos","Auto","Estado", "PrecioXMinutos","Auto","Estado", "PrecioXMinutos",
        };

        this.setDataVector(matrizTabla, NombresDeColumnas);

    }

    private Integer calcularCantidadFilas(List<PlayaEstacionamiento> filasTabla) {
        Integer cantidadFilas = filasTabla.size();
       /* for (PlayaEstacionamiento sim : filasTabla) {
            cantidadFilas += (sim.getEventosFinEstacionamiento().size());
        }*/
        return cantidadFilas;
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return false;
    }
}
