package com.example.lab2_appmoviles.DataSource;

import android.content.Context;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Room;

import com.example.lab2_appmoviles.Dao.DaoRoomRecordatorios;
import com.example.lab2_appmoviles.DataBase.RecordatorioRoomDataBase;
import com.example.lab2_appmoviles.Model.Recordatorio;
import com.example.lab2_appmoviles.utils.RecordatorioDataSource;

import java.util.List;


public class RecordatorioRoomDataSource implements RecordatorioDataSource {
    private DaoRoomRecordatorios dao;
    public RecordatorioRoomDataSource(Context context){
        RecordatorioRoomDataBase db= Room.databaseBuilder(context,RecordatorioRoomDataBase.class,"Lab_2")
                .allowMainThreadQueries()
                .fallbackToDestructiveMigration().build();
        dao=db.getDaoRoomRecordatorios();
    }
    @Override
    public void guardarRecordatorio(Recordatorio recordatorio, GuardarRecordatorioCallback callback) {
        dao.insertRecordatorio(recordatorio);
        callback.resultado(true);
    }

    public List<Recordatorio> recuperarRecordatorios() {
        return dao.getRecordatorios();
    }
}
