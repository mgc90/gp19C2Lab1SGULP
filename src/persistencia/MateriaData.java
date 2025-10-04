
package persistencia;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import modelo.Materia;


/**
 *
 * @author Grupo 19 (Matias Correa, Valeria Muñoz, Evelyn Cetera)
 */
public class MateriaData {
    private  Connection con = null;

    public MateriaData(Conexion c) {
       con = c.buscarConexion();
    }
    
    public void agregarMateria(Materia m){
        String query = "INSERT INTO materia(nombre, anio, estado) VALUES(?, ?, ?)";
        
        try{
            PreparedStatement ps = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, m.getNombre());
            ps.setInt(2, m.getAnio());
            ps.setBoolean(3, m.isEstado());
            ps.executeUpdate();
            
            
            ResultSet rs = ps.getGeneratedKeys();
            if(rs.next())
                m.setIdMateria(rs.getInt(1));
            else
                JOptionPane.showMessageDialog(null,"No se pudo obtener ID de la materia");
                //System.out.println("No se pudo obtener ID de la materia");
            ps.close();
            JOptionPane.showMessageDialog(null,"Materia guardada correctamente");
            //System.out.println("Materia guardada correctamente");
            
        }catch(SQLException ex) {//esa excepcion sirve para cuando no se puede hacer nada referido a codigo SQL
            JOptionPane.showMessageDialog(null,"Error al guardar datos" + ex.getMessage());
            //System.out.println("Error al guardar datos" + ex.getMessage());
        }
    }
    
    public Materia buscarMateria(int id){
        Materia m = null;
        String sql = "SELECT * FROM materia WHERE id_materia = ?";
        
        PreparedStatement ps;
            try{
                ps = con.prepareStatement(sql);
                ps.setInt(1, id);
                ResultSet rs = ps.executeQuery();
                while(rs.next()){
                    m = new Materia();
                    m.setIdMateria(rs.getInt("id_materia"));
                    m.setNombre(rs.getString("nombre"));
                    m.setAnio(rs.getInt("anio"));
                    m.setEstado(rs.getBoolean("estado"));
                }
                System.out.println("Materia: " + m.toString());//Solo para mostrarlo por consola
                ps.close();
                rs.close();
            }catch(SQLException ex){
                JOptionPane.showMessageDialog(null,"Materia no encontrada" + ex.getMessage());
            }
         return m;
    }
    
    public void actualizarMateria(Materia mat){
        String query = "UPDATE materia SET nombre =?,anio =?,estado =? WHERE id_materia = ?";
        
        try{
            PreparedStatement ps = con.prepareStatement(query);
      
            ps.setString(1, mat.getNombre());
            ps.setInt(2, mat.getAnio());
            ps.setBoolean(3, mat.isEstado());
            ps.setInt(4, mat.getIdMateria());
            ps.executeUpdate();
            
            ps.close();
            JOptionPane.showMessageDialog(null, "Materia actualizada correctamente");
            
        }catch(SQLException ex) {//esa excepcion sirve para cuando no se puede hacer nada referido a codigo SQL
            JOptionPane.showMessageDialog(null, "Error al actualizar datos" + ex.getMessage());
        }
    }
    
    public void borrarMateria(int id){
        String query = "DELETE FROM materia WHERE id_materia= ?";
        
        try{
            PreparedStatement ps = con.prepareStatement(query);
            
            ps.setInt(1, id);
            ps.executeUpdate();
            
            ps.close();
            JOptionPane.showMessageDialog(null, "Materia eliminada correctamente ");
            
        }catch(SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al eliminar" +  ex.getMessage());
            
        }
    }
    
    public void bajaMateria(int id){
        String query = "UPDATE materia SET estado = 0 WHERE id_materia=?";
        
        try{
            PreparedStatement ps = con.prepareStatement(query); 
            
            ps.setInt(1, id);
            ps.executeUpdate();
            
            ps.close();
            JOptionPane.showMessageDialog(null, "Baja realizada correctamente");
            
        }catch(SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al dar de baja" +  ex.getMessage());
             
        }
    
    }
    public void altaMateria(int id){
        String query = "UPDATE materia SET estado = 1 WHERE id_materia=?";
        
        try{
            PreparedStatement ps = con.prepareStatement(query); 
            
            ps.setInt(1, id);
            ps.executeUpdate();
            
            ps.close();
            JOptionPane.showMessageDialog(null, "Alta realizada correctamente");
            
        }catch(SQLException | NullPointerException ex) {
            JOptionPane.showMessageDialog(null, "Error al dar de alta" +  ex.getMessage());
             
        }
    
    }
    

public List<Materia> listarMateria(){
        Materia m = null;
        List<Materia> listaMat = new ArrayList<>();
        String query = "SELECT * FROM materia";
        
        try{
            PreparedStatement ps = con.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            
            while(rs.next()){
                m = new Materia();
                m.setIdMateria(rs.getInt("id_materia"));
                m.setNombre(rs.getString("nombre"));
                m.setAnio(rs.getInt("anio"));
                m.setEstado(rs.getBoolean("estado"));
                listaMat.add(m);
                System.out.println(m.toString());
            }
            ps.close();
            rs.close();
            JOptionPane.showMessageDialog(null, "Listado de materias recuperado con éxito!");
            
            
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, "Error al recuperar datos" + e.getMessage());
            
        }
        return listaMat;
    }
}
    
    
 
