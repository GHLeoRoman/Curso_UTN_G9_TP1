package modelos;

import com.opencsv.bean.CsvBindByPosition;

public class Resultados {
    @CsvBindByPosition(position = 0)
    private int Ronda;
    @CsvBindByPosition(position = 1)
    private int IdRonda;
    @CsvBindByPosition(position = 2)
    private int IdEquipo1;
    @CsvBindByPosition(position = 3)
    private String NombreEquipo1;
    @CsvBindByPosition(position = 4)
    private String DescripcionEquipo1;
    @CsvBindByPosition(position = 5)
    private int GolesEquipo1;
    @CsvBindByPosition(position = 6)
    private int GolesEquipo2;
    @CsvBindByPosition(position = 7)
    private int IdEquipo2;
    @CsvBindByPosition(position = 8)
    private String NombreEquipo2;
    @CsvBindByPosition(position = 9)
    private String DescripcionEquipo2;

    public int getRonda() {
        return Ronda;
    }
    public int getidRonda() {
        return IdRonda;
    }
    public int getIdEquipo1() {
        return IdEquipo1;
    }
    public String getNombreEquipo1() {
        return NombreEquipo1;
    }
    public String getDescripcionEquipo1() {
        return DescripcionEquipo1;
    }
    public int getGolesEquipo1() {
        return GolesEquipo1;
    }
    public int getGolesEquipo2() {
        return GolesEquipo2;
    }
    public int getIdEquipo2() {
        return IdEquipo2;
    }
    public String getNombreEquipo2() {
        return NombreEquipo2;
    }
    public String getDescripcionEquipo2() {
        return DescripcionEquipo2;
    }
}