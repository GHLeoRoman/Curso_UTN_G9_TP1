package modelos.Persona;

import modelos.Pronostico.Pronostico;

import java.util.List;

public class Persona {

    private String idPersona;
    private String nombre;
    private int puntosextra;

    public Persona(String idPersona, String nombre, int puntosextra) {
        this.idPersona = idPersona;
        this.nombre = nombre;
        this.puntosextra = puntosextra;
    }

    public String getIdPersona() {
        return idPersona;
    }

    public void setIdPersona(String idPersona) {
        this.idPersona = idPersona;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getPuntosextra() {
        return puntosextra;
    }

    public void setPuntosextra(int puntosextra) {
        this.puntosextra = puntosextra;
    }

    public Persona(String nombre, int puntos) {
        this.nombre = nombre;
    }
}
