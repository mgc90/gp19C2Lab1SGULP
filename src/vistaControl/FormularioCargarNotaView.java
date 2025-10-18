
package vistaControl;

import java.awt.event.ItemEvent;
import java.awt.event.KeyEvent;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
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
    private boolean cargandoMaterias = false;



    public FormularioCargarNotaView(AlumnoData aluData, MateriaData matData, InscripcionData insData) {
        
        initComponents();
        alumnoData = aluData;
        materiaData = matData;
        inscripcionesData = insData;
        listaAlumnos = alumnoData.listarAlumnos();  
        cargandoMaterias = true;
        cargarComboBoxAlumnos();
        jcbAlumnos.setSelectedIndex(-1);
        jcbMaterias.setSelectedIndex(-1);
        cargandoMaterias = false;
        
    }
    private void limpiarCampos(){
        cargandoMaterias = true;
        
        jcbAlumnos.removeAllItems();
        jcbMaterias.removeAllItems();
        jtxtNota.setText("");
        
        cargarComboBoxAlumnos();
        
        jcbAlumnos.setSelectedIndex(-1);
        jcbMaterias.setSelectedIndex(-1);
        
        cargandoMaterias = false;
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
    
    private void cargarNotaMateriaSelec() {
        jtxtNota.setText("");
        Alumno a = (Alumno) jcbAlumnos.getSelectedItem();
        Materia m = (Materia) jcbMaterias.getSelectedItem();

        if (a != null && m != null) {

            int id = a.getIdAlumno();
            int idMateria = m.getIdMateria();

            Double notaObtenida = inscripcionesData.obtenerNota(id, idMateria);

            if (notaObtenida != null) {
                jtxtNota.setText(String.valueOf(notaObtenida));
            } else {
                jtxtNota.setText(" ");
            }
        }
    }
    
    private void validarNumeros(char c, KeyEvent e) {
        if (Character.isLetter(c)) {
            JOptionPane.showMessageDialog(this, "Campo de valores númericos", "Entrada inválida", JOptionPane.WARNING_MESSAGE);
            e.consume();
        }
    }
    
    private boolean validarNota(String n) {
        
        Pattern patron=Pattern.compile("^\\d([.]\\d{1,2})?$");
        Matcher m=patron.matcher(n);
        return m.matches();
        
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

        setClosable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle("Cargar Notas");

        jLabel1.setFont(new java.awt.Font("Dialog", 1, 48)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Cargar Notas");
        jLabel1.setOpaque(true);

        jcbAlumnos.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jcbAlumnos.setToolTipText("");
        jcbAlumnos.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jcbAlumnosItemStateChanged(evt);
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
        jtxtNota.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jtxtNotaKeyTyped(evt);
            }
        });

        jbtnGuardar.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jbtnGuardar.setText("Guardar Nota");
        jbtnGuardar.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
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
                .addGap(0, 37, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jcbAlumnos, 0, 345, Short.MAX_VALUE)
                            .addComponent(jcbMaterias, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(105, 105, 105))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jtxtNota, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 138, Short.MAX_VALUE)
                        .addComponent(jbtnGuardar, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(80, 80, 80))))
            .addGroup(layout.createSequentialGroup()
                .addGap(110, 110, 110)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 376, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(53, 53, 53)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jcbAlumnos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(46, 46, 46)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jcbMaterias, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(50, 50, 50)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jtxtNota, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(52, 52, 52)
                        .addComponent(jbtnGuardar, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(66, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jbtnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnGuardarActionPerformed
        // TODO add your handling code here:
        try {
            Alumno a = (Alumno) jcbAlumnos.getSelectedItem();
            int idAlumno = a.getIdAlumno();
            
            Materia m = (Materia) jcbMaterias.getSelectedItem();
            int idMateria = m.getIdMateria();

            double nota = Double.parseDouble(jtxtNota.getText());

            int opcion = JOptionPane.showConfirmDialog(this, "¿Desea guardar la nota?", "Confirmación", JOptionPane.YES_NO_OPTION);

            if (opcion == JOptionPane.YES_NO_OPTION) {
                inscripcionesData.actualizarNotas(idAlumno, idMateria, nota);
                JOptionPane.showMessageDialog(this, "Nota guardada correctamente");
            }
            limpiarCampos();
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Error al guardar nota", "WARNING", JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_jbtnGuardarActionPerformed

    private void jcbAlumnosItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jcbAlumnosItemStateChanged
        // TODO add your handling code here:
        if (cargandoMaterias) {
            return;
        }
        if (evt.getStateChange() == ItemEvent.SELECTED && jcbAlumnos.getSelectedIndex() != -1) {

            cargandoMaterias = true;  //este booleano desactiva el listener para que cuando se esten cargando los comboBox no se ejecuten los evt
            jcbMaterias.removeAllItems();
            jtxtNota.setText("");

            Alumno a = (Alumno) jcbAlumnos.getSelectedItem();
            if (a != null) {
                int id = a.getIdAlumno();
                cargarComboBoxMaterias(id);

                cargandoMaterias = false; //Acá una vez cargado el comboBox lo volvemos a setear en false para que ejecuten los evt ItemStateChanged

                if (jcbMaterias.getItemCount() > 0) {//forzar la seleccion del primer elemento del combo de materias
                    cargarNotaMateriaSelec();
                }
            }
        }
    }//GEN-LAST:event_jcbAlumnosItemStateChanged

    private void jcbMateriasItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jcbMateriasItemStateChanged
        // TODO add your handling code here:
        if(cargandoMaterias) return;
        
        if(evt.getStateChange() == ItemEvent.SELECTED && jcbMaterias.getSelectedIndex() != -1){
            cargarNotaMateriaSelec();
        }
    }//GEN-LAST:event_jcbMateriasItemStateChanged

    private void jtxtNotaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtxtNotaKeyTyped
        // TODO add your handling code here:
        String texto = jtxtNota.getText() + evt.getKeyChar();
        
        try{
            if (!texto.matches("\\d{0,2}(\\.\\d{0,2})?")) {
                evt.consume(); 
            }
            double valor = Double.parseDouble(texto);
            if (valor < 1 || valor > 10) {
                evt.consume();
            }
        }catch(NumberFormatException e){
            JOptionPane.showMessageDialog(this, "Ingrese una nota del 1 al 10 con hasta 2 decimales", "Entrada inválida", JOptionPane.WARNING_MESSAGE);
        }
        
    }//GEN-LAST:event_jtxtNotaKeyTyped


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
