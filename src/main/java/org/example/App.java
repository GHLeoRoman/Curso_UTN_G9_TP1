package org.example;

import modelos.Pronostico;
import modelos.Resultado;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Hello world!
 *
 */
public class App 
{
    static List<Resultado> resultados = new ArrayList<>();
    static List<Pronostico> pronosticos = new ArrayList<>();

    static String pathResultado = "C:\\temp\\resultados.csv";
    static String pathPronostico = "C:\\temp\\pronostico.csv";

    public static void main( String[] args )  throws Exception
    {
        System.out.println ("resultados");
        leeresultados();
        System.out.println ("pronosticos");
        leepronosticos();
        System.out.println ("recorre");
        recorre();
    }

    private static void leeresultados() {
        // Leer resultados
        Path ruta  = Paths.get(pathResultado);
        List<String> lineaResultado = null;
        try {
            lineaResultado = Files.readAllLines(ruta);
        } catch (IOException e) {
            System.out.println("No se pudo leer la linea de resultados...");
            System.out.println(e.getMessage());
            System.exit(1);
        }
        boolean primera = true;
        for (String linea : lineaResultado) {
            if (primera) {
                primera = false;
            } else {
                String[] campos = linea.split(";");
                Resultado resultado = new Resultado();
                resultado.setRondaid(campos[0]);
                resultado.setRondanro(campos[1]);
                resultado.setEquipo1id(campos[2]);
                resultado.setEquipo1nombre(campos[3]);
                resultado.setEquipo1descripcion(campos[4]);
                resultado.setEquipo1cantidadgoles(Integer.parseInt(campos[5]));
                resultado.setEquipo2cantidadgoles(Integer.parseInt(campos[6]));
                resultado.setEquipo2id(campos[7]);
                resultado.setEquipo2nombre(campos[8]);
                resultado.setEquipo2descripcion(campos[9]);

                resultados.add(resultado);
            }
        }
    }

    private static void leepronosticos(){


        Path ruta = Paths.get(pathPronostico);
        List<String> lineasPronostico = null;
        try {
            lineasPronostico = Files.readAllLines(ruta);
        } catch (IOException e) {
            System.out.println("No se pudo leer la linea de pronosticos...");
            System.out.println(e.getMessage());
            System.exit(1);
        }
        boolean primera;
        primera = true;
        for (String lineaPronostico : lineasPronostico) {
            if (primera) {
                primera = false;
            } else {
                String[] campos = lineaPronostico.split(";");
                Pronostico pronostico = new Pronostico(campos[0],campos[1],campos[2],campos[3],campos[4],campos[5],campos[6]);
                pronostico.setPuntos(calculaPuntos(resultados,pronostico));
                pronosticos.add(pronostico);
            }
        }
    }

    public static Integer  calculaPuntos(List<Resultado> resultado, Pronostico pronosticoAux) {

        Optional<Resultado> partidoaux = resultado.stream().filter(a -> a.getEquipo1id().equals(pronosticoAux.getEquipo1id()) && a.getEquipo2id().equals(pronosticoAux.getEquipo2id())).findFirst();


        if (!partidoaux.isPresent()) {
            // Excepcion
            System.out.print("Partido no encontrado");
        } else
        {
            Resultado partido = partidoaux.get();
            if (pronosticoAux.getGana1().equals("X")) {
                if (partido.getEquipo1cantidadgoles() > partido.getEquipo2cantidadgoles()) {
                    // asigno puntos
                    return 1;
                }
            }

            if (pronosticoAux.getGana2().equals("X")) {
                if (partido.getEquipo1cantidadgoles() < partido.getEquipo2cantidadgoles()) {
                    // asigno puntos
                    return 1;
                }

            }

            if (pronosticoAux.getEmpata().equals("X")) {
                if (partido.getEquipo1cantidadgoles() == partido.getEquipo2cantidadgoles()) {
                    // asigno puntos
                    return 1;
                }
            }
        }
        return 0;
    }

    private static void recorre() {
        System.out.println ("tamaño de resultado : " + resultados.size());
        //for( int i=0 ; i<resultados.size() ; i++ )
        //    System.out.println( "Planeta " + (i+1) + ": " + resultados.get(i).. );
        //}

    }
}
