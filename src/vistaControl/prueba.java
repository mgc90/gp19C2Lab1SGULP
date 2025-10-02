package vistaControl;

// @author Matías Correa, Grupo 2, Comisión 2;

import java.sql.DriverManager;
import java.time.LocalDate;
import modelo.Alumno;
import persistencia.AlumnoData;
import persistencia.Conexion;

 
public class prueba {

    public static void main(String[] args) {
        
        try{
            Conexion c = new Conexion("jdbc:mariadb://localhost/GP19_bd_universidad", "root", "");
            AlumnoData ad = new AlumnoData(c);
            Alumno a1 = new Alumno(99999, "Correa", "Matías", LocalDate.of(1990, 07, 23), true);
            a1.setIdAlumno(1);
            //Alumno a2 = new Alumno(323654, "Muñoz", "Valeria", LocalDate.of(2000, 07, 23), true);
            //Alumno a3 = new Alumno(323679, "Cetera", "Evelyn", LocalDate.of(1997, 07, 23), true);
            
            //ad.guardarAlumno(a1);
            //ad.guardarAlumno(a2);
            //ad.guardarAlumno(a3);
            //ad.actualizarAlumno(a1);
            //ad.bajaAlumno(a1);
            ad.listarAlumnos();
            ad.buscarAlumno(3);
        }catch(Exception e){
            System.out.println("Error al procesar el pedido" + e.getMessage());
        }
    }

}
