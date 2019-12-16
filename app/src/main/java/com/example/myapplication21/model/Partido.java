package com.example.myapplication21.model;

public class Partido {
    String equipoA, equipoB, hora, resultado, jornada, fecha;

    public Partido(String equipoA, String equipoB, String hora, String resultado, String jornada, String fecha) {
        this.equipoA = equipoA;
        this.equipoB = equipoB;
        this.hora = hora;
        this.resultado = resultado;
        this.jornada = jornada;
        this.fecha = fecha;
    }

    public String getEquipoA() {
        return equipoA;
    }

    public void setEquipoA(String equipoA) {
        this.equipoA = equipoA;
    }

    public String getEquipoB() {
        return equipoB;
    }

    public void setEquipoB(String equipoB) {
        this.equipoB = equipoB;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public String getResultado() {
        return resultado;
    }

    public void setResultado(String resultado) {
        this.resultado = resultado;
    }

    public String getJornada() {
        return jornada;
    }

    public void setJornada(String jornada) {
        this.jornada = jornada;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }
}
