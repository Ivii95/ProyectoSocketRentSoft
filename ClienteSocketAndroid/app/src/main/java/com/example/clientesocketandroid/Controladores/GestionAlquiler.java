/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.clientesocketandroid.Controladores;

import android.os.Build;
import android.widget.Toast;

import androidx.annotation.RequiresApi;

import static com.example.clientesocketandroid.Controladores.Controlador.flujoObjEntrada;
import static com.example.clientesocketandroid.Controladores.Controlador.flujoObjSalida;
import static com.example.clientesocketandroid.Controladores.Controlador.flujo_entrada;
import static com.example.clientesocketandroid.Controladores.Controlador.flujo_salida;
import static com.example.clientesocketandroid.Controladores.Controlador.usuarioRegistrado;
import static com.example.clientesocketandroid.main.adapter.PistaAdapter.pistaSelec;

import java.io.IOException;
import java.time.DateTimeException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

import Modelos.*;

/**
 * @author Iván
 */
@RequiresApi(api = Build.VERSION_CODES.O)
public class GestionAlquiler {


    public LocalTime nowTime = LocalTime.now();
    public LocalDate ahora = LocalDate.now();
    public LocalDate tomorrow = ahora.plus(1, ChronoUnit.DAYS);
    DateTimeFormatter formatter = DateTimeFormatter.ISO_TIME;

    public ArrayList<Alquiler> cargarHorasDelDia() {
        ArrayList<Alquiler> horas = new ArrayList<>();
        try {
            int hora = 9;
            for (int i = 0; i < 14; i++) {
                Alquiler h = new Alquiler();
                h.setUsu(usuarioRegistrado);
                h.setP(pistaSelec);
                h.setHoraInicio(LocalTime.of(hora, 00));
                hora++;
                h.setHoraFin(LocalTime.of(hora, 00));
                h.setDia(ahora);
                horas.add(h);
            }
            hora = 9;
            for (int i = 0; i < 14; i++) {
                Alquiler h = new Alquiler();
                h.setUsu(usuarioRegistrado);
                h.setP(pistaSelec);
                h.setHoraInicio(LocalTime.of(hora, 00));
                hora++;
                h.setHoraFin(LocalTime.of(hora, 00));
                h.setDia(tomorrow);
                horas.add(h);
            }
        } catch (DateTimeException e) {
            e.printStackTrace();
        }
        return horas;
    }

    public ArrayList<Alquiler> gestionListarAlquileres() {
        ArrayList<Alquiler> alquiRecibido = new ArrayList<>();
        ArrayList<Alquiler> alquileres = new ArrayList<>();
        try {
            flujo_salida.writeUTF(Protocolo.LISTAR_ALQUILERES);
            alquiRecibido = (ArrayList<Alquiler>) flujoObjEntrada.readObject();
            if (!alquiRecibido.isEmpty()) {
                for (int i = 0; i < alquiRecibido.size(); i++) {
                    if (alquiRecibido.get(i).getDia().equals(ahora) || alquiRecibido.get(i).getDia().equals(tomorrow)) {
                        if (alquiRecibido.get(i).p.num == pistaSelec.num) {
                            alquileres.add(alquiRecibido.get(i));//Añadimos el alquiler del dia de hoy y de mañana que son los que se pueden contratar.
                        }
                    }
                }
            }
        } catch (ClassNotFoundException e) {
            System.out.println("Error al obtener la clase al listar alquileres");
        } catch (IOException e) {
            System.out.println("Error de IO al listar alquileres");
        } catch (Exception e) {

        }
        return alquileres;
    }

    /*public ArrayList<Alquiler> gestionListarTodosAlquileres() {
        ArrayList<Alquiler> alquileres = new ArrayList<>();
        try {
            flujo_salida.writeUTF(Protocolo.LISTAR_ALQUILERES);
            alquileres = (ArrayList<Alquiler>) flujoObjEntrada.readObject();
        } catch (ClassNotFoundException e) {
            System.out.println("Error al obtener la clase al listar alquileres");
        } catch (IOException e) {
            System.out.println("Error de IO al listar alquileres");
        } catch (Exception e) {

        }
        return alquileres;
    }*/
    public void gestionInsertarAlquiler(Alquiler alqui) {
        try {
            flujo_salida.writeUTF(Protocolo.INSERTAR_ALQUILER);
            flujoObjSalida.writeObject(alqui);
            boolean insertado = flujo_entrada.readBoolean();
            if (insertado) {
                //Toast.makeText(, "Se ha insertado el alquiler", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(null, "No se ha insertado el alquiler", Toast.LENGTH_SHORT).show();
            }
        } catch (IOException e) {
            Toast.makeText(null, "Error al insertar alquiler en el servidor", Toast.LENGTH_SHORT).show();
            System.out.println(e.getMessage());
        }
    }

    public void gestionBorrarAlquiler(int id) {
        try {
            flujo_salida.writeUTF(Protocolo.BORRAR_ALQUILER);
            flujo_salida.writeInt(id);
            if (flujoObjEntrada.readBoolean()) {
                Toast.makeText(null, "Se ha borrado", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(null, "No se ha borrado", Toast.LENGTH_SHORT).show();
            }
        } catch (IOException e) {

        }
    }

    public void gestionActualizarAlquiler(Alquiler alqui) {
        try {
            flujo_salida.writeUTF(Protocolo.ACTUALIZAR_ALQUILER);
            flujoObjSalida.writeObject(alqui);
            flujoObjSalida.writeObject(alqui.getId());
            boolean actualizado = flujoObjEntrada.readBoolean();
            if (actualizado) {
                Toast.makeText(null, "Se ha actualizado", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(null, "No se ha actualizado", Toast.LENGTH_SHORT).show();
            }
        } catch (IOException ex) {
            Logger.getLogger(GestionAlquiler.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
