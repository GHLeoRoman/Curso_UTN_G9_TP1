package modelos.Pronostico;

import modelos.EnumResultado;
import modelos.Partido.Partido;
import modelos.Persona.Persona;


public class Pronostico {

    private Persona persona ;
    private Partido partido;
    private EnumResultado resultado; // siempre se pone el resultado del equipo1
    private  boolean valor;
    private Integer puntos;

     public Pronostico( Persona persona , Partido partido, EnumResultado resultado, boolean valor , Integer puntos) {
         this.persona = persona;
         this.partido = partido;
         this.resultado = resultado;
         this.valor = valor;
         this.puntos = puntos;
    }

    public boolean isValor() {
        return valor;
    }

    public void setValor(boolean puntos) {
        this.valor = puntos;
    }


    public Partido getPartido() {
        return partido;
    }

    public void setPartido(Partido partido) {
        this.partido = partido;
    }

    public EnumResultado getResultado() {
        return resultado;
    }

    public void setResultado(EnumResultado resultado) {
        this.resultado = resultado;
    }

    public Persona getPersona() {
        return persona;
    }

    public String  getNombrePersona() {
        return persona.getNombre();
    }

    public String getPersonaid() {
         return persona.getIdPersona();
    }
    public String  getRondaidPartido() {
        return partido.getRondaid();
    }

    public void setPersona(Persona persona) {
        this.persona = persona;
    }

      public Integer getPuntos() {
        return puntos;
    }

    public void setPuntos(Integer puntos) {
        this.puntos = puntos;
    }
}

