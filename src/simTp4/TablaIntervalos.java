package simTp4;

import Eventos.EventoFinEstacionamiento;
import ObjetosPermanentes.Sector;
import ObjetosTemporales.Auto;
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
        Object[][] matrizTabla = new Object[calcularCantidadFilas(filasTabla)][158]; // Crea la tabla de tamaño cantidadIntervalos+1X4
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
            int columna = 10;
            for (EventoFinEstacionamiento finEstacionamiento : simulacion.getEventosFinEstacionamiento()) {
                //  i++;
                // filas ++;
                // int j = finEstacionamiento.getNro();
                // matrizTabla[i][filas] = j; //Nro estacionamieno
                columna++;
                matrizTabla[i][columna] = finEstacionamiento.getFinEstacionamiento(); //Fin estacionamiento

            }

            // i = iAnteriorAlLoop;
            for (Sector sector : simulacion.getSectores()) {

                columna++;
                matrizTabla[i][columna] = sector.getEstadoSector(); //Estado sector

            }

            // i = iAnteriorAlLoop;
            columna++;
            matrizTabla[i][columna] = simulacion.getEventoFinCobro() != null ? simulacion.getEventoFinCobro().getFinAtCobro() : 0; //Fin Cobro
            columna++;
            matrizTabla[i][columna] = simulacion.getCajaCobro().getEstadoCaja(); //Estado caja
            columna++;
            matrizTabla[i][columna] = simulacion.getCajaCobro().getCola(); //cola caja
            columna++;
            matrizTabla[i][columna] = simulacion.getVariablesEstadisticas().getRecaudacion(); //Recaudacion individual
            columna++;
            matrizTabla[i][columna] = simulacion.getVariablesEstadisticas().getCantidadAutosNoIngresados(); //Cantidad de autos no ingresados
            columna++;
            matrizTabla[i][columna] = df.format(simulacion.getVariablesEstadisticas().getPorcentajeUtilizacionPlaya()); // Porcentaje de utilizacion individual

            columna++;
            matrizTabla[i][columna] = df.format(simulacion.getVariablesEstadisticas().getRecaudacionAC()); //Recaudacion total
            columna++;
            matrizTabla[i][columna] = df.format(simulacion.getVariablesEstadisticas().getPorcentajeUtilizacionPlataAC()); // Porcentaje utilizacion playa total
            columna++;
            matrizTabla[i][columna] = simulacion.getVariablesEstadisticas().getCantidadAutosNoIngresadosAC(); //Cantidad autos no ingresados total
            columna++;
            matrizTabla[i][columna] = simulacion.getCantidadOcupados(); //Cantidad autos ocupados

            columna++;
            matrizTabla[i][columna] = simulacion.getD() != 0 ? simulacion.getD() : 0; //Tiempo Do
            columna++;
            matrizTabla[i][columna] = df.format(simulacion.getTiempoCobroIntegracion()); //Valor de la variable integrada
            // i = iAnteriorAlLoop;
            int col = columna;
            for (Map.Entry<Integer, Auto> entry : simulacion.getAutosTotales().entrySet()) {

                int colAuto = columna;
                int colEstado = columna;
                int colPrecioXMin = columna;
                int colTiempoDo = columna;
                int colVariableInt = columna;
                //   i++;
                //columna++;
                //col = columna;

                if (entry.getValue().getNroFinEstacionamiento() == 1) {
                    colAuto = columna + 1;
                    colEstado = colEstado + 2;
                    colPrecioXMin = colPrecioXMin + 3;
                    colTiempoDo = colTiempoDo + 4;
                    colVariableInt = colVariableInt + 5;
                }
                if (entry.getValue().getNroFinEstacionamiento() == 2) {
                    colAuto = columna + 5 + 1;
                    colEstado = colEstado + 5 + 2;
                    colPrecioXMin = colPrecioXMin + 5 + 3;
                    colTiempoDo = colTiempoDo + 5 + 4;
                    colVariableInt = colVariableInt + 5 + 5;
                }
                if (entry.getValue().getNroFinEstacionamiento() == 3) {
                    colAuto = columna + 10 + 1;
                    colEstado = colEstado + 10 + 2;
                    colPrecioXMin = colPrecioXMin + 10 + 3;
                    colTiempoDo = colTiempoDo + 10 + 4;
                    colVariableInt = colVariableInt + 10 + 5;
                }
                if (entry.getValue().getNroFinEstacionamiento() == 4) {
                    colAuto = columna + 15 + 1;
                    colEstado = colEstado + 15 + 2;
                    colPrecioXMin = colPrecioXMin + 15 + 3;
                    colTiempoDo = colTiempoDo + 15 + 4;
                    colVariableInt = colVariableInt + 15 + 5;
                }
                if (entry.getValue().getNroFinEstacionamiento() == 5) {
                    colAuto = columna + 20 + 1;
                    colEstado = colEstado + 20 + 2;
                    colPrecioXMin = colPrecioXMin + 20 + 3;
                    colTiempoDo = colTiempoDo + 20 + 4;
                    colVariableInt = colVariableInt + 20 + 5;
                }
                if (entry.getValue().getNroFinEstacionamiento() == 6) {
                    colAuto = columna + 25 + 1;
                    colEstado = colEstado + 25 + 2;
                    colPrecioXMin = colPrecioXMin + 25 + 3;
                    colTiempoDo = colTiempoDo + 25 + 4;
                    colVariableInt = colVariableInt + 25 + 5;
                }
                if (entry.getValue().getNroFinEstacionamiento() == 7) {
                    colAuto = columna + 30 + 1;
                    colEstado = colEstado + 30 + 2;
                    colPrecioXMin = colPrecioXMin + 30 + 3;
                    colTiempoDo = colTiempoDo + 30 + 4;
                    colVariableInt = colVariableInt + 30 + 5;
                }
                if (entry.getValue().getNroFinEstacionamiento() == 8) {
                    colAuto = columna + 35 + 1;
                    colEstado = colEstado + 35 + 2;
                    colPrecioXMin = colPrecioXMin + 35 + 3;
                    colTiempoDo = colTiempoDo + 35 + 4;
                    colVariableInt = colVariableInt + 35 + 5;
                }
                if (entry.getValue().getNroFinEstacionamiento() == 9) {
                    colAuto = columna + 40 + 1;
                    colEstado = colEstado + 40 + 2;
                    colPrecioXMin = colPrecioXMin + 40 + 3;
                    colTiempoDo = colTiempoDo + 40 + 4;
                    colVariableInt = colVariableInt + 40 + 5;
                }
                if (entry.getValue().getNroFinEstacionamiento() == 10) {
                    colAuto = columna + 45 + 1;
                    colEstado = colEstado + 45 + 2;
                    colPrecioXMin = colPrecioXMin + 45 + 3;
                    colTiempoDo = colTiempoDo + 45 + 4;
                    colVariableInt = colVariableInt + 45 + 5;
                }
                if (entry.getValue().getNroFinEstacionamiento() == 11) {
                    colAuto = columna + 50 + 1;
                    colEstado = colEstado + 50 + 2;
                    colPrecioXMin = colPrecioXMin + 50 + 3;
                    colTiempoDo = colTiempoDo + 50 + 4;
                    colVariableInt = colVariableInt + 50 + 5;
                }

                matrizTabla[i][colAuto] = "PATENTE: " + entry.getKey(); //Auto
                matrizTabla[i][colEstado] = entry.getValue().getEstadoAuto() + "_" + entry.getValue().getNroFinEstacionamiento();//Estado
                matrizTabla[i][colPrecioXMin] = entry.getValue().getPrecioXMinutos();//PrecioXMinutos
                matrizTabla[i][colTiempoDo] = entry.getValue().getD() != 0 ? entry.getValue().getD() : 0; //Tiempo Do
                matrizTabla[i][colVariableInt] = df.format(entry.getValue().getVariableIntegrada()); //Valor de la variable integrada

            }

            i++;
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
            "D", "Integracion",
            "Auto_est_1", "Estado", "PrecioXMinutos", "D", "Integracion", "Auto_est_2", "Estado", "PrecioXMinutos", "D", "Integracion", "Auto_est_3", "Estado", "PrecioXMinutos", "D", "Integracion",
            "Auto_est_4", "Estado", "PrecioXMinutos", "D", "Integracion", "Auto_est_5", "Estado", "PrecioXMinutos", "D", "Integracion", "Auto_est_6", "Estado", "PrecioXMinutos", "D", "Integracion",
            "Auto_est_7", "Estado", "PrecioXMinutos", "D", "Integracion", "Auto_est_8", "Estado", "PrecioXMinutos", "D", "Integracion", "Auto_est_9", "Estado", "PrecioXMinutos", "D", "Integracion",
            "Auto_est_10", "Estado", "PrecioXMinutos", "D", "Integracion", "Auto_est_11", "Estado", "PrecioXMinutos", "D", "Integracion"};

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
