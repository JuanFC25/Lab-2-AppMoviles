package com.example.lab2_appmoviles.DataBase;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.lab2_appmoviles.Dao.DaoRoomRecordatorios;
import com.example.lab2_appmoviles.DataSource.RecordatorioRoomDataSource;
import com.example.lab2_appmoviles.Model.Recordatorio;

@Database(entities = {Recordatorio.class}, version = 3)
public abstract class RecordatorioRoomDataBase extends RoomDatabase {
    public abstract DaoRoomRecordatorios getDaoRoomRecordatorios();
}
