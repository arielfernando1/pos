package gui;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import static sql.connection.conn;
import static sql.connection.ps;
import static sql.connection.rs;
import sql.metodos;
import internal.reportesPDF;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ariel593
 */
public class reportes extends javax.swing.JFrame {

    metodos m = new metodos();
    private static reportes obj = null;
    String pattern = "yyyy-MM-dd";
    SimpleDateFormat sdf = new SimpleDateFormat(pattern);
    Date hoy = java.util.Calendar.getInstance().getTime();
    Date dt = new Date();
    Calendar a = Calendar.getInstance();
    reportesPDF rp = new reportesPDF();

    public reportes() {
        initComponents();
        setDefaultCloseOperation(2);
        a.setTime(dt);
        a.add(Calendar.DATE, 1);
        dt = a.getTime();
        Calendario1.setDate(hoy);
        Calendario2.setDate(dt);
        Calendario5.setDate(hoy);
        Calendario6.setDate(dt);
        Calendario7.setDate(hoy);
        Calendario8.setDate(dt);
        cargarTablaImpresion();
        cargarVentas();
        cargarTablaInternet();
        cargarTablaFactura();
    }

    public static reportes getObj() {
        if (obj == null) {
            obj = new reportes();
        }
        return obj;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        PanelVentas = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        JTventas = new javax.swing.JTable();
        JTbuscar = new javax.swing.JTextField();
        BTNCargarDetalle = new javax.swing.JButton();
        Calendario1 = new com.toedter.calendar.JDateChooser();
        Calendario2 = new com.toedter.calendar.JDateChooser();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jButton4 = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        jSeparator8 = new javax.swing.JSeparator();
        jSeparator9 = new javax.swing.JSeparator();
        JLTotalDetalle = new javax.swing.JLabel();
        jSeparator10 = new javax.swing.JSeparator();
        jSeparator11 = new javax.swing.JSeparator();
        PanelFacturas = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        JTfactura = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();
        PanelImpresiones = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        JTimpresiones = new javax.swing.JTable();
        BTNCargarImpresion = new javax.swing.JButton();
        Calendario5 = new com.toedter.calendar.JDateChooser();
        Calendario6 = new com.toedter.calendar.JDateChooser();
        jButton5 = new javax.swing.JButton();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JSeparator();
        jSeparator3 = new javax.swing.JSeparator();
        JLbn = new javax.swing.JLabel();
        JLTotalImpresion = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        JLc100 = new javax.swing.JLabel();
        JLc50 = new javax.swing.JLabel();
        PanelInternet = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        JTinternet = new javax.swing.JTable();
        BTNCargarResumen = new javax.swing.JButton();
        jSeparator4 = new javax.swing.JSeparator();
        jSeparator5 = new javax.swing.JSeparator();
        jSeparator6 = new javax.swing.JSeparator();
        jSeparator7 = new javax.swing.JSeparator();
        jMonthChooser1 = new com.toedter.calendar.JMonthChooser();
        jYearChooser1 = new com.toedter.calendar.JYearChooser();
        PanelReporte = new javax.swing.JPanel();
        BTNGenerarReporte = new javax.swing.JButton();
        Calendario7 = new com.toedter.calendar.JDateChooser();
        Calendario8 = new com.toedter.calendar.JDateChooser();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 395, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMaximumSize(new java.awt.Dimension(700, 520));
        setMinimumSize(new java.awt.Dimension(700, 520));
        setPreferredSize(new java.awt.Dimension(700, 520));
        setResizable(false);
        setSize(new java.awt.Dimension(700, 520));
        getContentPane().setLayout(null);

        jTabbedPane1.setMaximumSize(new java.awt.Dimension(700, 500));
        jTabbedPane1.setMinimumSize(new java.awt.Dimension(700, 500));
        jTabbedPane1.setPreferredSize(new java.awt.Dimension(700, 500));

        PanelVentas.setBackground(new java.awt.Color(255, 255, 255));
        PanelVentas.setPreferredSize(new java.awt.Dimension(700, 700));
        PanelVentas.setLayout(null);

        JTventas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        JTventas.setMaximumSize(new java.awt.Dimension(300, 64));
        jScrollPane1.setViewportView(JTventas);

        PanelVentas.add(jScrollPane1);
        jScrollPane1.setBounds(20, 60, 650, 290);

        JTbuscar.setBackground(new java.awt.Color(204, 204, 204));
        JTbuscar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                JTbuscarKeyPressed(evt);
            }
        });
        PanelVentas.add(JTbuscar);
        JTbuscar.setBounds(10, 12, 176, 20);

        BTNCargarDetalle.setText("CARGAR");
        BTNCargarDetalle.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BTNCargarDetalleActionPerformed(evt);
            }
        });
        PanelVentas.add(BTNCargarDetalle);
        BTNCargarDetalle.setBounds(573, 10, 100, 23);
        PanelVentas.add(Calendario1);
        Calendario1.setBounds(240, 10, 72, 20);
        PanelVentas.add(Calendario2);
        Calendario2.setBounds(410, 10, 72, 20);

        jLabel1.setText("Desde");
        PanelVentas.add(jLabel1);
        jLabel1.setBounds(200, 10, 30, 14);

        jLabel2.setText("Hasta");
        PanelVentas.add(jLabel2);
        jLabel2.setBounds(360, 10, 28, 14);

        jButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/pdf.png"))); // NOI18N
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        PanelVentas.add(jButton4);
        jButton4.setBounds(610, 370, 70, 80);

        jLabel6.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel6.setText("TOTAL:");
        PanelVentas.add(jLabel6);
        jLabel6.setBounds(30, 420, 50, 17);
        PanelVentas.add(jSeparator8);
        jSeparator8.setBounds(0, 40, 700, 10);
        PanelVentas.add(jSeparator9);
        jSeparator9.setBounds(0, 360, 700, 10);

        JLTotalDetalle.setBackground(new java.awt.Color(255, 255, 255));
        JLTotalDetalle.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        PanelVentas.add(JLTotalDetalle);
        JLTotalDetalle.setBounds(80, 400, 130, 60);

        jSeparator10.setOrientation(javax.swing.SwingConstants.VERTICAL);
        PanelVentas.add(jSeparator10);
        jSeparator10.setBounds(501, 469, 50, 10);

        jSeparator11.setOrientation(javax.swing.SwingConstants.VERTICAL);
        PanelVentas.add(jSeparator11);
        jSeparator11.setBounds(508, 469, 2, 0);

        jTabbedPane1.addTab("Detalle de Ventas", PanelVentas);

        JTfactura.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane4.setViewportView(JTfactura);

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/pdf.png"))); // NOI18N
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout PanelFacturasLayout = new javax.swing.GroupLayout(PanelFacturas);
        PanelFacturas.setLayout(PanelFacturasLayout);
        PanelFacturasLayout.setHorizontalGroup(
            PanelFacturasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PanelFacturasLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton1)
                .addContainerGap())
            .addGroup(PanelFacturasLayout.createSequentialGroup()
                .addGap(43, 43, 43)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 605, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(47, Short.MAX_VALUE))
        );
        PanelFacturasLayout.setVerticalGroup(
            PanelFacturasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelFacturasLayout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 324, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 43, Short.MAX_VALUE)
                .addComponent(jButton1)
                .addContainerGap())
        );

        jTabbedPane1.addTab("Facturas", PanelFacturas);

        PanelImpresiones.setBackground(new java.awt.Color(255, 255, 255));

        JTimpresiones.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane3.setViewportView(JTimpresiones);

        BTNCargarImpresion.setText("CARGAR");
        BTNCargarImpresion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BTNCargarImpresionActionPerformed(evt);
            }
        });

        jButton5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/pdf.png"))); // NOI18N
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jLabel9.setText("Desde");

        jLabel10.setText("Hasta");

        JLbn.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        JLTotalImpresion.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N

        jLabel17.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel17.setText("Total: ");

        JLc100.setBackground(new java.awt.Color(204, 255, 255));
        JLc100.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        JLc50.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        javax.swing.GroupLayout PanelImpresionesLayout = new javax.swing.GroupLayout(PanelImpresiones);
        PanelImpresiones.setLayout(PanelImpresionesLayout);
        PanelImpresionesLayout.setHorizontalGroup(
            PanelImpresionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelImpresionesLayout.createSequentialGroup()
                .addGroup(PanelImpresionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(PanelImpresionesLayout.createSequentialGroup()
                        .addGap(28, 28, 28)
                        .addComponent(jLabel9)
                        .addGap(18, 18, 18)
                        .addComponent(Calendario5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(80, 80, 80)
                        .addComponent(jLabel10)
                        .addGap(18, 18, 18)
                        .addComponent(Calendario6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(220, 220, 220)
                        .addComponent(BTNCargarImpresion))
                    .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 695, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(PanelImpresionesLayout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 670, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(PanelImpresionesLayout.createSequentialGroup()
                        .addGap(25, 25, 25)
                        .addComponent(jLabel17)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(PanelImpresionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(PanelImpresionesLayout.createSequentialGroup()
                                .addComponent(JLTotalImpresion, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(38, 38, 38)
                                .addComponent(JLbn, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(PanelImpresionesLayout.createSequentialGroup()
                                .addGap(168, 168, 168)
                                .addGroup(PanelImpresionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(JLc50, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(JLc100, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(210, 210, 210)
                        .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 695, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(364, 364, 364))
        );
        PanelImpresionesLayout.setVerticalGroup(
            PanelImpresionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelImpresionesLayout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(PanelImpresionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(PanelImpresionesLayout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(jLabel9))
                    .addGroup(PanelImpresionesLayout.createSequentialGroup()
                        .addGap(4, 4, 4)
                        .addComponent(Calendario5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(PanelImpresionesLayout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(jLabel10))
                    .addGroup(PanelImpresionesLayout.createSequentialGroup()
                        .addGap(4, 4, 4)
                        .addComponent(Calendario6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(BTNCargarImpresion))
                .addGap(16, 16, 16)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 263, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(9, 9, 9)
                .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(PanelImpresionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(JLTotalImpresion, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(PanelImpresionesLayout.createSequentialGroup()
                        .addComponent(JLc100, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(PanelImpresionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(PanelImpresionesLayout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(JLc50, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(PanelImpresionesLayout.createSequentialGroup()
                                .addGap(1, 1, 1)
                                .addComponent(jLabel17)))
                        .addGap(15, 15, 15)
                        .addComponent(JLbn, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(28, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Impresiones", PanelImpresiones);

        PanelInternet.setBackground(new java.awt.Color(255, 255, 255));
        PanelInternet.setLayout(null);

        JTinternet.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane2.setViewportView(JTinternet);

        PanelInternet.add(jScrollPane2);
        jScrollPane2.setBounds(20, 60, 660, 347);

        BTNCargarResumen.setText("CARGAR");
        BTNCargarResumen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BTNCargarResumenActionPerformed(evt);
            }
        });
        PanelInternet.add(BTNCargarResumen);
        BTNCargarResumen.setBounds(600, 10, 77, 23);
        PanelInternet.add(jSeparator4);
        jSeparator4.setBounds(0, 40, 700, 10);
        PanelInternet.add(jSeparator5);
        jSeparator5.setBounds(0, 420, 0, 2);
        PanelInternet.add(jSeparator6);
        jSeparator6.setBounds(50, 432, 0, 30);
        PanelInternet.add(jSeparator7);
        jSeparator7.setBounds(0, 420, 700, 20);
        PanelInternet.add(jMonthChooser1);
        jMonthChooser1.setBounds(20, 10, 120, 20);
        PanelInternet.add(jYearChooser1);
        jYearChooser1.setBounds(160, 10, 70, 20);

        jTabbedPane1.addTab("Resumen diario", PanelInternet);

        PanelReporte.setBackground(new java.awt.Color(255, 255, 255));

        BTNGenerarReporte.setText("GENERAR REPORTE ");
        BTNGenerarReporte.setToolTipText("Generar reporte entre un intervalo de fechas, guarde en el disco local D\n");
        BTNGenerarReporte.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BTNGenerarReporteActionPerformed(evt);
            }
        });

        jLabel7.setText("Desde");

        jLabel8.setText("Hasta");

        javax.swing.GroupLayout PanelReporteLayout = new javax.swing.GroupLayout(PanelReporte);
        PanelReporte.setLayout(PanelReporteLayout);
        PanelReporteLayout.setHorizontalGroup(
            PanelReporteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PanelReporteLayout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(jLabel7)
                .addGap(34, 34, 34)
                .addComponent(Calendario7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(62, 62, 62)
                .addComponent(jLabel8)
                .addGap(39, 39, 39)
                .addComponent(Calendario8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 181, Short.MAX_VALUE)
                .addComponent(BTNGenerarReporte)
                .addGap(21, 21, 21))
            .addComponent(jSeparator1)
        );
        PanelReporteLayout.setVerticalGroup(
            PanelReporteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelReporteLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(PanelReporteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(BTNGenerarReporte)
                    .addGroup(PanelReporteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel7)
                        .addComponent(Calendario7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(Calendario8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel8)))
                .addGap(18, 18, 18)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(413, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Reporte", PanelReporte);

        getContentPane().add(jTabbedPane1);
        jTabbedPane1.setBounds(0, 0, 700, 500);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void BTNCargarImpresionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTNCargarImpresionActionPerformed

        cargarTablaImpresion();

    }//GEN-LAST:event_BTNCargarImpresionActionPerformed

    private void BTNCargarResumenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTNCargarResumenActionPerformed
        cargarTablaInternet();
    }//GEN-LAST:event_BTNCargarResumenActionPerformed

    private void BTNCargarDetalleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTNCargarDetalleActionPerformed
        String[] fechas = (GetDateFrom2JCalendar(Calendario1, Calendario2));
        cargarVentas();

    }//GEN-LAST:event_BTNCargarDetalleActionPerformed


    private void JTbuscarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_JTbuscarKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            cargarVentas();
        }
    }//GEN-LAST:event_JTbuscarKeyPressed


    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed

    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        // TODO add your handling code here:

    }//GEN-LAST:event_jButton5ActionPerformed

    private void BTNGenerarReporteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTNGenerarReporteActionPerformed
        try {
            String[] date = GetDateFrom2JCalendar(Calendario7, Calendario8);
            rp.pdfReporte(this, date[0], date[1]);
        } catch (SQLException | IOException ex) {
            System.out.print(ex);
        }
    }//GEN-LAST:event_BTNGenerarReporteActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        int factura = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el numero de factura"));
        try {
            rp.pdfFactura(this, factura);
        } catch (SQLException | IOException ex) {
            Logger.getLogger(reportes.class.getName()).log(Level.SEVERE, null, ex);
        }

    }//GEN-LAST:event_jButton1ActionPerformed

    /**
     * @param args the command line arguments
     */
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
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(reportes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(reportes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(reportes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(reportes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new reportes().setVisible(true);
            }
        });
    }

    private String[] GetDateFrom2JCalendar(com.toedter.calendar.JDateChooser Calendar1, com.toedter.calendar.JDateChooser Calendar2) {
        String date[] = new String[2];
        Date d1 = Calendar1.getDate();
        Date d2 = Calendar2.getDate();
        date[0] = sdf.format(d1);
        date[1] = sdf.format(d2);
        return date;
    }

    private void cargarVentas() {
        String[] fechas = (GetDateFrom2JCalendar(Calendario1, Calendario2));
        DefaultTableModel modelo = new DefaultTableModel(){
            @Override
            public boolean isCellEditable(int row, int column) {
                //all cells false
                return false;
            }
        };
        modelo.addColumn("ID detalle");
        modelo.addColumn("ID factura");
        modelo.addColumn("ID producto");
        modelo.addColumn("Detalle");
        modelo.addColumn("Cantidad");
        modelo.addColumn("Total");
        modelo.addColumn("Fecha");
        try {
            m.loadSQLTable(JTventas, modelo, "SELECT dv.id_detalle,dv.id_venta,dv.idproducto,p.nombre,dv.cant,dv.total,dv.fecha FROM detalle_venta dv INNER JOIN producto p ON p.idproducto=dv.idproducto WHERE fecha BETWEEN '" + fechas[0] + "' AND '" + fechas[1] + "';");
            JLTotalDetalle.setText("$ " + String.valueOf(m.loadSumOfColumn("total", "detalle_venta", fechas[0], fechas[1])));
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, e);
        }
    }

    private void cargarTablaFactura() {
        DefaultTableModel modelo = new DefaultTableModel(){
            @Override
            public boolean isCellEditable(int row, int column) {
                //all cells false
                return false;
            }
        };
        JTfactura.setModel(modelo);
        modelo.addColumn("ID");
        modelo.addColumn("Cedula");
        modelo.addColumn("Fecha");
        try {
            m.loadSQLTable(JTfactura, modelo, "SELECT * FROM reg_venta");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, e);
        }

    }

    private void cargarTablaInternet() {
        DefaultTableModel modelo = new DefaultTableModel(){
            @Override
            public boolean isCellEditable(int row, int column) {
                //all cells false
                return false;
            }
        };
        JTinternet.setModel(modelo);
        modelo.addColumn("Fecha");
        modelo.addColumn("Internet");
        modelo.addColumn("Impresiones");
        modelo.addColumn("Ventas");
        try {
            m.loadSQLTable(JTfactura, modelo, "SELECT * FROM reg_diario;");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, e);
        }
    }

    private void cargarTablaImpresion() {
        String[] fechas = (GetDateFrom2JCalendar(Calendario5, Calendario6));
        DefaultTableModel modelo = new DefaultTableModel(){
            @Override
            public boolean isCellEditable(int row, int column) {
                //all cells false
                return false;
            }
        };
        JTimpresiones.setModel(modelo);
        modelo.addColumn("Fecha y hora");
        modelo.addColumn("Color 100%");
        modelo.addColumn("Blanco y negro");
        modelo.addColumn("Total");

        try {
            m.loadSQLTable(JTimpresiones, modelo, "SELECT fecha,color100,bn,total FROM impresion WHERE fecha BETWEEN '" + fechas[0] + "' AND '" + fechas[1] + "';");
            JLTotalImpresion.setText("$ " + String.valueOf(m.loadSumOfColumn("total", "impresion", fechas[0], fechas[1])));
            JLc100.setText("Color 100 : " + String.valueOf(m.loadSumOfColumn("color100", "impresion", fechas[0], fechas[1])));
            JLbn.setText("B/N         : " + String.valueOf(m.loadSumOfColumn("bn", "impresion", fechas[0], fechas[1])));
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, e);
        }
    }

    public void getTotalImpresiones() throws SQLException {
        try {
            String[] fechas = (GetDateFrom2JCalendar(Calendario5, Calendario6));
            String sql = "select sum(total) from impresion where fecha between '" + fechas[0] + "' AND '" + fechas[1] + "';";
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                JLTotalImpresion.setText(rs.getString(1));
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, e);
        }

    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BTNCargarDetalle;
    private javax.swing.JButton BTNCargarImpresion;
    private javax.swing.JButton BTNCargarResumen;
    private javax.swing.JButton BTNGenerarReporte;
    private com.toedter.calendar.JDateChooser Calendario1;
    private com.toedter.calendar.JDateChooser Calendario2;
    private com.toedter.calendar.JDateChooser Calendario5;
    private com.toedter.calendar.JDateChooser Calendario6;
    private com.toedter.calendar.JDateChooser Calendario7;
    private com.toedter.calendar.JDateChooser Calendario8;
    private javax.swing.JLabel JLTotalDetalle;
    private javax.swing.JLabel JLTotalImpresion;
    private javax.swing.JLabel JLbn;
    private javax.swing.JLabel JLc100;
    private javax.swing.JLabel JLc50;
    private javax.swing.JTextField JTbuscar;
    private javax.swing.JTable JTfactura;
    private javax.swing.JTable JTimpresiones;
    private javax.swing.JTable JTinternet;
    private javax.swing.JTable JTventas;
    private javax.swing.JPanel PanelFacturas;
    private javax.swing.JPanel PanelImpresiones;
    private javax.swing.JPanel PanelInternet;
    private javax.swing.JPanel PanelReporte;
    private javax.swing.JPanel PanelVentas;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private com.toedter.calendar.JMonthChooser jMonthChooser1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator10;
    private javax.swing.JSeparator jSeparator11;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JSeparator jSeparator5;
    private javax.swing.JSeparator jSeparator6;
    private javax.swing.JSeparator jSeparator7;
    private javax.swing.JSeparator jSeparator8;
    private javax.swing.JSeparator jSeparator9;
    private javax.swing.JTabbedPane jTabbedPane1;
    private com.toedter.calendar.JYearChooser jYearChooser1;
    // End of variables declaration//GEN-END:variables
}
