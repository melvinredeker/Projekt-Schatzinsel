import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
//import java.io.FileNotFoundException;
import javafx.scene.image.Image;
import java.io.File;
//import javafx.stage.Stage;

public class inselController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ImageView hintergrundbild;

    @FXML
    private Button button_b;

    @FXML
    private Button button_a;

    @FXML
    private TextField insel_name;

    private App app;
    
     @FXML
    private Button button_hauptmenü;

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

    public void setzeApp(App pApp) {
        app = pApp;
    }

    @FXML
    void h_a(MouseEvent event) {
        button_a.setScaleX(1.1);
        button_a.setScaleY(1.1);
    }

    @FXML
    void h_b(MouseEvent event) {
        button_b.setScaleX(1.1);
        button_b.setScaleY(1.1);
    }

    @FXML
    void he_a(MouseEvent event) {
        button_a.setScaleX(1.0);
        button_a.setScaleY(1.0);
    }

    @FXML
    void he_b(MouseEvent event) {
        button_b.setScaleX(1.0);
        button_b.setScaleY(1.0);
    }

    @FXML
    void schiff_a(ActionEvent event) throws Exception {
        Schatzinselanwendung anwendung = this.app.gibAnwendung();
        anwendung.fahreMitSchiff("A");
            if(anwendung.istAmZiel()) {
                this.app.zielOeffnen();
            } else {
                this.neueInsel(anwendung.gibAktuelleInselBezeichnung());
            }
    }

    @FXML
    void schiff_b(ActionEvent event) throws Exception {
        Schatzinselanwendung anwendung = this.app.gibAnwendung();
        anwendung.fahreMitSchiff("B");
            if(anwendung.istAmZiel()) {
                this.app.zielOeffnen();
            } else {
                this.neueInsel(anwendung.gibAktuelleInselBezeichnung());
            }
    }

    @FXML
    void initialize() {
    } 
    
    public void neueInsel(String pBezeichnung) {
        insel_name.setText(pBezeichnung);
        
        File file1 = new File("resources/wallpapers/" + pBezeichnung + ".jpeg");
        File file2 = new File("resources/wallpapers/" + pBezeichnung + ".jpg");
        File file3 = new File("resources/wallpapers/" + pBezeichnung + ".png");
        
        if (file1.exists()) {
            this.hintergrundbild.setImage(new Image("resources/wallpapers/" + pBezeichnung + ".jpeg"));
        } else if(file2.exists()) {
            this.hintergrundbild.setImage(new Image("resources/wallpapers/" + pBezeichnung + ".jpg"));
        } else if(file3.exists()) {
            this.hintergrundbild.setImage(new Image("resources/wallpapers/" + pBezeichnung + ".png"));
        } else {
            this.hintergrundbild.setImage(new Image(("resources/wallpapers/default.png")));
        }
        
        hintergrundbild.setPreserveRatio(false);
        
    }
}