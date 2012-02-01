package es.upm.dit.adsw.intro;

/**
* Esta clase modela una antena de comunicaciones con cobertura circular. 
* @author Juan C. Due–as
*/


public class AntenaOmnidireccional extends AntenaDireccional {

	private double radio;
	private Punto posicion;

	/**
	 * @param radio Distancia m‡xima de cobertura de la antena
	 * @param posicion Coordenadas del punto donde se encuentra situada la antena
	 */
	public AntenaOmnidireccional(double radio, Punto posicion) {
            super(0, Math.PI * 2, radio, posicion);
	    this.radio    = radio;
	    this.posicion = posicion;
	}
	
	public boolean enCobertura (Punto unPunto) {
            return getPosicion().distancia(unPunto) <= getRadio();
	}
}
