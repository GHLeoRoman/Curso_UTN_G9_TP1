/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package G9TP1;
import java.io.*;          
import javax.swing.JOptionPane;
import java.io.FileNotFoundException;
import java.io.FileInputStream;
import java.io.IOException;


//import modelos.Pronosticos[6];
//import modelos.Resultados[6];

/**
 *
 * @author Grupo 9
 */
public class G9TP1 {

    static String PathPronostico = "C:\\temp\\pronostico.csv";
    static String PathResultado = "C:\\temp\\resultados.csv";
    
    static String [][] VarPronosticos = new String [10][10];
    static String [][] VarResultados = new String [10][10];
    static String[] result ;
    
    
       
    public static void main(String[] args) {
        
        try {
            
            System.out.println("Lee Archivo Pronostico");
            if (leeArchivoPronostico()== false) {
                System.out.println("Error al Cagar el Archivo");
            }
            {
                 System.out.println("Sin Errores");
            }
            
            System.out.println("Lee Archivo Resultado");
             if (leeArchivoResultado()== false) {
                System.out.println("Error al Cagar datos de Resultado");
            }
            {
                 System.out.println("Sin Errores");
            }
            
            System.out.println("Imprime");
            imprime();
             
            
        }
        
        catch ( Exception e ) {
                    JOptionPane.showMessageDialog( null,
                                  "Error Main : "+e.getMessage(),"Main",
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
      int i = 0;
      renglon = Datos.readLine();
      while ( renglon != null ) { //  Es "null" si encuentra fin del archivo.
         
         renglon = Datos.readLine();
         result = renglon.split( ";" );
         System.arraycopy(result, 0, VarPronosticos[i], 0, result.length);
         i=1+i;
      }

      //  Cerramos el archivo.
      fis.close();
      return true;
    }

    catch ( FileNotFoundException e ) {
      System.out.println( "Archivo inexistente."
                        + "\nEl programa se cancela." );
      return false;
    }

    catch ( IOException e ) {
      System.out.println( "Error en el uso o cierre del archivo"
                        + "\nEl programa se cancela." );
      return false;
    }

    catch ( Exception e ) {
      System.out.println( "Error."
                        + e.getMessage()  );
      return false;
    }

  }

  static boolean leeArchivoResultado(  ) throws IOException {
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
            int i = 0;
            renglon = Datos.readLine();
            while ( renglon != null ) { //  Es "null" si encuentra fin del archivo.

               renglon = Datos.readLine();
               result = renglon.split( ";" );
               System.arraycopy(result, 0, VarResultados[i], 0, result.length);
               i=1+i;
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
            System.out.println("El programa se cancela.Error." + e.getMessage());
            return false;
            }
    } 
    static void imprime() {
        
        System.out.println("Datos Pronosticos");
        for( int i=0 ; i<VarPronosticos.length ; i++ ) {
         for( int j=0 ; j<VarPronosticos[i].length ; j++ )
            System.out.println(VarPronosticos[i][j]);
            }
        System.out.println("Datos Resultados");
        
         for( int i=0 ; i<VarResultados.length ; i++ ) {
         for( int j=0 ; j<VarResultados[i].length ; j++ )
            System.out.println(VarResultados[i][j]);
            }
    }
}
