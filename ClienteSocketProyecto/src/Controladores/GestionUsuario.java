/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores;

import static Controladores.Controlador.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import Modelos.Protocolo;
import Modelos.Usuario;

/**
 *
 * @author Iván
 */
public class GestionUsuario implements Protocolo {

    public GestionUsuario() {

    }

    public Usuario getUsuario(int id) {
        Usuario usu=new Usuario();
        try {
            flujo_salida.writeUTF(LISTAR_USUARIO);
            flujo_salida.writeInt(id);
            usu = (Usuario) flujoObjEntrada.readObject();
            
        } catch (ClassNotFoundException e) {
            System.out.println("Error al obtener la clase al listar");
        } catch (IOException e) {
            System.out.println("Error de SQL al listar");
        }
        return usu;
    }

    public ArrayList<Usuario> gestionListarUsuarios() {
        ArrayList<Usuario> usus = null;
        try {
            flujo_salida.writeUTF(LISTAR_USUARIOS);
            usus = (ArrayList<Usuario>) flujoObjEntrada.readObject();
        } catch (ClassNotFoundException e) {
            System.out.println("Error al obtener la clase al listar");
        } catch (IOException e) {
            System.out.println("Error de SQL al listar");
        }
        return usus;
    }

    public void gestionInsertarUsuario(Usuario usu) {
        try {
            flujo_salida.writeUTF(INSERTAR_USUARIO);
            flujoObjSalida.writeObject(usu);
            boolean insertado = flujoObjEntrada.readBoolean();
            if (insertado) {
                JOptionPane.showMessageDialog(null, "Se ha insertado");
            } else {
                JOptionPane.showMessageDialog(null, "No se ha insertado");
            }
        } catch (IOException ex) {

        }
    }

    public void gestionBorrarUsuario(int id) {
        try {
            flujo_salida.writeUTF(BORRAR_USUARIO);
            flujoObjSalida.writeObject(id);
            if (flujoObjEntrada.readBoolean()) {
                JOptionPane.showMessageDialog(null, "Se ha borrado");
            } else {
                JOptionPane.showMessageDialog(null, "No se ha borrado");
            }
        } catch (IOException e) {

        }
    }

    public void gestionActualizarUsuario(Usuario usu) {
        try {
            flujo_salida.writeUTF(ACTUALIZAR_USUARIO);
            flujoObjSalida.writeObject(usu);
            boolean actualizado = flujo_entrada.readBoolean();
            if (actualizado) {
                JOptionPane.showMessageDialog(null, "Se ha actualizado");
            } else {
                JOptionPane.showMessageDialog(null, "No se ha actualizado");
            }
        } catch (IOException ex) {
            Logger.getLogger(GestionUsuario.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
