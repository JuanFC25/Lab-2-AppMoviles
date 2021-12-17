package com.example.lab2_appmoviles.DataBase;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.lab2_appmoviles.Dao.DaoRoomRecordatorios;
import com.example.lab2_appmoviles.Model.RecordatorioRoom;

@Database(entities = {RecordatorioRoom.class}, version = 3)
public abstract class RecordatorioRoomDataBase extends RoomDatabase {
    public abstract DaoRoomRecordatorios getDaoRoomRecordatorios();
}
