package com.example.clientesocketandroid.main.adapter;


import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.clientesocketandroid.Controladores.GestionAlquiler;
import com.example.clientesocketandroid.R;
import com.example.clientesocketandroid.main.activity.ListaPistaActivity;

import Modelos.*;

import java.text.BreakIterator;
import java.util.List;

public class HorarioAdapter extends RecyclerView.Adapter<HorarioAdapter.ViewHolder> {

    List<Alquiler> horasLista;
    Activity callerActivity;
    GestionAlquiler GA = new GestionAlquiler();

    public HorarioAdapter(List<Alquiler> horas, Activity callerActivity) {
        this.horasLista = horas;
        this.callerActivity = callerActivity;
    }

    @NonNull
    @Override
    public HorarioAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(
                LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.elemento_horario_layout, parent, false)
        );
    }

    @Override
    public void onBindViewHolder(@NonNull HorarioAdapter.ViewHolder holder, final int position) {
        holder.txtHoraInicio.setText(" Hora comienzo: ".concat(horasLista.get(position).getHoraInicio().toString()));
        holder.txtHoraFin.setText(" Hora finalizacion: ".concat(horasLista.get(position).getHoraFin().toString()));
        holder.txtFecha.setText(" Fecha: ".concat(horasLista.get(position).getDia().toString()));
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(callerActivity, "pista reservada " + horasLista.get(position).horaInicio, Toast.LENGTH_SHORT).show();
                GA.gestionInsertarAlquiler(horasLista.get(position));
                Intent in = new Intent(callerActivity, ListaPistaActivity.class);
                callerActivity.startActivity(in);
            }
        });
    }

    @Override
    public int getItemCount() {
        return horasLista.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        TextView txtHoraInicio = null;
        TextView txtHoraFin = null;
        TextView txtFecha = null;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtHoraInicio = itemView.findViewById(R.id.horaInicio);
            txtHoraFin = itemView.findViewById(R.id.horaFin);
            txtFecha = itemView.findViewById(R.id.fecha);
        }
    }
}
