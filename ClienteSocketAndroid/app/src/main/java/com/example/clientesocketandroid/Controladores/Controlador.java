/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.clientesocketandroid.Controladores;

import android.os.StrictMode;
import android.widget.Toast;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import Modelos.*;

/**
 * @author Ivii
 */
public class Controlador implements Protocolo {

    static Socket skCliente;
    private boolean salir = false;
    public String usuario = "";
    public String pass = "";
    public static ObjectInputStream flujoObjEntrada;
    public static ObjectOutputStream flujoObjSalida;
    public static DataInputStream flujo_entrada;
    public static DataOutputStream flujo_salida;

    public static Usuario usuarioRegistrado;

    public Controlador() {

    }

    public void inicializarConexiones() {
        StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder()
                .permitNetwork().build());
        try {

            skCliente = new Socket("192.168.1.100", 5000);
            flujoObjSalida = new ObjectOutputStream(skCliente.getOutputStream());
            flujoObjEntrada = new ObjectInputStream(skCliente.getInputStream());
            flujo_entrada = new DataInputStream(skCliente.getInputStream());
            flujo_salida = new DataOutputStream(skCliente.getOutputStream());
        } catch (IOException e) {
            //Toast.makeText(, "Error de conexion con el servidor.\nCompruebe su conexion a Internet.", Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }

    }

    public boolean gestionLOG(String usuario, String pass) {
        boolean userOn = false;
        try {
            //Login lg = new Login(null, true);
            //lg.setVisible(true);
            //usuario = lg.getUser();
            //pass = lg.getPass();
            flujo_salida.writeUTF(Protocolo.LOG);
            //Le envio el usuario
            flujo_salida.writeUTF(usuario);
            //Codifico la contraseña
            //pass=encriptaEnMD5(pass);
            //Le envio la contraseña
            flujo_salida.writeUTF(pass);
            //Espero confirmacion y la guardo en userOn
            userOn = flujo_entrada.readBoolean();
            if (userOn) {
                usuarioRegistrado = (Usuario) flujoObjEntrada.readObject();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return true;
    }

    public void gestionSalir() {
        try {
            flujoObjSalida.writeUTF(SALIR);
            System.exit(0);
        } catch (IOException e) {
            Toast.makeText(null, "Error al salir", Toast.LENGTH_SHORT).show();
        }

    }

    private static final char[] CONSTS_HEX = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};

    public static String encriptaEnMD5(String stringAEncriptar) {
        try {
            MessageDigest msgd = MessageDigest.getInstance("MD5");
            byte[] bytes = msgd.digest(stringAEncriptar.getBytes());
            StringBuilder strbCadenaMD5 = new StringBuilder(2 * bytes.length);
            for (int i = 0; i < bytes.length; i++) {
                int bajo = (int) (bytes[i] & 0x0f);
                int alto = (int) ((bytes[i] & 0xf0) >> 4);
                strbCadenaMD5.append(CONSTS_HEX[alto]);
                strbCadenaMD5.append(CONSTS_HEX[bajo]);
            }
            return strbCadenaMD5.toString();
        } catch (NoSuchAlgorithmException e) {
            return null;
        }
    }

    public void startReport() {
        /*
        try{
            JasperReport reporte;
            String path = "lib"+File.separator+"UsuariosPadel.jasper";
            reporte = (JasperReport) JRLoader.loadObjectFromFile(path);
            JasperPrint jprint = JasperFillManager.fillReport(path, null,conexion);
            JasperViewer viewer = new JasperViewer(jprint,false);
            viewer.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
            viewer.setVisible(true);
        }catch(Exception e){
            javax.swing.JOptionPane.showMessageDialog(null, e);
        }
         */
    }

}
