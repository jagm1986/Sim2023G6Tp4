package simTp4;

import Eventos.EventoFinCobro;
import Eventos.EventoFinEstacionamiento;
import Eventos.EventoLLegadaAuto;
import GeneradorDeAleatorios.GeneradorExponencial;
import GeneradorDeAleatorios.IGeneradorNumerosAleatorios;
import ObjetosPermanentes.CajaCobro;
import ObjetosPermanentes.Sector;
import ObjetosTemporales.Auto;
import VariablesEstadisticas.MinimoProxEvento;
import VariablesEstadisticas.VariablesEstadisticas;
import enums.Estado;
import enums.EstadoAuto;
import enums.Evento;
import enums.TipoCoche;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
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
public class VectorPlayaEstacionamiento {

    private List<PlayaEstacionamiento> simulaciones;

    private int N;

    IGeneradorNumerosAleatorios gna;

    private TablaProbabilidad tipoCoche;

    private TablaProbabilidad minutosEstacionamiento;

    private double recaudacionTotal;

    private int cantidadAutosNoIngresadosTotal;

    private double porcentajeUtilizacionTotal;

    public void crearSimulaciones(int cantidadSimulaciones, float llegadaAutos, double probChico, double probGrande, double probUtil, double estacionamiento1hs,
            double estacionamiento2hs, double estacionamiento3hs, double estacionamiento4hs, double tpoCobro) {

        gna = new GeneradorExponencial(llegadaAutos);

        PlayaEstacionamiento simulacion = null;
        PlayaEstacionamiento simulacionAnterior;

        TablaProbabilidad probabilidadesTipoCocheYPrecio = tablaTipoCoche(probChico, probGrande, probUtil);
        TablaProbabilidad probabilidadesMinutosEstacionamiento = tablaMinutosEstacionamiento(estacionamiento1hs, estacionamiento2hs,
                estacionamiento3hs, estacionamiento4hs);

        Random rndTipoCoche = new Random();
        Random rndMinutosEstacionamiento = new Random();

        int nroAuto = 0;

        //INICIO
        generarPrimerSimulacion(gna, simulacion);

        if (N > 1) {

            for (int i = 1; i < cantidadSimulaciones + 1; i++) {

                simulacion = new PlayaEstacionamiento();

                simulacionAnterior = simulaciones.get(i - 1);

                //simulacion = new PlayaEstacionamiento();
                simulacion.setNroSimulacion(i);
                simulacion.setEventoName(calcularEventoProximo(simulacion, simulacionAnterior, i));

                //LLEGADA AUTO
                if (simulacion.getEventoName().contains("LLEGADA_AUTO_")) {
                    nroAuto = i;
                    procesarEventoLlegadaAuto(simulacion, simulacionAnterior, rndTipoCoche, rndMinutosEstacionamiento,
                            probabilidadesTipoCocheYPrecio, probabilidadesMinutosEstacionamiento, nroAuto);
                    continue;
                }

                //FIN ESTACIONAMIENTO
                if (simulacion.getEventoName().contains("FIN_EST")) {
                    procesarEventoFinEstacionamiento(simulacion, simulacionAnterior, tpoCobro);
                    continue;
                }

                //FIN COBRO
                if (simulacion.getEventoName().contains("FIN_COBRO")) {
                    procesarEventoFinCobro(simulacion, simulacionAnterior, tpoCobro);
                }
            }

        }
    }

    private void generarPrimerSimulacion(IGeneradorNumerosAleatorios gna, PlayaEstacionamiento simulacion) {
        simulacion = new PlayaEstacionamiento();
        simulacion.setNroSimulacion(0);
        simulacion.setReloj(0);
        simulacion.setEvento(Evento.INICIO);
        simulacion.setEventoName(Evento.INICIO.name());

        NumeroRNDTable randomLlegadaAuto = gna.generarAleatorio();
        simulacion.setEventoLLegadaAuto(EventoLLegadaAuto.builder()
                .rnd(Math.floor(randomLlegadaAuto.getRnd() * 10000) / 10000)
                .tiempoEntreLlegadas(randomLlegadaAuto.getNumero())
                .build());
        simulacion.getEventoLLegadaAuto().setProximoAuto(simulacion.getEventoLLegadaAuto().getTiempoEntreLlegadas());

        simulacion.setEventoFinCobro(EventoFinCobro.builder()
                .finAtCobro(0)
                .tiempoCobro(0)
                .build());
        simulacion.setCajaCobro(CajaCobro.builder().estadoCaja(Estado.LIBRE).cola(0).build());

        simulacion.getEventoLLegadaAuto().setProximoAuto(simulacion.getEventoLLegadaAuto().getTiempoEntreLlegadas());

        List<Sector> sectores = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            Sector sector = Sector.builder()
                    .id(i + 1)
                    .estadoSector(Estado.LIBRE).build();
            sectores.add(sector);
        }
        simulacion.setSectores(sectores);

        List<EventoFinEstacionamiento> listaEventosFinEstacionamiento = new ArrayList<>();
        for (int j = 0; j < 11; j++) {
            EventoFinEstacionamiento estacionamiento = EventoFinEstacionamiento.builder().nro(j + 1)
                    .finEstacionamiento(0)
                    .build();

            listaEventosFinEstacionamiento.add(estacionamiento);

        }
        simulacion.setEventosFinEstacionamiento(listaEventosFinEstacionamiento);

        Map<Integer, Auto> autosMapeadosPrimeraSim = new HashMap<>();
        simulacion.setAutosMapeados(autosMapeadosPrimeraSim);

        Map<Double, Auto> autosEsperandoCobro = new HashMap<>();
        simulacion.setAutosEsperandoCobro(autosEsperandoCobro);

        Map<Integer, Auto> autosTotales = new HashMap<>();
        simulacion.setAutosTotales(autosTotales);

        ArrayList<Auto> autosTotalesList = new ArrayList<>();
        simulacion.setAutosTotalesList(autosTotalesList);

        simulacion.setVariablesEstadisticas(VariablesEstadisticas.builder().build());
        simulaciones.add(simulacion);

    }

    private void procesarEventoLlegadaAuto(PlayaEstacionamiento simulacion, PlayaEstacionamiento simulacionAnterior, Random rndTipoCoche, Random rndMinutosEstacionamiento,
            TablaProbabilidad probabilidadesTipoCocheYPrecio, TablaProbabilidad probabilidadesMinutosEstacionamiento, int nroAuto) {

        Map<Integer, Auto> autosTotales = copyAndSetNuevoMapParaAutosTotalesNuevosObjetos(simulacionAnterior.getAutosTotales());
        simulacion.setAutosTotales(autosTotales);

        Map<Integer, Auto> autosMapeadosNuevaSim = new HashMap<>();
        autosMapeadosNuevaSim.putAll(simulacionAnterior.getAutosMapeados());
        simulacion.setAutosMapeados(autosMapeadosNuevaSim);

        Map<Double, Auto> autosEsperandoCobro = new HashMap<>();
        autosEsperandoCobro.putAll(simulacionAnterior.getAutosEsperandoCobro());
        simulacion.setAutosEsperandoCobro(autosEsperandoCobro);

        simulacion.setRndTipoCoche(Math.floor(rndTipoCoche.nextDouble() * 10000) / 10000);
        simulacion.setTipoCoche(calcularProbabilidad(simulacion.getRndTipoCoche(), probabilidadesTipoCocheYPrecio).getTipoCoche());
        simulacion.setPrecio(calcularProbabilidad(simulacion.getRndTipoCoche(), probabilidadesTipoCocheYPrecio).getPrecio());
        simulacion.setRndMinutosEstacionamiento(Math.floor(rndMinutosEstacionamiento.nextDouble() * 10000) / 10000);
        simulacion.setMinutos(calcularProbabilidad(simulacion.getRndMinutosEstacionamiento(),
                probabilidadesMinutosEstacionamiento).getMinutos());

        NumeroRNDTable randomLlegadaAuto = gna.generarAleatorio();
        simulacion.setEventoLLegadaAuto(EventoLLegadaAuto.builder()
                .rnd(Math.floor(randomLlegadaAuto.getRnd() * 10000) / 10000)
                .tiempoEntreLlegadas(randomLlegadaAuto.getNumero())
                .build());

        simulacion.getEventoLLegadaAuto().setProximoAuto(Math.floor((simulacion.getEventoLLegadaAuto().getTiempoEntreLlegadas()
                + simulacion.getReloj()) * 10000) / 10000);

        simulacion.setEventoFinCobro(simulacionAnterior.getEventoFinCobro());
        simulacion.setCajaCobro(simulacionAnterior.getCajaCobro());

        simulacion.setVariablesEstadisticas(VariablesEstadisticas.builder()
                .cantidadAutosNoIngresados(simulacionAnterior.getVariablesEstadisticas().getCantidadAutosNoIngresados())
                .cantidadAutosNoIngresadosAC(simulacionAnterior.getVariablesEstadisticas().getCantidadAutosNoIngresadosAC())
                .recaudacion(simulacionAnterior.getVariablesEstadisticas().getRecaudacion())
                .build());

        simulacion.getVariablesEstadisticas().setPorcentajeUtilizacionPlaya(((Integer.valueOf(simulacionAnterior.getCantidadOcupados()).doubleValue()
                / 10) * 10000 / 10000) * (simulacion.getReloj() - simulacionAnterior.getReloj()));
        this.porcentajeUtilizacionTotal += simulacion.getVariablesEstadisticas().getPorcentajeUtilizacionPlaya();
        simulacion.getVariablesEstadisticas().setPorcentajeUtilizacionPlataAC(this.porcentajeUtilizacionTotal);

        List<EventoFinEstacionamiento> listaEventosFinEstacionamiento = new ArrayList<>();
        listaEventosFinEstacionamiento.addAll(simulacionAnterior.getEventosFinEstacionamiento());
        simulacion.setEventosFinEstacionamiento(listaEventosFinEstacionamiento);

        List<Sector> sectores = new ArrayList<>();
        sectores.addAll(simulacionAnterior.getSectores());
        simulacion.setSectores(sectores);
        Sector sectorLibre = haySectorLibre(sectores);
        if (sectorLibre != null) {
            sectorLibre.setEstadoSector(Estado.OCUPADO);
            simulacion.setCantidadOcupados(simulacionAnterior.getCantidadOcupados() + 1);
            sectores.add(sectorLibre);
            Collections.sort(sectores, Sector.SectorComparator);
        } else {
            simulacion.getVariablesEstadisticas()
                    .setCantidadAutosNoIngresados(1);

            simulacion.getVariablesEstadisticas()
                    .setCantidadAutosNoIngresadosAC(simulacion.getVariablesEstadisticas().getCantidadAutosNoIngresadosAC() + 1);

            this.cantidadAutosNoIngresadosTotal += simulacion.getVariablesEstadisticas().getCantidadAutosNoIngresados();

            simulacion.setCantidadOcupados(simulacionAnterior.getCantidadOcupados());

            simulaciones.add(simulacion);
            return;
            //continue;
        }

        EventoFinEstacionamiento eventoFinEstacionamientoLibre
                = hayEventoFinEstacionamientoLibre(listaEventosFinEstacionamiento);
        if (eventoFinEstacionamientoLibre != null) {
            eventoFinEstacionamientoLibre.setFinEstacionamiento(Math.floor((simulacion.getMinutos() + simulacion.getReloj()) * 10000) / 10000);
            listaEventosFinEstacionamiento.add(eventoFinEstacionamientoLibre);
            Collections.sort(listaEventosFinEstacionamiento, EventoFinEstacionamiento.FinEstacionamientoComparator);
        }

        simulacion.setCantidadOcupados(simulacionAnterior.getCantidadOcupados() + 1);

        Auto auto = Auto.builder().estadoAuto(EstadoAuto.ESTACIONADO)
                .precioXMinutos(simulacion.getPrecio() * (simulacion.getMinutos() / 60))
                .horaEntradaCobro(simulacion.getReloj())
                .id(nroAuto)
                .nroFinEstacionamiento(eventoFinEstacionamientoLibre != null ? eventoFinEstacionamientoLibre.getNro() : 0).build();

        autosTotales.put(nroAuto, auto);
        simulacion.setAutosTotales(autosTotales);

        autosMapeadosNuevaSim.put((eventoFinEstacionamientoLibre != null ? eventoFinEstacionamientoLibre.getNro() : 0), auto);
        simulacion.setAutosMapeados(autosMapeadosNuevaSim);

        simulacion.setAutosEsperandoCobro(autosEsperandoCobro);

        simulaciones.add(simulacion);
    }

    private void procesarEventoFinEstacionamiento(PlayaEstacionamiento simulacion, PlayaEstacionamiento simulacionAnterior, double tpoCobro) {

        Map<Integer, Auto> autosTotales = copyAndSetNuevoMapParaAutosTotalesNuevosObjetos(simulacionAnterior.getAutosTotales());
        simulacion.setAutosTotales(autosTotales);

        Map<Double, Auto> autosEsperandoCobro = new HashMap<>();
        autosEsperandoCobro.putAll(simulacionAnterior.getAutosEsperandoCobro());
        simulacion.setAutosEsperandoCobro(autosEsperandoCobro);

        Map<Integer, Auto> autosMapeadosNuevaSim = new HashMap<>();
        autosMapeadosNuevaSim.putAll(simulacionAnterior.getAutosMapeados());
        simulacion.setAutosMapeados(autosMapeadosNuevaSim);

        simulacion.setEventoLLegadaAuto(EventoLLegadaAuto.builder()
                .proximoAuto(simulacionAnterior.getEventoLLegadaAuto().getProximoAuto())
                .build());

        simulacion.setCajaCobro(CajaCobro.builder()
                .id(simulacionAnterior.getCajaCobro().getId())
                .cola(simulacionAnterior.getCajaCobro().getCola())
                .estadoCaja(simulacionAnterior.getCajaCobro().getEstadoCaja())
                .build());

        List<EventoFinEstacionamiento> listaEventosFinEstacionamiento = new ArrayList<>();
        listaEventosFinEstacionamiento.addAll(simulacionAnterior.getEventosFinEstacionamiento());
        simulacion.setEventosFinEstacionamiento(listaEventosFinEstacionamiento);

        List<Sector> listaSector = new ArrayList<>();
        listaSector.addAll(simulacionAnterior.getSectores());
        simulacion.setSectores(listaSector);

        Auto autoMap = null;
        int nroEstacionamiento = 0;
        double horaEntredaEstacionamiento = 0;
        double precioXMinutos = 0;
        Integer nroAuto = 0;
        //Buscamos todos los eventos de fin de estacionamiento para liberar el que sea igual al evento

        for (EventoFinEstacionamiento eventoFinEstacionamiento : listaEventosFinEstacionamiento) {
            if (simulacion.getEventoName().equals(Evento.FIN_ESTACIONAMIENTO_.name() + eventoFinEstacionamiento.getNro())) {

                //liberamos el evento fin estacionamiento correspondiente siempre que no haya cola en la caja de cobro
                EventoFinEstacionamiento eventoFinEstacionamientoAModificar = EventoFinEstacionamiento.builder()
                        .nro(eventoFinEstacionamiento.getNro())
                        .cantidadOcupados(eventoFinEstacionamiento.getCantidadOcupados())
                        .tiempoEstacionamiento(eventoFinEstacionamiento.getTiempoEstacionamiento())
                        .finEstacionamiento(0)
                        .build();
                listaEventosFinEstacionamiento.remove(eventoFinEstacionamiento);
                listaEventosFinEstacionamiento.add(eventoFinEstacionamientoAModificar);
                Collections.sort(listaEventosFinEstacionamiento, EventoFinEstacionamiento.FinEstacionamientoComparator);

                //liberamos el sector siempre que no este ocupado la caja
                if (simulacionAnterior.getCajaCobro().getEstadoCaja().equals(Estado.LIBRE)) {

                    for (Sector sector : listaSector) {
                        if (sector.getId() == eventoFinEstacionamientoAModificar.getNro()) {

                            List<Sector> sectores = new ArrayList<>();
                            sectores.addAll(simulacionAnterior.getSectores());
                            simulacion.setSectores(sectores);

                            Sector sectorLibre = new Sector();
                            sectorLibre.setId(sector.getId());
                            sectorLibre.setEstadoSector(Estado.LIBRE);
                            simulacion.setCantidadOcupados(simulacionAnterior.getCantidadOcupados() == 0 ? 0 : simulacionAnterior.getCantidadOcupados() - 1);
                            sectores.add(sectorLibre);
                            sectores.remove(sector);
                            Collections.sort(sectores, Sector.SectorComparator);
                            break;
                        }
                    }
                }

                autoMap = autosMapeadosNuevaSim.get(eventoFinEstacionamientoAModificar.getNro());
                nroEstacionamiento = eventoFinEstacionamientoAModificar.getNro();
                horaEntredaEstacionamiento = autoMap.getHoraEntradaCobro();
                precioXMinutos = autoMap.getPrecioXMinutos();
                nroAuto = autoMap.getId();

                autosEsperandoCobro.put(autoMap.getHoraEntradaCobro(), autoMap);

                break;
            }
        }

        //Ahora seteamos el fin atencion cobro del auto que salio, y si no lo mandamos a la cola
        Auto autoModificado;
        EventoFinCobro eventoFinCobro = simulacionAnterior.getEventoFinCobro();

        if (simulacionAnterior.getCajaCobro().getEstadoCaja().equals(Estado.OCUPADO)) {
            simulacion.setCajaCobro(CajaCobro.builder().estadoCaja(simulacionAnterior.getCajaCobro().getEstadoCaja())
                    .cola(simulacionAnterior.getCajaCobro().getCola() + 1).build());
            autoModificado = Auto.builder()
                    .id(nroAuto)
                    .estadoAuto(EstadoAuto.ESPERANDO_COBRO)
                    .horaEntradaCobro(horaEntredaEstacionamiento)
                    .precioXMinutos(precioXMinutos)
                    .nroFinEstacionamiento(nroEstacionamiento)
                    .build();
            autosTotales.put(nroAuto, autoModificado);

        } else {
            simulacion.setCajaCobro(CajaCobro.builder().estadoCaja(Estado.OCUPADO).build());
            autoModificado = Auto.builder()
                    .id(nroAuto)
                    .estadoAuto(EstadoAuto.SIENDO_COBRADO)
                    .horaEntradaCobro(horaEntredaEstacionamiento)
                    .precioXMinutos(precioXMinutos)
                    .nroFinEstacionamiento(nroEstacionamiento)
                    .build();
            eventoFinCobro = EventoFinCobro.builder()
                    .tiempoCobro(tpoCobro)
                    .finAtCobro(simulacion.getReloj() + tpoCobro)
                    .nroAuto(nroAuto)
                    .nroEstacionamiento(nroEstacionamiento)
                    .auto(autoMap)
                    .build();
            simulacion.setAutoSiendoCobrado(autoModificado);
            autosTotales.put(nroAuto, autoModificado);

            //liberamos el sector porque el auto fue a la caixa
            for (Sector sector : listaSector) {
                if (sector.getId() == autoModificado.getNroFinEstacionamiento()) {

                    List<Sector> sectores = new ArrayList<>();
                    sectores.addAll(simulacionAnterior.getSectores());
                    simulacion.setSectores(sectores);

                    Sector sectorLibre = new Sector();
                    sectorLibre.setId(sector.getId());
                    sectorLibre.setEstadoSector(Estado.LIBRE);
                    simulacion.setCantidadOcupados(simulacionAnterior.getCantidadOcupados() == 0 ? 0 : simulacionAnterior.getCantidadOcupados() - 1);
                    sectores.add(sectorLibre);
                    sectores.remove(sector);
                    Collections.sort(sectores, Sector.SectorComparator);
                    break;
                }
            }

        }

        simulacion.setAutosTotales(autosTotales);

        autosEsperandoCobro.put(autoModificado.getHoraEntradaCobro(), autoModificado);

        simulacion.setAutosEsperandoCobro(autosEsperandoCobro);
        simulacion.setAutosMapeados(autosMapeadosNuevaSim);

        simulacion.setEventoFinCobro(eventoFinCobro);

        simulacion.setVariablesEstadisticas(VariablesEstadisticas.builder()
                .cantidadAutosNoIngresados(simulacionAnterior.getVariablesEstadisticas().getCantidadAutosNoIngresados())
                .cantidadAutosNoIngresadosAC(simulacionAnterior.getVariablesEstadisticas().getCantidadAutosNoIngresadosAC())
                .recaudacion(simulacionAnterior.getVariablesEstadisticas().getRecaudacion())
                .build());

        simulacion.getVariablesEstadisticas().setPorcentajeUtilizacionPlaya(((Integer.valueOf(simulacionAnterior.getCantidadOcupados()).doubleValue()
                / 10) * 10000 / 10000) * (simulacion.getReloj() - simulacionAnterior.getReloj()));
        this.porcentajeUtilizacionTotal += simulacion.getVariablesEstadisticas().getPorcentajeUtilizacionPlaya();
        simulacion.getVariablesEstadisticas().setPorcentajeUtilizacionPlataAC(this.porcentajeUtilizacionTotal);

        simulaciones.add(simulacion);
    }

    private void procesarEventoFinCobro(PlayaEstacionamiento simulacion, PlayaEstacionamiento simulacionAnterior, double tpoCobro) {

        Map<Integer, Auto> autosTotales = copyAndSetNuevoMapParaAutosTotalesNuevosObjetos(simulacionAnterior.getAutosTotales());
        simulacion.setAutosTotales(autosTotales);

        Map<Double, Auto> autosEsperandoCobro = new HashMap<>();
        autosEsperandoCobro.putAll(simulacionAnterior.getAutosEsperandoCobro());
        simulacion.setAutosEsperandoCobro(autosEsperandoCobro);

        simulacion.setCajaCobro(simulacionAnterior.getCajaCobro());
        simulacion.setEventoFinCobro(simulacionAnterior.getEventoFinCobro());

        simulacion.setEventoLLegadaAuto(EventoLLegadaAuto.builder()
                .proximoAuto(simulacionAnterior.getEventoLLegadaAuto().getProximoAuto())
                .build());
        simulacion.setEventosFinEstacionamiento(simulacionAnterior.getEventosFinEstacionamiento());
        List<Sector> listaSector = new ArrayList<>();
        listaSector.addAll(simulacionAnterior.getSectores());
        simulacion.setSectores(listaSector);

        Map<Integer, Auto> autosMapeadosNuevaSim = new HashMap<>();
        autosMapeadosNuevaSim.putAll(simulacionAnterior.getAutosMapeados());
        simulacion.setAutosMapeados(autosMapeadosNuevaSim);

        double min = 1000000;
        if (simulacionAnterior.getCajaCobro().getCola() > 0) {
            simulacion.setCajaCobro(CajaCobro.builder().estadoCaja(simulacionAnterior.getCajaCobro().getEstadoCaja())
                    .cola(simulacionAnterior.getCajaCobro().getCola() - 1).build());

            Auto autoEsperandoCobro = null;
            for (Map.Entry<Double, Auto> entry : simulacion.getAutosEsperandoCobro().entrySet()) {
                if (entry.getValue().getEstadoAuto().equals(EstadoAuto.ESPERANDO_COBRO)) {
                    if (entry.getValue().getHoraEntradaCobro() < min) {
                        min = entry.getValue().getHoraEntradaCobro();
                        autoEsperandoCobro = entry.getValue();
                    }
                }
            }

            //liberamos el sector porque el auto fue a la caixa
            for (Sector sector : simulacionAnterior.getSectores()) {
                if (sector.getId() == autoEsperandoCobro.getNroFinEstacionamiento()) {

                    List<Sector> sectores = new ArrayList<>();
                    sectores.addAll(simulacionAnterior.getSectores());
                    simulacion.setSectores(sectores);

                    Sector sectorLibre = new Sector();
                    sectorLibre.setId(sector.getId());
                    sectorLibre.setEstadoSector(Estado.LIBRE);
                    simulacion.setCantidadOcupados(simulacionAnterior.getCantidadOcupados() == 0 ? 0 : simulacionAnterior.getCantidadOcupados() - 1);
                    sectores.add(sectorLibre);
                    sectores.remove(sector);
                    Collections.sort(sectores, Sector.SectorComparator);
                    break;
                }
            }

            simulacion.setEventoFinCobro(EventoFinCobro.builder()
                    .tiempoCobro(tpoCobro)
                    .finAtCobro(simulacion.getReloj() + tpoCobro)
                    .nroAuto(autoEsperandoCobro.getId())
                    .nroEstacionamiento(autoEsperandoCobro.getNroFinEstacionamiento())
                    .auto(autoEsperandoCobro)
                    .build());
            autosTotales.put(autoEsperandoCobro.getId(), Auto.builder()
                    .estadoAuto(EstadoAuto.SIENDO_COBRADO)
                    .id(autoEsperandoCobro.getId())
                    .nroFinEstacionamiento(autoEsperandoCobro.getNroFinEstacionamiento())
                    .precioXMinutos(autoEsperandoCobro.getPrecioXMinutos()).build());

            autosEsperandoCobro.remove(autoEsperandoCobro.getHoraEntradaCobro());

        } else {
            simulacion.setCajaCobro(CajaCobro.builder().estadoCaja(Estado.LIBRE).build());
            simulacion.setEventoFinCobro(EventoFinCobro.builder().build());

        }

        autosTotales.remove(simulacionAnterior.getEventoFinCobro().getAuto().getId());

        simulacion.setAutosTotales(autosTotales);

        simulacion.setCantidadOcupados(simulacionAnterior.getCantidadOcupados());

        simulacion.setVariablesEstadisticas(VariablesEstadisticas.builder()
                .cantidadAutosNoIngresados(simulacionAnterior.getVariablesEstadisticas().getCantidadAutosNoIngresados())
                .cantidadAutosNoIngresadosAC(simulacionAnterior.getVariablesEstadisticas().getCantidadAutosNoIngresadosAC())
                .recaudacion(simulacionAnterior.getVariablesEstadisticas().getRecaudacion())
                .build());

        simulacion.getVariablesEstadisticas().setRecaudacion(simulacionAnterior.getEventoFinCobro().getAuto().getPrecioXMinutos());
        this.recaudacionTotal += Math.floor(simulacion.getVariablesEstadisticas().getRecaudacion() * 100 / 100);
        simulacion.getVariablesEstadisticas().setRecaudacionAC(recaudacionTotal);

        autosEsperandoCobro.remove(simulacionAnterior.getEventoFinCobro().getAuto().getHoraEntradaCobro());
        simulacion.setAutosEsperandoCobro(autosEsperandoCobro);

        simulaciones.add(simulacion);
    }

    public Map<Integer, Auto> copyAndSetNuevoMapParaAutosTotalesNuevosObjetos(Map<Integer, Auto> autosTotales) {
        Map<Integer, Auto> autosTotalesAux = new HashMap<>(autosTotales.size());

        for (Map.Entry<Integer, Auto> entry : autosTotales.entrySet()) {

            Auto autoAnterior = entry.getValue();
            Integer nroAutoAnterior = entry.getKey();
            autosTotalesAux.put(nroAutoAnterior, autoAnterior);
        }

        return autosTotalesAux;
    }

    public Sector haySectorLibre(List<Sector> sectores) {
        Sector sectorLibre = null;
        for (Sector sector : sectores) {
            if (sector.getEstadoSector().equals(Estado.LIBRE)) {
                sectorLibre = Sector.builder().id(sector.getId()).estadoSector(sector.getEstadoSector()).build();
                sectores.remove(sector);
                break;
            }
        }
        return sectorLibre;
    }

    public EventoFinEstacionamiento hayEventoFinEstacionamientoLibre(List<EventoFinEstacionamiento> eventosFinEst) {
        EventoFinEstacionamiento eventoFinEstacionamientoLibre = null;
        for (EventoFinEstacionamiento eventoFinEstacionamiento : eventosFinEst) {
            if (eventoFinEstacionamiento.getFinEstacionamiento() == 0) {
                eventoFinEstacionamientoLibre = EventoFinEstacionamiento.builder().nro(eventoFinEstacionamiento.getNro())
                        .finEstacionamiento(eventoFinEstacionamiento.getFinEstacionamiento()).build();
                eventosFinEst.remove(eventoFinEstacionamiento);
                break;
            }
        }
        return eventoFinEstacionamientoLibre;
    }

    public void resetear() {
        this.N = 0;
        this.simulaciones = new ArrayList<>();
        this.tipoCoche = null;
        this.minutosEstacionamiento = null;
        this.recaudacionTotal = 0d;
        this.cantidadAutosNoIngresadosTotal = 0;
        this.porcentajeUtilizacionTotal = 0d;

    }

    public TablaProbabilidad tablaTipoCoche(double probabilidadChico, double probabilidadGrande, double probabilidadUtilitario) {
        TablaProbabilidad probabilidadesTipoCocheYPrecio = new TablaProbabilidad();
        List<Probabilidad> tipoCochePrecioList = new ArrayList<>();

        double probabilidadAC = 0;

        probabilidadAC += probabilidadChico;
        Probabilidad probChico = new Probabilidad(0, TipoCoche.CHICO, 300, probabilidadChico, probabilidadAC);
        tipoCochePrecioList.add(probChico);

        probabilidadAC += probabilidadGrande;
        Probabilidad probGrande = new Probabilidad(0, TipoCoche.GRANDE, 500, probabilidadGrande, probabilidadAC);
        tipoCochePrecioList.add(probGrande);

        probabilidadAC += probabilidadUtilitario;
        Probabilidad probUtilitario = new Probabilidad(0, TipoCoche.UTILITARIO, 1000, probabilidadUtilitario, probabilidadAC);
        tipoCochePrecioList.add(probUtilitario);

        probabilidadesTipoCocheYPrecio.setProbabilidades(tipoCochePrecioList);
        return probabilidadesTipoCocheYPrecio;

    }

    public TablaProbabilidad tablaMinutosEstacionamiento(double estacionamiento1hs,
            double estacionamiento2hs, double estacionamiento3hs, double estacionamiento4hs) {
        TablaProbabilidad probabilidadesMinutosEstacionamiento = new TablaProbabilidad();
        List<Probabilidad> minutosEstacionamientoList = new ArrayList<>();

        double probabilidadAC = 0;

        probabilidadAC += estacionamiento1hs;
        Probabilidad prob1hs = new Probabilidad(60, TipoCoche.NONE, 0, estacionamiento1hs, probabilidadAC);
        minutosEstacionamientoList.add(prob1hs);

        probabilidadAC += estacionamiento2hs;
        Probabilidad prob2hs = new Probabilidad(120, TipoCoche.NONE, 0, estacionamiento2hs, probabilidadAC);
        minutosEstacionamientoList.add(prob2hs);

        probabilidadAC += estacionamiento3hs;
        Probabilidad prob3hs = new Probabilidad(180, TipoCoche.NONE, 0, estacionamiento3hs, probabilidadAC);
        minutosEstacionamientoList.add(prob3hs);

        probabilidadAC += estacionamiento4hs;
        Probabilidad prob4hs = new Probabilidad(240, TipoCoche.NONE, 0, estacionamiento4hs, probabilidadAC);
        minutosEstacionamientoList.add(prob4hs);

        probabilidadesMinutosEstacionamiento.setProbabilidades(minutosEstacionamientoList);
        return probabilidadesMinutosEstacionamiento;

    }

    private Probabilidad calcularProbabilidad(double rnd, TablaProbabilidad probabilidades) {
        for (int i = 0; i < probabilidades.getProbabilidades().size(); i++) {

            Probabilidad probabilidadActual = probabilidades.getProbabilidades().get(i);
            Probabilidad probabilidadAnterior = null;
            if (i != 0) {
                probabilidadAnterior = probabilidades.getProbabilidades().get(i - 1);
                if (rnd < probabilidadActual.getProbabilidadAC() && (rnd > probabilidadAnterior.getProbabilidadAC())) {
                    return probabilidadActual;
                }
            }

            if (rnd < probabilidadActual.getProbabilidadAC()) {
                return probabilidadActual;
            }

        }
        return new Probabilidad();
    }

    private String calcularEventoProximo(PlayaEstacionamiento simulacion, PlayaEstacionamiento simulacionAnterior, int i) {
        double min = 0d;

        if (simulacionAnterior.getEventoFinCobro() != null && simulacionAnterior.getEventoFinCobro().getFinAtCobro() != 0) {

            if (simulacionAnterior.getEventoLLegadaAuto().getProximoAuto() < simulacionAnterior.getEventoFinCobro().getFinAtCobro()) {
                min = simulacionAnterior.getEventoLLegadaAuto().getProximoAuto();
                MinimoProxEvento eventoFinal = calcularMinimoDeEventosFinEstacionamiento(min, simulacionAnterior, Evento.LLEGADA_AUTO_.toString() + i);
                simulacion.setReloj(eventoFinal.getMin());

                return eventoFinal.getEvento();
            } else {
                min = simulacionAnterior.getEventoFinCobro().getFinAtCobro();
                MinimoProxEvento eventoFinal = calcularMinimoDeEventosFinEstacionamiento(min, simulacionAnterior, Evento.FIN_COBRO_.toString() + 
                        simulacionAnterior.getEventoFinCobro().getAuto().getId());
                simulacion.setReloj(eventoFinal.getMin());
                return eventoFinal.getEvento();
            }
        } else {
            min = simulacionAnterior.getEventoLLegadaAuto().getProximoAuto();
            MinimoProxEvento eventoFinal = calcularMinimoDeEventosFinEstacionamiento(min, simulacionAnterior, Evento.LLEGADA_AUTO_.toString() + i);
            simulacion.setReloj(eventoFinal.getMin());
            return eventoFinal.getEvento();
        }

    }

    private MinimoProxEvento calcularMinimoDeEventosFinEstacionamiento(double min, PlayaEstacionamiento simulacionAnterior, String evento) {
        for (EventoFinEstacionamiento eventoFinEst : simulacionAnterior.getEventosFinEstacionamiento()) {
            if (eventoFinEst.getFinEstacionamiento() != 0
                    && eventoFinEst.getFinEstacionamiento() < min) {
                min = eventoFinEst.getFinEstacionamiento();
                evento = Evento.FIN_ESTACIONAMIENTO_.toString() + eventoFinEst.getNro();
            }
        }
        return MinimoProxEvento.builder().evento(evento).min(min).build();
    }
}
