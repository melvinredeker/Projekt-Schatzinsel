package model.Automat;
 
import model.Automat.Menge.*;

/**
 * Ein Zeichen ist eine Spezialisierung eines Elementes
 * und muss die Bezeichnungslaenge 1 besitzen.
 * 
 * @author  Tristan Lippold
 * @version 2020-07-03
 */
public class Zeichen extends Element {  
    /**
     * Legt die maximale Laenge aller Zeichen fest.
     */
    public static final byte MAX = 1;
    
    /**
     * Konstruiert ein neues Zeichen mit der angegebenen Bezeichnung.
     * 
     * @param   pBezeichnung    Bezeichnung des Zeichens
     */
    private Zeichen(String pBezeichnung) {
        super.bezeichnung = pBezeichnung;
    }
    
    /**
     * Erstellt ein neues Zeichen, sofern die angegebene Bezeichnung erlaubt ist.
     * 
     * @param   pBezeichnung    gewuenschte Bezeichnung
     * 
     * @return  neues Zeichen, wenn die angegebene Bezeichnung erlaubt ist, sonst null
     */
    public static Zeichen erstelleZeichen(String pBezeichnung) {
        if(Zeichen.istErlaubt(pBezeichnung)) {
            return new Zeichen(pBezeichnung);
        } else {
            return null;
        }
    }
    
    /**
     * Bestimmt, ob die Bezeichnung erlaubt ist.
     * 
     * @param   pBezeichnung    zu ueberpruefende Bezeichnung
     * 
     * @return  wahr, wenn die Bezeichnung erlaubt ist
     */
    private static boolean istErlaubt(String pBezeichnung) {
        return pBezeichnung.length() <= Zeichen.MAX && pBezeichnung.length() > 0;
    }
}