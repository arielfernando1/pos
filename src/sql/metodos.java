package sql;

import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import static sql.connection.conn;
import static sql.connection.ps;
import static sql.connection.rs;

public class metodos {

    public void loadSQLTable(JTable tabla, DefaultTableModel modelo, String sql) throws SQLException {
        //Carga una tabla de MySQL a un objeto JTable
        tabla.setModel(modelo);
        ps = conn.prepareStatement(sql);
        rs = ps.executeQuery();
        ResultSetMetaData rsMd = rs.getMetaData();
        int cantidadColumnas = rsMd.getColumnCount();
        while (rs.next()) {
            final Object[] filas = new Object[cantidadColumnas];
            for (int i = 0; i < cantidadColumnas; ++i) {
                filas[i] = rs.getObject(i + 1);
            }
            modelo.addRow(filas);
        }
    }

    public float getPriceByID(String id) {
        float price = 0;
        try {
            String sql = "SELECT precio FROM producto WHERE idproducto='" + id + "';";
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                price = (rs.getFloat(1));
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return price;
    }

    public String getCIByName(String name) {
        String ci = null;
        try {
            String sql = "SELECT cedula FROM cliente WHERE nombre_cliente='" + name + "';";
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                ci = (rs.getString(1));
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return ci;
    }

    public ArrayList<String> fillComboBox(String sql, String column) throws SQLException {
        ArrayList<String> lista = new ArrayList<>();
        try {
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                lista.add(rs.getString(column));
            }
        } catch (SQLException e) {
            System.out.print(e);
        }
        return lista;
    }

    public float loadSumOfColumn(String column, String table, String date1, String date2) {
        float total = 0;
        try {
            String sql = "SELECT SUM(" + column + ") FROM " + table + " WHERE fecha BETWEEN '" + date1 + "' AND '" + date2 + "';";
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                total = (rs.getFloat(1));
            }
        } catch (SQLException e) {
            System.out.print(e);
        }
        return total;
    }

    public String getCategoryByID(String id) {
        String categoria = null;
        try {
            String sql = "select c.nombre_categoria from producto p inner join categoria c on p.idcategoria=c.idcategoria WHERE p.idproducto='" + id + "';";
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                categoria = (rs.getString(1));
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return categoria;
    }

    public int getCategoryNByID(String id) {
        int categoria = 0;
        try {
            String sql = "select idcategoria from producto wheew idproducto='" + id + "';";
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                categoria = (rs.getInt(1));
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return categoria;
    }

    public String getClientName(int idVenta) {
        String nombre = null;
        try {
            String sql = "SELECT c.nombre_cliente FROM cliente c INNER JOIN reg_venta rvt on rvt.cedula=c.cedula WHERE rvt.id_venta='" + idVenta + "';";
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                nombre = (rs.getString(1));

            }
        } catch (SQLException e) {
            System.out.println(e);
        }

        return nombre;
    }

    public String getClientDirection(int idVenta) {
        String direccion = null;
        try {
            String sql = "SELECT c.direccion FROM cliente c INNER JOIN reg_venta rvt on rvt.cedula=c.cedula WHERE rvt.id_venta='" + idVenta + "';";
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                direccion = (rs.getString(1));

            }
        } catch (SQLException e) {
            System.out.println(e);
        }

        return direccion;
    }

    public String getClientPhone(int idVenta) {
        String phone = null;
        try {
            String sql = "SELECT c.telefono FROM cliente c INNER JOIN reg_venta rvt on rvt.cedula=c.cedula WHERE rvt.id_venta='" + idVenta + "';";
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                phone = (rs.getString(1));

            }
        } catch (SQLException e) {
            System.out.println(e);
        }

        return phone;
    }

    public String getClientCI(int idVenta) {
        String cedula = null;
        try {
            String sql = "SELECT c.cedula FROM cliente c INNER JOIN reg_venta rvt on rvt.cedula=c.cedula WHERE rvt.id_venta='" + idVenta + "';";
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                cedula = (rs.getString(1));

            }
        } catch (SQLException e) {
            System.out.println(e);
        }

        return cedula;
    }

    public String getFactDate(int idVenta) {
        String fecha = null;
        try {
            String sql = "SELECT rvt.fecha FROM cliente c INNER JOIN reg_venta rvt on rvt.cedula=c.cedula WHERE rvt.id_venta='" + idVenta + "';";
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                fecha = (rs.getString(1));

            }
        } catch (SQLException e) {
            System.out.println(e);
        }

        return fecha;
    }

    public String getFactTotal(int idVenta) {
        String total = null;
        try {
            String sql = "SELECT SUM(total) FROM detalle_venta WHERE id_venta ='" + idVenta + "';";
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                total = (rs.getString(1));

            }
        } catch (SQLException e) {
            System.out.println(e);
        }

        return total;
    }

    public int getLast() {
        int last = 0;
        try {
            String sql = "SELECT MAX(id_venta) as last from reg_venta;";
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                last = (rs.getInt(1));
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return last;
    }

    public String getIDbyName(String name) throws SQLException {
        String id = null;
        String sql = "SELECT idproducto FROM producto WHERE nombre='" + name + "';";
        ps = conn.prepareStatement(sql);
        rs = ps.executeQuery();
        while (rs.next()) {
            id = rs.getString(1);
        }
        return id;
    }

    public String GetStockByID(String id) {
        String stock = null;
        try {
            String sql = "SELECT stock FROM producto WHERE idproducto ='" + id + "';";
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                stock = rs.getString(1);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return stock;

    }

    public String[] getProductData(String name) throws SQLException {
//        id,nombre,categoria,cod_prod,stock,precio
//        0,1,2...... 
        String data[] = null;
        String sql = "SELECT * FROM producto WHERE nombre='" + name + "';";
        ps = conn.prepareStatement(sql);
        rs = ps.executeQuery();
        ResultSetMetaData rsmd = rs.getMetaData();
        int column = rsmd.getColumnCount();
        while (rs.next()) {
            for (int i = 1; i <= column; i++) {
                data[i] = rs.getString(i+1);
            }
        }
        return data;
    }

    public int getColumnCount() throws SQLException {
        int column = 0;
        String sql = "SELECT * FROM producto;";
        ps = conn.prepareStatement(sql);
        rs = ps.executeQuery();
        ResultSetMetaData rsmd = rs.getMetaData();
        column = rsmd.getColumnCount();
        return column;
    }

}
