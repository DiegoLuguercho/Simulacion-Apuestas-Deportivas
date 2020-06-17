package Clases;

import java.io.Serializable;

public class Password implements Serializable {

    private static final int LONGITUD_MIN = 5;
    private static final int LONGITUD_MAX = 20;
    private String clave;

    //constructor
    public Password(String clave) {
        this.clave = clave;
    }
    //get
    public String getClave() {
        return clave;
    }


    /**
     * Comprueba si la clave introducida esta dentro de los limites establecidos dentro de la
     * clase. Por defecto, la longitud minima es de 8 caracteres y la maxima es de 20.
     * @param string El valor a comprobar.
     * @return true si esta dentro de los limites establecidos, false si no.
     */
    public static boolean hasLongitudCorrecta(String string) {

        return string.length() >= LONGITUD_MIN && string.length() <= LONGITUD_MAX;
    }
    

    /**
     * Comprueba si la clave introducida es iugal a la clave real
     * @param clave
     * @return
     */
    public boolean sonCoincidentes(String clave) {
        return this.clave.equals(clave);
    }
}