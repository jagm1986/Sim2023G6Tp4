package GeneradorDeAleatorios;

import simTp4.NumeroRNDTable;

public class GeneradorExponencial extends IGeneradorNumerosAleatorios
{
    private final float lambda;


    public GeneradorExponencial(float lambda) {
        super();
        this.lambda = lambda;
    }
    

    
    @Override
    public NumeroRNDTable generarAleatorio() 
    {
        double rnd = generador.nextDouble();
        float inversaLambda = ((float) -1)/(1/lambda);
        
        double complementoRND = 1 - rnd;
        double logaritmoNaturalComplemento = Math.log(complementoRND);
  
        double resultado = inversaLambda * logaritmoNaturalComplemento;
        double aux = (double) Math.round(resultado * 10000d) / 10000d;
        resultado = aux;
        
        NumeroRNDTable numeroRNDTable = new NumeroRNDTable();
        numeroRNDTable.setNumero(resultado);
        numeroRNDTable.setRnd(rnd);
        return numeroRNDTable;
        
    }
    
}
