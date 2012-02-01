package es.upm.dit.adsw.intro;

import java.util.ArrayList;
import java.util.Vector;

/**
 * Esta clase permite definir un conjunto de antenas en una regi'on de un plano.
 * Facilita el c'alculo de la cobertura en un punto, al comprobar cuantas
 * antenas le tienen en su cobertura
 * @author aalonso
 *
 */
public class Region {

    private final int X_MAX = 100;
    private final int Y_MAX = 100;
    public final int N_MAX_ANTENAS = 5;

    private ArrayList <Radiante> laRegion;
    private int nAntenas = 0;
/**
 * Constructor de la clase.
 */
    public Region() {
        laRegion = new ArrayList<Radiante>();
        nAntenas = 0;
    }

    private boolean estaDentro(Punto unPunto) {
        return    unPunto.getX() >= 0
               && unPunto.getX() < X_MAX
               && unPunto.getY() >= 0
               && unPunto.getY() < Y_MAX;
     }

    /**
     * A~nade una antena a la regi'on.
     * @param unaAntena La antena a a~nadir
     * @throws Exception Se eleva excepc'on en las siguientes situaciones:
     * - El par'ametro que se pasa vale null
     * - El punto est'a fuera del plano
     * - Ya se ha a~nadido el n'umero m'aximo de antenas
     * - Ya hay una antena en la posici'on de la antena pasada coo par'ametro
     */

    public void anadirAntena(Radiante unaAntena)
           throws Exception {

        if (unaAntena == null) {
            throw new Exception("Par‡metro con valor null");
        }

        if (!estaDentro(unaAntena.getPosicion())) {
            throw new Exception("Fuera del plano");
        }

        if (nAntenas == N_MAX_ANTENAS) {
            throw new Exception("Limite de antenas alcanzado");
        }

        if (hayAntena(unaAntena.getPosicion()) != null) {
            throw new Exception("Ya hay una antena en la posici—n");
        }

        laRegion.add(unaAntena);


        nAntenas++;

    }
/**
 * Determina con cuantas antenas tiene cobertura el punto dado.
 * @param unPunto El punto para analizar su cobertura
 * @return El n'umero de antenas con cobertura
 * @throws Exception Se eleva excepc'on en las siguientes situaciones:
 * - El par'ametro que se pasa vale null
 * - El punto est'a fuera del plano
 */

    public int nAntenasConCobertura(Punto unPunto) throws Exception {
        int nEnCobertura = 0;

        if (unPunto == null) {
            throw new Exception("Par‡metro con valor null");
        }

        if (!estaDentro(unPunto)) {
                throw new Exception("Fuera del plano");
            }

        for (Radiante objeto: laRegion) {
            if (objeto.enCobertura(unPunto)){
                nEnCobertura++;
            }
        }
        return nEnCobertura;
    }

    /**
     * Devuelve el n'umero de antenas definidas en la reg'on.
     * @return N'umero de antenas en la regi'on
     */
    public int nAntenasEnRegion() {
        return nAntenas;
    }

    /**
     * Comprueba si hay una antena creada en un punto dado.
     * @param unPunto el punto donde hay que determinar si hay antena
     * @return Devuelve la antena, o null si no hay antena
     * @throws Exception
     * Se eleva excepc'on en las siguientes situaciones:
     * - El par'ametro que se pasa vale null
     * - El punto est'a fuera del plano
     */

    public Radiante hayAntena(Punto unPunto)
           throws Exception {

        if (unPunto == null) {
            throw new Exception("Par‡metro con valor null");
        }

        if (!estaDentro(unPunto)) {
            throw new Exception("Fuera del plano");
        }

        for (Radiante objeto: laRegion) {
            if    (unPunto.getX() == objeto.getPosicion().getX()
                && unPunto.getY() == objeto.getPosicion().getY()) {
                return objeto;
            }
        }
        return null;
    }

    
    public int velocidadTransmisionWIFI(Punto unPunto)
          throws Exception {
        
        int velocidadMax = 0;
        
        for (Radiante objeto: laRegion) {
            if (objeto instanceof RouterWIFI) {
                RouterWIFI router = (RouterWIFI) objeto; 
                if (router.velocidadTransmisionWIFI(unPunto) >= velocidadMax) {
                    velocidadMax = router.velocidadTransmisionWIFI(unPunto);
                }
            }
        }
        return velocidadMax;
    }
    
    /**
     * Genera una tira de caracteres con los par‡metros de las antenas de la
     * regi'on.
     * @return Tira de caracteres que representa la regi'on.
     */

    public String toString() {

        String antenasRegion = "";

        for (Radiante objeto : laRegion) {
            antenasRegion = antenasRegion + objeto.toString() + "\n";
        }
        return antenasRegion;
    }

}
