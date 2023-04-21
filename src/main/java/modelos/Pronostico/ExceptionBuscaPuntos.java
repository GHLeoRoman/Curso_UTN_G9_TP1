package modelos.Pronostico;

public class ExceptionBuscaPuntos extends Exception {

    public ExceptionBuscaPuntos(String mensaje) {
        super(mensaje);
        System.out.println (mensaje);
    }
}
