
package persistencia;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import modelo.*;
import org.mariadb.jdbc.Statement;

/**
 *
 * @author Cetera Evelyn
 */
public class InscripcionData {
    private Connection con;
    private AlumnoData aluDt;
    private MateriaData matDt;

    public InscripcionData(Conexion c) {
       con = c.buscarConexion();
       aluDt = new AlumnoData(c);
       matDt = new MateriaData(c);
    }
    
    public void guardarInscripciones(Inscripcion ins){
    //INSERT INTO `inscripcion`(`id_inscripto`, `nota`, `id_alumno`, `id_materia`) VALUES ('[value-1]','[value-2]','[value-3]','[value-4]')
        String query = "INSERT INTO inscripcion(nota, id_alumno, id_materia) VALUES (?,?,?)";
        
        try {
            PreparedStatement ps = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            
            ps.setDouble(1, ins.getNota());
            ps.setInt(2, ins.getIdAlumno());
            ps.setInt(3, ins.getIdMateria());

            
            ps.executeUpdate();
            
            ResultSet rs = ps.getGeneratedKeys();
            
            if(rs.next()){
                ins.setIdInscripto(rs.getInt(1));
                System.out.println("Inscripción guardada!");
            }else {
                System.out.println("No se pudo obtener el id");
            }
            ps.close();
            rs.close();
            
             
        } catch (SQLException | NullPointerException ex) {
            System.out.println("Error de conexión al tratar de guardar inscripción: " + ex.getMessage());
        }
        
    }
    
    public List<Inscripcion> obtenerInscripciones(int idAlum){
        
        List<Inscripcion> inscripciones = new ArrayList<>();
        
        String query = "SELECT id_inscripto, nota, id_alumno, id_materia \n" +
                       "FROM inscripcion \n" +
                       "WHERE id_alumno = ?";
        
            try{
                PreparedStatement ps = con.prepareStatement(query);
                ps.setInt(1, idAlum);
                ResultSet rs = ps.executeQuery();
                
                
                
                while(rs.next()){
                    Inscripcion ic = new Inscripcion();
                    ic.setIdInscripto(rs.getInt("id_inscripto"));
                    ic.setNota(rs.getDouble("nota"));
                    
                    
                    ic.setIdAlumno(rs.getInt("id_alumno"));
                    ic.setIdMateria(rs.getInt("id_materia"));

                    inscripciones.add(ic);
                    
                }
                ps.close();
                rs.close();
                
            }catch(SQLException ex){
                System.out.println("Error de conexión al tratar de obtener inscripciones:  " + ex.getMessage());
            }
        
        
        return inscripciones;
    }
    public List<Inscripcion> listarAlumnoSegunMateria(int idMat){
        List<Inscripcion> listaInscripciones = new ArrayList<>();
        
        String query = "SELECT id_inscripto, id_alumno, nota FROM inscripcion WHERE id_materia = ?";
        
            try{
                PreparedStatement ps = con.prepareStatement(query);
                ps.setInt(1, idMat);
                ResultSet rs = ps.executeQuery();
                
                
                while(rs.next()){

                    Inscripcion ins = new Inscripcion(
                        rs.getInt("id_inscripto"),
                        rs.getDouble("nota"),
                        rs.getInt("id_alumno"),
                        idMat
                    );
                    
                    listaInscripciones.add(ins);
                }
                
            
            }catch(SQLException ex){
                System.out.println("Error de conexión al tratar de obtener alumnos por materia:  " + ex.getMessage());
            }
        
        
        return listaInscripciones;
    }
    
    public List<Materia> listarMateriasCursadas(int idAlum){
        List<Materia> materiasCursadas = new ArrayList<>();
        
        String query = "SELECT id_materia, nota FROM inscripcion WHERE id_alumno = ?";
        
            try{
                PreparedStatement ps = con.prepareStatement(query);
                ps.setInt(1, idAlum);
                ResultSet rs = ps.executeQuery();
                
                
                while(rs.next()){
                    int idMat = rs.getInt("id_materia");
                    double nota = rs.getDouble("nota");
                    
                    Materia mat = matDt.buscarMateria(idMat);
                    
                    if(mat !=null){
                        materiasCursadas.add(mat);
                        System.out.println("Materia: " + mat.getNombre() + " - nota: " + nota);
                    }
  
                }
                System.out.println("Materia cursadas: \n");
            
            }catch(SQLException ex){
                System.out.println("Error de conexión al tratar de obtener materias cursadas:  " + ex.getMessage());
            }
        
        
        return materiasCursadas;
    }

    public List<Materia> listarMateriasNoCursadas(int id){
    List<Materia> materiasNoCursadas = new ArrayList<>();
        //String sql = "Select * from materia where idMateria not in " + "(select idMateria from inscripcion where idAlumno =?);
        String query = "SELECT * \n" +
                       "FROM materia\n" +
                       "WHERE id_materia NOT IN (SELECT id_materia\n" +
                                                "FROM inscripcion\n" +
                                                "WHERE id_alumno = ?);";
        
            try{
                PreparedStatement ps = con.prepareStatement(query);
                ps.setInt(1, id);
                ResultSet rs = ps.executeQuery();
                
                while(rs.next()){
                    int idMat = rs.getInt("id_materia");

                    Materia mat = matDt.buscarMateria(idMat);
                    
                    if(mat !=null){
                        materiasNoCursadas.add(mat);
                    }
                }
                
                System.out.println("Materia no cursadas: \n");
            ps.close();
            rs.close();
            
            }catch(SQLException ex){
                System.out.println("Error de conexion al tratar de obtener materias no cursadas:  " + ex.getMessage());
            }
          
        return materiasNoCursadas;
    }

    
    public void borrarInscripcion(int idAlum, int idMat){

        String query = "DELETE FROM inscripcion WHERE id_alumno = ? AND id_materia = ?";
        try{
            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1, idAlum);
            ps.setInt(2, idMat);
            
            int filas = ps.executeUpdate();
            
            if(filas > 0){
                System.out.println("Se eliminó la inscripción correctamente");
            }else {
                System.out.println("Error, no existe inscripción");
            }
            ps.close();
        }catch(SQLException ex){
            System.out.println("Error de conexión desde borrar inscripcion" + ex.getMessage());
        }
        
    }

    public void actualizarNotas(int idAlum, int idMat, double nota){
    
        String query = "UPDATE inscripcion SET nota = ? WHERE id_alumno = ? AND id_materia = ?  ";
    
    
        try{
            PreparedStatement ps = con.prepareStatement(query);
            
            ps.setDouble(1, nota);
            ps.setInt(2, idAlum);
            ps.setInt(3, idMat);
            
            ps.executeUpdate();
            System.out.println("Modificacion realizada correctamente");
            
        }catch(SQLException ex){
            System.out.println("Error al modificar nota: " + ex.getMessage());
        }
        
    }

    
    public double obtenerNota(int idAlum, int idMat){
        String query = "SELECT nota FROM inscripcion WHERE id_alumno = ? AND id_materia = ?";
        double notaObtenida = 0;

        try{
            PreparedStatement ps = con.prepareStatement(query);
            
            
            ps.setInt(1, idAlum);
            ps.setInt(2, idMat);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                notaObtenida = rs.getDouble("nota");
            }
            
            
            
            
        }catch(SQLException ex){
            System.out.println("Error al obtener nota: " + ex.getMessage());
            
        }
        return notaObtenida;
        
    }
}
