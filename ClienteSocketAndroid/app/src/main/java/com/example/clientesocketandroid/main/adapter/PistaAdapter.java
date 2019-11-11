package com.example.clientesocketandroid.main.adapter;


import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.clientesocketandroid.R;
import com.example.clientesocketandroid.main.activity.ListaHorarioActivity;
import Modelos.*;
import java.util.List;

public class PistaAdapter extends RecyclerView.Adapter<PistaAdapter.ViewHolder> {

    public List<Pista> pistas;
    public static Pista pistaSelec;
    Activity callerActivity;

    public PistaAdapter(List<Pista> pistas, Activity callerActivity){
        this.pistas = pistas;
        this.callerActivity = callerActivity;
    }

    @NonNull
    @Override
    public PistaAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(
                    LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.elemento_pista_layout, parent, false)
                );
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        holder.imgTipoPista.setImageResource(R.drawable.padel);//FIXME pa' cambiar la imagen poner un case con los tipos y segun el tipo cojer una imagen u otra
        final int numPista = pistas.get(position).getNum();
        holder.txtNumPista.setText(" número de pista: ".concat(pistas.get(position).getNum()+""));
        holder.txtTipoPista.setText(" tipo de pista: ".concat(pistas.get(position).getTipo()));


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clickInPista(numPista);
            }
        });
    }

    @Override
    public int getItemCount() {
        return pistas.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{

        ImageView imgTipoPista = null;
        TextView txtTipoPista = null;
        TextView txtNumPista = null;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imgTipoPista = itemView.findViewById(R.id.imgTipoPista);
            txtTipoPista = itemView.findViewById(R.id.tipoPista);
            txtNumPista = itemView.findViewById(R.id.numPista);

        }
    }

    public void clickInPista(int num){
        Intent in = new Intent(callerActivity, ListaHorarioActivity.class);
        for (int i = 0; i < pistas.size(); i++) {
            if(pistas.get(i).num==num){
                pistaSelec=pistas.get(i);
            }
        }
        callerActivity.startActivity(in);
    }

}
