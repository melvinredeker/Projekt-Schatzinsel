package model.Automat;
 
/**
 * Ein Automat ist ein endlicher Automat und besteht aus einer Zustandsmenge,
 * einem Startzustand, einem Eingabealphabet, einer Menge akzeptierender
 * Endzustaende und einer Uebergangsrelation.
 * 
 * @author  Dennis Dierschke, Tristan Lippold
 * @version 2021-12-30
 */
public class Automat {
    /**
     * Referenziert eine Zustandsmenge, in der die Zustaende verwaltet werden.
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
     * Referenziert eine Uebergangsrelation, in der die Uebergaenge verwaltet werden.
     * Fuer alle Uebergaenge (qi x e -> qj) gilt, dass qi und qj Element der 
     * Zustandsmenge Q und e Element des Eingabealphabets sein muessen.
     */
    private Relation d;

    /**
     * Referenziert eine Zustandsmenge, in der die akzeptierenden Endzustaende
     * verwaltet werden, die alle Element der Zustandsmenge sein muessen.
     */
    private Zustandsmenge f;

    /**
     * Konstruiert einen Automaten mit leerer Zustandsmenge, nicht gesetztem Startzustand,
     * leerem Eingabealphabet, leere Menge akzeptierender Endzustaende und nicht
     * definierter Uebergangsrelation.
     */
    public Automat() {
        this.q = new Zustandsmenge();
        this.e = new Alphabet();
        this.s = null;
        this.d = new Relation();
        this.f = new Zustandsmenge();
    }

    /**
     * Gibt die Zustandsmenge zurueck.
     * 
     * @return  Zustandsmenge
     */
    public Zustandsmenge gibQ() {
        return this.q;
    }
    
    /**
     * Gibt das Eingabealphabet zurueck.
     * 
     * @return  Eingabealphabet
     */
    public Alphabet gibE() {
        return this.e;
    }
    
    /**
     * Gibt den Startzustand zurueck.
     * 
     * @return  Startzustand
     */
    public Zustand gibS() {
        return this.s;
    }

    /**
     * Gibt die Uebergangsrelation zurueck.
     * 
     * @return  Uebergangsrelation
     */
    public Relation gibD() {
        return this.d;
    }
    
    /**
     * Gibt die Menge der akzeptierenden Endzustaende zurueck.
     * 
     * @return  akzeptierende Endzustaende
     */
    public Zustandsmenge gibF() {
        return this.f;
    }

    /**
     * Fuegt dem Eingabealphabet ein neues Zeichen hinzu, falls
     * dieses nicht bereits in der Menge enthalten ist.
     * 
     * @param   pBezeichnung    Bezeichnung des Zeichens
     */
    public void neuesZeichen(String pBezeichnung) {
        this.e.hinzufuegen(Zeichen.erstelleZeichen(pBezeichnung));
    }

    /**
     * Fuegt der Zustandsmenge einen neuen Zustand hinzu, falls 
     * dieser nicht bereits in der Menge enthalten ist.
     * 
     * @param   pNummer         Nummer des Zustands
     */
    public void neuerZustand(int pNummer) {
        this.q.hinzufuegen(Zustand.erstelleZustand(pNummer));
    }
    
    /**
     * Fuegt der Zustandsmenge einen neuen Zustand hinzu, falls 
     * dieser nicht bereits in der Menge enthalten ist.
     * 
     * @param   pNummer         Nummer des Zustands
     * @param   pBezeichnung    Bezeichnung des Zustands
     */
    public void neuerZustand(int pNummer, String pBezeichnung) {
        this.q.hinzufuegen(Zustand.erstelleZustand(pNummer, pBezeichnung));
    }

    /**
     * Fuegt der Uebergangsrelation einen neuen Uebergang (qi x e -> qj) hinzu.
     * 
     * Falls die Zustaende bzw. das Zeichen noch nicht existieren, werden
     * sie erstellt und den Mengen hinzugeFuegt, es seidenn dass die
     * Bezeichnungen nicht erlaubt sind, dann geschieht nichts.
     * 
     * @param   pVon    Ausgangszustand
     * @param   pMit    Zeichen
     * @param   pNach   Nachfolgezustand
     */
    public void neuerUebergang(int pVon, String pMit, int pNach) {
        // erzeuge temporaere Elemente
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
            // dann fuege den Uebergang hinzu
            this.d.hinzufuegenUebergang(von, mit, nach);
        }
    }
    
    /**
     * Fuegt der Uebergangsrelation einen neuen Uebergang (qi x e -> qj) hinzu.
     * 
     * Falls die Zustaende bzw. das Zeichen noch nicht existieren, werden
     * sie erstellt und den Mengen hinzugeFuegt, es seidenn dass die
     * Bezeichnungen nicht erlaubt sind, dann geschieht nichts.
     * 
     * @param   pVonNr              Ausgangszustand
     * @param   pVonBezeichnung     Bezeichnung Ausgangszustand
     * @param   pMit                Zeichen
     * @param   pNachNr             Nachfolgezustand
     * @param   pNachBez            Bezeichnung Nachfolgezustand
     */
    public void neuerUebergang(int pVonNr, String pVonBez, String pMit, int pNachNr, String pNachBez) {
        // erzeuge temporaere Elemente
        Zustand von = Zustand.erstelleZustand(pVonNr, pVonBez);
        Zeichen mit = Zeichen.erstelleZeichen(pMit);
        Zustand nach = Zustand.erstelleZustand(pNachNr, pNachBez);

        // falls alle drei Elemente erzeugt werden konnten
        if(von != null && mit != null && nach != null) {
            // dann fuege diese Elemente den Mengen hinzu, 
            // (falls diese noch nicht Element der Mengen sind)
            this.neuerZustand(pVonNr, pVonBez);
            this.neuerZustand(pNachNr, pNachBez);
            this.neuesZeichen(pMit);
        }

        // falls jetzt alle drei Elemente Bestandteil ihrer Mengen sind
        if(this.q.istVorhanden(von) && this.e.istVorhanden(mit) && this.q.istVorhanden(nach)) {
            // dann fuege den Uebergang hinzu
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
     * Fuegt einen in der Zustandsmenge vorhandenen Zustand 
     * der Menge der akzeptierenden Endzustaende hinzu.
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
     * Entfernt einen Zustand aus der Menge der Zustaende.
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
     * Entfernt einen konkreten Uebergang (qi x e -> qj) aus der Uebergangsrelation.
     * 
     * @param   pVon    Ausgangszustand
     * @param   pMit    Zeichen
     * @param   pNach   Nachfolgezustand
     */
    public void entferneUebergang(int pVon, String pMit, int pNach) {
        this.gibD().entferneUebergang(Zustand.erstelleZustand(pVon), Zeichen.erstelleZeichen(pMit), Zustand.erstelleZustand(pNach));
    }

    /**
     * Entfernt alle Uebergaenge einer Kombination (qi,e) aus der Relation. 
     * 
     * @param   pVon    Ausgangszustand
     * @param   pMit    Zeichen
     */
    public void entferneUebergaengeMitAusgangszustandUndZeichen(int pVon, String pMit) {
        this.gibD().entferneUebergaengeMitAusgangszustandUndZeichen(Zustand.erstelleZustand(pVon), Zeichen.erstelleZeichen(pMit));
    }

    /**
     * Entfernt alle Uebergaenge eines Ausgangszustandes (qi) aus der Relation. 
     * 
     * @param   pVon    Ausgangszustand
     */
    public void entferneUebergaengeMitAusgangszustand(int pVon) {
        this.gibD().entferneUebergaengeMitAusgangszustand(Zustand.erstelleZustand(pVon));
    }

    /**
     * Entfernt alle Uebergaenge eine Uebergangszeichens (e) aus der Relation.
     * 
     * @param   pMit  Uebergangszeichen
     */
    public void entferneUebergaengeMitZeichen(String pMit) {
        this.gibD().entferneUebergaengeMitZeichen(Zeichen.erstelleZeichen(pMit));
    }

    /**
     * Entfernt alle Uebergaenge eines Nachfolgezustandes (qj) aus der Relation.
     * 
     * @param   pNach   Nachfolgezustand  
     */
    public void entferneUebergaengeMitNachfolgezustand(int pNach) {
        this.gibD().entferneUebergaengeMitNachfolgezustand(Zustand.erstelleZustand(pNach));
    }

    /**
     * Entfernt einen Zustand aus der Menge der akzeptierenden Endzustaende.
     * 
     * @param   pNummer         Nummer des zu entfernenden Zustands
     */
    public void entferneEndzustand(int pNummer) {
        this.gibF().entferne(Zustand.erstelleZustand(pNummer));
    }

    /**
     * Bestimmt, ob der Automat vollstaendig definiert wurde.
     * 
     * @return  wahr, wenn die drei Mengen nicht leer sind und ein Startzustand festgelegt wurde
     */
    public boolean istVollstaendigDefiniert() {
        return this.gibQ().anzahl() > 0 && this.gibS() != null && this.gibE().anzahl() > 0 && this.gibF().anzahl() > 0;
    }

    /**
     * Bestimmt, ob der Automat deterministisch ist, also ob alle Kombinationen (qi,e)
     * maximal in einen Nachfolgezustand ueberfuehrt werden.
     * 
     * @return  wahr, wenn die Uebergangsrelation fuer alle Kombinationen (qi,e) eindeutig ist
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