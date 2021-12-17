package com.example.lab2_appmoviles.Model;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import com.example.lab2_appmoviles.utils.DateConverter;

import java.util.Date;
import java.util.Objects;
@Entity (tableName = "recordatorios")
@TypeConverters(DateConverter.class)
public class Recordatorio {
    @PrimaryKey (autoGenerate = true)
    @NonNull
    private int id;
    @ColumnInfo(name = "texto")
    private String texto;
    @ColumnInfo(name = "fecha")
    private Date fecha;

    public Recordatorio(final String texto, final Date fecha) {
        this.texto = texto;
        this.fecha = fecha;
    }

    public String getTexto() {
        return texto;
    }
    public void setTexto(final String texto) {
        this.texto = texto;
    }
    public Date getFecha() {
        return fecha;
    }
    public void setFecha(final Date fecha) {
        this.fecha = fecha;
    }
    public void setId(int id) {
        this.id = id;
    }
    public int getId() {
        return id;
    }

    @Override
    public boolean equals(final Object other) {
        if (this == other) {
            return true;
        }
        if (other == null || !getClass().equals(other.getClass())) {
            return false;
        }
        final Recordatorio that = (Recordatorio) other;
        return Objects.equals(this.texto, that.texto) && Objects.equals(this.fecha, that.fecha);
    }

    @Override
    public int hashCode() {
        return Objects.hash(texto) + Objects.hash(fecha);
    }

    @Override
    public String toString() {
        return "Recordatorio = Texto:" + this.getTexto() + "  Fecha:" + this.getFecha().toString();
    }

}
