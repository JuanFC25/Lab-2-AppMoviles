package com.example.lab2_appmoviles.utils;

import com.example.lab2_appmoviles.Model.Recordatorio;

import java.util.List;

public interface RecordatorioDataSource {
    //interface GuardarRecordatorioCallback {
    //    void resultado(final boolean exito);
    //}

    //interface RecuperarRecordatorioCallback {
    //    void resultado(final boolean exito, final List<Recordatorio> recordatorios);
    //}

    Boolean guardarRecordatorio(final Recordatorio recordatorio);
    //void recuperarRecordatorios(final RecuperarRecordatorioCallback callback);
    List<Recordatorio> recuperarRecordatorios();


    Boolean borrarRecordatorios();
}
