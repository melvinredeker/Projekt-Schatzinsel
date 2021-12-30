import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;

public class zielController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button button_analysemodus;

    @FXML
    private Button button_hauptmenue;

    @FXML
    private TextField insel_name;
    
    @FXML
    private Text label_information;
    
    private App app;

    public void setzeApp(App pApp) {
        app = pApp;
        
        if(app.istOptimalerWeg(null)){
            label_information.setText("Sie haben den optimalen Weg gefunden!");
        }
        else{
            label_information.setText("Ihr Weg ist noch nicht optimal!");
        }
    }
    
    @FXML
    void h_analysemodus(MouseEvent event) {
        button_analysemodus.setScaleX(1.1);
        button_analysemodus.setScaleY(1.1);
    }

    @FXML
    void h_hauptmenue(MouseEvent event) {
        button_hauptmenue.setScaleX(1.1);
        button_hauptmenue.setScaleY(1.1);
    }

    @FXML
    void he_analysemodus(MouseEvent event) {
        button_analysemodus.setScaleX(1.0);
        button_analysemodus.setScaleY(1.0);
    }

    @FXML
    void he_hauptmenue(MouseEvent event) {
        button_hauptmenue.setScaleX(1.0);
        button_hauptmenue.setScaleY(1.0);
    }

    @FXML
    void zum_analysemodus(ActionEvent event) throws Exception {
        app.analyseOeffnen();
    }

    @FXML
    void zum_hauptmenue(ActionEvent event) throws Exception {
        app.startOeffnen();
    }

    @FXML
    void initialize() {
    }
    
    public void neueInsel(String pBezeichnung) {
        insel_name.setText(pBezeichnung);
    }
}
