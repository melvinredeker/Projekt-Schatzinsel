package model.Automat;

import model.Automat.Menge.*;

/**
 * Ein Zustand ist eine Spezialisierung eines Elementes
 * und muss mit einem der Praefixe {q,z} beginnen.
 * 
 * @author  Tristan Lippold
 * @version 2020-07-03
 */
public class Zustand extends Element {  
    /**
     * Legt die erlaubten Praefixe fest.
     */
    private int nummer;
    public static final String[] PRAEFIXE = {"q","z"};
    
    /**
     * Konstruiert einen neuen Zustand mit der angegebenen Nummer und der angegebenen Bezeichnung.
     * 
     * @param   pNummer         Nummer des Zustands
     * @param   pBezeichnung    Bezeichnung des Zustands
     */
    private Zustand(int pNummer, String pBezeichnung) {
        this.nummer = pNummer;
        super.bezeichnung = pBezeichnung;
    }

    /**
     * Erstellt einen neuen Zustand mit der angegebenen Nummer.
     * 
     * @param   pNummer         Nummer des Zustands
     * 
     * @return  neuer Zustand mit der angegebenen Nummer
     */
    public static Zustand erstelleZustand(int pNummer) {
        return new Zustand(pNummer, pNummer + "");
    }
    
    /**
     * Erstellt einen neuen Zustand mit der angegebenen Nummer und der angegebenen Bezeichnung.
     * 
     * @param   pNummer         Nummer des Zustands
     * @param   pBezeichnung    Bezeichnung des Zustands
     * 
     * @return  neuer Zustand mit der angegebenen Nummer und der angegebenen Bezeichnung.
     */
    public static Zustand erstelleZustand(int pNummer, String pBezeichnung) {
        return new Zustand(pNummer, pBezeichnung);
    }
    
    /**
     * Liefert den Namen des Zustands bestehend aus einem Praefix und einer Nummer.
     *
     * @return   Praefix und Nummer
     */
    public String gibName() {
        return Zustand.PRAEFIXE[0] + this.nummer;
    }
    
    /**
     * Liefert die Nummer des Zustands.
     *
     * @return  Nummer
     */
    public int gibNummer() {
        return this.nummer;
    }
    
    @Override public boolean istGleich(Element pElement) {
        Zustand pZustand = (Zustand) pElement;
        return this.nummer == pZustand.gibNummer();
    }
}