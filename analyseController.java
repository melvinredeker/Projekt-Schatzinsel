import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.image.Image;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.input.ScrollEvent;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.layout.Pane;
import javafx.scene.input.MouseEvent;
//import javafx.scene.input.MouseDragEvent;
import javafx.scene.input.KeyEvent;
import java.lang.StringBuilder;
import javafx.scene.text.Text;
import java.util.ArrayList;
import javafx.scene.input.KeyCode;
import javafx.scene.Cursor;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.OverrunStyle;
import javafx.geometry.Pos;
import javafx.scene.paint.Color;
import javafx.scene.text.TextAlignment;
import javafx.scene.control.Label;
import javafx.scene.image.WritableImage;
//import javafx.stage.FileChooser;
import java.io.File;
import java.awt.image.BufferedImage;
import javafx.embed.swing.SwingFXUtils;
import javax.imageio.ImageIO;
//import java.util.logging.Logger;
//import java.util.logging.Level;
import javafx.scene.SnapshotParameters;
//import javafx.stage.FileChooser;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import java.io.IOException;
import javax.swing.filechooser.FileNameExtensionFilter;

public class analyseController {
    @FXML
    private Pane pane_alles;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ImageView hintergrundbild;

    @FXML
    private TextField eingabe_feld;

    @FXML
    private Button button_los;

    @FXML
    private Text label_information;

    @FXML
    private Text wiederholung_1;

    @FXML
    private Text wiederholung_2;

    @FXML
    private Button button_pruefe;

    @FXML
    private ScrollPane scroll;

    @FXML
    private GridPane grid;
    
    @FXML
    private Button button_exportiere;

    private App app;

    private double pos;

    private int zaehler = 0;

    private ArrayList<ToggleButton> insel_liste = new ArrayList<ToggleButton>();

    @FXML
    private Button button_hauptmenü;

    @FXML
    void eingabe(KeyEvent event) {
        int position = eingabe_feld.getCaretPosition();
        eingabe_feld.setText(eingabe_feld.getText().toUpperCase());
        String text = eingabe_feld.getText();

        for(int i = 0; i<text.length(); i++){
            if(!(text.charAt(i) == 'A') && !(text.charAt(i) == 'B')){
                StringBuilder sb = new StringBuilder(text);
                sb.deleteCharAt(i);
                text = sb.toString();
                if(i <= position){
                    position--;
                }
            }
        }

        eingabe_feld.setText(text);
        eingabe_feld.positionCaret(position);
    }

    @FXML
    void exportiere(ActionEvent event) throws Exception {
        this.exportiere();
    }
    
    @FXML
    void h_exportiere(MouseEvent event) {
        button_exportiere.setScaleX(1.1);
        button_exportiere.setScaleY(1.1);
    }
    
    @FXML
    void he_exportiere(MouseEvent event) {
        button_exportiere.setScaleX(1.0);
        button_exportiere.setScaleY(1.0);
    }
    
    @FXML
    void h_hauptmenü(MouseEvent event) {
        button_hauptmenü.setScaleX(1.1);
        button_hauptmenü.setScaleY(1.1);
    }

    @FXML
    void hauptmenü(ActionEvent event) throws Exception {
        app.startOeffnen();
    }

    @FXML
    void he_hauptmenü(MouseEvent event) {
        button_hauptmenü.setScaleX(1.0);
        button_hauptmenü.setScaleY(1.0);
    }

    @FXML
    void h_prüfe(MouseEvent event) {
        button_pruefe.setScaleX(1.1);
        button_pruefe.setScaleY(1.1);
    }

    @FXML
    void he_prüfe(MouseEvent event) {
        button_pruefe.setScaleX(1.0);
        button_pruefe.setScaleY(1.0);
    }

    @FXML
    void pruefe(ActionEvent event) throws Exception {
        this.bearbeiteEingabe();
    }

    public int[] gibPosInselnKlick() {
        int zaehler = 0;
        int[] inselnPos = new int[2];
        for(int i = 0; i < insel_liste.size(); i++) {
            if(insel_liste.get(i).isSelected()) {
                if(zaehler < 2) {
                    inselnPos[zaehler] = i;
                }
                zaehler++;
            }
        }
        if(zaehler == 2) {
            return inselnPos;
        } else {
            return null;
        }
    }

    public void setzeApp(App pApp) {
        app = pApp;
    }

    @FXML
    void initialize() {
        grid.getStylesheets().add("resources/CSS/insel_button.css");
        scroll.setFitToHeight(true);
        pane_alles.setOnScroll(new EventHandler<ScrollEvent>() {
                @Override
                public void handle(ScrollEvent event) {
                    if (event.getDeltaY() > 0) {
                        scroll.setHvalue(pos == scroll.getHmin() ? scroll.getHmin() : --pos);
                    } else {
                        scroll.setHvalue(pos == scroll.getHmax() ? scroll.getHmax() : ++pos);
                    }
                }
            });
    }

    @FXML
    void enterGedrueckt(KeyEvent event) throws Exception{
        if(event.getCode() == KeyCode.ENTER){
            this.bearbeiteEingabe();
        }
    }

    @FXML
    void los(ActionEvent event) throws Exception{
        this.bearbeiteEingabe();
    }

    public String gibEingabeFeldInhalt() {
        return this.eingabe_feld.getText();
    }

    public void setzeEingabeFeldInhalt(String pEingabe) throws Exception {
        this.eingabe_feld.setText(pEingabe);
        this.bearbeiteEingabe();
    }

    public void bearbeiteEingabe(){
        Schatzinselanwendung anwendung = this.app.gibAnwendung();
        String schiffsFolge = this.gibEingabeFeldInhalt();
            if(anwendung.istGueltigeEingabe(schiffsFolge)) {
                anwendung.setzeSchiffsfolge(schiffsFolge);
                String[] schiffsFolgeArray = new String[schiffsFolge.toCharArray().length];
                for(int i = 0; i < schiffsFolgeArray.length; i++) {
                    schiffsFolgeArray[i] = schiffsFolge.substring(i, i+1);
                }
                this.folgeErstellen(anwendung.gibBefahreneInselnBezeichnungen(), schiffsFolgeArray);
                if(anwendung.istAmZiel()) {
                    this.setzeTextInformation("Ziel erreicht!");
                } else {
                    this.setzeTextInformation("Ziel nicht erreicht!");
                }
                if(anwendung.istWiederholungVorhanden(anwendung.gibSchiffsfolge())) {
                    this.setzeTextWiederholung1("Wiederholung vorhanden");
                    this.setzeTextWiederholung2("Bitte Wiederholung auswählen");
                    this.buttonPruefeAn();
                } else {
                    this.setzeTextWiederholung1("Keine Wiederholung vorhanden");
                    this.setzeTextWiederholung2("");
                    this.buttonPruefeAus();
                }
            } else {
                this.setzeTextInformation("Ungültige Eingabe!");
            }
    }

    public void pruefeWiederholung() throws Exception{
        Schatzinselanwendung anwendung = this.app.gibAnwendung();
        int[] inselnPos = this.gibPosInselnKlick();
            if(inselnPos != null) {
                String text = anwendung.entferneWiederholung(anwendung.gibSchiffsfolge(), inselnPos[0], inselnPos[1]);
                if(!anwendung.gibSchiffsfolge().equals(text)) {
                    this.setzeEingabeFeldInhalt(text);
                    this.bearbeiteEingabe();
                } else {
                    this.setzeTextWiederholung2("Falsche Auswahl");
                }
            } else {
                this.setzeTextWiederholung2("Bitte zwei Inseln auswählen");
            }
    }

    public void exportiere() {
        Schatzinselanwendung anwendung = this.app.gibAnwendung();
        String schiffsFolge = anwendung.gibSchiffsfolge();
        String[] inselnBezeichnungen = anwendung.gibBefahreneInselnBezeichnungen();

        Pane pane = new Pane();
        Label label = new Label("Schiffsfolge: "+ schiffsFolge);
        label.setStyle("-fx-background-color:  #10ebb8");
        label.setFont(new Font("Arial", 25));
        label.setTextFill(Color.web("#ffffff"));
        label.setAlignment(Pos.CENTER);
        label.setTextAlignment(TextAlignment.CENTER);
        label.setTextOverrun(OverrunStyle.ELLIPSIS);

        int inselnAnzahl = inselnBezeichnungen.length;
        int height_tmp = (int) (inselnAnzahl  / 7) + 1;
        int height = 25 + height_tmp * 250;
        int width;

        if(inselnAnzahl > 6) {
            width = 2330;
        } else {
            int n1 = (inselnAnzahl) * 200;
            int n2 = (inselnAnzahl - 1) * 180;
            int n3  = n1 + n2;
            width = n3 + 50;
        }

        label.setPrefWidth(width);
        label.setPrefHeight(35);

        int x = 25;
        int y = 50;

        for(int i = 0; i < inselnBezeichnungen.length; i++) {
            Label aktuelleInsel = new Label(inselnBezeichnungen[i]);
            aktuelleInsel.setPrefSize(200,200);
            aktuelleInsel.setFont(new Font("Arial", 20));
            aktuelleInsel.setStyle("-fx-background-color: white; -fx-border-color:  #10ebb8; -fx-border-width: 5");
            aktuelleInsel.setTextFill(Color.web("#000000"));
            aktuelleInsel.setTextAlignment(TextAlignment.CENTER);
            aktuelleInsel.setAlignment(Pos.CENTER);

            aktuelleInsel.setLayoutX(x);
            aktuelleInsel.setLayoutY(y);

            pane.getChildren().add(aktuelleInsel);

            if(i + 1 != inselnBezeichnungen.length) {
                ImageView schiffBild = null;
                if(schiffsFolge.substring(i, i + 1).equals("A")) {
                    schiffBild = new ImageView("resources/gui_images/Schiff_A_Schwarz.png");
                } else if (schiffsFolge.substring(i, i + 1).equals("B")) {
                    schiffBild = new ImageView("resources/gui_images/Schiff_B_Schwarz.png");
                } 
                schiffBild.setFitWidth(130);
                schiffBild.setFitHeight(164);
                schiffBild.setLayoutX(x + 225);
                schiffBild.setLayoutY(y + 21);
                pane.getChildren().add(schiffBild); 
                if((i + 1) % 6 != 0) {
                    x = x + 380;
                } else {
                    y = y + 250;
                    x = 25;
                }
            }
        }   

        pane.setPrefSize(width, height);        
        pane.getChildren().add(label);
        Scene scene = new Scene(pane,width,height, Color.WHITESMOKE);        
        Stage stage = new Stage();        
        stage.setTitle("Image Export");
        stage.setScene(scene);

        //stage.show();

        JFileChooser fileChooser = new JFileChooser();
        JFrame frame = new JFrame("");
        fileChooser.setDialogTitle("Waehlen Sie den Speicherort aus");
        fileChooser.setFileFilter(new FileNameExtensionFilter("PNG Dateien","png"));
        fileChooser.setApproveButtonText("New Approve Text");      
        if (fileChooser.showSaveDialog(frame) == JFileChooser.APPROVE_OPTION) {
            File outputfile = new File(fileChooser.getSelectedFile().toString());
                
            if(!fileChooser.getSelectedFile().toString().contains(".png")) {
                outputfile = new File(fileChooser.getSelectedFile().toString() + ".png");
            }
                
            WritableImage img = pane.snapshot(new SnapshotParameters(), null);
            BufferedImage img2 = SwingFXUtils.fromFXImage(img, null);

            try {
                ImageIO.write(img2, "png", outputfile);
            } catch (IOException e) {
        
            }
        }
    }

    private ToggleButton gibInselButton(String pLabel) {
        ToggleButton insel_button = new ToggleButton();
        insel_button.setOnMouseEntered(new EventHandler<MouseEvent>() {
                @Override    
                public void handle(MouseEvent me) {
                    app.gibSzene().setCursor(Cursor.HAND);
                }
            });
        insel_button.setOnMouseExited(new EventHandler<MouseEvent>() {
                @Override    
                public void handle(MouseEvent me) {
                    app.gibSzene().setCursor(Cursor.DEFAULT);
                }
            });
        insel_button.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    if(insel_button.isSelected()) {
                        if(zaehler == 2) {
                            insel_button.setSelected(false);
                        } else {
                            zaehler++;
                        }
                    } else {
                        zaehler--;
                    }
                }
            });
        insel_button.setText(pLabel);
        insel_button.setMinSize(196, 196);
        insel_button.setFont(new Font("Arial", 18));
        insel_button.getStyleClass().add("toggle-button");
        insel_button.setFocusTraversable(false);
        return insel_button;
    }

    public void folgeErstellen(String[] pInselFolgeLabels, String[] pSchiffsFolge) {
        if(pInselFolgeLabels.length == pSchiffsFolge.length + 1) {
            for(int i = 0; i < grid.getChildren().size(); i++) {
                grid.getColumnConstraints().set(i, new ColumnConstraints());
            }
            grid.getChildren().clear();
            this.insel_liste.clear();
            this.zaehler = 0;

            for(int i = 0; i < pInselFolgeLabels.length; i++) {
                if(i < pInselFolgeLabels.length - 1) {
                    ColumnConstraints column1 = new ColumnConstraints(205);
                    grid.getColumnConstraints().add(i * 2, column1);
                    ToggleButton aktuellerButton = this.gibInselButton(pInselFolgeLabels[i]);
                    this.insel_liste.add(aktuellerButton);
                    grid.add(aktuellerButton, i * 2, 0);
                    ImageView bild;
                    if(pSchiffsFolge[i].equals("A")) {
                        bild = new ImageView(new Image("File:resources/gui_images/Schiff_A.png"));
                    } else {
                        bild = new ImageView(new Image("File:resources/gui_images/Schiff_B.png"));
                    }
                    bild.setFitHeight(122);
                    bild.setFitWidth(100);
                    ColumnConstraints column2 = new ColumnConstraints(109);
                    grid.getColumnConstraints().add(i * 2 + 1, column2);
                    grid.add(bild, i * 2 + 1, 0);
                } else {
                    ColumnConstraints column = new ColumnConstraints(96);
                    grid.getColumnConstraints().add(i * 2, column);
                    ToggleButton aktuellerButton = this.gibInselButton(pInselFolgeLabels[i]);
                    this.insel_liste.add(aktuellerButton);
                    grid.add(aktuellerButton, i * 2, 0);
                }
            }
            scroll.setHmax(5 * pInselFolgeLabels.length);
        }
    }

    public void setzeTextInformation(String pInhalt) {
        label_information.setText(pInhalt);
    }

    public void setzeTextWiederholung1(String pInhalt) {
        wiederholung_1.setText(pInhalt);
    }

    public void setzeTextWiederholung2(String pInhalt) {
        wiederholung_2.setText(pInhalt);
    }

    public void buttonPruefeAus() {
        button_pruefe.setVisible(false);
        for(int i = 0; i < this.insel_liste.size(); i++) {
            ToggleButton insel_button = this.insel_liste.get(i);
            insel_button.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        insel_button.setSelected(false);
                    }
                });
        }
    }

    public void buttonPruefeAn() {
        button_pruefe.setVisible(true);
        for(int i = 0; i < this.insel_liste.size(); i++) {
            ToggleButton insel_button = this.insel_liste.get(i);
            insel_button.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        if(insel_button.isSelected()) {
                            if(zaehler == 2) {
                                insel_button.setSelected(false);
                            } else {
                                zaehler++;
                            }
                        } else {
                            zaehler--;
                        }
                    }
                });
        }
    }
}