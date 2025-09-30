package persistencia;

// @author Matías Correa, Grupo 19, Comisión 2;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import modelo.Alumno;

 
public class AlumnoData {
    private Connection con = null;
    
    public AlumnoData(Conexion c){
        this.con = c.buscarConexion();
    }
    
    public void guardarAlumno(Alumno a){
        String query = "INSERT INTO alumno(dni, apellido, nombre, "
                + "fechaNacimiento, estado) VALUES(?, ?, ?, ?, ?)"; //una query sql en un string
        
        try{
            PreparedStatement ps = con.prepareStatement(query, 
                    Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, a.getDni());
            ps.setString(2, a.getApellido());
            ps.setString(3, a.getNombre());
            ps.setDate(4, Date.valueOf(a.getFechaNacimiento()));
            ps.setBoolean(5, a.getEstado());
            ps.executeUpdate();
            
            ResultSet rs = ps.getGeneratedKeys(); //es necesario sobrecargar el ps
            if(rs.next()){
                a.setIdAlumno(rs.getInt(1));
            }else {
                System.out.println("No se pudo obtener ID");
            }
            ps.close();
            System.out.println("Guardado con éxito!");
            
        }catch(SQLException e){//cuando no se puede hacer nada relacionado con SQL
            System.out.println("Error al guardar datos" + e.getMessage());
        }
    }
    
    
    public void actualizarAlumno(Alumno a){
        String query = "UPDATE alumno SET dni='?', apellido='?', nombre='?', "
                + "fechaNacimiento='?', estado='?') WHERE idAlumno='?'"; 
        
        try{
            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1, a.getDni());
            ps.setString(2, a.getApellido());
            ps.setString(3, a.getNombre());
            ps.setDate(4, Date.valueOf(a.getFechaNacimiento()));
            ps.setBoolean(5, a.getEstado());
            ps.setInt(6, a.getIdAlumno());
            ps.executeUpdate();
            
            ps.close();
            System.out.println("Actualizado con éxito!");
            
        }catch(SQLException e){//cuando no se puede hacer nada relacionado con SQL
            System.out.println("Error al actualizar datos" + e.getMessage());
        }
    }
    
    
}
