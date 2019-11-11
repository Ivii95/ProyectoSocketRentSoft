package com.example.clientesocketandroid.data;

import com.example.clientesocketandroid.Controladores.GestionPistas;

import java.util.Arrays;
import java.util.List;

import Modelos.Pista;

public class PistaRepository {

    //TODO er ivii

    public static List<Pista> pistas(){
        List<Pista> arraypistas =null;
        GestionPistas GP=new GestionPistas();
        arraypistas=GP.gestionListarPistas();
        return arraypistas;
    }

}
