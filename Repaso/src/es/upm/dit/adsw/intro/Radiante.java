package es.upm.dit.adsw.intro;

/**
 * Esta interfaz define clases ubicadas que son capaces de emitir y recibir. 
 * @version 1.0
 * @author Alejandro Alonso
 */
public interface Radiante {

    /**
     * Este método devuelve las coordenadas de la posici'on del elemento.
     * @return Objeto de la clase Coordenada en el que se encuentra el elemento
     */
     public Punto getPosicion();
	
    /**
     * Este m'etodo determina si una coordenada tiene cobertura de un objeto
     * radiante
     * @param c Punto en el que se debe determinar la velocidad disponible
     * @return  Informa sobre la disponibilidad de cobertura
     */
    boolean enCobertura(Punto c);
}
