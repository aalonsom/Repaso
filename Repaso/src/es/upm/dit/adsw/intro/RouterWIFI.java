package es.upm.dit.adsw.intro;


public class RouterWIFI implements Radiante{


/**
* Esta clase modela una routr WIFI de comunicaciones de un tipo dado
* @author Alejandro Alonso
*/

        private double radio;
        private Punto posicion;

        /**
         * @param radio Distancia m‡xima de cobertura del router
         * @param posicion Coordenadas del punto donde se encuentra situado
         * el router
         */
        public RouterWIFI(double radio, Punto posicion) {
                this.radio    = radio;
                this.posicion = posicion;
        }

        /**
         * Consulta del valor del radio del router.
         * @return Valor actual del radio en radianes.
         */
        public double getRadio() {
                return radio;
        }
        
        /**
         * Asigna un nuevo valor al radio del router.
         * @param radio Nuevo valor del radio del router 
         */
        public void setRadio(double radio) {
                this.radio = radio;
        }
        /**
         * Consulta del punto (coordenadas) donde se encuentra el router.
         * @return El valor actual de las coordenadas de la posici—n del router.
         */
        public Punto getPosicion() {
                return posicion;
        }
        
        /** Asigna un nuevo valor del punto donde se encuentra el router.
         * @param p Nuevo valor de la posici—n del router
         */
        public void setPosicion(Punto p) {
                this.posicion = p;
        }
        
        /**
           * Calcula si el punto est‡ bajo la cobertura del router.
           * 
           * @return si est‡ en el ‡rea de cobertura
           * @param p Punto del que se quiere determinar su cobertura
        */

        public boolean enCobertura(Punto p) {
                return getPosicion().distancia(p) <= getRadio();
        }

        /**
         * Este m'etodo determina la velocidad de transmisi—n disponible en una
         * posici'on dada.
         * @param p Coordenada de la posici'on 
         * @return Devuelve el valor de la velocidad en Mbits/s
         * @throws Excepction Si el par'ametro es nulo
         */
        public int velocidadTransmisionWIFI (Punto p)
               throws Exception {

            if (p == null) {
                throw new Exception("Coordenada nula");
            }

            if (!enCobertura(p)) { return 0; }

            double distancia = getPosicion().distancia(p);
            
            if (distancia / radio < 0.5 )  {return 55;}
            if (distancia / radio < 0.75 ) {return 33;}
            return 11;

        }
        public static void main(String[] args) {
            RouterWIFI unRouter = new RouterWIFI (10, new Punto (10, 10));

            try {
                System.out.println(unRouter.velocidadTransmisionWIFI(new Punto(0,2)));                
                System.out.println(unRouter.velocidadTransmisionWIFI(new Punto(0,5)));                
                System.out.println(unRouter.velocidadTransmisionWIFI(new Punto(0,6)));                
                System.out.println(unRouter.velocidadTransmisionWIFI(new Punto(0,8)));                
                System.out.println(unRouter.velocidadTransmisionWIFI(new Punto(0,510)));                
                
            } catch (Exception e) {
                // TODO: handle exception
            }
        }
}
