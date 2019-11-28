package com.example.clientesocketandroid.data;

import android.util.Log;

import com.example.clientesocketandroid.Controladores.GestionAlquiler;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import Modelos.*;

import static com.example.clientesocketandroid.main.adapter.PistaAdapter.pistaSelec;

public class AlquilerRepository {

    static public List<Alquiler> getHorariosByNum() {
        ArrayList<Alquiler> alquileres = null;
        ArrayList<Alquiler> horas = null;
        ArrayList<Alquiler> horasReales = new ArrayList<>();
        GestionAlquiler GA = new GestionAlquiler();
        horas = GA.cargarHorasDelDia();
        alquileres = GA.gestionListarAlquileres();
        for (int i = 0; i < horas.size(); i++) {
            if (horas.get(i).p.num == pistaSelec.getNum()) {//NUMERO DE PISTA
                if (horas.get(i).getDia().equals(LocalDate.now())) {
                    if (horas.get(i).getHoraInicio().getHour() > LocalTime.now().getHour() + 1) {
                        if (alquileres.isEmpty()) {
                            horasReales.add(horas.get(i));
                        } else {
                            for (int j = 0; j < alquileres.size(); j++) {
                                if (horas.get(i).dia.equals(alquileres.get(j).dia)) {//DIA DE HOY
                                    if (horas.get(i).getHoraInicio().getHour() != alquileres.get(j).getHoraInicio().getHour()) {
                                        horasReales.add(horas.get(i));
                                        j = alquileres.size();
                                    }
                                } else {
                                    horasReales.add(horas.get(i));
                                    j = alquileres.size();
                                }
                            }
                        }
                    }
                } else {
                    if (alquileres.isEmpty()) {
                        horasReales.add(horas.get(i));
                    } else {
                        for (int j = 0; j < alquileres.size(); j++) {
                            if (GA.tomorrow.equals(alquileres.get(j).dia)) {//DIA DE MAÑANA
                                if (!horas.get(i).getHoraInicio().equals(alquileres.get(j).getHoraInicio())) {
                                    horasReales.add(horas.get(i));
                                    j = alquileres.size();
                                }
                            }
                        }
                    }
                }
            }
        }
        return horasReales;
    }
}
