package org.example;

import modelos.Pronostico;
import modelos.Resultado;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import java.sql.*;

import static conexion.sql.ConectorSQL.DB_URL;
import static conexion.sql.ConectorSQL.USER;
import static conexion.sql.ConectorSQL.PASS;
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

    static Connection conexion = null;
    static Statement consulta = null;

    static String Etapa = "3";

    public static void main( String[] args )  throws Exception
    {
        if ( Etapa == "2" ) {
            leeResultadosDesdeArchivo();
            leePronosticosDesdeArchivo();
            recorre(pronosticos);
            }
            else
            {
                leeResultadosDesdeDB();
                leePronosticosDesdeDB();
                recorre(pronosticos);
            }
    }

    private static void leeResultadosDesdeArchivo() {
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

    private static void leePronosticosDesdeArchivo(){


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
                Pronostico pronostico = new Pronostico(campos[0],campos[1],campos[2],campos[3],
                        campos[4],campos[5],campos[6],campos[7]);
                pronostico.setPuntos(pronostico.calculaPuntos(resultados,pronostico));
                pronosticos.add(pronostico);
            }
        }
    }

   private static void recorre(List<Pronostico> pronostico) {
        // Totales x Rueda y Persona
        Map<String, Integer> map = pronosticos.stream().collect(
                Collectors.groupingBy(                            // I want a Map<Integer, Integer>
                        Pronostico::getParticipantenombre,                              // price is the key
                        Collectors.summingInt(Pronostico::getPuntos) ) ); // sum of quantities is the va
        System.out.println ("Lista de Puntos Totales ");
        map.forEach((key, value) -> System.out.println(key + ":" + value));

        // Totales x Rueda y Persona
        Map<String, Map<String, Integer>> map2 = pronosticos.stream()
                .collect(
                        Collectors.groupingBy(Pronostico::getRondaid,
                                Collectors.groupingBy(Pronostico::getParticipantenombre,
                                        Collectors.summingInt(Pronostico::getPuntos))));

        System.out.println ("Lista de Puntos x Ronda");

        map2.forEach((key, value) -> System.out.println(key + ":" + value));


    }

    private static void leeResultadosDesdeDB() {
        // Leer resultados
        try {
            // Abrir la conexión
            System.out.println("conectando a la base de datos...");
            conexion = DriverManager.getConnection(DB_URL, USER, PASS);
            // Ejecutar una consulta
            System.out.println("Creating statement...");
            consulta = conexion.createStatement();
            String sql;
            sql = "SELECT Rondaid , Rondanro , Equipo1id , Equipo1nombre ," +
                    "Equipo1descripcion ,Equipo1cantidadgoles , Equipo2cantidadgoles , " +
                    "Equipo2id , Equipo2nombre , Equipo2descripcion FROM prode.resultados";
            //En la variable resultado obtendremos las distintas filas que nos devolvió la base
            ResultSet rs = consulta.executeQuery(sql);

            // Obtener las distintas filas de la consulta
            while (rs.next()) {
                // Obtener el valor de cada columna
                Resultado resultado = new Resultado();
                resultado.setRondaid(rs.getString("Rondaid"));
                resultado.setRondanro(rs.getString("Rondanro"));
                resultado.setEquipo1id(rs.getString("Equipo1id"));
                resultado.setEquipo1nombre(rs.getString("Equipo1nombre"));
                resultado.setEquipo1descripcion(rs.getString("Equipo1descripcion"));
                resultado.setEquipo1cantidadgoles(rs.getInt("Equipo1cantidadgoles"));
                resultado.setEquipo2cantidadgoles(rs.getInt("Equipo2cantidadgoles"));
                resultado.setEquipo2id(rs.getString("Equipo2id"));
                resultado.setEquipo2nombre(rs.getString("Equipo2nombre"));
                resultado.setEquipo2descripcion(rs.getString("Equipo2descripcion"));
                resultados.add(resultado);
            }
            // Esto se utiliza par cerrar la conexión con la base de datos
            rs.close();
            consulta.close();
            conexion.close();
        } catch (SQLException se) {
            // Execpción ante problemas de conexión
            se.printStackTrace();
        } finally {
            // Esta sentencia es para que ante un problema con la base igual se cierren las conexiones
            try {
                if (consulta != null)
                    consulta.close();
            } catch (SQLException se2) {
            }
            try {
                if (conexion != null)
                    conexion.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }
    }

    private static void leePronosticosDesdeDB(){
        try {
            // Abrir la conexión
            System.out.println("conectando a la base de datos...");
            conexion = DriverManager.getConnection(DB_URL, USER, PASS);
            // Ejecutar una consulta
            System.out.println("Creating statement...");
            consulta = conexion.createStatement();
            String sql;
            sql = "SELECT Rondaid , participanteid , participantenombre , equipo1id , gana1 , empata , gana2 , FROM prode.resultados";
            //En la variable resultado obtendremos las distintas filas que nos devolvió la base
            ResultSet rs = consulta.executeQuery(sql);

            // Obtener las distintas filas de la consulta
            while (rs.next()) {
            Pronostico pronostico = new Pronostico(rs.getString("Rondaid"),rs.getString("participanteid"),
                    ,rs.getString("participantenombre") , rs.getString("equipo1id"),rs.getString("gana1"),
                    rs.getString("empata"),rs.getString("gana2"),
                    rs.getString("equipo2id"));

                pronostico.setPuntos(pronostico.calculaPuntos(resultados,pronostico));
                pronosticos.add(pronostico);
            }
            // Esto se utiliza par cerrar la conexión con la base de datos
            rs.close();
            consulta.close();
            conexion.close();
        } catch (SQLException se) {
            // Execpción ante problemas de conexión
            se.printStackTrace();
        } finally {
            // Esta sentencia es para que ante un problema con la base igual se cierren las conexiones
            try {
                if (consulta != null)
                    consulta.close();
            } catch (SQLException se2) {
            }
            try {
                if (conexion != null)
                    conexion.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }

    }
}
