package model.Automat;

import model.Automat.Menge.*;

/**
 * Eine Zustandsmenge ist eine Spezialisierung der Menge und kann nur Zustaende verwalten.
 * 
 * @author  Tristan Lippold
 * @version 2020-07-03
 */
public class Zustandsmenge extends Menge<Zustand> {
    /**
     * Konstruiert eine neue Zustandsmenge, welche zunaechst 
     * eine leere Menge von Zustaenden verwaltet.
     */
    public Zustandsmenge() {
        super.elemente = new List<Zustand>();
    }
    
    @Override public Menge<Zustand> erstelleLeereMenge() {
        return new Zustandsmenge();
    }
}