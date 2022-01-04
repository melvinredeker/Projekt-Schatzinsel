package model.Automat.Menge;

/**
 * Ein Element besitzt eine Bezeichnung.
 * 
 * @author  Tristan Lippold
 * @version 2020-07-03
 */
public abstract class Element {
    /**
     * Verwaltet die generische Bezeichnung.
     */
    protected String bezeichnung;
    
    /**
     * Gibt die Bezeichnung des Elements zurueck.
     * 
     * @return  Bezeichnung vom Element
     */
    public String gibBezeichnung() {
        return this.bezeichnung;
    }
    
    /**
     * Bestimmt, ob die Bezeichnungen der beiden Elemente gleich ist.
     * 
     * @param   pElement   Element
     * 
     * @return  wahr, wenn die Bezeichnungen beider Elemente gleich sind
     */
    public boolean istGleich(Element pElement) {
        return this.gibBezeichnung().equals(pElement.gibBezeichnung());
    }
}