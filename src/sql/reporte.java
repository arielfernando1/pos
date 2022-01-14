
package sql;

import java.util.ArrayList;

public class reporte {
    static ArrayList<reporte> rp = new ArrayList<reporte>();
    
    
    public reporte(String fecha,String nombre,int cantidad,float total){
        
    }
    
    public static void main(String[] args) {
        reporte r1 = new reporte("12","12", 0, 0);
        rp.add(r1);
        System.out.print(rp.get(0).toString());
        
    }
    
}

