package simTp4;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;
import java.util.stream.IntStream;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class Principal extends javax.swing.JFrame {

    private static final DecimalFormat df = new DecimalFormat("0.00");

    Locale locale = new Locale("en", "US");
    NumberFormat currencyFormatter = NumberFormat.getCurrencyInstance(locale);

    private final VectorPlayaEstacionamiento manejador;

    public Principal() {
        initComponents();

        manejador = new VectorPlayaEstacionamiento();
        this.setLocationRelativeTo(null);
    }

    private void limpiarTextBox() {
        tpoEntreLlegadas.setText("");
        prob2HS.setText("");
        prob4HS.setText("");
        probChico.setText("");
        prob3HS.setText("");
        probGrande.setText("");
        prob1HS.setText("");
        probUtilitario.setText("");
        txtDesde.setText("");
        txtHasta.setText("");
        recaudacionTotal.setText("");

    }

    private void setColumnSize(javax.swing.JTable tabla) {
        tabla.getColumn("Nro").setMinWidth(20);
        tabla.getColumn("Reloj").setMinWidth(36);
        tabla.getColumn("Evento").setMinWidth(200);
        tabla.getColumn("RndTC").setMinWidth(36);
        tabla.getColumn("Tipo_Coche").setMinWidth(100);
        tabla.getColumn("Precio").setMinWidth(36);
        tabla.getColumn("Rnd_Min").setMinWidth(36);
        tabla.getColumn("Minutos").setMinWidth(36);
        tabla.getColumn("Rnd_Llegada_Auto").setMinWidth(150);
        tabla.getColumn("TpoELLg").setMinWidth(36);
        tabla.getColumn("Proximo_Auto").setMinWidth(100);
        tabla.getColumn("Nro_FinEst").setMinWidth(80);
        tabla.getColumn("Fin_Estacionamiento").setMinWidth(150);
        tabla.getColumn("Nro_Sector").setMinWidth(100);
        tabla.getColumn("Estado_Sector").setMinWidth(150);
        tabla.getColumn("Fin_Cobro").setMinWidth(75);
        tabla.getColumn("Caja_Estado").setMinWidth(75);
        tabla.getColumn("Caja_Cola").setMinWidth(75);
        tabla.getColumn("Recaudacion").setMinWidth(75);
        tabla.getColumn("Autos_No_Ingresados").setMinWidth(200);
        tabla.getColumn("Porc_Utilizacion").setMinWidth(150);
        tabla.getColumn("Recaudacion_Total").setMinWidth(150);
        tabla.getColumn("Porc_UtilizacionTotal").setMinWidth(150);
        tabla.getColumn("Autos_No_Ingresados_AC").setMinWidth(150);
        tabla.getColumn("Cantidad_Sectores_Ocupados").setMinWidth(200);
        tabla.getColumn("Autos").setMinWidth(300);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        grupoRdDistribuciones = new javax.swing.ButtonGroup();
        grupoCantidadIntervalos = new javax.swing.ButtonGroup();
        jLabel14 = new javax.swing.JLabel();
        grupoRadioIntervalos = new javax.swing.ButtonGroup();
        buttonGroup2 = new javax.swing.ButtonGroup();
        panel = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        prob2HS = new javax.swing.JTextField();
        lblB = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        tpoEntreLlegadas = new javax.swing.JTextField();
        txtN = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        btnGenerar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabla = new javax.swing.JTable();
        lblResultadoPrueba = new javax.swing.JLabel();
        lblIntegrantes = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        probChico = new javax.swing.JTextField();
        prob3HS = new javax.swing.JTextField();
        prob1HS = new javax.swing.JTextField();
        jLabel18 = new javax.swing.JLabel();
        probGrande = new javax.swing.JTextField();
        jLabel19 = new javax.swing.JLabel();
        probUtilitario = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        cantidadAutosNoIngresados = new javax.swing.JTextField();
        recaudacionTotal = new javax.swing.JTextField();
        lblB1 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        porcUtilizacion = new javax.swing.JTextField();
        jLabel28 = new javax.swing.JLabel();
        jLabel29 = new javax.swing.JLabel();
        prob4HS = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        tpoCobro = new javax.swing.JTextField();
        txtDesde = new javax.swing.JTextField();
        txtHasta = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        btnMostrarIntervalos = new javax.swing.JButton();

        jLabel14.setFont(new java.awt.Font("Cambria", 3, 14)); // NOI18N
        jLabel14.setText("Seleccione la cantidad de intervalos");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        panel.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        panel.setFont(new java.awt.Font("Tempus Sans ITC", 0, 11)); // NOI18N

        jLabel1.setFont(new java.awt.Font("Cambria Math", 1, 14)); // NOI18N
        jLabel1.setText("Playa de Estacionamiento");

        jLabel2.setFont(new java.awt.Font("Cambria Math", 1, 14)); // NOI18N
        jLabel2.setText("Cobro");

        prob2HS.setText("0.30");
        prob2HS.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                prob2HSActionPerformed(evt);
            }
        });

        lblB.setFont(new java.awt.Font("Cambria Math", 1, 14)); // NOI18N
        lblB.setText("Tiempo");

        jLabel5.setFont(new java.awt.Font("Cambria Math", 1, 14)); // NOI18N
        jLabel5.setText("Probabilidad");

        tpoEntreLlegadas.setText("13");
        tpoEntreLlegadas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tpoEntreLlegadasActionPerformed(evt);
            }
        });

        txtN.setText("30000");

        jLabel13.setFont(new java.awt.Font("Cambria Math", 1, 14)); // NOI18N
        jLabel13.setText("N");

        btnGenerar.setText("Simular");
        btnGenerar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGenerarActionPerformed(evt);
            }
        });

        tabla.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {},
                {},
                {},
                {},
                {},
                {},
                {},
                {},
                {},
                {},
                {},
                {},
                {},
                {},
                {},
                {},
                {},
                {},
                {},
                {},
                {},
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        tabla.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        tabla.setColumnSelectionAllowed(true);
        jScrollPane1.setViewportView(tabla);

        lblResultadoPrueba.setFont(new java.awt.Font("Cambria Math", 1, 14)); // NOI18N

        lblIntegrantes.setFont(new java.awt.Font("Cambria Math", 1, 14)); // NOI18N
        lblIntegrantes.setText("Integrantes: Pirra Juan Pablo, Gudin Andres, Lopez Eduardo, Mafud Hassan, Ghirardotti Andres");

        jLabel17.setFont(new java.awt.Font("Cambria Math", 0, 12)); // NOI18N
        jLabel17.setText("Chico");

        probChico.setText("0.45");
        probChico.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                probChicoActionPerformed(evt);
            }
        });

        prob3HS.setText("0.15");

        prob1HS.setText("0.50");
        prob1HS.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                prob1HSActionPerformed(evt);
            }
        });

        jLabel18.setFont(new java.awt.Font("Cambria Math", 0, 12)); // NOI18N
        jLabel18.setText("Grande");

        probGrande.setText("0.25");

        jLabel19.setFont(new java.awt.Font("Cambria Math", 0, 12)); // NOI18N
        jLabel19.setText("Utilitario");

        probUtilitario.setText("0.30");

        jLabel15.setFont(new java.awt.Font("Cambria Math", 1, 14)); // NOI18N
        jLabel15.setText("Recaudacion:");

        jLabel23.setFont(new java.awt.Font("Cambria Math", 1, 14)); // NOI18N
        jLabel23.setText("Cantidad de autos no ingresados:");

        cantidadAutosNoIngresados.setEditable(false);
        cantidadAutosNoIngresados.setBackground(new java.awt.Color(204, 255, 204));

        recaudacionTotal.setEditable(false);
        recaudacionTotal.setBackground(new java.awt.Color(204, 255, 204));
        recaudacionTotal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                recaudacionTotalActionPerformed(evt);
            }
        });

        lblB1.setFont(new java.awt.Font("Cambria Math", 1, 14)); // NOI18N
        lblB1.setText("Tipo Coche");

        jLabel6.setFont(new java.awt.Font("Cambria Math", 1, 14)); // NOI18N
        jLabel6.setText("Probabilidad");

        jLabel24.setFont(new java.awt.Font("Cambria Math", 0, 12)); // NOI18N
        jLabel24.setText("1 hs");

        jLabel25.setFont(new java.awt.Font("Cambria Math", 0, 12)); // NOI18N
        jLabel25.setText("2 hs");

        jLabel26.setFont(new java.awt.Font("Cambria Math", 0, 12)); // NOI18N
        jLabel26.setText("3 hs");

        jLabel27.setFont(new java.awt.Font("Cambria Math", 1, 14)); // NOI18N
        jLabel27.setText("Porcentaje Utilizacion:");

        porcUtilizacion.setEditable(false);
        porcUtilizacion.setBackground(new java.awt.Color(204, 255, 204));

        jLabel28.setFont(new java.awt.Font("Cambria Math", 0, 12)); // NOI18N
        jLabel28.setText("4 hs");

        jLabel29.setFont(new java.awt.Font("Cambria Math", 0, 12)); // NOI18N

        prob4HS.setEditable(false);
        prob4HS.setText("0.05");

        jLabel3.setFont(new java.awt.Font("Cambria Math", 1, 14)); // NOI18N
        jLabel3.setText("Indice");

        tpoCobro.setText("2");
        tpoCobro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tpoCobroActionPerformed(evt);
            }
        });

        txtDesde.setText("29998");

        txtHasta.setText("30000");

        jLabel16.setFont(new java.awt.Font("Cambria Math", 1, 14)); // NOI18N
        jLabel16.setText("Desde");

        jLabel20.setFont(new java.awt.Font("Cambria Math", 1, 14)); // NOI18N
        jLabel20.setText("Hasta");

        btnMostrarIntervalos.setText("Mostrar");
        btnMostrarIntervalos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMostrarIntervalosActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelLayout = new javax.swing.GroupLayout(panel);
        panel.setLayout(panelLayout);
        panelLayout.setHorizontalGroup(
            panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelLayout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelLayout.createSequentialGroup()
                        .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(panelLayout.createSequentialGroup()
                                .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(panelLayout.createSequentialGroup()
                                        .addComponent(lblB1, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jLabel5)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 139, Short.MAX_VALUE)
                                        .addComponent(lblB))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelLayout.createSequentialGroup()
                                        .addGap(0, 0, Short.MAX_VALUE)
                                        .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel24, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel25, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel26, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel28, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelLayout.createSequentialGroup()
                                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(27, 27, 27)
                                .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel6)
                                    .addComponent(prob2HS, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(prob1HS, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel29, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(prob3HS, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(prob4HS, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(tpoCobro, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(166, 166, 166)
                                .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel27, javax.swing.GroupLayout.PREFERRED_SIZE, 253, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel23, javax.swing.GroupLayout.PREFERRED_SIZE, 411, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 214, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelLayout.createSequentialGroup()
                                        .addGap(0, 133, Short.MAX_VALUE)
                                        .addComponent(jLabel13)
                                        .addGap(18, 18, 18)
                                        .addComponent(txtN, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(32, 32, 32)
                                        .addComponent(btnGenerar, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(52, 52, 52)))
                                .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(panelLayout.createSequentialGroup()
                                        .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(cantidadAutosNoIngresados, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(porcUtilizacion, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 267, Short.MAX_VALUE))
                                    .addGroup(panelLayout.createSequentialGroup()
                                        .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(panelLayout.createSequentialGroup()
                                                .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                    .addGroup(panelLayout.createSequentialGroup()
                                                        .addComponent(jLabel20)
                                                        .addGap(18, 18, 18)
                                                        .addComponent(txtHasta, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                    .addGroup(panelLayout.createSequentialGroup()
                                                        .addComponent(jLabel16)
                                                        .addGap(18, 18, 18)
                                                        .addComponent(txtDesde, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                                .addGap(18, 18, 18)
                                                .addComponent(btnMostrarIntervalos, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addComponent(recaudacionTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(0, 58, Short.MAX_VALUE))))
                            .addComponent(jScrollPane1))
                        .addGap(31, 31, 31))
                    .addGroup(panelLayout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panelLayout.createSequentialGroup()
                                .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(panelLayout.createSequentialGroup()
                                        .addGap(24, 24, 24)
                                        .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel19)
                                            .addGroup(panelLayout.createSequentialGroup()
                                                .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addGap(30, 30, 30)
                                                .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(probGrande, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addComponent(probChico, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addComponent(probUtilitario, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                                    .addComponent(tpoEntreLlegadas, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(lblIntegrantes)
                                .addGap(19, 19, 19))
                            .addComponent(lblResultadoPrueba, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(panelLayout.createSequentialGroup()
                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 221, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE)))))
                .addContainerGap())
        );
        panelLayout.setVerticalGroup(
            panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelLayout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblIntegrantes, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(51, 51, 51)
                        .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel15, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(recaudacionTotal, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel23)
                            .addComponent(cantidadAutosNoIngresados, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(1, 1, 1)
                        .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel27)
                            .addComponent(porcUtilizacion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(prob3HS, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel26)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelLayout.createSequentialGroup()
                        .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(tpoCobro, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel3)
                                    .addComponent(jLabel2)))
                            .addComponent(tpoEntreLlegadas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(panelLayout.createSequentialGroup()
                                .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel6)
                                    .addComponent(lblB, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel17)
                                    .addComponent(probChico, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(prob1HS, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel24))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                            .addGroup(panelLayout.createSequentialGroup()
                                .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(lblB1, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel5))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(probGrande, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(prob2HS, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel25))
                            .addComponent(jLabel18))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(probUtilitario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel19))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtN, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnGenerar)
                    .addComponent(jLabel13)
                    .addComponent(jLabel28)
                    .addComponent(jLabel29)
                    .addComponent(prob4HS, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel16)
                    .addComponent(txtDesde, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnMostrarIntervalos))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtHasta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel20))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 395, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(104, 104, 104)
                .addComponent(lblResultadoPrueba, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 12, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panel, javax.swing.GroupLayout.PREFERRED_SIZE, 720, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void recaudacionTotalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_recaudacionTotalActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_recaudacionTotalActionPerformed

    private void prob1HSActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_prob1HSActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_prob1HSActionPerformed

    //Generar Numeros aleatorios y calcular frecuencias
    private void btnGenerarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGenerarActionPerformed

        manejador.resetear();
        boolean fallo = false;

        if (tpoEntreLlegadas.getText().equals("") || tpoCobro.getText().equals("") || prob2HS.getText().equals("")
                || probChico.getText().equals("") || prob1HS.getText().equals("")
                || probGrande.getText().equals("") || prob3HS.getText().equals("")
                || probUtilitario.getText().equals("")) {

            JOptionPane.showMessageDialog(new JFrame(), "Para simular complete todos los campos de probabilidades y precios",
                    "Parámetros incorrectos", JOptionPane.WARNING_MESSAGE);
            fallo = true;

        }

        if (probChico.getText().contains(",")
                || probGrande.getText().contains(",")
                || probUtilitario.getText().contains(",")
                || prob1HS.getText().contains(",")
                || prob2HS.getText().contains(",")
                || prob3HS.getText().contains(",")) {

            JOptionPane.showMessageDialog(new JFrame(), "Los valores de probabilidades no pueden contener comas ,",
                    "Parámetros incorrectos", JOptionPane.WARNING_MESSAGE);
            fallo = true;

        }

        if (Double.parseDouble(probChico.getText())
                + Double.parseDouble(probGrande.getText())
                + Double.parseDouble(probUtilitario.getText()) != 1) {

            JOptionPane.showMessageDialog(new JFrame(), "La suma de las probabilidades de el tipo de auto no puede ser distinto de uno (1)",
                    "Parámetros incorrectos", JOptionPane.WARNING_MESSAGE);
            fallo = true;

        }
        
        double prob4 = (1 - (Double.parseDouble(prob1HS.getText()) + Double.parseDouble(prob2HS.getText()) + Double.parseDouble(prob3HS.getText()))) < 0 ? 0 :
                (1 - (Double.parseDouble(prob1HS.getText()) + Double.parseDouble(prob2HS.getText()) + Double.parseDouble(prob3HS.getText())));

        if (Double.parseDouble(prob1HS.getText())
                + Double.parseDouble(prob2HS.getText())
                + prob4
                + Double.parseDouble(prob3HS.getText()) != 1) {

            JOptionPane.showMessageDialog(new JFrame(), "La suma de las probabilidades del tiempo de estacionamiento no puede ser distinto de uno (1)",
                    "Parámetros incorrectos", JOptionPane.WARNING_MESSAGE);
            fallo = true;

        } else {
            prob4HS.setText(df.format(1 - (Double.parseDouble(prob1HS.getText()) 
                    + Double.parseDouble(prob2HS.getText()) + Double.parseDouble(prob3HS.getText()))));
        }

        if (fallo == false) {

            if (Integer.parseInt(txtN.getText()) < 100001 & Integer.parseInt(txtN.getText()) > 0) {
                manejador.setN(Integer.parseInt(txtN.getText()));
                fallo = false;
            } else {
                JOptionPane.showMessageDialog(new JFrame(), "_La cantidad de simulaciones no deberia ser mayor a 100000 o menor a 0", "Parámetros incorrectos", JOptionPane.WARNING_MESSAGE);
                txtN.requestFocus();
                fallo = true;
            }

            if (Integer.parseInt(txtHasta.getText()) > Integer.parseInt(txtN.getText())) {
                JOptionPane.showMessageDialog(new JFrame(), "Hasta no deberia ser mayor a N", "Parámetros incorrectos", JOptionPane.WARNING_MESSAGE);
                txtHasta.requestFocus();
                fallo = true;
            }

            if (fallo == false) {

                int cantidadSimulaciones = Integer.parseInt(txtN.getText());

                manejador.crearSimulaciones(cantidadSimulaciones, (float) Double.parseDouble(tpoEntreLlegadas.getText()),
                        Double.parseDouble(probChico.getText()),
                        Double.parseDouble(probGrande.getText()),
                        Double.parseDouble(probUtilitario.getText()),
                        Double.parseDouble(prob1HS.getText()),
                        Double.parseDouble(prob2HS.getText()),
                        Double.parseDouble(prob3HS.getText()),
                        Double.parseDouble(prob4HS.getText()),
                        Double.parseDouble(tpoCobro.getText()));

                //Carga de grilla
                Integer desde = Integer.parseInt(txtDesde.getText());
                int hasta = Integer.parseInt(txtHasta.getText()) +1;
                List<PlayaEstacionamiento> listaDesdeHasta = IntStream.range(desde, hasta)
                        .mapToObj(i -> manejador.getSimulaciones().get(i))
                        .toList();

                TablaIntervalos filas = new TablaIntervalos(listaDesdeHasta);
                tabla.setModel(filas);

                recaudacionTotal.setText(String.valueOf(NumberFormat.getCurrencyInstance(new Locale("en", "US"))
        .format(manejador.getRecaudacionTotal())));
                cantidadAutosNoIngresados.setText(String.valueOf(manejador.getCantidadAutosNoIngresadosTotal()));
                porcUtilizacion.setText(String.valueOf(Math.floor((manejador.getPorcentajeUtilizacionTotal() * 100
                        / manejador.getSimulaciones().get(manejador.getSimulaciones().size() - 1).getReloj()) * 100 / 100)) + " %");

                setColumnSize(tabla);

            }
        }

    }//GEN-LAST:event_btnGenerarActionPerformed

    private void tpoEntreLlegadasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tpoEntreLlegadasActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tpoEntreLlegadasActionPerformed

    private void prob2HSActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_prob2HSActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_prob2HSActionPerformed

    private void probChicoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_probChicoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_probChicoActionPerformed

    private void tpoCobroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tpoCobroActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tpoCobroActionPerformed

    private void btnMostrarIntervalosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMostrarIntervalosActionPerformed
        if (Integer.parseInt(txtHasta.getText()) > Integer.parseInt(txtN.getText())
                || Integer.parseInt(txtDesde.getText()) > Integer.parseInt(txtN.getText())) {
            JOptionPane.showMessageDialog(new JFrame(), "Desde o Hasta no deberia ser mayor a N", "Parámetros incorrectos", JOptionPane.WARNING_MESSAGE);
            txtHasta.requestFocus();
            return;
        }

         //Carga de grilla
                Integer desde = Integer.parseInt(txtDesde.getText());
                int hasta = Integer.parseInt(txtHasta.getText()) +1;
                List<PlayaEstacionamiento> listaDesdeHasta = IntStream.range(desde, hasta)
                        .mapToObj(i -> manejador.getSimulaciones().get(i))
                        .toList();

                TablaIntervalos filas = new TablaIntervalos(listaDesdeHasta);
                tabla.setModel(filas);

                recaudacionTotal.setText(String.valueOf(NumberFormat.getCurrencyInstance(new Locale("en", "US"))
        .format(manejador.getRecaudacionTotal())));
                cantidadAutosNoIngresados.setText(String.valueOf(manejador.getCantidadAutosNoIngresadosTotal()));
                porcUtilizacion.setText(String.valueOf(Math.floor((manejador.getPorcentajeUtilizacionTotal() * 100
                        / manejador.getSimulaciones().get(manejador.getSimulaciones().size() - 1).getReloj()) * 100 / 100)) + " %");

                setColumnSize(tabla);

    }//GEN-LAST:event_btnMostrarIntervalosActionPerformed

    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            new Principal().setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnGenerar;
    private javax.swing.JButton btnMostrarIntervalos;
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.JTextField cantidadAutosNoIngresados;
    private javax.swing.ButtonGroup grupoCantidadIntervalos;
    private javax.swing.ButtonGroup grupoRadioIntervalos;
    private javax.swing.ButtonGroup grupoRdDistribuciones;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblB;
    private javax.swing.JLabel lblB1;
    private javax.swing.JLabel lblIntegrantes;
    private javax.swing.JLabel lblResultadoPrueba;
    private javax.swing.JPanel panel;
    private javax.swing.JTextField porcUtilizacion;
    private javax.swing.JTextField prob1HS;
    private javax.swing.JTextField prob2HS;
    private javax.swing.JTextField prob3HS;
    private javax.swing.JTextField prob4HS;
    private javax.swing.JTextField probChico;
    private javax.swing.JTextField probGrande;
    private javax.swing.JTextField probUtilitario;
    private javax.swing.JTextField recaudacionTotal;
    private javax.swing.JTable tabla;
    private javax.swing.JTextField tpoCobro;
    private javax.swing.JTextField tpoEntreLlegadas;
    private javax.swing.JTextField txtDesde;
    private javax.swing.JTextField txtHasta;
    private javax.swing.JTextField txtN;
    // End of variables declaration//GEN-END:variables
}
