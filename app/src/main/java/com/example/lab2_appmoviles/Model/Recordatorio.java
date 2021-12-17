package com.example.lab2_appmoviles.Model;


import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;
public class Recordatorio {
    private String texto;
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

    public String toStringFecha(){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        return "Fecha: " + simpleDateFormat.format(this.fecha);
    }
}
