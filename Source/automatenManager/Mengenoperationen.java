package automatenManager;

/**
 * TO-DO
 * 
 * @author  Dennis Dierschke
 * @version 2020-07-02
 */

public interface Mengenoperationen<Inhaltstyp extends Element> {
    /**
     * Bestimmt, ob ein Element mit der Bezeichnung bereits in der Menge verwaltet wird.
     * 
     * @param   pElement    zu ueberpruefendes Element
     * 
     * @return  wahr, wenn das Element bereits in der Menge enthalten ist
     */
    public boolean istVorhanden(Inhaltstyp pElement);
    
    /**
     * Bestimmt, ob die Menge leer ist.
     * 
     * @return  wahr, wenn leer, sonst falsch
     */
    public boolean istLeer();
    
    /**
     * Liefert die Anzahl an Elementen, die in der Menge enthalten sind.
     * 
     * @return  Anzahl an Elementen
     */
    public int anzahl();
    
    /**
     * Fuegt der Menge all die Elemente einer uebergebenen Menge hinzu, 
     * die noch nicht Teil der Menge sind.
     * 
     * @param   pMenge    uebergebene Menge
     */
    public void vereinigen(Menge<Inhaltstyp> pMenge);
    
    /**
     * Bestimmt die Vereinigungsmenge der Menge und einer uebergebenen Menge.
     * 
     * @param   pMenge    uebergebene Menge
     * 
     * @return  Vereinigungsmenge
     */
    public Menge<Inhaltstyp> bestimmeVereinigung(Menge<Inhaltstyp> pMenge);
    
    /**
     * Bestimmt die Schnittmenge der Menge und einer uebergebenen Menge.
     * 
     * @param   pMenge    uebergebene Menge
     * 
     * @return  Schnittmenge
     */
    public Menge<Inhaltstyp> bestimmeSchnitt(Menge<Inhaltstyp> pMenge);
    
    /**
     * Bestimmt die Symmetrische Differenz der Menge und einer uebergebenen Menge.
     * 
     * @param   pMenge    uebergebene Menge
     * 
     * @return  SymmetrischeDifferenz
     */
    public Menge<Inhaltstyp> bestimmeSymmetrischeDifferenz(Menge<Inhaltstyp> pMenge);
    
    /**
     * Bestimmt die Differenzmenge von der Menge und einer uebergebenen Menge (Menge ohne uebergebene Menge).
     * 
     * @param   pMenge    uebergebene Menge
     * 
     * @return  Differenzmenge
     */
    public Menge<Inhaltstyp> bestimmeDifferenz(Menge<Inhaltstyp> pMenge);
    
    /**
     * Gibt den Inhalt der Menge als String in der Form "{q0,q1,q2...qi}" zurueck.
     * 
     * @return  Inhalt der Menge als String
     */
    public String gibString();
    
    public Menge<Inhaltstyp> gibKopie();
}