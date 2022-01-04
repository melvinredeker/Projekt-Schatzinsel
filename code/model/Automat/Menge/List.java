package model.Automat.Menge;

/**
 * Materialien zu den zentralen NRW-Abiturpruefungen im Fach Informatik ab 2018
 * 
 * Generische Klasse List<ContentType>
 * 
 * Objekt der generischen Klasse List verwalten beliebig viele linear
 * angeordnete Objekte vom Typ ContentType. Auf hoechstens ein Listenobjekt,
 * aktuellesObjekt genannt, kann jeweils zugegriffen werden.
 * 
 * Wenn eine Liste leer ist, vollstaendig durchlaufen wurde oder das aktuelle
 * Objekt am Ende der Liste geloescht wurde, gibt es kein aktuelles Objekt.
 * Das erste oder das letzte Objekt einer Liste koennen durch einen Auftrag zum
 * aktuellen Objekt gemacht werden. Ausserdem kann das dem aktuellen Objekt
 * folgende Listenobjekt zum neuen aktuellen Objekt werden.Das aktuelle Objekt 
 * kann gelesen, veraendert oder geloescht werden. Ausserdem kann vor dem 
 * aktuellen Objekt ein Listenobjekt eingefuegt werden.
 * 
 * @author  NRW
 * @version 2015-10-25
 */
public class List<ContentType> {

    /* --------- Anfang der privaten inneren Klasse -------------- */
    private class ListNode {
        private ContentType content;
        private ListNode next;

        /**
         * Ein neues Objekt wird erschaffen. Der Verweis ist leer.
         * 
         * @param   pContent    das Inhaltsobjekt vom Typ ContentType
         */
        private ListNode(ContentType pContent) {
            this.content = pContent;
            this.next = null;
        }

        /**
         * Der Inhalt des Knotens wird zurueckgeliefert.
         * 
         * @return  das Inhaltsobjekt des Knotens
         */
        public ContentType getcontent() {
            return this.content;
        }

        /**
         * Der Inhalt dieses Kontens wird gesetzt.
         * 
         * @param   pContent    das Inhaltsobjekt vom Typ ContentType
         */
        public void setcontent(ContentType pContent) {
            this.content = pContent;
        }

        /**
         * Der Nachfolgeknoten wird zurueckgeliefert.
         * 
         * @return  das Objekt, auf das der aktuelle Verweis zeigt
         */
        public ListNode getNext() {
            return this.next;
        }

        /**
         * Der Verweis wird auf das Objekt, das als Parameter uebergeben wird, gesetzt.
         * 
         * @param   pNext   der Nachfolger des Knotens
         */
        public void setNext(ListNode pNext) {
            this.next = pNext;
        }

    }
    /* ----------- Ende der privaten inneren Klasse -------------- */

    ListNode first;
    ListNode last;
    ListNode current;

    /**
     * Eine leere Liste wird erzeugt.
     */
    public List() {
        this.first = null;
        this.last = null;
        this.current = null;
    }

    /**
     * Die Anfrage liefert den Wert true, 
     * wenn die Liste keine Objekte enthaelt,
     * sonst liefert sie den Wert false.
     * 
     * @return  true, wenn die Liste leer ist, sonst false
     */
    public boolean isEmpty() {
        return this.first == null;
    }

    /**
     * Die Anfrage liefert den Wert true, 
     * wenn es ein aktuelles Objekt gibt,
     * sonst liefert sie den Wert false.
     * 
     * @return  true, falls Zugriff moeglich, sonst false
     */
    public boolean hasAccess() {
        return this.current != null; 
    }

    /**
     * Falls die Liste nicht leer ist, es ein aktuelles Objekt gibt und dieses
     * nicht das letzte Objekt der Liste ist, wird das dem aktuellen Objekt in
     * der Liste folgende Objekt zum aktuellen Objekt, andernfalls gibt es nach
     * Ausfuehrung des Auftrags kein aktuelles Objekt, d.h. hasAccess() liefert
     * den Wert false.
     */
    public void next() {
        if(this.hasAccess()) {
            this.current = this.current.getNext();
        }
    }

    /**
     * Falls die Liste nicht leer ist, wird das erste Objekt der Liste 
     * aktuelles Objekt. Ist die Liste leer, geschieht nichts.
     */
    public void toFirst() {
        if(!this.isEmpty()) {
            this.current = this.first;
        }
    }

    /**
     * Falls die Liste nicht leer ist, wird das letzte Objekt der Liste
     * aktuelles Objekt. Ist die Liste leer, geschieht nichts.
     */
    public void toLast() {
        if(!this.isEmpty()) {
            this.current = this.last;
        }
    }

    /**
     * Falls es ein aktuelles Objekt gibt (hasAccess() == true), wird das aktuelle Objekt zurueckgegeben, 
     * andernfalls (hasAccess() == false) gibt die Anfrage den Wert null zurueck.
     * 
     * @return  das aktuelle Objekt (vom Typ ContentType) oder 
     *          null, wenn es kein aktuelles Objekt gibt
     */
    public ContentType getContent() {
        if(this.hasAccess()) {
            return this.current.getcontent();
        } 
        else {
            return null;
        }
    }

    /**
     * Falls es ein aktuelles Objekt gibt (hasAccess() == true) und pContent ungleich null ist, 
     * wird das aktuelle Objekt durch pContent ersetzt. Sonst geschieht nichts.
     * 
     * @param   pContent    das zu schreibende Objekt vom Typ ContentType
     */
    public void setContent(ContentType pContent) {
        if(pContent != null && this.hasAccess()) { 
            this.current.setcontent(pContent);
        }
    }

    /**
     * Falls es ein aktuelles Objekt gibt (hasAccess() == true), 
     * wird ein neues Objekt vor dem aktuellen Objekt in die Liste eingefuegt. 
     * Das aktuelle Objekt bleibt unveraendert. 
     * 
     * Wenn die Liste leer ist, wird pContent in die Liste eingefuegt und 
     * es gibt weiterhin kein aktuelles Objekt (hasAccess() == false).
     * Falls es kein aktuelles Objekt gibt (hasAccess() == false) und die 
     * Liste nicht leer ist oder pContent gleich null ist, geschieht nichts.
     * 
     * @param   pContent    das einzufuegende Objekt vom Typ ContentType
     */
    public void insert(ContentType pContent) {
        if(pContent != null) {
            if(this.hasAccess()) {
                ListNode newNode = new ListNode(pContent); 
                if(this.current != first) {
                    ListNode previous = this.getPrevious(current);
                    newNode.setNext(previous.getNext());
                    previous.setNext(newNode);
                } 
                else {
                    newNode.setNext(first);
                    this.first = newNode;
                }
            } 
            else { 
                if(this.isEmpty()) {
                    ListNode newNode = new ListNode(pContent); 
                    this.first = newNode;
                    this.last = newNode;
                }
            }
        }
    }

    /**
     * Falls pContent gleich null ist, geschieht nichts.
     * Ansonsten wird ein neues Objekt pContent am Ende der 
     * Liste eingefuegt.Das aktuelle Objekt bleibt unveraendert.
     * 
     * Wenn die Liste leer ist, wird das Objekt pContent in die Liste eingefuegt
     * und es gibt weiterhin kein aktuelles Objekt (hasAccess() == false).
     * 
     * @param   pContent    das anzuhaengende Objekt vom Typ ContentType
     */
    public void append(ContentType pContent) {
        if(pContent != null) {
            if (this.isEmpty()) {
                this.insert(pContent);
            } 
            else {
                ListNode newNode = new ListNode(pContent); 
                this.last.setNext(newNode);
                this.last = newNode;
            }
        }
    }

    /**
     * Falls es sich bei der Liste und pList um dasselbe Objekt handelt,
     * pList null oder eine leere Liste ist, geschieht nicht. Ansonsten 
     * wird die Liste pList an die aktuelle Liste angehaengt. Anschliessend 
     * wird pList eine leere Liste. Das aktuelle Objekt bleibt unveraendert. 
     * Insbesondere bleibt hasAccess identisch.
     * 
     * @param   pList   die am Ende anzuhaengende Liste vom Typ List<ContentType>
     */
    public void concat(List<ContentType> pList) {
        if(pList != this && pList != null && !pList.isEmpty()) {
            if(this.isEmpty()) {
                this.first = pList.first;
                this.last = pList.last;
            } 
            else {
                this.last.setNext(pList.first);
                this.last = pList.last;
            }
            pList.first = null;
            pList.last = null;
            pList.current = null;
        }
    }

    /**
     * Wenn die Liste leer ist oder es kein aktuelles Objekt gibt (hasAccess() == false), 
     * geschieht nichts. Falls es ein aktuelles Objekt gibt (hasAccess() == true), wird 
     * das aktuelle Objekt geloescht und das Objekt hinter dem geloeschten Objekt wird 
     * zum aktuellen Objekt. Wird das Objekt, das am Ende der Liste steht, geloescht, 
     * gibt es kein aktuelles Objekt mehr.
     */
    public void remove() {
        if(this.hasAccess() && !this.isEmpty()) { 
            if(this.current == this.first) {
                this.first = this.first.getNext();
            } 
            else {
                ListNode previous = this.getPrevious(current);
                if(this.current == this.last) {
                    this.last = previous;
                }
                previous.setNext(this.current.getNext());
            }
            ListNode temp = current.getNext();
            this.current.setcontent(null);
            this.current.setNext(null);
            this.current = temp;
            if(this.isEmpty()) {
                this.last = null;
            }
        }
    }

    /**
     * Liefert den Vorgaengerknoten des Knotens pNode. Ist die Liste leer 
     * (pNode == null) pNode nicht in der Liste oder pNode der erste Knoten 
     * der Liste, wird null zurueckgegeben.
     *
     * @param   pNode   der Knoten, dessen Vorgaenger zurueckgegeben werden soll
     * 
     * @return  der Vorgaenger des Knotens pNode oder null, falls die Liste leer ist,
     *          pNode == null ist, pNode nicht in der Liste ist oder pNode der erste Knoten der Liste ist
     */
    private ListNode getPrevious(ListNode pNode) {
        if(pNode != null && pNode != this.first && !this.isEmpty()) {
            ListNode temp = this.first;
            while(temp != null && temp.getNext() != pNode) {
                temp = temp.getNext();
            }
            return temp;
        } 
        else {
            return null;
        }
    }
}