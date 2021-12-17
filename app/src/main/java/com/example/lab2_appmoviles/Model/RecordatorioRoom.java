package com.example.lab2_appmoviles.Model;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import com.example.lab2_appmoviles.utils.DateConverter;

import java.util.Date;
@Entity (tableName = "recordatorios")
@TypeConverters (DateConverter.class)
public class RecordatorioRoom {
    @ColumnInfo(name = "id")
    @PrimaryKey(autoGenerate = true)
    private int id;
    @ColumnInfo(name = "texto")
    private String texto;
    @ColumnInfo(name = "fecha")
    private Date fecha;
    public String getTexto() {
        return texto;
    }
    public void setTexto(String texto) {
        this.texto = texto;
    }
    public Date getFecha() {
        return fecha;
    }
    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public Recordatorio toRecordatorio(){
        return new Recordatorio(texto,fecha);
    }
    public static RecordatorioRoom toRecordatorioRoom(Recordatorio r){
        RecordatorioRoom rec= new RecordatorioRoom();
        rec.setFecha(r.getFecha());
        rec.setTexto(r.getTexto());
        return rec;
    }
}
