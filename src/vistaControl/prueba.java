package vistaControl;

/**
 *
 * @author Grupo 19 (Matias Correa, Valeria Muñoz, Evelyn Cetera)
 */

import java.time.LocalDate;
import modelo.Alumno;
import modelo.Materia;
import persistencia.AlumnoData;
import persistencia.Conexion;
import persistencia.MateriaData;

 
public class prueba {

    public static void main(String[] args) {
        
        try{
            Conexion c = new Conexion("jdbc:mariadb://localhost/GP19_bd_universidad", "root", "");
            AlumnoData ad = new AlumnoData(c);
            MateriaData md = new MateriaData(c);
            /*Alumno a1 = new Alumno(99999, "Correa", "Matías", LocalDate.of(1990, 07, 23), true);
            Alumno a2 = new Alumno(323654, "Muñoz", "Valeria", LocalDate.of(2000, 07, 23), true);
            Alumno a3 = new Alumno(323679, "Cetera", "Evelyn", LocalDate.of(1997, 07, 23), true);
            
            //ad.guardarAlumno(a1);
            //ad.guardarAlumno(a2);
            //ad.guardarAlumno(a3);

            //ad.listarAlumnos();
            ad.buscarAlumno(4);
            //ad.actualizarAlumno(a3);
            //ad.listarAlumnos();*/
            
            Materia m1 = new Materia("Laboratorio I", 2024, true);
            Materia m2 = new Materia("Programación web I", 2023, true);
            
            
            md.agregarMateria(m1);
            //md.agregarMateria(m2);
            
            
            //md.actualizarMateria(m2);

            //md.altaMateria(2);
            //md.bajaMateria(2);
            //md.borrarMateria(1);
            
            
            md.listarMateria();
            
            
        }catch(Exception e){
            System.out.println("Error al procesar el pedido" + e.getMessage());
        }
    }

}
