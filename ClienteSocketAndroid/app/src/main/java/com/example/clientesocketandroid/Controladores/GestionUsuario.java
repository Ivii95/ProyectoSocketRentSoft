/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.clientesocketandroid.Controladores;

import android.widget.Toast;

import java.io.IOException;
import Modelos.*;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import static com.example.clientesocketandroid.Controladores.Controlador.flujoObjEntrada;
import static com.example.clientesocketandroid.Controladores.Controlador.flujoObjSalida;
import static com.example.clientesocketandroid.Controladores.Controlador.flujo_entrada;
import static com.example.clientesocketandroid.Controladores.Controlador.flujo_salida;

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
                Toast.makeText(null, "Se ha insertado", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(null, "No se ha insertado", Toast.LENGTH_SHORT).show();
            }
        } catch (IOException ex) {

        }
    }

    public void gestionBorrarUsuario(int id) {
        try {
            flujo_salida.writeUTF(BORRAR_USUARIO);
            flujoObjSalida.writeObject(id);
            if (flujoObjEntrada.readBoolean()) {
                Toast.makeText(null, "Se ha borrado", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(null, "No ha borrado", Toast.LENGTH_SHORT).show();
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
                Toast.makeText(null, "Se ha actualizado", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(null, "No se ha actualizado", Toast.LENGTH_SHORT).show();
            }
        } catch (IOException ex) {
            Logger.getLogger(GestionUsuario.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
