package model.Automat;

import java.util.ArrayList;

/**
 * Die Klasse operiert auf Automaten und stellt verschiedene Verfahren zur Verfuegung.
 * 
 * @author  Tristan Lippold
 * @version 2021-02-15
 */
public abstract class Algorithmen {
    /**
     * Leitet ein Wort ab, indem es Zeichen fuer die Zeichen die Zustandsfolge bestimmt.
     * 
     * @param   pAutomat            endlicher Automat
     * @param   pWort               Zeichenfolge
     * @param   pAlsBezeichnung     true, wenn Ausgabe der Bezeichnungen erfolgen soll
     *                              false, wenn Ausgabe der Nummern (+ Praefixe) erfolgen soll
     * 
     * @return  Ableitung(en)
     */
    public static String ableiten(Automat pAutomat, String pWort, boolean pAlsBezeichnung) {
        String ausgabe = "";
        Zustand s;
        if(pAutomat.istVollstaendigDefiniert()) {
            s = pAutomat.gibS();
            ausgabe = Algorithmen.ableiten(pAutomat,pWort,s,"", pAlsBezeichnung);
            if(!ausgabe.contains(" e F => w e L(A)")) {
                ausgabe += "\nalle qi !e F => w !e L(A)";
            }
        }
        return ausgabe;
    }

    /**
     * Leitet ein Wort ab, indem es Zeichen fuer die Zeichen die Zustandsfolge bestimmt.
     * 
     * @param   pAutomat            endlicher Automat
     * @param   pWort               Zeichenfolge
     * @param   pZustand            Zustand ab dem abgeleitet werden soll
     * @param   pAbleitung          Ableitung fuer die bisher verarbeiteten Zeichen des Wortes
     * @param   pAlsBezeichnung     true, wenn Ausgabe der Bezeichnungen erfolgen soll
     *                              false, wenn Ausgabe der Nummern (+ Praefixe) erfolgen soll
     * 
     * @return  Ableitung(en)
     */
    private static String ableiten(Automat pAutomat, String pWort, Zustand pZustand, String pAbleitung, boolean pAlsBezeichnung) {
        // Lokale Variablen
        Relation d;
        Zustandsmenge f;

        String aktuelleAbleitung = "";
        Zeichen aktuellesZeichen;
        Zustand aktuellerZustand;
        Zustand aktuellerNachfolgezustand;
        Uebergang aktuellerUebergang;

        Zustandsmenge nachfolgeZustaende;
        String ableitungSammlung = "";

        String e;
        int zaehler = 0;

        if(pAutomat.istVollstaendigDefiniert()) {
            d = pAutomat.gibD();
            f = pAutomat.gibF();

            if(pAlsBezeichnung) {
                ableitungSammlung += pZustand.gibBezeichnung();
            } else {
                ableitungSammlung += pZustand.gibNummer();
            }
            if(pWort.length() > 0) {
                e = pWort.substring(0,1);
                aktuellesZeichen = Zeichen.erstelleZeichen(e);
                aktuellerZustand = pZustand;
                aktuellerUebergang = d.gibUebergang(aktuellerZustand,aktuellesZeichen);
                if(aktuellerUebergang != null) {
                    nachfolgeZustaende = aktuellerUebergang.gibNachfolgezustaende();
                    nachfolgeZustaende.zumAnfang();
                    while(!nachfolgeZustaende.istAmEnde()) {
                        aktuellerNachfolgezustand = nachfolgeZustaende.gibAktuelles();
                        if (zaehler > 0) {
                            if(pAlsBezeichnung) {
                                 aktuelleAbleitung = "\n" + pAbleitung + pZustand.gibBezeichnung();
                            } else {
                                 aktuelleAbleitung = "\n" + pAbleitung + pZustand.gibNummer();
                            }
                        }
                        aktuelleAbleitung += "," + e + " -> ";
                        ableitungSammlung += aktuelleAbleitung;
                        ableitungSammlung += Algorithmen.ableiten(pAutomat,pWort.substring(1),aktuellerNachfolgezustand,ableitungSammlung, pAlsBezeichnung);
                        nachfolgeZustaende.zumNaechsten();
                        zaehler++;
                    }
                }
            }
            else {
                if(f.istVorhanden(pZustand)) {
                    ableitungSammlung += " e F => w e L(A)";
                }
            }
        }
        return ableitungSammlung;
    }

    /**
     * Ueberprueft, ob das eingegebene Wort Element der vom Automaten akzeptierten Sprache ist.
     * 
     * @param   pAutomat    endlicher Automat
     * @param   pWort       Zeichenfolge
     *  
     * @return  wahr, wenn Wort Element der vom Automaten akzeptierten Sprache ist
     */
    public static boolean akzeptiertEingabe(Automat pAutomat, String pWort) {
        // Lokale Variablen
        Zustandsmenge q;
        Relation d;
        Zustandsmenge f;
        Zustand s;

        Zustand aktuellerZustand;
        Zustandsmenge alleAktuellenZustaende = new Zustandsmenge();
        Zeichen aktuellesZeichen;
        Uebergang aktuellerUebergang;
        Relation alleAktuellenUebergaenge;

        Zustandsmenge nachfolgeZustaende;
        Zustandsmenge alleEndZustaende;

        String e;
        boolean akzeptiert = false;

        if(pAutomat.istVollstaendigDefiniert()) {
            // Lokale Variablen initialisieren
            q = pAutomat.gibQ();
            d = pAutomat.gibD();
            f = pAutomat.gibF();
            s = pAutomat.gibS();

            alleAktuellenZustaende.hinzufuegen(q.gibElement(Zustand.erstelleZustand(s.gibNummer())));
            // solange die Eingabe nicht vollstaendig bearbeitet wurde
            for(int i = 0; i < pWort.length(); i++) {
                // reset Nachfolge-Zustaende
                nachfolgeZustaende = new Zustandsmenge();

                // reset Uebergaenge
                alleAktuellenUebergaenge = new Relation();

                // neue Eingabe
                e = pWort.substring(i,i+1);
                aktuellesZeichen = Zeichen.erstelleZeichen(e);

                // fuer alle moeglichen Zustaende alle moeglichen Uebergaenge suchen
                alleAktuellenZustaende.zumAnfang();
                while(!alleAktuellenZustaende.istAmEnde()) {
                    aktuellerZustand = alleAktuellenZustaende.gibAktuelles();
                    alleAktuellenUebergaenge.vereinigen(d.gibAlleUebergaenge(aktuellerZustand, aktuellesZeichen));
                    alleAktuellenZustaende.zumNaechsten();
                }

                // mit allen moeglichen Uebergaengen alle moeglichen Nachfolge-Zustaende suchen
                alleAktuellenUebergaenge.zumAnfang();
                while(!alleAktuellenUebergaenge.istAmEnde()) {
                    aktuellerUebergang = alleAktuellenUebergaenge.gibAktuelles();
                    nachfolgeZustaende.vereinigen(aktuellerUebergang.gibNachfolgezustaende());
                    alleAktuellenUebergaenge.zumNaechsten();
                }

                // Nachfolge-Zustaende sind neue untersuchte Zustaende
                alleAktuellenZustaende = nachfolgeZustaende;

                // naechste Eingabe ueberpruefen
            }
            alleEndZustaende = alleAktuellenZustaende;

            // ueberpruefen, ob Endzustand erreicht ist
            if(!f.bestimmeSchnitt(alleEndZustaende).istLeer()) {
                akzeptiert = true;
            }
        }
        return akzeptiert;
    }

    /**
     * Gibt die Zustandsfolge fuer das eingegebene Wort als Array zurueck.
     *
     * @param pAutomat      endlicher Automat
     * @param pWort         Zeichenfolge
     *
     * @return  Zustandsfolge
     */
    public static Zustand[] gibZustandsFolge(Automat pAutomat, String pWort) {
        // Lokale Variablen
        Zustand[] zustaende = new Zustand[pWort.toCharArray().length + 1];

        if(pAutomat.istVollstaendigDefiniert() && pAutomat.istDeterministisch()) {
            zustaende[0] = pAutomat.gibS();
            return Algorithmen.gibZustandsFolge(pAutomat, pWort, pAutomat.gibS(), zustaende);
        }
        return null;
    }

    /**
     * Gibt die Zustandsfolge ab dem gegebenen Zustand fuer das eingegebene Wort als Array zurueck.
     *
     * @param pAutomat          endlicher Automat
     * @param pWort             Zeichenfolge
     * @param pAktuellerZustand Zustand, ab dem Zustandsfolge bestimmt werden soll
     * @param pZustaende        Array, in dem die Zustandsfolge eingetragen werden soll
     *
     * @return  Zustandsfolge
     */
    private static Zustand[] gibZustandsFolge(Automat pAutomat, String pWort, Zustand pAktuellerZustand, Zustand[] pZustaende) {
        int zaehler = 0;
        while(pZustaende.length > zaehler && pZustaende[zaehler] != null) {
            zaehler++;
        }
        if(pWort.toCharArray().length >= 1) {
            Zustandsmenge nachfolge = pAutomat.gibD().gibUebergang(pAktuellerZustand, Zeichen.erstelleZeichen(pWort.substring(0, 1))).gibNachfolgezustaende();
            nachfolge.zumAnfang();
            pZustaende[zaehler] = (Zustand) nachfolge.gibAktuelles();
            return Algorithmen.gibZustandsFolge(pAutomat, pWort.substring(1), nachfolge.gibAktuelles(), pZustaende);
        } else {
            return pZustaende;
        }
    }

    /**
     * Gibt zurueck, ob das eingegebene Wort Teil der Sprache des Automaten ist.
     *
     * @param pAutomat  endlicher Automat
     * @param pWort     Zeichenfolge
     *
     * @return  true, wenn Wort Teil der Sprache ist, sonst false
     */
    public static boolean akzeptiertEingabeRekursiv(Automat pAutomat, String pWort) {
        // Lokale Variablen
        boolean akzeptiert = false;
        Zustand s;

        if(pAutomat.istVollstaendigDefiniert()) {
            s = pAutomat.gibS();
            akzeptiert = Algorithmen.akzeptiertEingabeRekursiv(pAutomat,pWort,s);
        }
        return akzeptiert;
    }

    /**
     * Gibt zurueck, ob das eingegebene Wort Teil der Sprache des Automaten ist. 
     * Dabei stellt der uebergebene Zustand den Startzustand dar.
     *
     * @param pAutomat  endlicher Automat
     * @param pWort     Zeichenfolge
     * @param pZustand  Startzustand
     *
     * @return  true, wenn Wort Teil der Sprache ist, sonst false
     */
    private static boolean akzeptiertEingabeRekursiv(Automat pAutomat, String pWort, Zustand pZustand) {
        // Lokale Variablen
        Zustandsmenge f = pAutomat.gibF();
        Relation d = pAutomat.gibD();

        Zustand aktuellerZustand = pZustand;
        Zustand aktuellerNachfolgezustand;
        Zeichen aktuellesZeichen;
        Uebergang aktuellerUebergang;

        Zustandsmenge nachfolgeZustaende;

        boolean akzeptiert = false;
        String e;

        if(pWort.length() > 0) {
            e = pWort.substring(0,1);
            aktuellesZeichen = Zeichen.erstelleZeichen(e);
            aktuellerUebergang = d.gibUebergang(aktuellerZustand, aktuellesZeichen);
            if(aktuellerUebergang != null) {
                nachfolgeZustaende = aktuellerUebergang.gibNachfolgezustaende();
                nachfolgeZustaende.zumAnfang();
                while(!nachfolgeZustaende.istAmEnde()) {
                    aktuellerNachfolgezustand = nachfolgeZustaende.gibAktuelles();
                    if(Algorithmen.akzeptiertEingabeRekursiv(pAutomat, pWort.substring(1), aktuellerNachfolgezustand)) {
                        akzeptiert = true;
                    }
                    nachfolgeZustaende.zumNaechsten();
                }
            }
        }
        else {
            if(f.istVorhanden(aktuellerZustand)) {
                akzeptiert = true;
            }
        }
        return akzeptiert;
    }

    /**
     * Erstellt einen aequivalenten deterministischen endlichen Automaten (DEA) 
     * eines nicht-deterministischen endlichen Automaten (NEA)
     * mithilfe des Verfahren der Potenzmengenkonstruktion.
     * 
     * @param   pNEA    NEA
     * 
     * @return  aequivalenter DEA
     */
    public static Automat konstruiereDEA(Automat pNEA) {
        // Lokale Variablen
        Automat dea = new Automat();

        Alphabet eNEA;
        Relation dNEA;
        Zustandsmenge fNEA;
        Zustand sNEA;

        Uebergang aktuellerUebergang;
        Zustand aktuellerZustand;
        Zustandsmenge aktuellerZustandMenge;
        Zeichen aktuellesZeichen;
        Zustand aktuellerFolgezustand;
        Zustandsmenge aktuellerFolgezustandMenge = new Zustandsmenge();

        Zustandsmenge neueZustaendeDEA = new Zustandsmenge();
        ArrayList<Zustandsmenge> neueZustaendeNEA = new ArrayList<Zustandsmenge>();
        ArrayList<Zustandsmenge> nochBearbeiten = new ArrayList<Zustandsmenge>();
        //ArrayList<Zustandsmenge> bearbeitet = new ArrayList<Zustandsmenge>();

        //String bezeichnungZustandDEA;
        String bezeichnungNeuerZustand;

        if(pNEA.istVollstaendigDefiniert()) {
            if(pNEA.istDeterministisch()) {
                dea = pNEA;
            } else {
                // Lokale Variablen initialisieren
                eNEA = pNEA.gibE();
                dNEA = pNEA.gibD();
                fNEA = pNEA.gibF();
                sNEA = pNEA.gibS();
                // neue Zustaende bestimmen und als Zustandsmenge speichern
                aktuellerFolgezustandMenge.hinzufuegen(sNEA);
                neueZustaendeNEA.add((Zustandsmenge) aktuellerFolgezustandMenge.gibKopie());
                nochBearbeiten.add((Zustandsmenge) aktuellerFolgezustandMenge.gibKopie());
                aktuellerFolgezustandMenge.leere();
                // Fuer jeden neuen Zustand
                while(!nochBearbeiten.isEmpty()) {
                    aktuellerZustandMenge = nochBearbeiten.get(0);
                    // Fuer jedes Zeichen
                    eNEA.zumAnfang();
                    while(!eNEA.istAmEnde()) {
                        aktuellesZeichen = eNEA.gibAktuelles();
                        // Moegliche Folgezustaende
                        aktuellerZustandMenge.zumAnfang();
                        while(!aktuellerZustandMenge.istAmEnde()) {
                            aktuellerUebergang = dNEA.gibUebergang(aktuellerZustandMenge.gibAktuelles(), aktuellesZeichen);
                            if(aktuellerUebergang != null) {
                                aktuellerFolgezustandMenge.vereinigen(aktuellerUebergang.gibNachfolgezustaende());
                            }
                            aktuellerZustandMenge.zumNaechsten();
                        }
                        if(!aktuellerFolgezustandMenge.istLeer()) {
                            boolean istBereitsVorhanden = false;
                            for(int i = 0; i < neueZustaendeNEA.size(); i++) {
                                Zustandsmenge temp = neueZustaendeNEA.get(i);
                                if(temp.bestimmeSymmetrischeDifferenz(aktuellerFolgezustandMenge).istLeer()) {
                                    istBereitsVorhanden = true;
                                    break;
                                }
                            }
                            if(!istBereitsVorhanden) {
                                // Neuer Zustand
                                neueZustaendeNEA.add((Zustandsmenge) aktuellerFolgezustandMenge.gibKopie());
                                nochBearbeiten.add((Zustandsmenge) aktuellerFolgezustandMenge.gibKopie());
                            }
                            aktuellerFolgezustandMenge.leere();
                        }
                        eNEA.zumNaechsten();
                    }
                    nochBearbeiten.remove(0);
                }

                // Neue Zustaende als Zustand speichern
                for(int i = 0; i < neueZustaendeNEA.size(); i++) {
                    bezeichnungNeuerZustand = "";
                    aktuellerZustandMenge = neueZustaendeNEA.get(i);
                    aktuellerZustandMenge.zumAnfang();
                    // Bezeichnung bestimmen
                    while(!aktuellerZustandMenge.istAmEnde()) {
                        bezeichnungNeuerZustand += aktuellerZustandMenge.gibAktuelles().gibBezeichnung();
                        aktuellerZustandMenge.zumNaechsten();
                    }
                    neueZustaendeDEA.hinzufuegen(Zustand.erstelleZustand(10000000, bezeichnungNeuerZustand));                               // !!!!!!!!!!!!!!
                }

                // Zustandsmenge
                neueZustaendeDEA.zumAnfang();
                while(!neueZustaendeDEA.istAmEnde()) {
                    dea.neuerZustand(neueZustaendeDEA.gibAktuelles().gibNummer());                                   // !!!!!
                    neueZustaendeDEA.zumNaechsten();
                }
                neueZustaendeDEA.zumAnfang();
                dea.setzeStartzustand(neueZustaendeDEA.gibAktuelles().gibNummer());

                // Uebergangsrelation
                int zaehler1 = 0;
                Zustandsmenge neueZustaendeDEAKopie = (Zustandsmenge) neueZustaendeDEA.gibKopie();
                neueZustaendeDEA.zumAnfang();
                // Fuer jeden Zustand im DEA
                while(!neueZustaendeDEA.istAmEnde()) {
                    aktuellerZustand = neueZustaendeDEA.gibAktuelles();
                    aktuellerZustandMenge = neueZustaendeNEA.get(zaehler1);
                    // Fuer jedes Zeichen
                    eNEA.zumAnfang();
                    while(!eNEA.istAmEnde()) {
                        aktuellesZeichen = eNEA.gibAktuelles();
                        aktuellerFolgezustandMenge.leere();
                        // Moegliche Folgezustaende im NEA
                        aktuellerZustandMenge.zumAnfang();
                        while(!aktuellerZustandMenge.istAmEnde()) {
                            aktuellerUebergang = dNEA.gibUebergang(aktuellerZustandMenge.gibAktuelles(), aktuellesZeichen);
                            if(aktuellerUebergang != null) {
                                aktuellerFolgezustandMenge.vereinigen(aktuellerUebergang.gibNachfolgezustaende());
                            }
                            aktuellerZustandMenge.zumNaechsten();
                        }
                        // Fuer jeden Folgezustand im NEA
                        aktuellerFolgezustandMenge.zumAnfang();
                        while(!aktuellerFolgezustandMenge.istAmEnde()) {
                            int zaehler2 = 0;
                            // Suche nach entsprechendem Zustand im DEA
                            neueZustaendeDEAKopie.zumAnfang();
                            while(!neueZustaendeDEAKopie.istAmEnde()) {
                                aktuellerFolgezustand = neueZustaendeDEAKopie.gibAktuelles();
                                if(zaehler1 != zaehler2) {
                                    if(neueZustaendeNEA.get(zaehler2).bestimmeSymmetrischeDifferenz(aktuellerFolgezustandMenge).istLeer()) {
                                        // Neuer Uebergang
                                        dea.neuerUebergang(aktuellerZustand.gibNummer(), aktuellesZeichen.gibBezeichnung(), aktuellerFolgezustand.gibNummer());
                                        break; // Optional, da DEA
                                    }
                                }
                                zaehler2++;
                                neueZustaendeDEAKopie.zumNaechsten();
                            }
                            aktuellerFolgezustandMenge.zumNaechsten();
                        }
                        eNEA.zumNaechsten();
                    }
                    zaehler1++;
                    neueZustaendeDEA.zumNaechsten();
                }

                // Endzustandsmenge
                neueZustaendeDEA.zumAnfang();
                zaehler1 = 0;
                while(!neueZustaendeDEA.istAmEnde()) {
                    boolean istEndzustand = false;
                    aktuellerZustand = neueZustaendeDEA.gibAktuelles();
                    aktuellerZustandMenge = neueZustaendeNEA.get(zaehler1);
                    aktuellerZustandMenge.zumAnfang();
                    while(!aktuellerZustandMenge.istAmEnde()) {
                        if(!aktuellerZustandMenge.bestimmeSchnitt(fNEA).istLeer()) {
                            istEndzustand = true;
                            break; // Optional
                        }
                        aktuellerZustandMenge.zumNaechsten();
                    }
                    if(istEndzustand) {
                        dea.setzeZustandAlsEndzustand(aktuellerZustand.gibNummer());
                    }
                    zaehler1++;
                    neueZustaendeDEA.zumNaechsten();
                }

                // Eingabealphabet
                eNEA.zumAnfang();
                while(!eNEA.istAmEnde()) {
                    dea.neuesZeichen(eNEA.gibAktuelles().gibBezeichnung());
                    eNEA.zumNaechsten();
                }

                // Startzustand
                dea.setzeStartzustand(sNEA.gibNummer());
            }
        }
        return dea;
    }

    /**
     * Minimiert einen deterministischen endlichen Automaten (DEA) 
     * mithilfe des Verfahrens der Aequivalenzklassenkonstruktion.
     * 
     * @param   pDEA    zu minimierender DEA
     * 
     * @return  minimierter DEA
     */
    public static Automat konstruiereMIN(Automat pDEA) {
        Automat minDEA = new Automat();

        Alphabet e;
        Zustandsmenge q;
        Zustandsmenge f;
        Relation d;
        Zustand s;

        Zustand aktuellerZustand1;
        Zustand aktuellerZustand2;
        Zustandsmenge aktuellerNeuerZustand;
        Zeichen aktuellesZeichen;
        Uebergang aktuellerUebergang;

        ArrayList<Zustandsmenge> neueZustaende = new ArrayList<Zustandsmenge>();
        Zustandsmenge qNeu;
        String bezeichnungNeuerZustand;
        Zustandsmenge nachfolgeZustaende;
        Zustand nachfolgeZustand;

        boolean[][] matrix;
        Zustandsmenge qKopie;
        Zustandsmenge temp;
        String[] worte;
        int zaehler1;
        int zaehler2;
        boolean gefunden = true;
        int laenge;
        int anzahlZeichen;

        if(pDEA.istVollstaendigDefiniert()) {
            if(!pDEA.istDeterministisch()) {
                minDEA = Algorithmen.konstruiereMIN(Algorithmen.konstruiereDEA(pDEA));
            } else {
                e = pDEA.gibE();
                q = pDEA.gibQ();
                f = pDEA.gibF();
                d = pDEA.gibD();
                s = pDEA.gibS();

                matrix = new boolean[q.anzahl()][q.anzahl()];
                qKopie = (Zustandsmenge) q.gibKopie();
                anzahlZeichen = e.anzahl();

                // End-/Nichtendzustaende
                zaehler1 = 0;
                q.zumAnfang();
                while(!q.istAmEnde()) {
                    zaehler2 = 0;
                    qKopie.zumAnfang();
                    while(!qKopie.istAmEnde()) {
                        if(zaehler2 > zaehler1) {
                            aktuellerZustand1 = q.gibAktuelles();
                            aktuellerZustand2 = qKopie.gibAktuelles();
                            if((f.istVorhanden(aktuellerZustand1) && !f.istVorhanden(aktuellerZustand2)) || (!f.istVorhanden(aktuellerZustand1) && f.istVorhanden(aktuellerZustand2))) {
                                matrix[zaehler1][zaehler2] = true;
                            }
                        }
                        zaehler2++;
                        qKopie.zumNaechsten();
                    }
                    zaehler1++;
                    q.zumNaechsten();
                }

                laenge = 1;
                while(gefunden && laenge <= q.anzahl()) {
                    gefunden = false;
                    worte = new String[(int) Math.pow(anzahlZeichen, laenge)];
                    for(int i = 0; i < worte.length; i++) {
                        worte[i] = "";
                    }
                    for(int i = 1; i <= laenge; i++) {
                        zaehler1 = 0;
                        for(int j= 0; j < (int) Math.pow(anzahlZeichen, i-1); j++) {
                            e.zumAnfang();
                            while(!e.istAmEnde()) {
                                aktuellesZeichen = e.gibAktuelles();
                                for(int k = 0; k < (int) Math.pow(anzahlZeichen, laenge - i); k++) {
                                    worte[zaehler1] += aktuellesZeichen.gibBezeichnung();
                                    zaehler1++;
                                }
                                e.zumNaechsten();
                            }
                        }
                    }
                    zaehler1 = 0;
                    q.zumAnfang();
                    while(!q.istAmEnde()) {
                        zaehler2 = 0;
                        qKopie.zumAnfang();
                        // Rekursion
                        while(!qKopie.istAmEnde()) {
                            if(zaehler2 > zaehler1) {
                                if(!matrix[zaehler1][zaehler2]) {
                                    aktuellerZustand1 = q.gibAktuelles();
                                    aktuellerZustand2 = qKopie.gibAktuelles();

                                    for(int i = 0; i < worte.length; i++) {
                                        if(Algorithmen.akzeptiertEingabeRekursiv(pDEA, worte[i], aktuellerZustand1) != Algorithmen.akzeptiertEingabeRekursiv(pDEA, worte[i], aktuellerZustand2)) {
                                            matrix[zaehler1][zaehler2] = true;
                                            gefunden = true;
                                            break;
                                        }
                                    }
                                }
                            }
                            zaehler2++;
                            qKopie.zumNaechsten();
                        }
                        zaehler1++;
                        q.zumNaechsten();
                    }
                    laenge++;
                }

                zaehler1 = 0;
                q.zumAnfang();
                while(!q.istAmEnde()) {
                    zaehler2 = 0;
                    qKopie.zumAnfang();
                    while(!qKopie.istAmEnde()) {
                        if(zaehler2 > zaehler1) {
                            aktuellerZustand1 = q.gibAktuelles();
                            aktuellerZustand2 = qKopie.gibAktuelles();
                            if(!matrix[zaehler1][zaehler2]) {
                                gefunden = false;
                                for(int i = 0; i < neueZustaende.size(); i++) {
                                    aktuellerNeuerZustand = neueZustaende.get(i);
                                    if(aktuellerNeuerZustand.istVorhanden(aktuellerZustand1) || aktuellerNeuerZustand.istVorhanden(aktuellerZustand2)) {
                                        aktuellerNeuerZustand.hinzufuegen(aktuellerZustand1);
                                        aktuellerNeuerZustand.hinzufuegen(aktuellerZustand2);
                                        gefunden = true;
                                        break;
                                    }
                                }
                                if(!gefunden) {
                                    aktuellerNeuerZustand = new Zustandsmenge();
                                    aktuellerNeuerZustand.hinzufuegen(aktuellerZustand1);
                                    aktuellerNeuerZustand.hinzufuegen(aktuellerZustand2);
                                    neueZustaende.add(aktuellerNeuerZustand);
                                }
                            }
                        }
                        zaehler2++;
                        qKopie.zumNaechsten();
                    }
                    zaehler1++;
                    q.zumNaechsten();
                }

                q.zumAnfang();
                while(!q.istAmEnde()) {
                    aktuellerZustand1 = q.gibAktuelles();
                    gefunden = true;
                    for(int i = 0; i < neueZustaende.size(); i++) {
                        aktuellerNeuerZustand = neueZustaende.get(i);
                        if(aktuellerNeuerZustand.istVorhanden(aktuellerZustand1)) {
                            gefunden = false;
                            break;
                        }
                    }
                    if(gefunden) {
                        aktuellerNeuerZustand = new Zustandsmenge();
                        aktuellerNeuerZustand.hinzufuegen(aktuellerZustand1);
                        neueZustaende.add(aktuellerNeuerZustand);
                    }
                    q.zumNaechsten();
                }

                // Eingabealphabet
                e.zumAnfang();
                while(!e.istAmEnde()) {
                    aktuellesZeichen = e.gibAktuelles();
                    minDEA.neuesZeichen(aktuellesZeichen.gibBezeichnung());
                    e.zumNaechsten();
                }

                // Zustandsmenge
                // Endzustandsmenge
                qNeu = new Zustandsmenge();
                for(int i = 0; i < neueZustaende.size(); i++) {
                    aktuellerNeuerZustand = neueZustaende.get(i);
                    bezeichnungNeuerZustand = "";
                    aktuellerNeuerZustand.zumAnfang();
                    while(!aktuellerNeuerZustand.istAmEnde()) {
                        aktuellerZustand1 = aktuellerNeuerZustand.gibAktuelles();
                        bezeichnungNeuerZustand += aktuellerZustand1.gibBezeichnung();
                        aktuellerNeuerZustand.zumNaechsten();
                    }
                    qNeu.hinzufuegen(Zustand.erstelleZustand(i,bezeichnungNeuerZustand));
                }
                qNeu.zumAnfang();
                for(int i = 0; i < neueZustaende.size(); i++) {
                    aktuellerZustand1 = qNeu.gibAktuelles();
                    minDEA.neuerZustand(aktuellerZustand1.gibNummer(), aktuellerZustand1.gibBezeichnung());
                    aktuellerNeuerZustand = neueZustaende.get(i);
                    if(!f.bestimmeSchnitt(aktuellerNeuerZustand).istLeer()) {
                        minDEA.setzeZustandAlsEndzustand(aktuellerZustand1.gibNummer());
                    }
                    qNeu.zumNaechsten();
                }

                // Uebergangsrelation
                qKopie = (Zustandsmenge) qNeu.gibKopie();
                qNeu.zumAnfang();
                for(int i = 0; i < neueZustaende.size(); i++) {
                    aktuellerZustand1 = qNeu.gibAktuelles();
                    aktuellerNeuerZustand = neueZustaende.get(i);
                    e.zumAnfang();
                    while(!e.istAmEnde()) {
                        aktuellesZeichen = e.gibAktuelles();
                        nachfolgeZustaende = new Zustandsmenge();
                        aktuellerNeuerZustand.zumAnfang();
                        while(!aktuellerNeuerZustand.istAmEnde()) {
                            aktuellerUebergang = d.gibUebergang(aktuellerNeuerZustand.gibAktuelles(), aktuellesZeichen);
                            if(aktuellerUebergang != null) {
                                nachfolgeZustaende.vereinigen(aktuellerUebergang.gibNachfolgezustaende());
                                break;
                            }
                            aktuellerNeuerZustand.zumNaechsten();
                        }
                        nachfolgeZustaende.zumAnfang();
                        nachfolgeZustand = nachfolgeZustaende.gibAktuelles();
                        if(nachfolgeZustand != null) {
                            gefunden = false;
                            qKopie.zumAnfang();
                            for(int j = 0; j < neueZustaende.size(); j++) {
                                temp = (Zustandsmenge) neueZustaende.get(j).gibKopie();
                                temp.zumAnfang();
                                while(!temp.istAmEnde()) {
                                    if(temp.istVorhanden(nachfolgeZustand)) {
                                        gefunden = true;
                                    }
                                    temp.zumNaechsten();
                                }
                                if(gefunden) {
                                    aktuellerZustand2 = qKopie.gibAktuelles();
                                    minDEA.neuerUebergang(aktuellerZustand1.gibNummer(), aktuellesZeichen.gibBezeichnung(), aktuellerZustand2.gibNummer());                                       // °°°°°°°°°°°
                                    break;
                                }
                                qKopie.zumNaechsten();
                            }
                        }
                        e.zumNaechsten();
                    }
                    qNeu.zumNaechsten();
                }

                // Startzustand
                minDEA.setzeStartzustand(s.gibNummer());
            }
        }

        return minDEA;
    }
}