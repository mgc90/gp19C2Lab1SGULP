/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vistaControl;

import java.util.List;
import javax.swing.JOptionPane;
import modelo.Alumno;
import modelo.Materia;
import persistencia.AlumnoData;
import persistencia.InscripcionData;
import persistencia.MateriaData;

/**
 *
 * @author Hueso
 */
public class FormularioCargarNotaView extends javax.swing.JInternalFrame {

    private AlumnoData alumnoData;
    private MateriaData materiaData;
    private InscripcionData inscripcionesData;
    private List<Alumno> listaAlumnos;
    private List<Materia> listaMateria;
    /**
     * Creates new form FormularioCargarNotaView
     */
    public FormularioCargarNotaView(AlumnoData aluData, MateriaData matData, InscripcionData insData) {
        
        initComponents();
        alumnoData = aluData;
        materiaData = matData;
        inscripcionesData = insData;
        listaAlumnos = alumnoData.listarAlumnos();
              
        cargarComboBoxAlumnos();
        jcbAlumnos.setSelectedIndex(-1);
        jcbMaterias.setSelectedIndex(-1);
    }

    private void cargarComboBoxAlumnos() {
        
        for (Alumno aux : listaAlumnos) {
            jcbAlumnos.addItem(aux);
        }
    }
    
    private void cargarComboBoxMaterias(int id){
        listaMateria = inscripcionesData.listarMateriasCursadas(id);
        for(Materia aux: listaMateria)
            jcbMaterias.addItem(aux);
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jcbAlumnos = new javax.swing.JComboBox<>();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jcbMaterias = new javax.swing.JComboBox<>();
        jLabel4 = new javax.swing.JLabel();
        jtxtNota = new javax.swing.JTextField();
        jbtnGuardar = new javax.swing.JButton();

        jLabel1.setFont(new java.awt.Font("Dialog", 1, 48)); // NOI18N
        jLabel1.setText("Cargar Notas");

        jcbAlumnos.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jcbAlumnos.setToolTipText("");
        jcbAlumnos.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jcbAlumnosItemStateChanged(evt);
            }
        });
        jcbAlumnos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jcbAlumnosActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel2.setText("Alumno:");

        jLabel3.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel3.setText("Materia:");

        jcbMaterias.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jcbMaterias.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jcbMateriasItemStateChanged(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel4.setText("Nota:");

        jtxtNota.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        jtxtNota.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtxtNotaActionPerformed(evt);
            }
        });

        jbtnGuardar.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jbtnGuardar.setText("Guardar Nota");
        jbtnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnGuardarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jtxtNota, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jcbAlumnos, 0, 345, Short.MAX_VALUE)
                    .addComponent(jcbMaterias, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(105, 105, 105))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(305, 305, 305)
                        .addComponent(jbtnGuardar, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(210, 210, 210)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 316, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(261, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(47, 47, 47)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jcbAlumnos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(46, 46, 46)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jcbMaterias, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(50, 50, 50)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jtxtNota, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(54, 54, 54)
                .addComponent(jbtnGuardar, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(157, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jtxtNotaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtxtNotaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jtxtNotaActionPerformed

    private void jbtnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnGuardarActionPerformed
        // TODO add your handling code here:
        try{
            Alumno a = (Alumno)jcbAlumnos.getSelectedItem();
            int idAlumno = a.getIdAlumno();

            Materia m = (Materia)jcbMaterias.getSelectedItem();
            int idMateria = m.getIdMateria();

            double nota = Double.parseDouble(jtxtNota.getText());

            inscripcionesData.actualizarNotas(idAlumno, idMateria, nota);
        }catch(Exception e){
            JOptionPane.showMessageDialog(this, "Error al guardar nota", "WARNING", JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_jbtnGuardarActionPerformed

    private void jcbAlumnosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jcbAlumnosActionPerformed
        // TODO add your handling code here:
        
    }//GEN-LAST:event_jcbAlumnosActionPerformed

    private void jcbAlumnosItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jcbAlumnosItemStateChanged
        // TODO add your handling code here:
        if(jcbAlumnos.getSelectedIndex() != -1){
            jcbMaterias.removeAllItems();
            Alumno a = (Alumno)jcbAlumnos.getSelectedItem();
           
            int id = a.getIdAlumno();

            cargarComboBoxMaterias(id);
        }
    }//GEN-LAST:event_jcbAlumnosItemStateChanged

    private void jcbMateriasItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jcbMateriasItemStateChanged
        // TODO add your handling code here:
        if(jcbMaterias.getSelectedIndex() != -1) {
            Alumno a = (Alumno)jcbAlumnos.getSelectedItem();
            int id = a.getIdAlumno();

            Materia m = (Materia)jcbMaterias.getSelectedItem();
            int idMateria = m.getIdMateria();

            //double notaObtenida = inscripcionesData.obtenerNota(id, idMateria);

            //jtxtNota.setText(notaObtenida + "");
        }
    }//GEN-LAST:event_jcbMateriasItemStateChanged


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JButton jbtnGuardar;
    private javax.swing.JComboBox<Alumno> jcbAlumnos;
    private javax.swing.JComboBox<Materia> jcbMaterias;
    private javax.swing.JTextField jtxtNota;
    // End of variables declaration//GEN-END:variables
}
