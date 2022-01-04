package model.Automat;

import model.Automat.Menge.*;

/**
 * Ein Alphabet ist eine Spezialisierung der Menge und kann nur Zeichen verwalten.
 * 
 * @author  Tristan Lippold
 * @version 2020-07-03
 */
public class Alphabet extends Menge<Zeichen> {
    /**
     * Konstruiert ein neues Alphabet, welches zunaechst 
     * eine leere Menge von Zeichen verwaltet.
     */
    public Alphabet() {
        this.elemente = new List<Zeichen>();
    }
    
    @Override public Menge<Zeichen> erstelleLeereMenge() {
        return new Alphabet();
    }
}