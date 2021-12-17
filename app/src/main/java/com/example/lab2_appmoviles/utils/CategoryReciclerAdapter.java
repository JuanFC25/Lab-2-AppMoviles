package com.example.lab2_appmoviles.utils;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lab2_appmoviles.Model.Recordatorio;
import com.example.lab2_appmoviles.R;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;


class CategoryRecyclerAdapter
        extends RecyclerView.Adapter<CategoryRecyclerAdapter.ViewHolder>
        implements View.OnClickListener {

    private List<Recordatorio> mDataset;
    private  View.OnClickListener listener;
    int[] colores;

    public static class ViewHolder extends RecyclerView.ViewHolder{
        TextView recordatorioTitulo;
        TextView recordatorio;
        TextView fecha;
        CardView cd;
        View v;
        Boolean pulsado;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            v = itemView;
            recordatorioTitulo = itemView.findViewById(R.id.tituloRecordatorio);
            recordatorio = itemView.findViewById(R.id.textoRecordatorio);
            fecha = itemView.findViewById(R.id.fechaRecordatorio);
            cd = itemView.findViewById(R.id.cardView);
            pulsado = false;
        }


    }

    public CategoryRecyclerAdapter(List<Recordatorio> recordatorios,int[] color){
        colores = color;
        mDataset = new ArrayList<Recordatorio>();
        int position;
        int cantCiclos = recordatorios.size();
        for (int j = 0; j < cantCiclos ; j++) {
            Recordatorio menor = recordatorios.get(0);
            position=0;
            for(int i =1 ; i<recordatorios.size();i++){
                if (recordatorios.get(i).getFecha().getTime()<menor.getFecha().getTime()){
                    menor = recordatorios.get(i);
                    position=i;
                }
            }
            mDataset.add(menor);
            recordatorios.remove(position);
        }
        Integer a =(mDataset.size());

        Log.i("Tamaño dataset",a.toString());
    }

    public void setOnClickListener(View.OnClickListener listener) {
        this.listener=listener;
    }

    @NonNull
    @Override
    public CategoryRecyclerAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fila_recordatorio,parent,false);
        v.setOnClickListener(this);
        return new ViewHolder(v);
    }

    @SuppressLint("ResourceAsColor")
    @Override
    public void onBindViewHolder(@NonNull CategoryRecyclerAdapter.ViewHolder holder, int position) {
        Long today =Calendar.getInstance().getTimeInMillis();

        Recordatorio unRecordatorio = mDataset.get(position);
        holder.recordatorio.setText(unRecordatorio.getTexto());
        holder.fecha.setText(unRecordatorio.toStringFecha());
        if(unRecordatorio.getFecha().getTime()>today){
            holder.cd.setCardBackgroundColor(colores[2]);
        }else {
            holder.cd.setCardBackgroundColor(colores[0]);
        }

        holder.cd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!holder.pulsado){
                    if(unRecordatorio.getFecha().getTime()>today){
                        holder.cd.setCardBackgroundColor(colores[3]);
                    }else {
                        holder.cd.setCardBackgroundColor(colores[1]);
                    }
                    LinearLayout.LayoutParams lparams =
                            new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                    holder.cd.setLayoutParams(lparams);
                    holder.pulsado=true;
                } else{
                    if(unRecordatorio.getFecha().getTime()>today){
                        holder.cd.setCardBackgroundColor(colores[2]);
                    }else {
                        holder.cd.setCardBackgroundColor(colores[0]);
                    }
                    LinearLayout.LayoutParams lparams =
                            new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, 173);
                    holder.cd.setLayoutParams(lparams);
                    holder.pulsado=false;
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }

    @Override
    public void onClick(View view) {
        if (listener != null) {
            listener.onClick(view);
        }
    }


}
