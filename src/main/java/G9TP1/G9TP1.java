/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package G9TP1;
import java.io.FileReader;
import java.util.List;
import javax.swing.JOptionPane;
import com.opencsv.bean.CsvToBeanBuilder;
import java.io.FileNotFoundException;
import modelos.Pronosticos;
import modelos.Resultados;

/**
 *
 * @author Leo
 */
public class G9TP1 {

    static String PathPronostico = "C:\\temp\\pronostico.csv";
    static String PathResultado = "C:\\temp\\resultados.csv";
    static List <Resultados> listaDeResultados;
    static List <Pronosticos> listaDePronosticos;
    
    public static void main(String[] args) {
        
        try {
            
            System.out.println("Inicio");
            if (leeArchivoPronostico()== false) {
                JOptionPane.showMessageDialog( null,
                                  "Error detalle: "+ "Error al Cagar datos de Pronostico",
                                  "Error", JOptionPane.PLAIN_MESSAGE
                                   );
            }
            
           // if ( leeArchivoResultados()== false) {
           //     JOptionPane.showMessageDialog( null,
           //                       "Error detalle: "+ "Error al Cagar datos de Resultados",
           //                       "Error", JOptionPane.PLAIN_MESSAGE);
           // }
                    
            imprime();
            //evaluo();
            // imprimeresultado;

            
        }
        
        catch ( Exception e ) {
                    JOptionPane.showMessageDialog( null,
                                  "Error: "+e.getMessage(),"Main",
                                  JOptionPane.PLAIN_MESSAGE);
   }

        
    }
    
    static boolean leeArchivoPronostico(  ) {
        try {
            
            // En esta primera línea definimos el archivos que va a ingresar
            listaDePronosticos = new CsvToBeanBuilder(new FileReader(PathPronostico))
            // Es necesario definir el tipo de dato que va a generar el objeto que estamos queriendo parsear a partir del CSV
            .withType(Pronosticos.class)
            .build()
            .parse();

            return false;
        }
        catch ( FileNotFoundException | IllegalStateException e ) {
            JOptionPane.showMessageDialog( null,
                                  "Error: "+e.getMessage(),"Leyendo Archivo Pronostico",
                                  JOptionPane.PLAIN_MESSAGE);
            
            return false ;
        }
        
        
    } 
    
    
    static boolean leeArchivoResultados(  ) {
        try {
            
            // En esta primera línea definimos el archivos que va a ingresar
            listaDeResultados = new CsvToBeanBuilder(new FileReader(PathResultado))
            // Es necesario definir el tipo de dato que va a generar el objeto que estamos queriendo parsear a partir del CSV
            .withType(Resultados.class)
            .build()
            .parse();

           
            return true;
        }
        catch ( FileNotFoundException | IllegalStateException e ) {
            JOptionPane.showMessageDialog( null,
                                  "Error: "+e.getMessage(),"Leyendo Archivo Resultado",
                                  JOptionPane.PLAIN_MESSAGE);
            
            return false ;
        }
        //*catch ( Exception e ) {
        //            JOptionPane.showMessageDialog( null,
        //                          "Error 2: "+e.getMessage(),"Leyendo Archivo Resultado",
        //                          JOptionPane.PLAIN_MESSAGE);
        //    return false ;
        //}
    }
    
    static boolean imprime(  ) {
        try {
            //El resultado de este método nos da una lita del objetos
            for (Pronosticos pronostico : listaDePronosticos) {
                System.out.println(pronostico.getIdEquipo1()+ ";" );
            }
            
            //El resultado de este método nos da una lita del objetos

            for (Resultados resultado : listaDeResultados) {
                System.out.println(resultado.getIdEquipo1()+ ";" );
            }
            return true;
        }
        
        catch ( Exception e ) {  //  Atrapador genérico de excepciones.
           System.out.println( "Hubo un error: " + e.getMessage() );
           return false;
        }
   

   }
    }
}
