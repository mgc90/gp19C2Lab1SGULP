
package modelo;

/**
 *
 * @author Grupo 19 (Matias Correa, Valeria Muñoz, Evelyn Cetera)
 */

public class Inscripcion {
    private int idInscripto;
    private double nota;
    private int idAlumno;
    private int idMateria;

    public Inscripcion(int idInscripto, double nota, int idAlumno, int idMateria) {
        this.idInscripto = idInscripto;
        this.nota = nota;
        this.idAlumno = idAlumno;
        this.idMateria = idMateria;
    }

    public Inscripcion(double nota, int idAlumno, int idMateria) {
        this.nota = nota;
        this.idAlumno = idAlumno;
        this.idMateria = idMateria;
    }

    public Inscripcion() {
    }
    

    public int getIdInscripto() {
        return idInscripto;
    }

    public void setIdInscripto(int idInscripto) {
        this.idInscripto = idInscripto;
    }

    public double getNota() {
        return nota;
    }

    public void setNota(double nota) {
        this.nota = nota;
    }

    public int getIdAlumno() {
        return idAlumno;
    }

    public void setIdAlumno(int idAlumno) {
        this.idAlumno = idAlumno;
    }

    public int getIdMateria() {
        return idMateria;
    }

    public void setIdMateria(int idMateria) {
        this.idMateria = idMateria;
    }
}
