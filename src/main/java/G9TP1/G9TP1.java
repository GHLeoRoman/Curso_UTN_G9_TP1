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
            
            System.out.println("Procesa");
            procesa();
            
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
            System.out.println(VarPronosticos[i][0] + " " + VarPronosticos[i][1] + " " + VarPronosticos[i][2]+ " Punto " + VarPronosticos[i][7] );
        }
    }
    
    static void procesa() {
        try{
            for( int p=0 ; p<VarPronosticos.length ; p++ ) {

                for( int r=0 ; r<VarResultados.length ; r++ ) {
                    //System.out.println("Pronostico : " + p + " Id Equipo 1 : " + VarPronosticos [p] [2] );
                    if (VarResultados [r] [2] != null) {
                        //System.out.println("Resultado : " + r + " Id Equipo 1 : " + VarResultados [r] [2] );
                        if( Integer.parseInt(VarResultados[r] [2]) == Integer.parseInt(VarPronosticos [p] [2]) )  {  // igual equipo 1 )  
                        //System.out.println("Tengo E1");
                        if( Integer.parseInt(VarPronosticos [p] [6]) == Integer.parseInt(VarResultados[r] [7]) ) {  // igual equipo 2 )  
                            //System.out.println("Tengo E2");
                            int resultado = 0;
                            
                            //System.out.println("Resultado E1 : " + VarResultados [p] [5]);
                            //System.out.println("Resultado E2 : " + VarResultados [p] [6]);
                            
                            if (VarResultados [p] [5] !=null && VarResultados [p] [6]!=null ) {
                              resultado = Integer.parseInt(VarResultados [p] [5]) - Integer.parseInt(VarResultados [p] [6]);
                              //System.out.println("Resultado del partido : " + resultado);
                            }                            

                            // Empate
                            
                            if( resultado == 0 ) {
                                if (VarPronosticos [p] [3].equals("X")) {
                                   VarPronosticos [p] [7] = "1";
                                }
                            }
                            // Gana E1
                            if( resultado > 0 ) {
                                if ( VarPronosticos [p] [4].equals("X")) {
                                    VarPronosticos [p] [7] = "1";
                                }
                            }
                            // Gana E2
                            if( resultado < 0 ) {
                                if ( VarPronosticos [p] [5].equals("X") ) {
                                    VarPronosticos [p] [7] = "1";
                                    //System.out.println("Asigna resultado Gana E2 ");
                                    //System.out.println(VarPronosticos [p] [7]);
                                    }
                                }
                            }
                        }
                    }
                }
            } 
        }
        catch ( Exception e ) {
            System.out.println("El programa se cancela.Error." + e.getMessage());
        }
    }
}
        