
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
    
    public boolean agregarMateria(Materia m){
  
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
            System.out.println("Materia guardada correctamente");
            return true;
            
        }catch(SQLException ex) {//esa excepcion sirve para cuando no se puede hacer nada referido a codigo SQL
            JOptionPane.showMessageDialog(null,"Error al guardar datos" + ex.getMessage());
            //System.out.println("Error al guardar datos" + ex.getMessage());
            return false;
        }
    }
    
    public Materia buscarMateria(int id) {
        Materia m = null;
        String sql = "SELECT * FROM materia WHERE id_materia = ?";

        PreparedStatement ps;
        try {
            ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                m = new Materia();
                m.setIdMateria(rs.getInt("id_materia"));
                m.setNombre(rs.getString("nombre"));
                m.setAnio(rs.getInt("anio"));
                m.setEstado(rs.getBoolean("estado"));
            }
            ps.close();
            rs.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al buscar materia: " + ex.getMessage());
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
            System.out.println("Materia actualizada correctamente");
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
            System.out.println("Materia eliminada correctamente");
            
        }catch(SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al eliminar" +  ex.getMessage());
            
        }
    }
    

    //Unifiqué los metodos alta y baja 
    public void actualizarEstadoMat(int id, boolean estado){
        String query = "UPDATE materia SET estado = ? WHERE id_materia = ?";
        
        try{
            PreparedStatement ps = con.prepareStatement(query);
            
            ps.setBoolean(1, estado);
            ps.setInt(2, id);
            ps.executeUpdate();
            
            ps.close();
            System.out.println("Estado actualizado con éxito");
            
        }catch(SQLException | NullPointerException ex){
            JOptionPane.showMessageDialog(null, "Error al cambiar estado: " + ex.getMessage());
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
            System.out.println("Listado de materias recuperado con éxito!");
            
            
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, "Error al recuperar datos" + e.getMessage());
            
        }
        return listaMat;
    }
    
    //EXTRA
    public boolean existeMateria(String nombre){
        String query = "SELECT * FROM materia WHERE nombre = ?";
        boolean existe = false;
        
        try{
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, nombre);
            ResultSet rs = ps.executeQuery();
            
            if(rs.next()){
                existe = true;
            }
            ps.close();
            rs.close();
            
        }catch(SQLException ex){
        
            JOptionPane.showMessageDialog(null, "Error al verificar materia: " + ex.getMessage());
                    
        }
        return existe;
    } 

}
    
    
 
