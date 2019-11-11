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
import static com.example.clientesocketandroid.Controladores.Controlador.flujo_salida;

import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import Modelos.*;

/**
 * @author DisenoWeb
 */
@RequiresApi(api = Build.VERSION_CODES.O)
public class GestionPistas {

    ArrayList<Pista> pistas = new ArrayList<>();
    GestionAlquiler GA = new GestionAlquiler();

    /*public void crearPistas() {
        //Primero cargamos las horas del dia de hoy y de mañana para alquilar.
        GA.cargarHorasDelDia();
    }*/
    /*
    public DefaultTableModel getModeloReserva(int tabla, ArrayList<Alquiler> alquileres) {
        try {
            modeloPistas.setRowCount(0);
            modeloPistas.addRow(new Object[]{"----------------- " + GA.ahora + " -----------------", "----------------- " + GA.ahora + " -----------------", "----------------- " + GA.ahora + " -----------------"});
            for (int i = 0; i < GA.horas_inicios.length; i++) {
                if (GA.horas_inicios[i].getHour() > GA.nowTime.getHour()) {//Comprobamos que las horas sean mayor que la actual
                    if (alquileres.isEmpty()) {
                        modeloPistas.addRow(new Object[]{GA.ahora, GA.horas_inicios[i], GA.horas_finales[i]});
                    } else {
                        for (int j = 0; j < alquileres.size(); j++) {
                            if (alquileres.get(j).p.num == tabla) {
                                if (!alquileres.get(j).horaInicio.equals(GA.horas_inicios[i])) {
                                    modeloPistas.addRow(new Object[]{GA.ahora, GA.horas_inicios[i], GA.horas_finales[i]});
                                    j = alquileres.size();//Hacemos que una vez entre en la condicion se salga para que no se repitan los valores
                                }
                            } else {
                                modeloPistas.addRow(new Object[]{GA.ahora, GA.horas_inicios[i], GA.horas_finales[i]});
                            }
                        }
                    }
                }
            }
            modeloPistas.addRow(new Object[]{"----------------- " + GA.tomorrow + " -----------------", "----------------- " + GA.ahora + " -----------------", "----------------- " + GA.ahora + " -----------------"});
            for (int i = 0; i < GA.horas_inicios.length; i++) {
                if (alquileres.isEmpty()) {
                    modeloPistas.addRow(new Object[]{GA.tomorrow, GA.horas_inicios[i], GA.horas_finales[i]});
                } else {
                    for (int j = 0; j < alquileres.size(); j++) {
                        if (alquileres.get(j).p.num == tabla) {
                            if (!alquileres.get(j).horaInicio.equals(GA.horas_inicios[i])) {
                                modeloPistas.addRow(new Object[]{GA.tomorrow, GA.horas_inicios[i], GA.horas_finales[i]});
                                j = alquileres.size();//Hacemos que una vez entre en la condicion se salga para que no se repitan los valores
                            }
                        } else {
                            modeloPistas.addRow(new Object[]{GA.ahora, GA.horas_inicios[i], GA.horas_finales[i]});
                        }
                    }
                }
            }

        } catch (Exception e) {
            modeloPistas = null;
        }
        return modeloPistas;
    }
*/
    /*
    public DefaultTableModel getModeloReservaHecha(int tabla, ArrayList<Alquiler> alquileres) {
        modeloPistasAlquiladas.setRowCount(0);
        for (int i = 0; i < GA.horas_inicios.length; i++) {
            if (GA.horas_inicios[i].getHour() > GA.nowTime.getHour()) {//Comprobamos que las horas sean mayor que la actual
                if (!alquileres.isEmpty()) {
                    for (int j = 0; j < alquileres.size(); j++) {
                        if (alquileres.get(j).p.num == tabla) {//Comprobamos que el numero de pista sea el mismo
                            if (alquileres.get(j).horaInicio.equals(GA.horas_inicios[i])) {//Comprobamos que las horas sean las mismas
                                modeloPistasAlquiladas.addRow(new Object[]{alquileres.get(j).dia, alquileres.get(j).horaInicio, alquileres.get(j).horaFin});
                            }
                        }
                    }
                }
            }
        }
        return modeloPistasAlquiladas;

    }*/

    public ArrayList<Pista> gestionListarPistas() {

        try {
            flujo_salida.writeUTF(Protocolo.LISTAR_PISTAS);
            //flujoObjSalida.writeObject(pistas);
            pistas = (ArrayList<Pista>) flujoObjEntrada.readObject();
        } catch (ClassNotFoundException e) {
            System.out.println("Error al obtener la clase al listar");
        } catch (IOException e) {
            System.out.println("Error de IO al listar pistas");
            System.out.println(e.getMessage());
        } catch (Exception e) {

        }
        return pistas;
    }

    public void gestionInsertarPista(Pista pista) {
        try {
            flujo_salida.writeUTF(0 + "");
            flujoObjSalida.writeObject(pista);
            boolean insertado = flujoObjEntrada.readBoolean();
            if (insertado) {
                Toast.makeText(null, "Se ha insertado", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(null, "No se ha insertado", Toast.LENGTH_SHORT).show();
            }
        } catch (IOException ex) {

        }

        //gestionListarMetas();
    }

    public void gestionBorrarPista(int id) {
        try {
            flujo_salida.writeUTF(Protocolo.BORRAR_ALQUILER);
            flujoObjSalida.writeObject(id);

            if (flujoObjEntrada.readBoolean()) {
                Toast.makeText(null, "Se ha borrado", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(null, "No ha borrado", Toast.LENGTH_SHORT).show();
            }
        } catch (IOException e) {

        }
        //gestionListarMetas();
    }

    public void gestionActualizarPista(Pista pista) {
        try {
            flujo_salida.writeUTF(0 + "");
            flujoObjSalida.writeObject(pista);
            flujoObjSalida.writeObject(pista.getId());
            boolean actualizado = flujoObjEntrada.readBoolean();
            if (actualizado) {
                Toast.makeText(null, "Se ha actualizado", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(null, "No se ha actualizado", Toast.LENGTH_SHORT).show();
            }
        } catch (IOException ex) {
            Logger.getLogger(GestionAlquiler.class
                    .getName()).log(Level.SEVERE, null, ex);
        }

    }
}
