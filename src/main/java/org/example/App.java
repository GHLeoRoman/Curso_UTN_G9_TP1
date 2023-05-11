package org.example;

import modelos.EnumResultado;
import modelos.Equipo.Equipo;
import modelos.Persona.Persona;
import modelos.Pronostico.ExceptionBuscaPuntos;
import modelos.Pronostico.Pronostico;
import modelos.Partido.Partido;
import modelos.Ronda.Ronda;

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
import static javax.management.Query.value;

public class App 
{
    static List<Persona> personas = new ArrayList<>();
    static List<Partido> partidos = new ArrayList<>();
    static List<Pronostico> pronosticos = new ArrayList<>();
    static List<Ronda> rondas = new ArrayList<>();

    static String pathResultado = "src\\main\\java\\resources\\resultados.csv";

    static String pathPronostico = "src\\main\\java\\resources\\pronostico.csv";

    static Connection conexion = null;
    static Statement consulta = null;

    static String Etapa = "2";

    public static void main( String[] args )  throws Exception
    {
        if ( Etapa == "2" ) {
            leeResultadosDesdeArchivo();
            leePronosticosDesdeArchivo();
            asignaBonus();
            recorre(pronosticos);
            }
            else
            {
                leeResultadosDesdeDB();
                leePronosticosDesdeDB();
                recorre(pronosticos);
            }
    }

    private static void leeResultadosDesdeArchivo()   {
        // Leer resultados
        Path ruta  = Paths.get(pathResultado);
        List<String> lineaResultado = null;
        try {
            lineaResultado = Files.readAllLines(ruta);
        } catch (IOException e) {
            System.out.println("No se pudo leer el Archivo Resultados...");
            System.out.println(e.getMessage());
            System.exit(1);
        }

        boolean primera;
        primera = true;
        for (String linea : lineaResultado) {
            if (primera) {
                primera = false;
            } else {
                String[] campos = linea.split(";");

                try {
                    Equipo equipo1 = new Equipo(campos[2],campos[3] , campos[4]);
                    Equipo equipo2 = new Equipo(campos[7],campos[8] , campos[9]);
                    // busco a ronda
                    Ronda rondaAux = buscoRonda(campos[0],campos[1]);

                    Partido resultado = new Partido(rondaAux ,equipo1, Integer.parseInt(campos[5]),
                            Integer.parseInt(campos[6]),equipo2);
                    partidos.add(resultado);
                } catch (Exception e) {
                    System.out.println("No se pudo leer la linea de Resultado :  " + linea );
                    System.out.println(e.getMessage());
                    //System.exit(1);
                }
            }
        }
    }

    private static void leePronosticosDesdeArchivo(){
        Path ruta = Paths.get(pathPronostico);
        List<String> lineaPronostico = null;
        try {
            lineaPronostico = Files.readAllLines(ruta);
        } catch (IOException e) {
            System.out.println("No se pudo abrir el Archivo Pronosticos...");
            System.out.println(e.getMessage());
            System.exit(1);
        }
        boolean primera;
        primera = true;
        for (String linea : lineaPronostico) {
            if (primera) {
                primera = false;
            } else {
                String[] campos = linea.split(";");
                try {
                    // busco a la persona
                    Persona personaAux = buscoPersona(campos[1],campos[2]);

                    // busco partido
                    Optional<Partido> partidoAux = partidos.stream().filter(a ->
                            a.getEquipoid1().equals(campos[3])  && a.getEquipoid2().equals(campos[7])
                                    && a.getRondaid().equals(campos[0]) ).findFirst();
                    if (!partidoAux.isPresent()) {
                        // No Existe Partido
                        //System.out.print("Partido no encontrado");
                        throw new ExceptionBuscaPuntos("Partido no encontrado , Ronda " +
                                campos[0] + ", Equipo 1 : "  + campos[3] +
                                ", Equipo2 : " + campos[7]);
                    }
                    // busco pronostico
                    Optional<Pronostico> pronosticoAux = pronosticos.stream().filter(a ->
                            a.getPartido().equals(partidoAux.get()) &&
                            a.getPersona().equals(personaAux)).findFirst();
                    if (!pronosticoAux.isPresent()) {
                        // alta ponostico
                        altaPronostico(personaAux,partidoAux.get(),campos[4],
                                campos[5],campos[6] );
                    } else {
                        // Pronostico ya Existe
                        //System.out.print("Pronostico ya Existe");
                        throw new ExceptionBuscaPuntos("Partido no encontrado , Ronda " +
                                campos[0] + ", Equipo 1 : "  + campos[3] +
                                ", Equipo2 : " + campos[7]);
                    }
                    } catch (Exception e) {
                        System.out.println("No se pudo Tratar la linea de Pronostico :  " + linea );
                        System.out.println(e.getMessage());
                    }
            }
        }
    }


    private static Persona buscoPersona(String id , String nombre ) {
        Optional<Persona> personaAux = personas.stream().filter(a -> a.getIdPersona().equals(id)).findFirst();
        if (!personaAux.isPresent()) {
            // alta persona
            Persona persona = new Persona(id,nombre ,0);
            personas.add(persona);
           return  persona;
        }else{
            return personaAux.get();
        }
    }

    private static Ronda buscoRonda(String id , String nombre ) {
        Optional<Ronda> rondaAux = rondas.stream().filter(a -> a.getRondaid().equals(id)).findFirst();
        if (!rondaAux.isPresent()) {
            // alta persona
            Ronda ronda = new Ronda(id,nombre);
            rondas.add(ronda);
            return  ronda;
        }else{
            return rondaAux.get();
        }
    }

    private static Pronostico altaPronostico(Persona persona, Partido partido,
                                             String gana, String empata, String pierde)
    {
        EnumResultado resultadoAux = null;
        boolean r = false;
        Integer p = 0;
        if (gana.equals("X") ) {
            resultadoAux = EnumResultado.GANADOR;
            if (partido.getEquipo1cantidadgoles() > partido.getEquipo2cantidadgoles()){
                r = true;
                p=1;
            }
        }
        if (empata.equals("X") ) {
            resultadoAux = EnumResultado.EMPATE;
            if (partido.getEquipo1cantidadgoles() == partido.getEquipo2cantidadgoles()){
                r = true;
                p=1;
            }
        }
        if (pierde.equals("X") ) {
            resultadoAux = EnumResultado.PERDEDOR;
            if (partido.getEquipo1cantidadgoles() < partido.getEquipo2cantidadgoles()){
                r = true;
                p=1;
            }
        }

        Pronostico pronostico = new Pronostico( persona, partido ,resultadoAux ,r,p);
        pronosticos.add(pronostico);
        return pronostico;
    }

    private static void asignaBonus( ) {
        // si la persona acertó todos los partidos de la ronda, le asignamos bonus
        // recorro rondas

        rondas.forEach((Ronda r) -> {
            // leo cuantos partidos para cada ronda
            Long todos ;
            todos = partidos.stream().filter(a -> a.getRondaid().equals(r.getRondaid())).count();

            for (int x = 0; x < personas.size(); x++) {
                Persona p = personas.get(x);
                // leo cuatos pronosticos certeros de para cada ronda persona
                long ganados ;
                ganados = pronosticos.stream().filter(a -> a.getPersonaid().equals(p.getIdPersona()) &&
                    a.isValor()==true && a.getRondaidPartido().equals(r.getRondaid()) ).count();
                if ( todos == ganados) {
                    Persona personaAux = buscoPersona(p.getIdPersona(), "");
                    personaAux.setPuntosextra(10);
                    System.out.println ("Asignó Bonus ");
                }
            };
        });
    }


   private static void recorre(List<Pronostico> pronostico) {

       // Totales x Rueda y Persona
       Map<String, Map<String, Integer>> map2 = pronosticos.stream()
               .collect(
                       Collectors.groupingBy(Pronostico::getRondaidPartido,
                               Collectors.groupingBy(Pronostico::getNombrePersona,
                                       Collectors.summingInt(Pronostico::getPuntos))));

       System.out.println ("Tabla de Puntos x Ronda");
       map2.forEach((key, value) -> System.out.println(key + ":" + value));

       // Totales x Persona
        Map<String, Integer> map = pronosticos.stream().collect(
                Collectors.groupingBy(
                        Pronostico::getPersonaid,
                        Collectors.summingInt(Pronostico::getPuntos) ) );
        System.out.println ("Tabla de Puntos Totales ");
        map.forEach((key, value) -> {
            Persona pAux = buscoPersona(key, "");
            Integer p = pAux.getPuntosextra() + value;
            System.out.println(pAux.getNombre() + ":" + p.toString());
        });



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
            sql = "SELECT * FROM resultado";
            //En la variable resultado obtendremos las distintas filas que nos devolvió la base
            ResultSet rs = consulta.executeQuery(sql);

            // Obtener las distintas filas de la consulta
            while (rs.next()) {

                Equipo equipo1 = new Equipo(rs.getString("Equipo1id"),rs.getString("Equipo1nombre")
                        , rs.getString("Equipo1descripcion"));
                Equipo equipo2 = new Equipo(rs.getString("Equipo2id"),rs.getString("Equipo2nombre")
                        , rs.getString("Equipo2descripcion"));

                // busco a ronda
                Ronda rondaAux = buscoRonda(rs.getString("Rondaid"),rs.getString("Rondanro"));

                Partido resultado = new Partido(rondaAux,
                        equipo1, rs.getInt("Equipo1cantidadgoles"),
                        rs.getInt("Equipo2cantidadgoles"),equipo2);
                partidos.add(resultado);

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
            sql = "SELECT * FROM pronostico";
            //En la variable resultado obtendremos las distintas filas que nos devolvió la base
            ResultSet rs = consulta.executeQuery(sql);

            // Obtener las distintas filas de la consulta
            while (rs.next()) {
                try {

                    // busco a la persona
                    Persona personaAux = buscoPersona(rs.getString("participanteid"),rs.getString("participantenombre"));

                    // busco partido
                    Optional<Partido> partidoAux = partidos.stream().filter(a ->
                    {
                        try {
                            return a.getEquipoid1().equals(rs.getString("equipo1id"))
                                    && a.getEquipoid2().equals(rs.getString("equipo2id"))
                                    && a.getRondaid().equals(rs.getString("Rondaid"));
                        } catch (SQLException e) {
                            throw new RuntimeException(e);
                        }
                    }).findFirst();
                    if (!partidoAux.isPresent()) {
                        // No Existe Partido
                        //System.out.print("Partido no encontrado");
                        throw new ExceptionBuscaPuntos("Partido no encontrado , Ronda " +
                                rs.getString("Rondaid") + ", Equipo 1 : "  + rs.getString("equipo1id") +
                                ", Equipo2 : " + rs.getString("equipo2id"));
                    }

                    // busco pronostico
                    Optional<Pronostico> pronosticoAux = pronosticos.stream().filter(a ->
                            a.getPartido().equals(partidoAux)).findFirst();
                    if (!pronosticoAux.isPresent()) {
                        // alta ponostico

                        altaPronostico(personaAux, partidoAux.get(), rs.getString("gana1"),
                                rs.getString("empata"),rs.getString("gana2"));

                    } else {
                        // Pronostico ya Existe
                        //System.out.print("Pronostico ya Existe");
                        throw new ExceptionBuscaPuntos("Pronostico ya Existe , Persona " +
                                rs.getString("participantenombre") + " Ronda " +
                                rs.getString("Rondaid") + ", Equipo 1 : " +
                                rs.getString("equipo1id") + ", Equipo2 : " +
                                rs.getString("equipo2id"));
                    }



                } catch ( Exception e) {
                    System.out.println(e.getMessage());
                }
            }
            // Esto se utiliza par cerrar la conexión con la base de datos
            rs.close();
            consulta.close();
            conexion.close();
        } catch (SQLException  se) {
            // Execpción ante problemas de conexión
            se.printStackTrace();
        } finally {
            // Esta sentencia es para que ante un problema con la base igual se cierren las conexiones
            try {
                if (consulta != null)
                    consulta.close();
            } catch (SQLException se2) {
            }
        }

    }
}
