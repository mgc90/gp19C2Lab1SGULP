package persistencia;

// @author Matías Correa, Grupo 19, Comisión 2;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
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
            rs.close();
            System.out.println("Guardado con éxito!");
            
        }catch(SQLException e){//cuando no se puede hacer nada relacionado con SQL
            System.out.println("Error al guardar datos" + e.getMessage());
        }
    }
    
    
    public void actualizarAlumno(Alumno a){
        String query = "UPDATE alumno SET dni=?, apellido=?, nombre=?, "
                + "fechaNacimiento=?, estado=? WHERE id_alumno=?"; 
        
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
            
        }catch(SQLException e){
            System.out.println("Error al actualizar datos" + e.getMessage());
        }
    }
    
    public void eliminarAlumno(Alumno a){
        String query = "DELETE FROM alumno WHERE id_alumno=?"; 
        
        try{
            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1, a.getIdAlumno());
            ps.executeUpdate();
            
            System.out.println("Eliminado con éxito!");
            ps.close();
            
            
        }catch(SQLException e){
            System.out.println("Error al eliminar datos" + e.getMessage());
        }
    }
    
    public void bajaAlumno(Alumno a){
        String query = "UPDATE alumno SET estado= 0 WHERE id_alumno=?"; 
        
        try{
            PreparedStatement ps = con.prepareStatement(query);
           
            ps.setInt(1, a.getIdAlumno());
            ps.executeUpdate();
            
            ps.close();
            System.out.println("Alumno dado de baja con éxito!");
            
        }catch(SQLException e){
            System.out.println("Error al dar de baja" + e.getMessage());
        }
    }
    
    public Alumno buscarAlumno(int id){
        Alumno a = null;
        String query = "SELECT * FROM alumno WHERE id_alumno=?";
        
        try{
            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            
            while(rs.next()){
                a = new Alumno();
                a.setIdAlumno(rs.getInt("id_alumno"));
                a.setDni(rs.getInt("dni"));
                a.setNombre(rs.getString("nombre"));
                a.setApellido(rs.getString("apellido"));
                a.setFechaNacimiento(rs.getDate("fechaNacimiento").toLocalDate());
                a.setEstado(rs.getBoolean("estado"));
            }
            ps.close();
            rs.close();
            System.out.println(a);
            System.out.println("Alumno de id " + id + " recuperado con éxito:");
            
            
        }catch(SQLException e){//cuando no se puede hacer nada relacionado con SQL
            System.out.println("Error al guardar datos" + e.getMessage());
        }
        return a;
    }
    
    public List<Alumno> listarAlumnos(){
        Alumno a = null;
        List<Alumno> alumnos = new ArrayList<>();
        String query = "SELECT * FROM alumno";
        
        try{
            PreparedStatement ps = con.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            
            while(rs.next()){
                a = new Alumno();
                a.setIdAlumno(rs.getInt("id_alumno"));
                a.setDni(rs.getInt("dni"));
                a.setNombre(rs.getString("nombre"));
                a.setApellido(rs.getString("apellido"));
                a.setFechaNacimiento(rs.getDate("fechaNacimiento").toLocalDate());
                a.setEstado(rs.getBoolean("estado"));
                alumnos.add(a);
                System.out.println(a.toString());
            }
            ps.close();
            rs.close();
            System.out.println("Listado de alumnos recuperado con éxito!");
            
        }catch(SQLException e){//cuando no se puede hacer nada relacionado con SQL
            System.out.println("Error al recuperar datos" + e.getMessage());
        }
        return alumnos;
    }
}
