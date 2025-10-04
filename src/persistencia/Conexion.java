package persistencia;

/**
 *
 * @author Grupo 19 (Matias Correa, Valeria Muñoz, Evelyn Cetera)
 */

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

 
public class Conexion {
    private String url;
    private String usuario;
    private String password;
    
    private static Connection conexion = null;

    public Conexion(String url, String usuario, String password) {
        this.url = url;
        this.usuario = usuario;
        this.password = password;
    }

    public static Connection getConexion() {
        return conexion;
    }
    
    
    public Connection buscarConexion(){
        if (conexion==null){
            try{
                Class.forName("org.mariadb.jdbc.Driver");
                conexion = DriverManager.getConnection(url, usuario, password);
            }catch(SQLException | ClassNotFoundException e){
                System.out.println("No se pudo cargar el driver " + e.getMessage());
            }
        }
        return conexion; 
    }

    
}
