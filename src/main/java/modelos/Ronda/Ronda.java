package modelos.Ronda;

public class Ronda {
    private String Rondaid;
    private String Rondanro ;

    public String getRondaid() {
        return Rondaid;
    }

    public void setRondaid(String rondaid) {
        Rondaid = rondaid;
    }

    public String getRondanro() {
        return Rondanro;
    }

    public void setRondanro(String rondanro) {
        Rondanro = rondanro;
    }

    public Ronda(String rondaid, String rondanro) {
        Rondaid = rondaid;
        Rondanro = rondanro;
    }
}
