package com.example.clientesocketandroid.main.activity;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.clientesocketandroid.Controladores.Controlador;
import com.example.clientesocketandroid.R;


public class MainActivity extends AppCompatActivity {

    public static Controlador c=new Controlador();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.start_layout);
    }

    public void mainViewOnClick(View view){
        setContentView(R.layout.main_activity);
        getSupportFragmentManager().beginTransaction().commit();
        c.inicializarConexiones();
    }

}
