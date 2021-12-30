# Projekt-Schatzinsel

## Planung ##

### Meilenstein 1 ###
- [X] App bekommt getter Methode für die Anwendung
- [X] Methode "aktion" in App wird in die einzelnen Controller ausgelagert

### Meilenstein 2 ###

- [ ] Anpassungen der Automaten-Algorithmen an die Modellierungsergänzung der Klasse Zustand (Bezeichnung besteht fortan aus Präfix, ID und zusätzlich einer textlichen Beschriftung für das Schatzinsel-Projekt)

- [ ] Warnhinweis/Sicherheitsfrage vor Rückkehr aus dem Entdecker-Modus in das Hauptmenü

- [ ] Inseldesign im Analyse-Modus und beim Export


### Meilenstein 3 ###

- [ ] Vergrößerung der GUI

- [ ] Textfeld für die Buchstabenfolge im Analyse-Modus verbreitern für längere Eingaben

- [ ] Textliche Anpassungen im Analyse-Modus
	- "Umweg vorhanden." anstatt "Wiederholung vorhanden."
	- "Bitte doppeltes Inselpaar auswählen!" anstatt "Bitte Wiederholung auswählen!"
	- "Kürzesten Weg gefunden." anstatt "Keine Wiederholung vorhanden."

- [ ] Benutzerfeedback im Analyse-Modus optimieren
	- anstatt bei einer falschen Auswahl das Feedback in weiß auf grün unten über den Button auszugeben 
	- und bei korrekter Auswahl kein Feedback zu erhalten, 
	- sollte das Feedback mittig für ca. 3sek eingeblendet werden
	-> bei falscher Auswahl: "Kein Inselpaar!" in orange
	-> bei korrekter Auswahl: "Prima!" in grün

- [ ] Bessere farbige Hervorhebung im Analyse-Modus
	- "Ziel nicht erreicht." rechts unten in orange
	- "Ungültige Eingabe." rechts unten in orange

- [ ] Fehlersenke beim Überschreiten der Schatzinsel 
	- die Schatzinsel orange anstatt grün färben
	- untergehendes Schiff mit Bezeichnung zusätzlich anzeigen

### Meilenstein 4 ###

Zusätzliche Buttons auf dem Startbildschirm...

- [ ] zum Öffnen einer als Grafik hinterlegten Seekarte, die zusätzlich automatisch in den Zwischenspeicher kopiert wird und in einem beliebigen Programm eingefügt werden kann, um die Seekarte durch Eintragungen zu vervollständigen.

- [ ] für einen weiteren Modus (Name?), indem die Übergangsfunktion tabellarisch ausgefüllt und die Eingabe vom System auf Fehler überprüft werden kann.
	- ggf. schaltet sich dieser Modus erst dann frei, wenn man zuvor über den Analyse-Modus den kürzesten Weg eingegeben hat

- [ ]  zum Laden von anderen Seekarten bzw. Automaten

### Meilenstein 5 ###

- [ ] Erweiterbarkeit durch eigene Automaten

	resources/
		css/...
		gui_images/...
		automat_default/
			wallpapers/...
			automat.ini
			seekarte.jpg
		automat_beispiel1/
			wallpapers/...
			automat.ini
			seekarte.jpg
		automat_beispiel2/
			wallpapers/...
			automat.ini
			seekarte.jpg	
		readme.txt

- [ ] Start des Spiels ohne BlueJ per exe
