package internal;

import com.itextpdf.io.font.FontProgram;
import com.itextpdf.io.font.FontProgramFactory;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.property.TextAlignment;
import java.awt.Component;
import java.awt.HeadlessException;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.SQLException;
import javax.swing.JFileChooser;
import sql.connection;
import static sql.connection.conn;
import static sql.connection.ps;
import static sql.connection.rs;
import sql.metodos;

public class reportesPDF {

    metodos m = new metodos();
    connection c = new connection();

    public Table SQLTPDFVentas(String date1, String date2) throws SQLException {
        String sql = "SELECT p.nombre,dv.cant,dv.total,dv.fecha FROM detalle_venta dv INNER JOIN producto p ON p.idproducto=dv.idproducto WHERE fecha BETWEEN '" + date1 + "' AND '" + date2 + "';";
        Table tablaVenta = new Table(4);
        tablaVenta.addCell("Detalle");
        tablaVenta.addCell("Cantidad");
        tablaVenta.addCell("Total");
        tablaVenta.addCell("Fecha");
        ps = conn.prepareStatement(sql);
        rs = ps.executeQuery();
        while (rs.next()) {
            for (int i = 0; i < 1; i++) {
                String NombreProducto = rs.getString(1);
                String Cantidad = rs.getString(2);
                String Total = rs.getString(3);
                String Fecha = rs.getString(4);
                tablaVenta.addCell(NombreProducto);
                tablaVenta.addCell(Cantidad);
                tablaVenta.addCell(Total);
                tablaVenta.addCell(Fecha);
            }
        }
        return tablaVenta;
    }

    public Table SQLTPDFImpresiones(String date1, String date2) throws SQLException {
        String sql = "SELECT fecha,color100,bn,total FROM impresion WHERE fecha BETWEEN '" + date1 + "' AND '" + date2 + "';";
        Table tablaImpresion = new Table(4);
        tablaImpresion.addCell("Color");
        tablaImpresion.addCell("B/N");
        tablaImpresion.addCell("Total");
        tablaImpresion.addCell("Hora");
        ps = conn.prepareStatement(sql);
        rs = ps.executeQuery();
        while (rs.next()) {
            for (int i = 0; i < 1; i++) {
                String hr = rs.getString(1);
                String cl100 = rs.getString(2);
                String bn = rs.getString(3);
                String tot = rs.getString(4);
                tablaImpresion.addCell(cl100);
                tablaImpresion.addCell(bn);
                tablaImpresion.addCell(tot);
                tablaImpresion.addCell(hr);
            }
        }
        return tablaImpresion;
    }

    public Table SQLTPDFInventario() throws SQLException {
        String sql = "SELECT idproducto,idcategoria,nombre,marca,cod_prod,stock,precio,detalle FROM producto ORDER BY nombre asc;";
        Table tablaInventario = new Table(8);
        tablaInventario.addCell("idproducto");
        tablaInventario.addCell("idcategoria");
        tablaInventario.addCell("nombre");
        tablaInventario.addCell("marca");
        tablaInventario.addCell("cod_prod");
        tablaInventario.addCell("stock");
        tablaInventario.addCell("precio");
        tablaInventario.addCell("detalle");
        ps = conn.prepareStatement(sql);
        rs = ps.executeQuery();
        while (rs.next()) {
            for (int i = 0; i < 1; i++) {
                String idprod = rs.getString(1);
                String idcat = rs.getString(2);
                String nombre = rs.getString(3);
                String marca = rs.getString(4);
                String cod = rs.getString(5);
                String stock = rs.getString(6);
                if (rs.wasNull()) {
                    stock = "";
                }
                String precio = rs.getString(7);
                String detalle = rs.getString(8);
                if (rs.wasNull()) {
                    detalle = "";
                }
                tablaInventario.addCell(idprod);
                tablaInventario.addCell(idcat);
                tablaInventario.addCell(nombre);
                tablaInventario.addCell(marca);
                tablaInventario.addCell(cod);
                tablaInventario.addCell(stock);
                tablaInventario.addCell(precio);
                tablaInventario.addCell(detalle);
            }
        }
        return tablaInventario;
    }

    public Table SQLTPDFFactura(int factura) throws SQLException {

        String sql = "select dv.cant,p.nombre,p.precio,dv.total from detalle_venta dv inner join producto p on p.idproducto=dv.idproducto where dv.id_venta='" + factura + "';";
        Table tablaFactura = new Table(4);
        tablaFactura.addCell("CANTIDAD");
        tablaFactura.addCell("DESCRIPCION");
        tablaFactura.addCell("V.UNITARIO");
        tablaFactura.addCell("TOTAL");
        ps = conn.prepareStatement(sql);
        rs = ps.executeQuery();
        while (rs.next()) {
            for (int i = 0; i < 1; i++) {
                String cant = rs.getString(1);
                String det = rs.getString(2);
                String prec = rs.getString(3);
                String tot = rs.getString(4);
                tablaFactura.addCell(cant);
                tablaFactura.addCell(det);
                tablaFactura.addCell(prec);
                tablaFactura.addCell(tot);
            }
        }
        return tablaFactura;
    }

    public Paragraph titulo(String texto) throws IOException {
        Paragraph titulo = new Paragraph(texto);
        titulo.setTextAlignment(TextAlignment.CENTER);
        titulo.setFontSize(24);
        titulo.setBold();
//        titulo.setFont(ticketing());
        return titulo;
    }

    public PdfFont ticketing() throws IOException {
        String path = "src/fonts/ticketing.ttf";
        FontProgram fontProgram = FontProgramFactory.createFont(path);
        PdfFont ticketing = PdfFontFactory.createFont(fontProgram);
        return ticketing;
    }

    public Paragraph subtitulo(String texto) throws IOException {
        Paragraph subtitulo = new Paragraph(texto);
        subtitulo.setTextAlignment(TextAlignment.LEFT);
        subtitulo.setFontSize(12);
//        subtitulo.setFont(ticketing());
        return subtitulo;
    }

    public Paragraph footer(String texto) {
        Paragraph footer = new Paragraph(texto);
        return footer;
    }

    public void pdfFactura(Component cmp, int factura) throws SQLException, IOException {
        JFileChooser j = new JFileChooser();
        j.setDialogTitle("Elegir la ubicación para guardar la factura");
        j.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
        j.setSelectedFile(archivoFactura(j, factura));
        try {
            int result = j.showSaveDialog(cmp);
            if (result == JFileChooser.APPROVE_OPTION) {
                Document doc;
                doc = new Document(new PdfDocument(new PdfWriter(new FileOutputStream(archivoFactura(j, factura)))));
                doc.add(titulo("CIBER Y PAPELERIA ARIANA"));
                doc.add(titulo("NOTA DE VENTA"));
                doc.add(subtitulo("N°        :    " + String.valueOf(factura)));
                doc.add(subtitulo("Fecha     :    " + m.getFactDate(factura)));
                doc.add(subtitulo("Cliente   :    " + m.getClientName(factura)));
                doc.add(subtitulo("RUC / CI  :    " + m.getClientCI(factura)));
                doc.add(subtitulo("Direccion :    " + m.getClientDirection(factura)));
                doc.add(subtitulo("Teléfono  :    " + m.getClientPhone(factura)));
                doc.add(SQLTPDFFactura(factura));
                doc.add(subtitulo("TOTAL: S " + m.getFactTotal(factura)));
                doc.close();
            }
        } catch (HeadlessException | SQLException e) {
            System.out.print(e);
        }

    }

    public void pdfReporte(Component cmp, String fecha1, String fecha2) throws SQLException, IOException {
        JFileChooser j = new JFileChooser();
        j.setDialogTitle("Elegir la ubicación para guardar el reporte");
        j.setFileSelectionMode(JFileChooser.FILES_ONLY);
        j.setSelectedFile(archivoReporte(j, fecha1, fecha2));
        int result = j.showSaveDialog(cmp);
        if (result == JFileChooser.APPROVE_OPTION) {
            Document doc;
            doc = new Document(new PdfDocument(new PdfWriter(new FileOutputStream(archivoReporte(j, fecha1, fecha2)))));
            doc.add(subtitulo("Reporte entre: " + fecha1 + " y " + fecha2));
            doc.add(SQLTPDFVentas(fecha1, fecha2));
            doc.add(subtitulo("Total VENTAS: " + String.valueOf(m.loadSumOfColumn("total", "detalle_venta", fecha1, fecha2))));
            doc.add(SQLTPDFImpresiones(fecha1, fecha2));
            doc.add(subtitulo("Total IMPRESIONES: " + String.valueOf(m.loadSumOfColumn("total", "impresion", fecha1, fecha2))));
            doc.close();
        }
    }

    public void pdfInventario(Component cmp) throws SQLException, IOException {
        JFileChooser j = new JFileChooser();
        j.setDialogTitle("Elegir la ubicación para guardar el reporte");
        j.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
        j.setSelectedFile(archivoInventario(j));
        int result = j.showSaveDialog(cmp);
        if (result == JFileChooser.APPROVE_OPTION) {
            Document doc;
            doc = new Document(new PdfDocument(new PdfWriter(new FileOutputStream(archivoInventario(j)))));
            doc.add(SQLTPDFInventario());
            doc.close();

        }
    }

    public File archivoFactura(JFileChooser j, int nfactura) {
        String path = j.getCurrentDirectory().getAbsolutePath();
        File arch = new File(path + "/factura" + String.valueOf(nfactura) + ".pdf");
        return arch;
    }

    public File archivoReporte(JFileChooser j, String fecha1, String fecha2) {
        String path = j.getCurrentDirectory().getAbsolutePath();
        File arch = new File(path + "/reporte" + fecha1 + "-" + fecha2 + ".pdf");
        return arch;
    }

    public File archivoInventario(JFileChooser j) {
        String path = j.getCurrentDirectory().getAbsolutePath();
        File arch = new File(path + "/inventario.pdf");
        return arch;
    }
}
