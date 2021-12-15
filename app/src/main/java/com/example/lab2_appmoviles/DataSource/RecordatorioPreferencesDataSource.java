package com.example.lab2_appmoviles.DataSource;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;

import com.example.lab2_appmoviles.Model.Recordatorio;
import com.example.lab2_appmoviles.utils.RecordatorioDataSource;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class RecordatorioPreferencesDataSource implements RecordatorioDataSource {

    private final SharedPreferences sharedPreferences;
    public RecordatorioPreferencesDataSource(final Context context) {
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
    }

    @Override
    public void guardarRecordatorio(Recordatorio recordatorio, GuardarRecordatorioCallback callback) {
        Integer id = proximoId();
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("RecordatorioTexto" + id.toString(),recordatorio.getTexto());
        editor.putLong("RecordatorioFecha" + id.toString(),recordatorio.getFecha().getTime());
        editor.commit();
        callback.resultado(true);
    }

    @Override
    public List<Recordatorio> recuperarRecordatorios() {
        List<Recordatorio> recordatorios = new ArrayList<Recordatorio>();

        Integer cantidad = proximoId();

        for (Integer i = 0 ; i<cantidad ; i++){
            Recordatorio r = new Recordatorio(
                    sharedPreferences.getString("RecordatorioTexto" + i.toString(),""),
                    new Date(sharedPreferences.getLong("RecordatorioFecha" + i.toString(),0))
            );
            recordatorios.add(r);
        }
        return  recordatorios;
    }

    @Override
    public Integer proximoId() {
        Integer id;
        SharedPreferences.Editor editor = sharedPreferences.edit();
        id = sharedPreferences.getInt("IdRecordatorio",-1);
        if(id == -1){
            editor.putInt("IdRecordatorio",1);
            editor.commit();
            return 0;
        } else {
            editor.putInt("IdRecordatorio",(id + 1));
            editor.commit();
            Log.i("TAG", "proximoId: " + id.toString());
            return id;

        }
    }


}
