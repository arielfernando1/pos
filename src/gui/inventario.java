package gui;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import java.awt.event.KeyEvent;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.sql.SQLException;
import java.sql.Types;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import static sql.connection.conn;
import static sql.connection.ps;
import internal.reportesPDF;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import sql.metodos;
import internal.logic;
import javax.swing.ButtonGroup;

public final class inventario extends javax.swing.JFrame {

    //TextAreaOutputStream taos;
    //PrintStream prs;
    private static inventario obj = null;
    reportesPDF rp = new reportesPDF();
    metodos m = new metodos();
    logic lgc = new logic();
    ButtonGroup group = new ButtonGroup();

    private inventario() {
        initComponents();
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        cargarTablaProducto();
        cargarTablaEditar();
        group.add(jr1);
        group.add(jr2);
        // taos = new TextAreaOutputStream(consola.jt, 60);
        //prs = new PrintStream((OutputStream) taos);
    }

    public static inventario getObj() {
        if (obj == null) {
            obj = new inventario();
        }
        return obj;
    }

    private void cargarTablaProducto() {
        String campo = txtcampo.getText();
        String where = "";
        DefaultTableModel modelo = new DefaultTableModel(){
            @Override
            public boolean isCellEditable(int row, int column) {
                //all cells false
                return false;
            }
        };
        modelo.addColumn("ID PRODUCTO");
        modelo.addColumn("ID CATEGORIA");
        modelo.addColumn("NOMBRE");
        modelo.addColumn("MARCA");
        modelo.addColumn("COD");
        modelo.addColumn("STOCK");
        modelo.addColumn("PRECIO");
        modelo.addColumn("DETALLE");
        if (!"".equals(campo)) {
            where = "WHERE nombre LIKE '%" + campo + "%'OR marca LIKE '%" + campo + "%'";
        }
        try {
            m.loadSQLTable(JTproductos, modelo, "SELECT * from producto " + where);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, e);
        }
    }

    private void pdfStock() {
        String path = "";
        JFileChooser j = new JFileChooser();
        j.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        int x = j.showSaveDialog(this);
        if (x == JFileChooser.APPROVE_OPTION) {
            path = j.getSelectedFile().getPath();
            try {
                DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd-HH-mm-ss");
                LocalDateTime now = LocalDateTime.now();
                String nombre = "reporte_stock " + dtf.format(now) + ".pdf";
                Document doc;
                doc = new Document(new PdfDocument(new PdfWriter(new FileOutputStream(path + nombre))));
                Table tab = new Table(JTproductos.getColumnCount());
                tab.addCell("ID");
                tab.addCell("NOMBRE");
                tab.addCell("COD");
                tab.addCell("MARCA");
                tab.addCell("STOCK");
                tab.addCell("PRECIO");
                for (int i = 0; i < JTproductos.getRowCount(); i++) {
                    String id = JTproductos.getValueAt(i, 0).toString();
                    String nombrep = JTproductos.getValueAt(i, 1).toString();
                    String cod = JTproductos.getValueAt(i, 2).toString();
                    String marca = JTproductos.getValueAt(i, 3).toString();
                    String stock = JTproductos.getValueAt(i, 4).toString();
                    String precio = JTproductos.getValueAt(i, 5).toString();
                    tab.addCell(id);
                    tab.addCell(nombrep);
                    tab.addCell(cod);
                    tab.addCell(marca);
                    tab.addCell(stock);
                    tab.addCell(precio);
                }
                doc.add(new Paragraph("Reporte de productos disponibles"));
                doc.add(tab);
                doc.close();
            } catch (FileNotFoundException e) {
                System.out.print(e);
            }
        }
    }

    public void añadirProducto() {
        int answer = JOptionPane.showConfirmDialog(this, "Desea añadir " + SPstock.getValue().toString() + " unidades del producto " + JTnombre.getText());
        if (answer == 0) {
            try {
                if (JTcategoria.getText().equals('4')) {
                    ps = conn.prepareStatement("INSERT INTO producto (idcategoria,nombre,cod_prod,marca,stock,precio,detalle) VALUES(?,?,?,?,?,?,?)");
                    ps.setInt(1, Integer.parseInt(JTcategoria.getText()));
                    ps.setString(2, JTnombre.getText());
                    ps.setString(3, JTcodprod.getText());
                    ps.setString(4, JTmarca.getText());
                    ps.setNull(5, Types.NULL);
                    ps.setDouble(6, Double.parseDouble(JTprecio.getText()));
                    ps.setString(7, TAdetalle.getText());
                    ps.executeUpdate();
                    JOptionPane.showMessageDialog(this, "Servicio añadido correctamente");
                } else {
                    ps = conn.prepareStatement("INSERT INTO producto (idcategoria,nombre,cod_prod,marca,stock,precio,detalle) VALUES(?,?,?,?,?,?,?)");
                    ps.setInt(1, Integer.parseInt(JTcategoria.getText()));
                    ps.setString(2, JTnombre.getText());
                    ps.setString(3, JTcodprod.getText());
                    ps.setString(4, JTmarca.getText());
                    ps.setNull(5, (int) SPstock.getValue());
                    ps.setDouble(6, Double.parseDouble(JTprecio.getText()));
                    ps.setString(7, TAdetalle.getText());
                    ps.executeUpdate();
                    JOptionPane.showMessageDialog(this, "Producto añadido correctamente");
                }

            } catch (SQLException e) {
                JOptionPane.showMessageDialog(this, e);
                System.out.print(e);
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "Ha ingresado una letra donde solo se permiten números");
            }
        } else {
            JOptionPane.showMessageDialog(this, "Operacion cancelada");
        }
    }
    public void logicAdd(){
        if(jr2.isSelected()){
            SPstock.setEnabled(false);   
        }else if(jr2.isSelected()==false){
            SPstock.setEnabled(true);
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTabbedPane1 = new javax.swing.JTabbedPane();
        productos = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        JTproductos = new javax.swing.JTable();
        txtcampo = new javax.swing.JTextField();
        BTNCargarProducto = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JSeparator();
        editar = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        JTeditar = new javax.swing.JTable();
        jButton4 = new javax.swing.JButton();
        TFid = new javax.swing.JTextField();
        TFnombre = new javax.swing.JTextField();
        TFprecio = new javax.swing.JTextField();
        TFcp1 = new javax.swing.JTextField();
        TFmarca1 = new javax.swing.JTextField();
        TFstock1 = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        MARCA = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        BTNModificarProducto = new javax.swing.JButton();
        TFcategoria = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        TAdescripcion = new javax.swing.JTextArea();
        jButton1 = new javax.swing.JButton();
        añadir = new javax.swing.JPanel();
        JTnombre = new javax.swing.JTextField();
        JTcodprod = new javax.swing.JTextField();
        JTmarca = new javax.swing.JTextField();
        JTprecio = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        BTNAñadirProducto = new javax.swing.JButton();
        SPstock = new javax.swing.JSpinner();
        JTcategoria = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        TAdetalle = new javax.swing.JTextArea();
        jr1 = new javax.swing.JRadioButton();
        jr2 = new javax.swing.JRadioButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        productos.setBackground(new java.awt.Color(255, 255, 255));

        JTproductos.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(JTproductos);

        txtcampo.setBackground(new java.awt.Color(204, 204, 204));
        txtcampo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtcampoKeyPressed(evt);
            }
        });

        BTNCargarProducto.setText("BUSCAR");
        BTNCargarProducto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BTNCargarProductoActionPerformed(evt);
            }
        });

        jButton3.setText("GENERAR PDF");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout productosLayout = new javax.swing.GroupLayout(productos);
        productos.setLayout(productosLayout);
        productosLayout.setHorizontalGroup(
            productosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(productosLayout.createSequentialGroup()
                .addGroup(productosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(productosLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(txtcampo, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(BTNCargarProducto)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton3))
                    .addGroup(productosLayout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 686, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(jSeparator1)
        );
        productosLayout.setVerticalGroup(
            productosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(productosLayout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(productosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtcampo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BTNCargarProducto)
                    .addComponent(jButton3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 396, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Inventario", productos);

        JTeditar.setAutoCreateRowSorter(true);
        JTeditar.setModel(new javax.swing.table.DefaultTableModel(
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
        JTeditar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                JTeditarMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(JTeditar);

        jButton4.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jButton4.setText("CARGAR");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        TFid.setEditable(false);

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel7.setText("ID");

        jLabel8.setText("CATEGORÍA");

        jLabel9.setText("CODIGO");

        MARCA.setText("MARCA");

        jLabel10.setText("STOCK");

        jLabel11.setText("PRECIO");

        BTNModificarProducto.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        BTNModificarProducto.setText("MODIFICAR");
        BTNModificarProducto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BTNModificarProductoActionPerformed(evt);
            }
        });

        jLabel12.setText("NOMBRE");

        jLabel14.setText("DETALLE");

        TAdescripcion.setBackground(new java.awt.Color(255, 255, 204));
        TAdescripcion.setColumns(20);
        TAdescripcion.setLineWrap(true);
        TAdescripcion.setRows(5);
        jScrollPane3.setViewportView(TAdescripcion);

        jButton1.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jButton1.setForeground(new java.awt.Color(255, 51, 51));
        jButton1.setText("ELIMINAR");

        javax.swing.GroupLayout editarLayout = new javax.swing.GroupLayout(editar);
        editar.setLayout(editarLayout);
        editarLayout.setHorizontalGroup(
            editarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(editarLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(editarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 685, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel12)
                    .addComponent(jLabel9)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, editarLayout.createSequentialGroup()
                        .addGroup(editarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(MARCA)
                            .addGroup(editarLayout.createSequentialGroup()
                                .addGroup(editarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(editarLayout.createSequentialGroup()
                                        .addGroup(editarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel7)
                                            .addComponent(jLabel8))
                                        .addGap(27, 27, 27)
                                        .addGroup(editarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(TFcategoria, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(TFid, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(TFnombre, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(TFcp1, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(TFmarca1, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(TFstock1, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(TFprecio, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addComponent(jLabel11)
                                    .addComponent(jLabel10))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(editarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(editarLayout.createSequentialGroup()
                                        .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(jLabel14))
                                    .addGroup(editarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addComponent(jButton1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(BTNModificarProducto, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))))
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 246, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(37, Short.MAX_VALUE))
        );
        editarLayout.setVerticalGroup(
            editarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, editarLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(editarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(editarLayout.createSequentialGroup()
                        .addGroup(editarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel7)
                            .addComponent(TFid, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton4)
                            .addComponent(jLabel14))
                        .addGap(9, 9, 9)
                        .addGroup(editarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel8)
                            .addComponent(TFcategoria, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(editarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel12)
                            .addComponent(TFnombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(editarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel9)
                            .addComponent(TFcp1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(editarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(MARCA)
                            .addComponent(TFmarca1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(editarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel10)
                            .addGroup(editarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(TFstock1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jButton1)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(editarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(editarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel11)
                                .addComponent(TFprecio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(BTNModificarProducto)))
                    .addComponent(jScrollPane3))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 235, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(54, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Editar", editar);

        añadir.setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel1.setText("Nombre");

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel2.setText("Cod prod");

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel3.setText("Marca");

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel4.setText("Stock");

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel5.setText("Precio");

        BTNAñadirProducto.setText("AÑADIR");
        BTNAñadirProducto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BTNAñadirProductoActionPerformed(evt);
            }
        });

        SPstock.setModel(new javax.swing.SpinnerNumberModel(0, 0, 999, 1));

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel6.setText("Categoría");

        jLabel13.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel13.setText("Detalle");

        TAdetalle.setColumns(20);
        TAdetalle.setRows(5);
        jScrollPane4.setViewportView(TAdetalle);

        jr1.setSelected(true);
        jr1.setText("Producto");
        jr1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jr1ActionPerformed(evt);
            }
        });

        jr2.setText("Servicio");
        jr2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jr2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout añadirLayout = new javax.swing.GroupLayout(añadir);
        añadir.setLayout(añadirLayout);
        añadirLayout.setHorizontalGroup(
            añadirLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(añadirLayout.createSequentialGroup()
                .addContainerGap(649, Short.MAX_VALUE)
                .addComponent(BTNAñadirProducto)
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, añadirLayout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(añadirLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(añadirLayout.createSequentialGroup()
                        .addComponent(jr1)
                        .addGap(39, 39, 39)
                        .addComponent(jr2))
                    .addGroup(añadirLayout.createSequentialGroup()
                        .addGroup(añadirLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(añadirLayout.createSequentialGroup()
                                .addGroup(añadirLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel2)
                                    .addComponent(jLabel3)
                                    .addComponent(jLabel4)
                                    .addComponent(jLabel5)
                                    .addComponent(jLabel1)
                                    .addComponent(jLabel6))
                                .addGap(32, 32, 32))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, añadirLayout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel13)
                                .addGap(18, 18, 18)))
                        .addGroup(añadirLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(JTprecio, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(SPstock, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(JTmarca, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(JTcodprod, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(JTcategoria, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(JTnombre, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        añadirLayout.setVerticalGroup(
            añadirLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(añadirLayout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addGroup(añadirLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jr1)
                    .addComponent(jr2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(añadirLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(JTnombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addGap(11, 11, 11)
                .addGroup(añadirLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(JTcategoria, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6))
                .addGap(12, 12, 12)
                .addGroup(añadirLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(JTcodprod, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(añadirLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(JTmarca, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(añadirLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(SPstock, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(añadirLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(JTprecio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(añadirLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(añadirLayout.createSequentialGroup()
                        .addGap(35, 35, 35)
                        .addComponent(jLabel13)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 149, Short.MAX_VALUE)
                .addComponent(BTNAñadirProducto)
                .addContainerGap())
        );

        jTabbedPane1.addTab("Añadir", añadir);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtcampoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtcampoKeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            cargarTablaProducto();
        }
    }//GEN-LAST:event_txtcampoKeyPressed

    private void BTNAñadirProductoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTNAñadirProductoActionPerformed

        añadirProducto();
    }//GEN-LAST:event_BTNAñadirProductoActionPerformed

    private void BTNCargarProductoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTNCargarProductoActionPerformed
        // TODO add your handling code here:
        cargarTablaProducto();
    }//GEN-LAST:event_BTNCargarProductoActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        try {
            rp.pdfInventario(this);
        } catch (SQLException | IOException ex) {
            Logger.getLogger(inventario.class.getName()).log(Level.SEVERE, null, ex);
        }

    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        cargarTablaEditar();
    }//GEN-LAST:event_jButton4ActionPerformed

    private void cargarTablaEditar() {
        DefaultTableModel modelo = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                //all cells false
                return false;
            }
        };
        modelo.addColumn("ID PRODUCTO");
        modelo.addColumn("ID CATEGORIA");
        modelo.addColumn("NOMBRE");
        modelo.addColumn("MARCA");
        modelo.addColumn("COD");
        modelo.addColumn("STOCK");
        modelo.addColumn("PRECIO");
        modelo.addColumn("DETALLE");
        try {
            m.loadSQLTable(JTeditar, modelo, "SELECT * from producto;");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, e);
        }
    }

    private void JTeditarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_JTeditarMouseClicked
        try {
            TFid.setText(String.valueOf(JTeditar.getValueAt(JTeditar.getSelectedRow(), 0)));
            TFcategoria.setText(String.valueOf(JTeditar.getValueAt(JTeditar.getSelectedRow(), 1)));
            TFnombre.setText(String.valueOf(JTeditar.getValueAt(JTeditar.getSelectedRow(), 2)));
            TFcp1.setText(String.valueOf(JTeditar.getValueAt(JTeditar.getSelectedRow(), 3)));
            TFmarca1.setText(String.valueOf(JTeditar.getValueAt(JTeditar.getSelectedRow(), 4)));
            TFstock1.setText(String.valueOf(JTeditar.getValueAt(JTeditar.getSelectedRow(), 5)));
            TFprecio.setText(String.valueOf(JTeditar.getValueAt(JTeditar.getSelectedRow(), 6)));
            TAdescripcion.setText(String.valueOf(JTeditar.getValueAt(JTeditar.getSelectedRow(), 7)));
        } catch (Exception e) {
            System.out.println(e);
        }
    }//GEN-LAST:event_JTeditarMouseClicked

    private void BTNModificarProductoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTNModificarProductoActionPerformed
        int answ = JOptionPane.showConfirmDialog(this, "¿Desea editar el producto " + TFnombre.getText() + " ?");
        if (answ == 0) {
            try {
                String sql = "UPDATE producto SET idcategoria='" + TFcategoria.getText() + "', marca='" + TFmarca1.getText() + "', cod_prod='" + TFcp1.getText() + "', stock='" + TFstock1.getText() + "', precio='" + TFprecio.getText() + "',detalle='" + TAdescripcion.getText() + "' WHERE idproducto='" + TFid.getText() + "';";
                ps = conn.prepareStatement(sql);
                ps.executeUpdate();
                cargarTablaEditar();
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(this, ex);
            }
        }
    }//GEN-LAST:event_BTNModificarProductoActionPerformed

    private void jr2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jr2ActionPerformed
        // TODO add your handling code here:
        logicAdd();
    }//GEN-LAST:event_jr2ActionPerformed

    private void jr1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jr1ActionPerformed
        // TODO add your handling code here:
        logicAdd();
    }//GEN-LAST:event_jr1ActionPerformed

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
            java.util.logging.Logger.getLogger(inventario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(inventario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(inventario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(inventario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new inventario().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BTNAñadirProducto;
    private javax.swing.JButton BTNCargarProducto;
    private javax.swing.JButton BTNModificarProducto;
    private javax.swing.JTextField JTcategoria;
    private javax.swing.JTextField JTcodprod;
    private javax.swing.JTable JTeditar;
    private javax.swing.JTextField JTmarca;
    private javax.swing.JTextField JTnombre;
    private javax.swing.JTextField JTprecio;
    private javax.swing.JTable JTproductos;
    private javax.swing.JLabel MARCA;
    private javax.swing.JSpinner SPstock;
    private javax.swing.JTextArea TAdescripcion;
    private javax.swing.JTextArea TAdetalle;
    private javax.swing.JTextField TFcategoria;
    private javax.swing.JTextField TFcp1;
    private javax.swing.JTextField TFid;
    private javax.swing.JTextField TFmarca1;
    private javax.swing.JTextField TFnombre;
    private javax.swing.JTextField TFprecio;
    private javax.swing.JTextField TFstock1;
    private javax.swing.JPanel añadir;
    private javax.swing.JPanel editar;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JRadioButton jr1;
    private javax.swing.JRadioButton jr2;
    private javax.swing.JPanel productos;
    private javax.swing.JTextField txtcampo;
    // End of variables declaration//GEN-END:variables
}
