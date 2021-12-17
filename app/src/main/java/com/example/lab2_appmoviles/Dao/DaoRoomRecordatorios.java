package com.example.lab2_appmoviles.Dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.lab2_appmoviles.Model.Recordatorio;
import com.example.lab2_appmoviles.Model.RecordatorioRoom;

import java.util.List;

@Dao
public interface DaoRoomRecordatorios {
    @Insert
    void insertRecordatorio(RecordatorioRoom r);
    @Query("SELECT * FROM recordatorios")
    List<RecordatorioRoom> getRecordatorios();
    @Query("DELETE FROM recordatorios")
    void borrarRecordatorios();
}
