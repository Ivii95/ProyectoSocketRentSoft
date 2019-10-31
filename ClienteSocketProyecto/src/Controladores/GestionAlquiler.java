/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores;

import static Controladores.Controlador.flujoObjEntrada;
import static Controladores.Controlador.flujoObjSalida;
import static Controladores.Controlador.flujo_entrada;
import static Controladores.Controlador.flujo_salida;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import Modelos.Alquiler;
import Modelos.Protocolo;
import static Modelos.Protocolo.ACTUALIZAR_ALQUILER;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

/**
 *
 * @author Iván
 */
public class GestionAlquiler {

    public LocalTime[] horas_inicios = new LocalTime[14];
    public LocalTime[] horas_finales = new LocalTime[14];
    public ArrayList<LocalTime> horasOcupadas = new ArrayList<>();
    public LocalTime nowTime = LocalTime.now();
    public LocalDate ahora = LocalDate.now();
    public LocalDate tomorrow = ahora.plus(1, ChronoUnit.DAYS);
    DateTimeFormatter formatter = DateTimeFormatter.ISO_TIME;

    public void cargarHorasDelDia() {
        int hora = 9;
        for (int i = 0; i < 14; i++) {
            if (hora < 10) {
                horas_inicios[i] = LocalTime.parse("0" + hora + ":00", formatter);
                hora++;
                horas_finales[i] = LocalTime.parse(hora + ":00", formatter);
            } else {
                horas_inicios[i] = LocalTime.parse(hora + ":00", formatter);
                hora++;
                horas_finales[i] = LocalTime.parse(hora + ":00", formatter);
            }
        }
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
                        alquileres.add(alquiRecibido.get(i));//Añadimos el alquiler del dia de hoy y de mañana que son los que se pueden contratar.
                        for (int j = 0; j < horas_inicios.length; j++) {
                            if (alquiRecibido.get(i).horaInicio.equals(horas_inicios[j])) {
                                horasOcupadas.add(horas_inicios[j]);
                            }
                        }
                    }
                }
            }
        } catch (ClassNotFoundException e) {
            System.out.println("Error al obtener la clase al listar alquileres");
        } catch (IOException e) {
            System.out.println("Error de IO al listar alquileres");
        }
        return alquileres;
    }

    public void gestionInsertarAlquiler(Alquiler alqui) {
        try {
            flujo_salida.writeUTF(Protocolo.INSERTAR_ALQUILER);
            flujoObjSalida.writeObject(alqui);
            boolean insertado = flujo_entrada.readBoolean();
            if (insertado) {
                JOptionPane.showMessageDialog(null, "Se ha insertado el alquiler");
            } else {
                JOptionPane.showMessageDialog(null, "No se ha insertado el alquiler");
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error al insertar alquiler en el servidor");
            System.out.println(e.getMessage());
        }
    }

    public void gestionBorrarAlquiler(int id) {
        try {
            flujo_salida.writeUTF(Protocolo.BORRAR_ALQUILER);
            flujo_salida.writeInt(id);
            if (flujoObjEntrada.readBoolean()) {
                JOptionPane.showMessageDialog(null, "Se ha borrado");
            } else {
                JOptionPane.showMessageDialog(null, "No se ha borrado");
            }
        } catch (IOException e) {

        }
        //gestionListarMetas();
    }

    public void gestionActualizarAlquiler(Alquiler alqui) {
        try {
            flujo_salida.writeUTF(ACTUALIZAR_ALQUILER);
            flujoObjSalida.writeObject(alqui);
            flujoObjSalida.writeObject(alqui.getId());
            boolean actualizado = flujoObjEntrada.readBoolean();
            if (actualizado) {
                JOptionPane.showMessageDialog(null, "Se ha actualizado");
            } else {
                JOptionPane.showMessageDialog(null, "No se ha actualizado");
            }
        } catch (IOException ex) {
            Logger.getLogger(GestionAlquiler.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
