package es.upm.dit.adsw.intro;


/**
 * Esta clase modela una antena de comunicaciones orientada en una direcci—n.
 * @author Juan C. Due–as, Alejandro Alonso
 */



public class AntenaDireccional implements Radiante {

    private double angulo;
    private double apertura;

    private double radio;
    private Punto posicion;

    /**
     * @param angulo çngulo (radianes) que forma el eje de la antena con
     * el eje de abcisas
     * @param apertura çngulo (radianes) de apertura de la antena respecto
     * al eje
     * @param radio Distancia m‡xima de cobertura de la antena
     * @param posicion Coordenadas del punto donde se encuentra situada
     * la antena
     */
    public AntenaDireccional(double angulo, double apertura,
            double radio, Punto posicion) {

        this.angulo = angulo;
        this.apertura = apertura;
        this.radio = radio;
        this.posicion = posicion;
    }

    /**
     * Consulta del valor del radio de la antena.
     * @return Valor actual del radio en radianes.
     */
    public double getRadio() {
        return radio;
    }
    /**
     * Asigna un nuevo valor al radio de la antena.
     * @param radio Nuevo valor en radianes del radio de la antena
     */
    public void setRadio(double radio) {
        this.radio = radio;
    }
    /**
     * Consulta del punto (coordenadas) donde se encuentra la antena.
     * @return El valor actual de las coordenadas de la posici—n del antena.
     */
    public Punto getPosicion() {
        return posicion;
    }
    /**
     * Asigna un nuevo valor del punto donde se encuentra la antena.
     * @param p Nuevo valor de la posici—n de la antena
     */
    public void setPosicion(Punto p) {
        this.posicion = p;
    }

    /**
     * Consulta del valor del ‡ngulo de la antena.
     * @return Valor actual del ‡ngulo.
     */
    public double getAngulo() {
        return angulo;
    }

    /**
     * Asigna un nuevo valor del ‡ngulo de la antena.
     * @param angulo Nuevo valor del ‡ngulo.
     */
    public void setAngulo(double angulo) {
        this.angulo = angulo;
    }

    /**
     * Consulta del valor de la apertura de la antena.
     * @return Valor actual en radianes de la apertura.
     */
    public double getApertura() {
        return apertura;
    }

    /**
     * Asigna un nuevo valor de la apertura de la antena.
     * @param apertura Nuevo valor en radianes de apertura.
     */
    public void setApertura(double apertura) {
        this.apertura = apertura;
    }

    /**
     *<p>Calcula si el punto est‡ bajo la cobertura de la antena.</p>
     *
     * @return si est‡ en el ‡rea de cobertura
     * @param p Punto del que se quiere determinar su cobertura
     */
    public boolean enCobertura(Punto p) {

        double distancia = p.distancia(posicion);
        if (distancia < 0.01) {
            return true;
        }
        if (distancia > radio) {
            return false;
        }
        
        Punto diferencia  = new Punto(p.getX() - posicion.getX(),
                                      p.getY() - posicion.getY());

        double anguloDiferencia = Math.atan2(diferencia.getY(), diferencia.getX());
        
        if (anguloDiferencia < 0) { 
            anguloDiferencia = (2 * Math.PI) + anguloDiferencia; 
            }

        double anguloMax = angulo + apertura / 2;
        if (angulo + apertura / 2 >= Math.PI * 2) {
            anguloMax = (angulo + apertura / 2) - (2 * Math.PI);
        }
        double anguloMin = angulo - apertura / 2;
        if (angulo - apertura / 2 < 0) {
            anguloMin = (2 * Math.PI) + (angulo - apertura / 2);
        }

        if (anguloMax > anguloMin) {
            return (anguloDiferencia > anguloMin)
                && (anguloDiferencia < anguloMax);
        }

        return (anguloDiferencia < anguloMax) || (anguloDiferencia > anguloMin);

    }

    /**
     * Devuelve un valor en radianes equivalene al par'ametro, pero comprendido
     * entre 0 y 2*PI
     * @param angulo Valor a normalizar
     * @return Valor normalizado
     */
    private double normalizarAngulo(double angulo) {
        while (angulo < 0) {
            angulo = angulo + 2 * Math.PI;
        }
        
        while (angulo >= 2 * Math.PI){
            angulo = angulo - 2 * Math.PI;
        }
        
        return angulo;
    }

    /**
     * Este m'etodo compara los atributos del objeto con el par'ametro del
     * m'etodo.
     * @param o Objeto con el que se quiere determinar la igualdad
     * @return Un valor booleano que indica si los elementos son o no
     * iguales
     */

    public boolean equals(Object o) {

        double error = 0.001;
        boolean igualAngulo = false;
        boolean igualApertura = false;
        
        if (o == null) { return false; }
        if (o == this) { return true;  }
        if (!(o instanceof AntenaDireccional)) { return false; }

        AntenaDireccional antena = (AntenaDireccional) o;

        igualAngulo = Math.abs 
           ((normalizarAngulo(angulo) - normalizarAngulo(antena.getAngulo())))
            < error;

        igualApertura = Math.abs 
        ((normalizarAngulo(apertura) - normalizarAngulo(antena.getApertura())))
         < error;
        
        return (posicion.equals(antena.getPosicion())
                 && (igualAngulo)
                 && (igualApertura)
                 && (Math.abs(antena.getRadio() - radio) < error)); 
    }

    /**
     * Genera una tira de caracteres con los par‡metros de la antena.
     * @return Tira de caracteres que representa la antena.
     */

    public String toString() {
        return  angulo + ", " + apertura + ", " + radio + ", "
        + posicion.toString();
    }


}
