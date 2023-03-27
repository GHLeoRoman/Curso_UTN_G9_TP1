/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package G9TP1;
import java.io.*;          
import javax.swing.JOptionPane;
import java.io.FileNotFoundException;
//import modelos.Pronosticos[6];
//import modelos.Resultados[6];

/**
 *
 * @author Leo
 */
public class G9TP1 {

    static String PathPronostico = "C:\\temp\\pronostico.csv";
    static String PathResultado = "C:\\temp\\resultados.csv";
    
    public static void main(String[] args) {
        
        try {
            
            System.out.println("Inicio");
            if (leeArchivoPronostico()== false) {
                JOptionPane.showMessageDialog( null,
                                  "Error detalle: "+ "Error al Cagar datos de Pronostico",
                                  "Error", JOptionPane.PLAIN_MESSAGE
                                   );
            }
            
            if ( leeArchivoResultados()== false) {
                JOptionPane.showMessageDialog( null,
                                  "Error detalle: "+ "Error al Cagar datos de Resultados",
                                  "Error", JOptionPane.PLAIN_MESSAGE);
            }
                    
            
        }
        
        catch ( Exception e ) {
                    JOptionPane.showMessageDialog( null,
                                  "Error: "+e.getMessage(),"Main",
                                  JOptionPane.PLAIN_MESSAGE);
   }

        
    }
    
    static boolean leeArchivoPronostico(  ) throws IOException {
        try {
            FileInputStream fis; //  La clase "FileInputStream" sirve para
                           //referir a archivos.
            DataInputStream Datos; //  La clase "DataInputStream" sirve para
                             //leer independientemente del hardware,
                             //tipos de datos de una "corriente" o
                             //"stream" que en nuestro caso es un archivo.
            String renglon = null;

            //  Al instanciar o crear el objeto, abrimos el archivo.
            fis = new FileInputStream( PathPronostico );
            Datos = new DataInputStream( fis );

            renglon = Datos.readLine();
            
            String Linea[] = renglon.split( "," );
            
            while ( renglon != null ) { //  Es "null" si encuentra fin del archivo.
            System.out.println( renglon );
            renglon = Datos.readLine();
        }

        //  Cerramos el archivo.
        fis.close();
        return true;
           
        }
        
        catch ( FileNotFoundException e ) {
            System.out.println("""
                               Archivo inexistente.
                               El programa se cancela.""");
            return false;
            }

        catch ( IOException e ) {
            System.out.println("""
                               Error en el uso o cierre del archivo
                               El programa se cancela.""");
            return false;
            }

        catch ( Exception e ) {
            System.out.println("El programa se cancela.Error.");
            return false;
            }
        
    } 
    
    
    static boolean leeArchivoResultados(  ) {
        try {
            
            FileInputStream fis; //  La clase "FileInputStream" sirve para
                           //referir a archivos.
            DataInputStream Datos; //  La clase "DataInputStream" sirve para
                             //leer independientemente del hardware,
                             //tipos de datos de una "corriente" o
                             //"stream" que en nuestro caso es un archivo.
            String renglon = null;

            //  Al instanciar o crear el objeto, abrimos el archivo.
            fis = new FileInputStream( PathResultado );
            Datos = new DataInputStream( fis );

            renglon = Datos.readLine();
            while ( renglon != null ) { //  Es "null" si encuentra fin del archivo.
            System.out.println( renglon );
            renglon = Datos.readLine();
        }

        //  Cerramos el archivo.
        fis.close();
        return true;
    }

    catch ( FileNotFoundException | IllegalStateException e ) {
            JOptionPane.showMessageDialog( null,
                                  "Error: "+e.getMessage(),"Leyendo Archivo Resultado",
                                  JOptionPane.PLAIN_MESSAGE);
        return false ;
    }
    catch ( Exception e ) {
                    JOptionPane.showMessageDialog( null,
                                  "Error 2: "+e.getMessage(),"Leyendo Archivo Resultado",
                                  JOptionPane.PLAIN_MESSAGE);
            return false ;
    }
    }
}