package simTp4;

import VariablesEstadisticas.VariablesEstadisticas;
import enums.TipoCoche;
import ObjetosPermanentes.Sector;
import enums.Evento;
import ObjetosTemporales.Auto;
import Eventos.EventoFinCobro;
import Eventos.EventoFinEstacionamiento;
import Eventos.EventoLLegadaAuto;
import ObjetosPermanentes.CajaCobro;
import enums.EstadoAuto;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
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
public class PlayaEstacionamiento {

    private int nroSimulacion;
    private double reloj;
    private Evento evento;
    private String eventoName;
    private double rndTipoCoche;
    private TipoCoche tipoCoche;
    private double precio;
    private double rndMinutosEstacionamiento;
    private double minutos;
    private EventoLLegadaAuto eventoLLegadaAuto;
    private List<EventoFinEstacionamiento> eventosFinEstacionamiento;
    private EventoFinCobro eventoFinCobro;
    private CajaCobro cajaCobro;
    private List<Sector> sectores;
    private VariablesEstadisticas variablesEstadisticas;
    private int cantidadOcupados;
    private Map<Integer, Auto> autosMapeados;
    private Map<Double, Auto> autosEsperandoCobro;
    private Auto autoSiendoCobrado;
    private Map<Integer, EstadoAuto> autosTotales;
    private ArrayList<Auto> autosTotalesList;

}
