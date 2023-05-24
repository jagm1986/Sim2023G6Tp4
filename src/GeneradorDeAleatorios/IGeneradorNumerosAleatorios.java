
package GeneradorDeAleatorios;

import java.util.Random;
import simTp4.NumeroRNDTable;
  
public abstract class IGeneradorNumerosAleatorios 
{
    Random generador ;
    public IGeneradorNumerosAleatorios() 
    {
         generador = new Random();
    }
  
    public abstract NumeroRNDTable generarAleatorio();
    
}
