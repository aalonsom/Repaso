/**
 * 
 */
package es.upm.dit.adsw.intro;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * @author aalonso
 *
 */
public class RegionTest {

	// Declaración de objetos para usar en las prubas
	
    private Region            unaRegion;
    private AntenaDireccional ad1;
    private AntenaDireccional ad2;
    private AntenaDireccional ad3;
    private AntenaDireccional ad1MismaPosicion;
    private AntenaDireccional ad4;
    private AntenaDireccional ad5;
    private AntenaDireccional ad6;
    private AntenaDireccional ad7;
    private AntenaDireccional adFuera;
    private AntenaDireccional adFuera1;

    private Punto pad1;
    private Punto padFuera;
    private Punto padFuera1;
    
    AntenaOmnidireccional ao1;
    AntenaOmnidireccional ao2;

    RouterWIFI rw3;
    RouterWIFI rw4;

    // 0 antenas
    Punto p0Cob1;
    Punto p0Cob2;

    // 1 antena
    Punto p1Cob1;
    Punto p1Cob2;

    // 2 antenas
    Punto p2Cob1;
    Punto p2Cob2;

    // 3 antenas
    Punto p3Cob1;

    // Antena Compuesta
    Punto posicionAC;
    AntenaDireccional adc1;
    AntenaDireccional adc2;
    AntenaDireccional adc3;
	
	/** Iniciación de las variables que se van a emplear en cada prueba
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		
        unaRegion = new Region();
        // Declaro una antena direccional
        pad1   = new Punto(80, 50);
        double dad1  = 25;
        double dao1  = 10;
        double anad1 = Math.PI / 2;
        double apad1 = Math.PI / 4;
        ao1 = new AntenaOmnidireccional(dao1, pad1);
        ad1 = new AntenaDireccional(anad1, apad1, dad1, pad1);
        //laVentana.pintarAntenaDireccional(ad1);

        //Declaro una antena direccional
        Punto pad2   = new Punto(20, 30);
        double dad2  = 20;
        double anad2 = 1.2 * Math.PI;
        double apad2 = Math.PI / 2;
        //System.out.println(Math.toDegrees(anad2 + apad2));
        ao2 = new AntenaOmnidireccional(dad2, pad2);
        ad2 = new AntenaDireccional(anad2, apad2, dad2, pad2);
        ad1MismaPosicion = new AntenaDireccional(anad2, apad2, dad2, pad1);

        
        Punto pad3 = new Punto(10, 30);
        double dad3  = 30;
        double drw3  = 10;
        double anad3 = -Math.PI / 2;
        double apad3 = Math.PI / 4;
        ad3 = new AntenaDireccional(anad3, apad3, dad3, pad3);
        rw3 = new RouterWIFI(drw3, pad3);

        Punto pad4 = new Punto(60, 50);
        double dad4  = 25;
        double anad4 = Math.PI / 3;
        double apad4 = Math.PI / 4;
        ad4 = new AntenaDireccional(anad4, apad4, dad4, pad4);
        rw4 = new RouterWIFI(dad4, pad4);

        Punto pad5 = new Punto(80, 70);
        ad5 = new AntenaDireccional(anad2, apad2, dad2, pad5);

        padFuera  = new Punto(-50, 50);
        adFuera = new AntenaDireccional(anad2, apad2, dad2, padFuera);

        Punto pad6 = new Punto(40, 20);
        ad6 = new AntenaDireccional(anad2, apad2, dad2, pad6);

        Punto pad7   = new Punto(20, 20);
        double dad7  = 20;
        double anad7 = 2 * Math.PI;
        double apad7 = 3 * Math.PI / 2;

        ad7 = new AntenaDireccional(anad7, apad7, dad7, pad7);

        //************************
        // PUNTOS PRUEBA COBERTURA
        //************************
        p0Cob1 = new Punto(40, 20);
        p0Cob2 = new Punto(50, 80);
        
        // 1 antena
        p1Cob1 = new Punto(10, 18);
        p1Cob2 = new Punto(61, 54);

        // 2 antenas
        p2Cob1 = new Punto(15, 25);
        p2Cob2 = new Punto(72, 55);

        // 3 antenas
        p3Cob1 = new Punto(78, 65);
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
	}

	@Test
//	public void test() {
//		fail("Not yet implemented");
//	}
    public void testAnadirAntena() {

		
		// Probar que se lanza la excepción con un par'ametro null		
		try {
			unaRegion.anadirAntena(null);
			fail("No se eleva excepci'on con par'ametro nulo");
		} catch (Exception e) {
			// Si ejecuta el manejador, el comportamiento es correcto
		}
		
		//Probar que se lanza la expcepci'on con antena fuera de regi'on
		try {
			unaRegion.anadirAntena(adFuera);
			fail("No se eleva excepci'on con par'ametro nulo");
		} catch (Exception e) {
			// Si ejecuta el manejador, el comportamiento es correcto
            assertEquals(0, unaRegion.nAntenasEnRegion());
		}
		
		// Se an~ade una antena y comprobamos que el n de anteas es correcto
		try {
            assertEquals(0, unaRegion.nAntenasEnRegion());
            unaRegion.anadirAntena(ad1);
            assertEquals(1, unaRegion.nAntenasEnRegion());			
		} catch (Exception e) {
			fail("Excepci'on elevada sin motivo");
		}
		
        // Crear antena en posici'on con antena previa.
        try {
            unaRegion.anadirAntena(ad1MismaPosicion);
            fail("No se lanza excepci'on. Antena en punto ocupado");
        } catch (Exception e) {
            assertEquals(1, unaRegion.nAntenasEnRegion());
        }
		
        // Se crean varias antenas y se comprueba
        // que el n'umero de las creadas es correcto
        try {
            unaRegion.anadirAntena(ad2);
            unaRegion.anadirAntena(ad3);
            assertEquals(3, unaRegion.nAntenasEnRegion());
        } catch (Exception e) {
            fail("Excepci'on con par'ametros correctos");
        }

        //Crear el n'umero m'aximo de antenas
        try {
            unaRegion.anadirAntena(ad4);
            unaRegion.anadirAntena(ad5);
            assertEquals(5, unaRegion.nAntenasEnRegion());
        } catch (Exception e) {
            fail("Excepci'on con par'ametros correctos");
        }

        // Crear antenas cuando ya hay el n'umero m'aximo
        try {
            unaRegion.anadirAntena(ad6);
            fail("No se lanza excepci√≥n. N√∫mero de antenas completo");
        } catch (Exception e) {
            assertEquals(5, unaRegion.nAntenasEnRegion());
        }

    }


}
