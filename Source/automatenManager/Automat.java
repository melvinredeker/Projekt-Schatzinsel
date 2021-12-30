package automatenManager;

/**
 * Ein Automat ist ein endlicher Automat und besteht aus einer Zustandsmenge,
 * einem Startzustand, einem Eingabealphabet, einer Menge akzeptierender
 * Endzustände und einer Übergangsrelation.
 * 
 * @author  Lippold
 * @version 2020-08-30
 */
public class Automat {
    /**
     * Referenziert eine Zustandsmenge, in der die Zustände verwaltet werden.
     */
    private Zustandsmenge q;

    /**
     * Referenziert ein Alphabet, in dem die Eingabezeichen verwaltet werden.
     */
    private Alphabet e;

    /**
     * Referenziert einen Startzustand, der Element der Zustandsmenge sein muss.
     */
    private Zustand s;

    /**
     * Referenziert eine Übergangsrelation, in der die Übergänge verwaltet werden.
     * Für alle Übergänge (qi x e -> qj) gilt, dass qi und qj Element der 
     * Zustandsmenge Q und e Element des Eingabealphabets sein müssen.
     */
    private Relation d;

    /**
     * Referenziert eine Zustandsmenge, in der die akzeptierenden Endzustände
     * verwaltet werden, die alle Element der Zustandsmenge sein müssen.
     */
    private Zustandsmenge f;

    /**
     * Konstruiert einen Automaten mit leerer Zustandsmenge, nicht gesetztem Startzustand,
     * leerem Eingabealphabet, leere Menge akzeptierender Endzustände und nicht
     * definierter Übergangsrelation.
     */
    public Automat() {
        this.q = new Zustandsmenge();
        this.e = new Alphabet();
        this.s = null;
        this.d = new Relation();
        this.f = new Zustandsmenge();
    }

    /**
     * Gibt die Zustandsmenge zurück.
     * 
     * @return  Zustandsmenge
     */
    public Zustandsmenge gibQ() {
        return this.q;
    }
    
    /**
     * Gibt das Eingabealphabet zurück.
     * 
     * @return  Eingabealphabet
     */
    public Alphabet gibE() {
        return this.e;
    }
    
    /**
     * Gibt den Startzustand zurück.
     * 
     * @return  Startzustand
     */
    public Zustand gibS() {
        return this.s;
    }

    /**
     * Gibt die Übergangsrelation zurück.
     * 
     * @return  Übergangsrelation
     */
    public Relation gibD() {
        return this.d;
    }
    
    /**
     * Gibt die Menge der akzeptierenden Endzustände zurück.
     * 
     * @return  akzeptierende Endzustände
     */
    public Zustandsmenge gibF() {
        return this.f;
    }

    /**
     * Fügt dem Eingabealphabet ein neues Zeichen hinzu, falls
     * dieses nicht bereits in der Menge enthalten ist.
     * 
     * @param   pBezeichnung    Bezeichnung des Zeichens
     */
    public void neuesZeichen(String pBezeichnung) {
        this.e.hinzufuegen(Zeichen.erstelleZeichen(pBezeichnung));
    }

    /**
     * Fügt der Zustandsmenge einen neuen Zustand hinzu, falls 
     * dieser nicht bereits in der Menge enthalten ist.
     * 
     * @param   pNummer         Nummer des Zustands
     */
    public void neuerZustand(int pNummer) {
        this.q.hinzufuegen(Zustand.erstelleZustand(pNummer));
    }
    
    /**
     * Fügt der Zustandsmenge einen neuen Zustand hinzu, falls 
     * dieser nicht bereits in der Menge enthalten ist.
     * 
     * @param   pNummer         Nummer des Zustands
     * @param   pBezeichnung    Bezeichnung des Zustands
     */
    public void neuerZustand(int pNummer, String pBezeichnung) {
        this.q.hinzufuegen(Zustand.erstelleZustand(pNummer, pBezeichnung));
    }

    /**
     * Fügt der Übergangsrelation einen neuen Übergang (qi x e -> qj) hinzu.
     * 
     * Falls die Zustände bzw. das Zeichen noch nicht existieren, werden
     * sie erstellt und den Mengen hinzugefügt, es seidenn dass die
     * Bezeichnungen nicht erlaubt sind, dann geschieht nichts.
     * 
     * @param   pVon    Ausgangszustand
     * @param   pMit    Zeichen
     * @param   pNach   Nachfolgezustand
     */
    public void neuerUebergang(int pVon, String pMit, int pNach) {
        // erzeuge temporäre Elemente
        Zustand von = Zustand.erstelleZustand(pVon);
        Zeichen mit = Zeichen.erstelleZeichen(pMit);
        Zustand nach = Zustand.erstelleZustand(pNach);

        // falls alle drei Elemente erzeugt werden konnten
        if(von != null && mit != null && nach != null) {
            // dann fuege diese Elemente den Mengen hinzu, 
            // (falls diese noch nicht Element der Mengen sind)
            this.neuerZustand(pVon);
            this.neuerZustand(pNach);
            this.neuesZeichen(pMit);
        }

        // falls jetzt alle drei Elemente Bestandteil ihrer Mengen sind
        if(this.q.istVorhanden(von) && this.e.istVorhanden(mit) && this.q.istVorhanden(nach)) {
            // dann füge den Übergang hinzu
            this.d.hinzufuegenUebergang(von, mit, nach);
        }
    }
    
    /**
     * Fügt der Übergangsrelation einen neuen Übergang (qi x e -> qj) hinzu.
     * 
     * Falls die Zustände bzw. das Zeichen noch nicht existieren, werden
     * sie erstellt und den Mengen hinzugefügt, es seidenn dass die
     * Bezeichnungen nicht erlaubt sind, dann geschieht nichts.
     * 
     * @param   pVon                Ausgangszustand
     * @param   pVonBezeichnung     Bezeichnung Ausgangszustand
     * @param   pMit                Zeichen
     * @param   pNach               Nachfolgezustand
     * @param   pNachBezeichnung    Bezeichnung Nachfolgezustand
     */
    public void neuerUebergang(int pVon, String pVonBezeichnung, String pMit, int pNachNummer, String pNachBezeichnung) {
        // erzeuge temporäre Elemente
        Zustand von = Zustand.erstelleZustand(pVon, pVonBezeichnung);
        Zeichen mit = Zeichen.erstelleZeichen(pMit);
        Zustand nach = Zustand.erstelleZustand(pNachNummer, pNachBezeichnung);

        // falls alle drei Elemente erzeugt werden konnten
        if(von != null && mit != null && nach != null) {
            // dann fuege diese Elemente den Mengen hinzu, 
            // (falls diese noch nicht Element der Mengen sind)
            this.neuerZustand(pVon, pVonBezeichnung);
            this.neuerZustand(pNachNummer, pNachBezeichnung);
            this.neuesZeichen(pMit);
        }

        // falls jetzt alle drei Elemente Bestandteil ihrer Mengen sind
        if(this.q.istVorhanden(von) && this.e.istVorhanden(mit) && this.q.istVorhanden(nach)) {
            // dann fuege den Übergang hinzu
            this.d.hinzufuegenUebergang(von, mit, nach);
        }
    }

    /**
     * Legt den Startzustand fest.
     * 
     * @param   pNummer         Nummer des Zustands
     */
    public void setzeStartzustand(int pNummer) {
        Zustand temp = Zustand.erstelleZustand(pNummer);
        if(this.gibQ().istVorhanden(temp)) {
            this.s = this.gibQ().gibElement(temp);
        }
    }

    /**
     * Fügt einen in der Zustandsmenge vorhandenen Zustand 
     * der Menge der akzeptierenden Endzustände hinzu.
     * 
     * @param   pNummer         Nummer des Zustands
     */
    public void setzeZustandAlsEndzustand(int pNummer) {
        Zustand temp = Zustand.erstelleZustand(pNummer);
        if(this.gibQ().istVorhanden(temp)) {
            this.gibF().hinzufuegen(this.gibQ().gibElement(temp));
        }
    }
    
    /**
     * Entfernt einen Zustand aus der Menge der Zustände.
     * 
     * @param   pNummer         Nummer des zu entfernenden Zustands
     */
    public void entferneZustand(int pNummer) {
        Zustand temp = Zustand.erstelleZustand(pNummer);
        this.gibQ().entferne(temp);
        this.gibF().entferne(temp);
        if(this.gibS().istGleich(temp)) {
            this.s = null;
        }
        this.entferneUebergaengeMitAusgangszustand(pNummer);
        this.entferneUebergaengeMitNachfolgezustand(pNummer);
    }
    
    /**
     * Entfernt ein Zeichen aus dem Eingabealphabet.
     * 
     * @param   pBezeichnung    Bezeichnung des zu entfernenden Zeichens
     */
    public void entferneZeichen(String pBezeichnung) {
        this.gibE().entferne(Zeichen.erstelleZeichen(pBezeichnung));
        this.entferneUebergaengeMitZeichen(pBezeichnung);
    }

    /**
     * Entfernt einen konkreten Übergang (qi x e -> qj) aus der Übergangsrelation.
     * 
     * @param   pVon    Ausgangszustand
     * @param   pMit    Zeichen
     * @param   pNach   Nachfolgezustand
     */
    public void entferneUebergang(int pVon, String pMit, int pNach) {
        this.gibD().entferneUebergang(Zustand.erstelleZustand(pVon), Zeichen.erstelleZeichen(pMit), Zustand.erstelleZustand(pNach));
    }

    /**
     * Entfernt alle Übergänge einer Kombination (qi,e) aus der Relation. 
     * 
     * @param   pVon    Ausgangszustand
     * @param   pMit    Zeichen
     */
    public void entferneUebergaengeMitAusgangszustandUndZeichen(int pVon, String pMit) {
        this.gibD().entferneUebergaengeMitAusgangszustandUndZeichen(Zustand.erstelleZustand(pVon), Zeichen.erstelleZeichen(pMit));
    }

    /**
     * Entfernt alle Übergänge eines Ausgangszustandes (qi) aus der Relation. 
     * 
     * @param   pVon    Ausgangszustand
     */
    public void entferneUebergaengeMitAusgangszustand(int pVon) {
        this.gibD().entferneUebergaengeMitAusgangszustand(Zustand.erstelleZustand(pVon));
    }

    /**
     * Entfernt alle Übergänge eine Übergangszeichens (e) aus der Relation.
     * 
     * @param   pMit  Übergangszeichen
     */
    public void entferneUebergaengeMitZeichen(String pMit) {
        this.gibD().entferneUebergaengeMitZeichen(Zeichen.erstelleZeichen(pMit));
    }

    /**
     * Entfernt alle Übergänge eines Nachfolgezustandes (qj) aus der Relation.
     * 
     * @param   pNach   Nachfolgezustand  
     */
    public void entferneUebergaengeMitNachfolgezustand(int pNach) {
        this.gibD().entferneUebergaengeMitNachfolgezustand(Zustand.erstelleZustand(pNach));
    }

    /**
     * Entfernt einen Zustand aus der Menge der akzeptierenden Endzustände.
     * 
     * @param   pNummer         Nummer des zu entfernenden Zustands
     */
    public void entferneEndzustand(int pNummer) {
        this.gibF().entferne(Zustand.erstelleZustand(pNummer));
    }

    /**
     * Bestimmt, ob der Automat vollständig definiert wurde.
     * 
     * @return  wahr, wenn die drei Mengen nicht leer sind und ein Startzustand festgelegt wurde
     */
    public boolean istVollstaendigDefiniert() {
        return this.gibQ().anzahl() > 0 && this.gibS() != null && this.gibE().anzahl() > 0 && this.gibF().anzahl() > 0;
    }

    /**
     * Bestimmt, ob der Automat deterministisch ist, also ob alle Kombinationen (qi,e)
     * maximal in einen Nachfolgezustand überführt werden.
     * 
     * @return  wahr, wenn die Übergangsrelation für alle Kombinationen (qi,e) eindeutig ist
     */
    public boolean istDeterministisch() {
        this.gibD().zumAnfang();
        while(!this.gibD().istAmEnde()) {
            if(this.gibD().gibAktuelles().gibNachfolgezustaende().anzahl() > 1) {
                return false;
            }
            this.gibD().zumNaechsten();
        }
        return true;
    }
}