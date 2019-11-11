package com.example.clientesocketandroid.main.activity;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.clientesocketandroid.Controladores.GestionAlquiler;
import com.example.clientesocketandroid.R;
import com.example.clientesocketandroid.data.AlquilerRepository;
import com.example.clientesocketandroid.main.adapter.HorarioAdapter;


public class ListaHorarioActivity extends AppCompatActivity {

    RecyclerView horariosView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lista_horario_layuot);


        horariosView = findViewById(R.id.listaHorarioView);
        horariosView.setLayoutManager(new LinearLayoutManager(this));
        horariosView.setAdapter(new HorarioAdapter(AlquilerRepository.getHorariosByNum(), this));
    }
}
