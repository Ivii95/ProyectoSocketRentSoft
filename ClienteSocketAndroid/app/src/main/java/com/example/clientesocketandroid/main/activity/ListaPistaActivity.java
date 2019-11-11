package com.example.clientesocketandroid.main.activity;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.clientesocketandroid.R;
import com.example.clientesocketandroid.data.PistaRepository;
import com.example.clientesocketandroid.main.adapter.PistaAdapter;
import Modelos.*;

public class ListaPistaActivity extends AppCompatActivity {

    RecyclerView pistasView = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lista_pistas_layuot);

        pistasView = findViewById(R.id.listaView);
        pistasView.setLayoutManager(new LinearLayoutManager(this));
        pistasView.setAdapter( new PistaAdapter(PistaRepository.pistas(),this));

    }

}
