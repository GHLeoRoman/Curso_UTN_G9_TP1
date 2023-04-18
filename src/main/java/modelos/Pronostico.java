package modelos;

public class Pronostico {
    private String participanteid;
    private String participantenombre;
    private String equipo1id;
    private String gana1;
    private String empata;
    private String gana2;
    private String equipo2id;
    private int puntos;

    public Pronostico(String participanteid, String participantenombre, String equipo1id, String gana1, String empata, String gana2, String equipo2id) {
        this.participanteid = participanteid;
        this.participantenombre = participantenombre;
        this.equipo1id = equipo1id;
        this.gana1 = gana1;
        this.empata = empata;
        this.gana2 = gana2;
        this.equipo2id = equipo2id;
    }

    public String getParticipanteid() {
        return participanteid;
    }

    public void setParticipanteid(String participanteid) {
        this.participanteid = participanteid;
    }

    public String getParticipantenombre() {
        return participantenombre;
    }

    public void setParticipantenombre(String participantenombre) {
        this.participantenombre = participantenombre;
    }

    public String getEquipo1id() {
        return equipo1id;
    }

    public void setEquipo1id(String equipo1id) {
        this.equipo1id = equipo1id;
    }

    public String getGana1() {
        return gana1;
    }

    public void setGana1(String gana1) {
        this.gana1 = gana1;
    }

    public String getEmpata() {
        return empata;
    }

    public void setEmpata(String empata) {
        this.empata = empata;
    }

    public String getGana2() {
        return gana2;
    }

    public void setGana2(String gana2) {
        this.gana2 = gana2;
    }

    public String getEquipo2id() {
        return equipo2id;
    }

    public void setEquipo2id(String equipo2id) {
        this.equipo2id = equipo2id;
    }

    public int getPuntos() {
        return puntos;
    }

    public void setPuntos(int puntos) {
        this.puntos = puntos;
    }
}
