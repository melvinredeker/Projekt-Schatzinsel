package automatenManager;

/**
 * Eine Übergang besteht aus einem Ausgangszustand, einem Übergangszeichen und
 * ihr kann eine beliebige Menge von Nachfolgezuständen zugewiesen werden.
 * 
 * @author  Dierschke
 * @version 2018-10-15
 */
public class Uebergang extends Element {
    /**
     * Referenziert einen Zustand.
     */
    private Zustand ausgangszustand;

    /**
     * Referenziert ein Zeichen.
     */
    private Zeichen uebergangszeichen;

    /**
     * Verwaltet die Nachfolgezustände in einer Zustandsmenge.
     */
    private Zustandsmenge nachfolgezustaende;

    /**
     * Konstruiert eine neue Uebergang mit einer zunächst leeren Menge von Nachfolgezuständen.
     * 
     * @param   pVon    Ausgangszustand
     * @param   pMit    Uebergangszeichen
     */
    public Uebergang(Zustand pVon, Zeichen pMit) {
        this.ausgangszustand = pVon;
        this.uebergangszeichen = pMit;
        this.nachfolgezustaende = new Zustandsmenge();
    }

    /**
     * Gibt den Zustand des Uebergangs zurueck.
     * 
     * @return  Ausgangszustand
     */
    public Zustand gibZustand() {
        return this.ausgangszustand;
    }

    /**
     * Gibt das Zeichen des Uebergangs zurueck,
     * 
     * @return  Uebergangszeichen
     */
    public Zeichen gibZeichen() {
        return this.uebergangszeichen;
    }

    /**
     * Gibt die Menge der Nachfolgezustände des Uebergangs zurueck.
     * 
     * @return  Nachfolgezustände
     */
    public Zustandsmenge gibNachfolgezustaende() {
        return this.nachfolgezustaende;
    }

    @Override 
    public boolean istGleich(Element pElement) {
        Uebergang pUebergang = (Uebergang) pElement;
        return pUebergang.gibZustand().istGleich(this.gibZustand()) && pUebergang.gibZeichen().istGleich(this.gibZeichen());
    }

    @Override 
    public String gibBezeichnung() {
        return this.gibZustand().gibBezeichnung() + " x " + this.gibZeichen().gibBezeichnung() + " -> " + this.gibNachfolgezustaende().gibString();
    }

    /**
     * Fuegt der Uebergang einen weiteren Nachfolgezustand hinzu, sofern 
     * dieser nicht bereits in der Zustandsmenge enthalten ist.
     * 
     * @param   pNach   hinzuzufuegender Nachfolgezustand
     */
    public void hinzufuegenNachfolgezustand(Zustand pNach) {
        this.gibNachfolgezustaende().hinzufuegen(pNach);
    }

    /**
     * Entfernt einen konkreten Nachfolgezustand aus der Zustandsmenge,
     * sofern dieser Element der Zustandsmenge ist, sonst passiert nichts.
     * 
     * @param   pNach   zu entfernender Nachfolgezustand
     */
    public void entferneNachfolgezustand(Zustand pNach) {
        this.gibNachfolgezustaende().zumAnfang();
        while(!this.gibNachfolgezustaende().istAmEnde()) {
            if(this.gibNachfolgezustaende().gibAktuelles().istGleich(pNach)) {
                this.gibNachfolgezustaende().entferne();
            } else {
                this.gibNachfolgezustaende().zumNaechsten();
            }
        }
    }
}