package com.example.lab2_appmoviles.Repository;

import android.util.Log;

import com.example.lab2_appmoviles.Model.Recordatorio;
import com.example.lab2_appmoviles.utils.RecordatorioDataSource;

import java.util.List;

public class RecordatorioRepository {

    private final RecordatorioDataSource datasource;
    public RecordatorioRepository(final RecordatorioDataSource datasource) {
        this.datasource = datasource;
    }

    public Boolean guardarRecordatorio(Recordatorio record){
        return datasource.guardarRecordatorio(record);
    }

    public List<Recordatorio> recuperarRecordatorios(){
        List<Recordatorio> recordatoriosRecuperados = null;
        recordatoriosRecuperados = datasource.recuperarRecordatorios();
        return recordatoriosRecuperados;
    }

    public Boolean borrarRecordatorios(){
        Boolean res = datasource.borrarRecordatorios();
        return res;
    }
}
