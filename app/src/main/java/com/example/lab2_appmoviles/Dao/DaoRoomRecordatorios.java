package com.example.lab2_appmoviles.Dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.lab2_appmoviles.Model.Recordatorio;

import java.util.List;

@Dao
public interface DaoRoomRecordatorios {
    @Insert
    void insertRecordatorio(Recordatorio r);
    @Query("SELECT * FROM recordatorios")
    List<Recordatorio> getRecordatorios();
}
