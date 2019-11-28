package com.example.clientesocketandroid.main.adapter;


import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
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

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull HorarioAdapter.ViewHolder holder, final int position) {
        holder.txtHoraInicio.setText(" Hora comienzo: ".concat(horasLista.get(position).getHoraInicio().toString()));
        holder.txtHoraFin.setText(" Hora finalizacion: ".concat(horasLista.get(position).getHoraFin().toString()));
        holder.txtFecha.setText(" Fecha: ".concat(horasLista.get(position).getDia().toString()));
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder dialogo1 = new AlertDialog.Builder(callerActivity);
                dialogo1.setTitle("Señor/a: " + horasLista.get(position).usu.getNombre() + "-" + horasLista.get(position).getUsu().getApellidos());
                dialogo1.setMessage("¿ Seguro de que desea reservar la pista" + horasLista.get(position).p.num + " a las " + horasLista.get(position).horaInicio +
                        " el dia " + horasLista.get(position).getDia() + "?");
                dialogo1.setCancelable(false);
                dialogo1.setPositiveButton("Confirmar", new DialogInterface.OnClickListener() { //SII
                    public void onClick(DialogInterface dialogo1, int id) {
                        GA.gestionInsertarAlquiler(horasLista.get(position));
                        Toast.makeText(callerActivity, "reservada a las " + horasLista.get(position).horaInicio + " el dia " + horasLista.get(position).getDia()
                                + " en la pista" + horasLista.get(position).p.num, Toast.LENGTH_LONG).show();
                        Intent in = new Intent(callerActivity, ListaPistaActivity.class);
                        callerActivity.startActivity(in);
                    }
                });
                dialogo1.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() { //NOO
                    public void onClick(DialogInterface dialogo1, int id) {

                    }
                });
                dialogo1.show();
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
