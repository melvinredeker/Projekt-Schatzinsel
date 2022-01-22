package model.Automat.Menge;
 
/**
 * Objekte der generischen Klasse Menge verwalten beliebig viele Inhaltsobjekte,
 * allerdings darf jedes Inhaltsobjekt nur einmal in der Menge vorkommen.
 * Jede Menge muss ueber alle Mengenoperationen verfuegen.
 * 
 * Anmerkung: Der Inhaltstyp muss eine Spezialisierung eines Elements sein, 
 * also muss der konkrete Inhaltstyp von der Klasse Element erben.
 * 
 * @author  Tristan Lippold, Dennis Dierschke
 * @version 2021-12-30
 */
public abstract class Menge<Inhaltstyp extends Element> implements Mengenoperationen<Inhaltstyp> {
    /**
     * Verwaltet die Elemente in einer NRW-List.
     */
    protected List<Inhaltstyp> elemente;

    /**
     * Gibt eine leere Menge zurueck.
     * 
     * @return  leere Menge
     */
    public abstract Menge<Inhaltstyp> erstelleLeereMenge();



    // Operationen auf der NRW-List



    /**
     * Setzt den Zeiger fuer das aktuelle Objekt auf den Anfang der NRW-List.
     */
    public void zumAnfang() {
        this.elemente.toFirst();
    }

    /**
     * Setzt den Zeiger fuer das aktuelle Objekt auf das Ende der NRW-List.
     */
    public void zumEnde() {
        this.elemente.toLast();
    }

    /**
     * Setzt den Zeiger fuer das aktuelle Objekt auf das naechste Objekt der NRW-List.
     */
    public void zumNaechsten() {
        this.elemente.next();
    }

    /**
     * Bestimmt, ob sich der Zeiger fuer das aktuelle Objekt am Ende der NRW-List befindet.
     * 
     * @return  wahr, wenn Zeiger am Ende der NRW-List
     */
    public boolean istAmEnde() {
        return !this.elemente.hasAccess();
    }

    /**
     * Bestimmt, ob die die NRW-List leer ist.
     * 
     * @return  wahr, wenn leer, sonst falsch
     */
    public boolean istLeer() {
        return this.elemente.isEmpty();
    }

    /**
     * Liefert das Inhaltsobjekt aus der Menge, auf das der Zeiger fuer 
     * das aktuelle Objekt in der NRW-List verweist.
     * 
     * @return  aktuelles Inhaltsobjekt
     */
    public Inhaltstyp gibAktuelles() {
        return this.elemente.getContent();
    }

    /**
     * Entfernt aus der NRW-List das aktuelle Objekt.
     */
    public void entferne() {
        this.elemente.remove();
    }



    // Operationen auf der Menge



    /**
     * Entfernt aus der Menge das uebergebene Element,
     * sofern das Element Teil der Menge ist.
     * 
     * @param   pElement    zu entfernendes Element
     */
    public void entferne(Inhaltstyp pElement) {
        Inhaltstyp aktuelles;
        this.zumAnfang();
        while(!this.istAmEnde()) {
            aktuelles = this.gibAktuelles();
            if(aktuelles.istGleich(pElement)) {
                this.entferne();
            }
            this.zumNaechsten();
        }
    }

    /**
     * Fuegt der Menge ein uebergegebenes Element hinzu,
     * sofern das Element existiert und noch nicht Teil der Menge ist.
     * 
     * @param   pElement    hinzuzufuegendes Element
     */
    public void hinzufuegen(Inhaltstyp pElement) {
        if(pElement != null) {
            if(!this.istVorhanden(pElement)) {
                this.elemente.append(pElement);
            }
        }
    }

    /**
     * Ueberschreibt die verwalteten Elemente mit der uebergebenen Menge.0A
     *
     * @param   pMenge  neue Menge
     */
    public void setzeMenge(Menge<Inhaltstyp> pMenge) {
        while(!this.istLeer()) {
            this.zumAnfang();
            this.entferne();
        }

        if(pMenge != null) {
            pMenge.zumAnfang();
            while(!pMenge.istAmEnde()) {
                this.hinzufuegen(pMenge.gibAktuelles());
                pMenge.zumNaechsten();
            }
        }

        // Alternative: this.elemente = pMenge;        
    }
    
    /**
     * Entfernt alle verwalteten Elemente.
     */
    public void leere() {
        this.setzeMenge(this.erstelleLeereMenge());
    }


    // Mengenoperationen



    @Override public boolean istVorhanden(Inhaltstyp pElement) {
        if(pElement != null) {
            return this.gibElement(pElement) != null;
        } else {
            return false;
        }
    }

    /**
     * Gibt das Element zurueck, das gleich dem uebergegebenen Element ist,
     * sofern das Element Teil der Menge ist, sonst null.
     * 
     * @param   pElement    zu suchendes Element
     * 
     * @return  Element, falls es in der Menge vorhanden ist
     */
    public Inhaltstyp gibElement(Inhaltstyp pElement) {
        Inhaltstyp aktuelles;
        Inhaltstyp e = null;
        this.zumAnfang();
        while(!this.istAmEnde()) {
            aktuelles = this.gibAktuelles();
            if(aktuelles.istGleich(pElement)) {
                e = aktuelles;
            }
            this.zumNaechsten();
        }
        return e;
    }

    @Override public int anzahl() {
        int anzahl = 0;
        this.zumAnfang();
        while(!this.istAmEnde()) {
            anzahl++;
            this.zumNaechsten();
        }
        return anzahl;
    } 

    @Override public String gibString() {
        String e = "{";
        this.zumAnfang();
        while(!this.istAmEnde()) {
            e += this.gibAktuelles().gibBezeichnung() + ",";
            this.zumNaechsten();
        }
        e += "}";
        return e;
    }

    @Override public Menge<Inhaltstyp> gibKopie() {
        Menge<Inhaltstyp> ergebnismenge = this.erstelleLeereMenge();
        this.zumAnfang();
        while(!this.istAmEnde()) {
            ergebnismenge.hinzufuegen(this.gibAktuelles());
            this.zumNaechsten();
        }
        return ergebnismenge;
    }

    @Override public void vereinigen(Menge<Inhaltstyp> pMenge) {
        this.setzeMenge(this.bestimmeVereinigung(pMenge));
    }

    @Override public Menge<Inhaltstyp> bestimmeVereinigung(Menge<Inhaltstyp> pMenge) {
        Menge<Inhaltstyp> ergebnismenge = this.erstelleLeereMenge();
        if(pMenge != null) {
            pMenge.zumAnfang();
            while(!pMenge.istAmEnde()) {
                ergebnismenge.hinzufuegen(pMenge.gibAktuelles());
                pMenge.zumNaechsten();
            }
        }
        this.zumAnfang();
        while(!this.istAmEnde()) {
            ergebnismenge.hinzufuegen(this.gibAktuelles());
            this.zumNaechsten();
        }
        return ergebnismenge;
    }

    @Override public Menge<Inhaltstyp> bestimmeSchnitt(Menge<Inhaltstyp> pMenge) {
        Menge<Inhaltstyp> ergebnismenge = this.erstelleLeereMenge();
        if(pMenge != null) {
            pMenge.zumAnfang();
            while(!pMenge.istAmEnde()) {
                ergebnismenge.hinzufuegen(pMenge.gibAktuelles());
                pMenge.zumNaechsten();
            }
        }
        ergebnismenge.zumAnfang();
        while(!ergebnismenge.istAmEnde()) {
            if(!this.istVorhanden(ergebnismenge.gibAktuelles())) {
                ergebnismenge.entferne();
            } else {
                ergebnismenge.zumNaechsten();
            }
        }
        return ergebnismenge;
    }

    @Override public Menge<Inhaltstyp> bestimmeSymmetrischeDifferenz(Menge<Inhaltstyp> pMenge) {
        Menge<Inhaltstyp> ergebnismenge = this.erstelleLeereMenge();
        ergebnismenge = (this.bestimmeVereinigung(pMenge)).bestimmeDifferenz(this.bestimmeSchnitt(pMenge));
        // ergebnismenge = (this.bestimmeDifferenz(pMenge)).bestimmeVereinigung(pMenge.bestimmeDifferenz(this));
        return ergebnismenge;
    }

    @Override public Menge<Inhaltstyp> bestimmeDifferenz(Menge<Inhaltstyp> pMenge) {
        Menge<Inhaltstyp> ergebnismenge = this.erstelleLeereMenge();
        this.zumAnfang();
        while(!this.istAmEnde()) {
            ergebnismenge.hinzufuegen(this.gibAktuelles());
            this.zumNaechsten();
        } 
        if(pMenge != null) {
            ergebnismenge.zumAnfang();
            while(!ergebnismenge.istAmEnde()) {
                if(pMenge.istVorhanden(ergebnismenge.gibAktuelles())) {
                    ergebnismenge.entferne();
                } else {
                    ergebnismenge.zumNaechsten();
                }
            }
        }
        return ergebnismenge;
    }
}