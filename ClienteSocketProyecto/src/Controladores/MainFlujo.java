/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores;

import javax.swing.JOptionPane;
import Modelos.Usuario;
import Vistas.Tabla;

/**
 *
 * @author Iván
 */
public class MainFlujo {
    //Usuario que usa la app
    public static Usuario usuarioRegistrado;
    public static boolean userOn = false;
    public static void main(String args[]) {
        //Inicio del programa y declaracion de variables
        Controlador ctrl=new Controlador();
        ctrl.inicializarConexiones();
        do {
            ctrl.gestionLOG();
        } while (!userOn);
        
        if (usuarioRegistrado.getAdmin()) {
            Tabla tbl=new Tabla(null,true);
            tbl.setVisible(true);
        }else{
            JOptionPane.showMessageDialog(null,"Para acceder necesitas ser administrador");
        }
    }
}
