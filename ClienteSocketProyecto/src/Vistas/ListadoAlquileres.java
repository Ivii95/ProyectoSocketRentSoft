/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vistas;

import Controladores.GestionAlquiler;
import Controladores.UtilidadesPantalla;
import Modelos.Alquiler;
import Modelos.Usuario;
import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author DisenoWeb
 */
public class ListadoAlquileres extends javax.swing.JDialog {

    GestionAlquiler GA;
    Usuario usuListado;
    private ArrayList<Alquiler> alquileres;

    /**
     * Creates new form ListadoAlquileres
     *
     * @param parent
     * @param modal
     */
    public ListadoAlquileres(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
    }

    /**
     * Creates new form ListadoAlquileres
     *
     * @param parent
     * @param modal
     * @param usu
     */
    public ListadoAlquileres(java.awt.Frame parent, boolean modal, Usuario usu) {
        super(parent, modal);
        initComponents();
        UtilidadesPantalla.resolucionPantallaExterna(this);
        UtilidadesPantalla.centrarTablas(tablaAlquileres);
        usuListado = usu;
        GA = new GestionAlquiler();
        alquileres = GA.gestionListarTodosAlquileres();
        Titulo.setText(Titulo.getText() + " " + usuListado.getNombre() + "-" + usuListado.getApellidos());
        rellenarTabla();
    }

    private void rellenarTabla() {
        DefaultTableModel dtm = (DefaultTableModel) tablaAlquileres.getModel();
        Object[] columnas = new Object[dtm.getColumnCount()];
        for (int i = 0; i < alquileres.size(); i++) {
            columnas[0] = alquileres.get(i).p.num + "-" + alquileres.get(i).p.tipo;
            columnas[1] = alquileres.get(i).horaInicio;
            columnas[2] = alquileres.get(i).dia;
            dtm.addRow(columnas);
        }
        tablaAlquileres.setModel(dtm);
    }

    /**
     * This method is called from within the constructor to initialize the form. WARNING: Do NOT modify this code. The content of this method is always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        tablaAlquileres = new javax.swing.JTable();
        Titulo = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setBackground(new java.awt.Color(204, 255, 204));
        setResizable(false);

        tablaAlquileres.setAutoCreateRowSorter(true);
        tablaAlquileres.setBackground(new java.awt.Color(204, 255, 204));
        tablaAlquileres.setForeground(new java.awt.Color(0, 0, 51));
        tablaAlquileres.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Pista", "Hora", "Dia"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                true, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tablaAlquileres);
        if (tablaAlquileres.getColumnModel().getColumnCount() > 0) {
            tablaAlquileres.getColumnModel().getColumn(0).setResizable(false);
            tablaAlquileres.getColumnModel().getColumn(1).setResizable(false);
            tablaAlquileres.getColumnModel().getColumn(2).setResizable(false);
        }

        Titulo.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        Titulo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Titulo.setText("Usuario:");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 1200, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(Titulo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(1, 6, Short.MAX_VALUE)
                .addComponent(Titulo, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(6, 6, 6)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 650, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;

                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(ListadoAlquileres.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ListadoAlquileres.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ListadoAlquileres.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ListadoAlquileres.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                ListadoAlquileres dialog = new ListadoAlquileres(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Titulo;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tablaAlquileres;
    // End of variables declaration//GEN-END:variables

}
