package model.Automat;

import model.Automat.Menge.*;

/**
 * Eine Relation kann jeder Uebergang aus einem Zustand (der Zustandsmenge Q des Automaten)
 * und einem Zeichen (des Eingabealphabets E des Automaten) mehrere Nachfolgezustaende zuordnen.
 * 
 * @author  Tristan Lippold
 * @version 2020-07-03
 */
public class Relation extends Menge<Uebergang> {
    /**
     * Konstruiert eine neue Relation, welche zunaechst 
     * eine leere Liste von Uebergaenge verwaltet.
     */
    public Relation() {
        super.elemente = new List<Uebergang>();
    }
    
    @Override public Menge<Uebergang> erstelleLeereMenge() {
        return new Relation();
    }
    
    /**
     * Fuegt der Relation einen konkreten Uebergang (qi x e -> qj) hinzu.
     * 
     * Sollte fuer den Uebergang (qi,e) noch kein Endzustand definiert worden sein, 
     * wird der Uebergang der Tabelle hinzugefuegt. Anschliessend wird ein 
     * Uebergang fuer die Uebergang zum Nachfolgezustand hinzugefuegt. D.h. es 
     * ist moeglich einem Uebergang mehrere Nachfolgezustaende zu zuordnen.
     * 
     * @param   pVon    Ausgangszustand
     * @param   pMit    Zeichen
     * @param   pNach   Nachfolgezustand
     */
    public void hinzufuegenUebergang(Zustand pVon, Zeichen pMit, Zustand pNach) {
        Uebergang k;
        this.hinzufuegenUebergang(pVon, pMit);
        k = this.gibUebergang(pVon, pMit);
        k.hinzufuegenNachfolgezustand(pNach);
    }
    
    /**
     * Fuegt der Relation eine neue Uebergang (qi,e) hinzu, sofern die 
     * Uebergang nicht bereits in der Tabelle der Relation existiert.
     * 
     * @param   pVon    Ausgangszustand
     * @param   pMit    Zeichen
     */
    private void hinzufuegenUebergang(Zustand pVon, Zeichen pMit) {
        if(!this.istVorhanden(pVon, pMit)) {
            super.hinzufuegen(new Uebergang(pVon, pMit));
        }
    }
    
    /**
     * Entfernt einen konkreten Uebergang (qi,e,qj) 
     * aus der Relation fuer den gilt qi x e -> qj.
     * 
     * @param   pVon    Ausgangszustand
     * @param   pMit    Zeichen
     * @param   pNach   Nachfolgezustand     
     */
    public void entferneUebergang(Zustand pVon, Zeichen pMit, Zustand pNach) {
        Element aktuellerZustand, aktuellesZeichen;
        boolean gleicherZustand, gleichesZeichen;
        super.zumAnfang();
        while(!super.istAmEnde()) {
            aktuellerZustand = super.gibAktuelles().gibZustand();
            aktuellesZeichen = super.gibAktuelles().gibZeichen();
            gleicherZustand = aktuellerZustand.istGleich(pVon);
            gleichesZeichen = aktuellesZeichen.istGleich(pMit);
            if(gleicherZustand && gleichesZeichen) {
                super.gibAktuelles().entferneNachfolgezustand(pNach);
            }
            super.zumNaechsten();
        }
    }
    
    /**
     * Entfernt alle Uebergaenge einer Uebergang (qi,e) aus der Relation fuer die 
     * gilt qi x e -> qj, indem die Uebergang aus der Tabelle entfernt wird. 
     * 
     * @param   pVon    Ausgangszustand
     * @param   pMit    Zeichen
     */
    public void entferneUebergaengeMitAusgangszustandUndZeichen(Zustand pVon, Zeichen pMit) {
        Element aktuellerZustand, aktuellesZeichen;
        boolean gleicherZustand, gleichesZeichen;
        super.zumAnfang();
        while(!super.istAmEnde()) {
            aktuellerZustand = super.gibAktuelles().gibZustand();
            aktuellesZeichen = super.gibAktuelles().gibZeichen();
            gleicherZustand = aktuellerZustand.istGleich(pVon);
            gleichesZeichen = aktuellesZeichen.istGleich(pMit);
            if(gleicherZustand && gleichesZeichen) {
                super.entferne();
            } else {
                super.zumNaechsten();
            }
        }
    }
    
    /**
     * Entfernt alle Uebergaenge eines Ausgangszustandes (qi) aus der Relation fuer die gilt 
     * qi x e -> qj, indem alle Uebergaenge (qi,e) mit qi aus der Tabelle entfernt werden. 
     * 
     * @param   pVon    Ausgangszustand
     */
    public void entferneUebergaengeMitAusgangszustand(Zustand pVon) {
        Element aktuellerZustand;
        boolean gleicherZustand;
        super.zumAnfang();
        while(!super.istAmEnde()) {
            aktuellerZustand = super.gibAktuelles().gibZustand();
            gleicherZustand = aktuellerZustand.istGleich(pVon);
            if(gleicherZustand) {
                super.entferne();
            } else {
                super.zumNaechsten();
            }
        }
    }
    
    /**
     * Entfernt alle Uebergaenge eine Zeichens (e) aus der Relation fuer die gilt
     * qi x e -> qj, indem alle Uebergaenge (qi,e) mit e aus der Tabelle entfernt werden.
     * 
     * @param   pMit  Zeichen
     */
    public void entferneUebergaengeMitZeichen(Zeichen pMit) {
        Element aktuellesZeichen;
        boolean gleichesZeichen;
        super.zumAnfang();
        while(!super.istAmEnde()) {
            aktuellesZeichen = super.gibAktuelles().gibZeichen();
            gleichesZeichen = aktuellesZeichen.istGleich(pMit);
            if(gleichesZeichen) {
                super.entferne();
            } else {
                super.zumNaechsten();
            }
        }
    }
    
    /**
     * Entfernt alle Uebergaenge eines Nachfolgezustandes (qj) aus der 
     * Relation fuer die gilt qi x e -> qj, indem alle qj in den 
     * Nachfolgezustandsmengen aller Uebergaenge (qi,e) entfernt werden.
     * 
     * @param   pNach   Nachfolgezustand  
     */
    public void entferneUebergaengeMitNachfolgezustand(Zustand pNach) {
        super.zumAnfang();
        while(!super.istAmEnde()) {
            super.gibAktuelles().entferneNachfolgezustand(pNach);
            super.zumNaechsten();
        }
    }
    
    /**
     * Bestimmt, ob eine Uebergang (qi,e) bereits in der Tabelle vorhanden ist.
     * 
     * @param   pVon    Ausgangszustand
     * @param   pMit    Zeichen
     * 
     * @return  wahr, wenn Uebergang in der Tabelle verwaltet wird
     */
    private boolean istVorhanden(Zustand pVon, Zeichen pMit) {
        return this.gibUebergang(pVon, pMit) != null;
    }
    
    /**
     * Liefert den Uebergang mit (qi,e), sofern dieser 
     * in der Tabelle verwaltet wird, sonst null.
     * 
     * @param   pVon    Ausgangszustand
     * @param   pMit    Zeichen
     * 
     * @return  Uebergang, wenn vorhanden, sonst null
     */
    public Uebergang gibUebergang(Zustand pVon, Zeichen pMit) {
        Zustand aktuellerZustand;
        Element aktuellesZeichen;
        boolean gleicherZustand, gleichesZeichen;
        Uebergang k = null;
        super.zumAnfang();
        while(!super.istAmEnde()) {
            aktuellerZustand = super.gibAktuelles().gibZustand();
            aktuellesZeichen = super.gibAktuelles().gibZeichen();
            gleicherZustand = aktuellerZustand.istGleich(pVon);
            gleichesZeichen = aktuellesZeichen.istGleich(pMit);
            if(gleicherZustand && gleichesZeichen) {
                k = super.gibAktuelles();
            }
            super.zumNaechsten();
        }
        return k;
    }
    
    /**
     * Liefert eine Relation mit den Uebergaengen mit (qi,e), sofern diese 
     * in der Tabelle verwaltet werden, sonst null.
     * 
     * @param   pVon    Ausgangszustand
     * @param   pMit    Zeichen
     * 
     * @return  Relation, wenn Uebergaenge vorhanden, sonst null
     */
    public Relation gibAlleUebergaenge(Zustand pVon, Zeichen pMit) {
        Element aktuellerZustand, aktuellesZeichen;
        boolean gleicherZustand, gleichesZeichen;
        Relation k = new Relation();
        super.zumAnfang();
        while(!super.istAmEnde()) {
            aktuellerZustand = super.gibAktuelles().gibZustand();
            aktuellesZeichen = super.gibAktuelles().gibZeichen();
            gleicherZustand = aktuellerZustand.istGleich(pVon);
            gleichesZeichen = aktuellesZeichen.istGleich(pMit);
            if(gleicherZustand && gleichesZeichen) {
                k.hinzufuegen(super.gibAktuelles());
            }
            super.zumNaechsten();
        }
        return k;
    }
    
    // TO-DO: JavaDoc
    public void entferneLeereUebergaenge() {
        super.zumAnfang();
        if(super.gibAktuelles().gibNachfolgezustaende().anzahl() == 0) {
            super.entferne();
        }
        else {
            super.zumNaechsten();
        }
    }
}