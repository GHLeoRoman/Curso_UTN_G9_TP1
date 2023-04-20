package dominioTest;

import modelos.Pronostico.ExceptionBuscaPuntos;
import modelos.Pronostico.Pronostico;
import modelos.Resultado.Resultado;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class PronosticoTest {

    @Test
    public void PronosticoTest1() throws ExceptionBuscaPuntos {

        List<Resultado> resultados = new ArrayList<>();
        List<Pronostico> pronosticos = new ArrayList<>();

        Resultado resultado1 = new Resultado();
        resultado1.setRondaid("1");
        resultado1.setRondanro("1");
        resultado1.setEquipo1id("1");
        resultado1.setEquipo1nombre("Argentina");
        resultado1.setEquipo1descripcion("Argentina Seleccion");
        resultado1.setEquipo1cantidadgoles(4);
        resultado1.setEquipo2cantidadgoles(3);
        resultado1.setEquipo2id("2");
        resultado1.setEquipo2nombre("Francia");
        resultado1.setEquipo2descripcion("Fraancia Seleccion");
        resultados.add(resultado1);

        Pronostico pronostico1 = new Pronostico("1","1","1","1","","X","","2");
        pronostico1.setPuntos(Pronostico.calculaPuntos(resultados,pronostico1));
        pronosticos.add(pronostico1);

        Assertions.assertEquals(1, pronostico1.getPuntos());


    }


}
