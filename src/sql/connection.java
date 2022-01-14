package sql;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
public class connection {   
public static Connection conn;
public static PreparedStatement ps;
public static ResultSet rs;

    public static void connect() throws ClassNotFoundException, SQLException {
        if (conn == null) {
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                conn = DriverManager.getConnection("jdbc:mysql://192.168.10.2:3306/db_local", "root", "maria.db");
                if (conn != null) {
                    System.out.println("Conexion Establecida");
                } else {
                    System.out.println("Error de conexion");
                }
            } catch (ClassNotFoundException | SQLException ex) {
                System.out.print(ex);
            }
        } else {
            System.out.println("Ya estas conectado");

        }
    }
}
       
