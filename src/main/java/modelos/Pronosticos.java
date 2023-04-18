package modelos;

import com.opencsv.bean.CsvBindByPosition;

public class Pronosticos {
    @CsvBindByPosition(position = 0)
    private String idParticipante;
    @CsvBindByPosition(position = 1)
    private String NombreParticipante;
    @CsvBindByPosition(position = 2)
    private String idEquipo1;
    @CsvBindByPosition(position = 3)
    private String Gana1;
    @CsvBindByPosition(position = 4)
    private String Empate;
    @CsvBindByPosition(position = 5)
    private String Gana2;
    @CsvBindByPosition(position = 6)
    private String idEquipo2;
    
    
     public String getidParticipante() {
        return idParticipante;
    }
    public String getNombreParticipante() {
        return NombreParticipante;
    }
    public String getIdEquipo1() {
        return idEquipo1;
    }
    public String getIdEquipo2() {
        return idEquipo2;
    }
    public String getGana1() {
        return Gana1;
    }
    public String getEmpate() {
        return Empate;
    }
    public String getGana2() {
        return Gana2;
    }
}
