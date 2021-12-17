package com.example.lab2_appmoviles.DataSource;

import android.annotation.SuppressLint;
import android.content.Context;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Room;

import com.example.lab2_appmoviles.Dao.DaoRoomRecordatorios;
import com.example.lab2_appmoviles.DataBase.RecordatorioRoomDataBase;
import com.example.lab2_appmoviles.Model.Recordatorio;
import com.example.lab2_appmoviles.Model.RecordatorioRoom;
import com.example.lab2_appmoviles.utils.RecordatorioDataSource;

import java.util.List;
import java.util.stream.Collectors;


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
        dao.insertRecordatorio(RecordatorioRoom.toRecordatorioRoom(recordatorio));
        callback.resultado(true);
    }

    @SuppressLint("NewApi")
    public List<Recordatorio> recuperarRecordatorios() {

        return dao.getRecordatorios().stream().map(RecordatorioRoom::toRecordatorio).collect(Collectors.toList());
    }
    public Boolean borrarRecordatorios(){
        dao.borrarRecordatorios();
        return true;
    }
}
