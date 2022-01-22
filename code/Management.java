import model.Automat.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Eine Anwendung verwaltet einen Automaten und eine aktuelle Position in Form einer Schiffsfolge. 
 * Sie initialisiert den Automaten und stellt das Bindeglied zwischen Applikation und model dar.
 * 
 * @author Tristan Lippold, Melvin Redeker
 * @version 2021-12-30
 */
public class Management {
    
    private Automat inseln;
    private String schiffsFolge;

    /**
     * Konstruktor fuer Objekte der Klasse Management
     */
    public Management() {

    }
    
    /**
     * Gibt zurueck, ob mit der derzeitigen Schiffsfolge ein Endzustand erreicht wird.
     * 
     * @return      wahr, wenn Eingabe Teil der Sprache ist
     */ 
    public boolean isAtEnd() {
        return Algorithmen.akzeptiertEingabe(this.inseln, this.schiffsFolge);
    }
    
    /**
     * Setzt die aktuelle Schiffsfolge zurueck.
     */
    public void leereSchiffsFolge() {
        this.schiffsFolge = "";
    }
    
    /**
     * Gibt die aktuelle Schiffsfolge als String zurueck.
     *
     * @return      aktuelle Schiffsfolge
     */
    public String gibSchiffsfolge() {
        return this.schiffsFolge;
    }
    
    /**
     * Setzt die Schiffsfolge auf den uebergebenen String.
     *
     * @param   pSchiffsFolge   neue Schiffsfolge
     */
    public void setzeSchiffsfolge(String pSchiffsFolge) {
        this.schiffsFolge = pSchiffsFolge;
    }
    
    /**
     * Prueft, ob mit der uebergebenen Eingabe eine Fehlersenke erreicht wird.
     *
     * @param   pEingabe    zu ueperpruefende Eingabe
     *
     * @return      wahr, wenn keine Fehlersenke erreicht wird
     */
    public boolean istGueltigeEingabe(String pEingabe) {
        Zustand aktuellerZustand = this.inseln.gibS();
        for(int i = 0; i < pEingabe.toCharArray().length; i++) {
            Zeichen aktuellesZeichen = Zeichen.erstelleZeichen(pEingabe.substring(i, i+1));
            if(!this.inseln.gibE().istVorhanden(aktuellesZeichen)) {
                return false;
            } else {
                Uebergang aktuellerUebergang = this.inseln.gibD().gibUebergang(aktuellerZustand, aktuellesZeichen);
                if(aktuellerUebergang == null) {
                    return false;
                } else {
                    aktuellerUebergang.gibNachfolgezustaende().zumAnfang();
                    aktuellerZustand = aktuellerUebergang.gibNachfolgezustaende().gibAktuelles();
                }
            }
        }
        return true;
    }
    
    /**
     * Laedt die .ini Datei aus dem Ordner '/resources/Data/' und erzeugt aus den darin enthaltenen Daten einen Automaten.
     */
    public void ladeIniDatei(){
        try {
            File myObj = new File("resources/Data/Automat.ini");
            Scanner myReader = new Scanner(myObj);
            this.inseln = new Automat();
            boolean zustaendeErledigt = false;
            boolean zeichenErledigt = false;
            boolean startErledigt = false;
            boolean endeErledigt = false;

            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                if(data.equals("[Zustaende]")){             // Abfangen von erster Einlesezeile
                }
                else if(data.equals("[Zeichen]")){
                    zustaendeErledigt = true;
                }
                else if(data.equals("[Startzustand]")){
                    zeichenErledigt = true;
                }
                else if(data.equals("[Endzustand]")){                    
                    startErledigt = true;
                }
                else if(data.equals("[Uebergaenge]")){
                    endeErledigt = true;
                }
                else if(zustaendeErledigt != true){         // Zustaende erstellen
                    String[] tmp = data.split(" - ");
                    String bezeichnung = tmp[1];
                    String id = tmp [0];

                    this.inseln.neuerZustand(Integer.parseInt(id.substring(1)), bezeichnung);
                }
                else if(zeichenErledigt != true){           // Zeichen erstellen
                    this.inseln.neuesZeichen(data);
                }
                else if(startErledigt != true){             // Startzustand setzen
                    this.inseln.setzeStartzustand(Integer.parseInt(data.substring(1)));
                }
                else if(endeErledigt != true){              // Endzustaende setzen
                    this.inseln.setzeZustandAlsEndzustand(Integer.parseInt(data.substring(1)));
                }
                else{                                       // Uebergaenge erstellen
                    String[] tmp = data.split(", ");
                    String id1 = tmp [0];
                    String eingabe = tmp [1];
                    String id2 = tmp [2];
                    String bezeichnung1 = tmp[3];
                    String bezeichnung2 = tmp[4];

                    this.inseln.neuerUebergang(Integer.parseInt(id1.substring(1)), bezeichnung1, eingabe, Integer.parseInt(id2.substring(1)), bezeichnung2);
                }
            }
            myReader.close();
            this.leereSchiffsFolge();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * Erstellt einen Automaten nach der Vorlage der Inseln des Schatzinselprojekts
     */
    public void erstelleKonstellationDefault() {
        this.inseln = new Automat();
        this.inseln.neuerZustand(0, "Pirateninsel");
        this.inseln.neuerZustand(1, "Schiffswrackbucht");
        this.inseln.neuerZustand(2, "Musketenhuegel"); 
        this.inseln.neuerZustand(3, "Totenkopfinsel");
        this.inseln.neuerZustand(4, "Meuterinsel");
        this.inseln.neuerZustand(5, "Schmugglerbucht");
        this.inseln.neuerZustand(6, "Schatzinsel");
        this.inseln.neuesZeichen("A");
        this.inseln.neuesZeichen("B");
        this.inseln.setzeStartzustand(0);
        this.inseln.setzeZustandAlsEndzustand(6);
        this.inseln.neuerUebergang(0, "A", 1);
        this.inseln.neuerUebergang(1, "A", 2);
        this.inseln.neuerUebergang(2, "A", 0);
        this.inseln.neuerUebergang(3, "A", 2);
        this.inseln.neuerUebergang(4, "A", 5);
        this.inseln.neuerUebergang(5, "A", 0);
        this.inseln.neuerUebergang(0, "B", 2);
        this.inseln.neuerUebergang(1, "B", 3);
        this.inseln.neuerUebergang(2, "B", 4);
        this.inseln.neuerUebergang(3, "B", 1);
        this.inseln.neuerUebergang(4, "B", 3);
        this.inseln.neuerUebergang(5, "B", 6);
        this.leereSchiffsFolge();
    }

    /**
     * Fuegt die Eingabe der schiffsFolge hinzu.
     */
    public void useShip(String pEingabe) {
        if(this.inseln.gibE().istVorhanden(Zeichen.erstelleZeichen(pEingabe))) {
            this.schiffsFolge += pEingabe;
        }
    }

    /**
     * Ueberprueft, ob in der Zustandsfolge, die durch die gegebene Eingabe entsteht, eine Wiederholung vorhanden ist.
     */
    public boolean istWiederholungVorhanden(String pEingabe) {
        Zustand[] temp = Algorithmen.gibZustandsFolge(this.inseln, pEingabe);
        for(int i = 0; i < temp.length; i++) {
            for(int j = 0; j < temp.length; j++) {
                if(j != i) {
                    if(temp[i].istGleich(temp[j])) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    /**
     * Wenn eine Wiederholung in der durch die Eingabe entstehende Zustandsfolge vorhanden ist und die gegebenen Stellen
     * die Randstellen dieser Wiederholung darstellen, werden die entsprechenden Zeichen aus der Eingabe entfernt und
     * wird die ueberarbeitete Eingabe zurueckgegeben.
     */
    public String entferneWiederholung(String pEingabe, int pStelle1, int pStelle2) {
        Zustand[] temp = Algorithmen.gibZustandsFolge(this.inseln, pEingabe);
        String text = "";
        if(pStelle1 < pStelle2) {
            if(temp[pStelle1].istGleich(temp[pStelle2])) {
                if(pStelle1 > 0) {
                    text += pEingabe.substring(0, pStelle1);
                }
                if(pStelle2 < pEingabe.toCharArray().length - 1) {
                    text += pEingabe.substring(pStelle2 + 1, pEingabe.toCharArray().length);
                }
                return text;
            }
        }
        return pEingabe;
    }

    /**
     * Gibt das Label des AktuellenZustandes zurueck.
     */
    public String getCurrentIslandName() {
        Zustand[] temp = Algorithmen.gibZustandsFolge(this.inseln, this.schiffsFolge);
        return temp[temp.length - 1].gibBezeichnung();
    }

    /**
     * Gibt die Labels der befahrenen Inseln zurueck.
     */
    public String[] gibBefahreneInselnBezeichnungen() {
        Zustand[] temp = Algorithmen.gibZustandsFolge(this.inseln, this.schiffsFolge);
        String[] tempBezeichnungen = new String[temp.length];
        for(int i = 0; i < temp.length; i++) {
            tempBezeichnungen[i] = temp[i].gibBezeichnung();
        }
        return tempBezeichnungen;
    }
    
}