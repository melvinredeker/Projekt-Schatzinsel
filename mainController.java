import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

public class mainController {
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ImageView logo;

    @FXML
    private ImageView button_analysemodus;

    @FXML
    private ImageView button_entdeckermodus;

    @FXML
    private ImageView button_hilfe;

    private App app;

    public void openURL(String pURL) throws IOException {
        Desktop desktop = java.awt.Desktop.getDesktop();
        try {
            URI oURL = new URI(pURL);
            desktop.browse(oURL);
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }

    public void setzeApp(App pApp) {
        app = pApp;
    }
        
    @FXML
    void analyse(MouseEvent event) throws Exception {
        app.analyseOeffnen();
    }

    @FXML
    void entdecken(MouseEvent event) throws Exception {
        app.entdeckenOeffnen();
    }

    @FXML
    void h_analyse(MouseEvent event) {
        button_analysemodus.setScaleX(1.1);
        button_analysemodus.setScaleY(1.1);
    }

    @FXML
    void h_entdecken(MouseEvent event) {
        button_entdeckermodus.setScaleX(1.1);
        button_entdeckermodus.setScaleY(1.1);
    }

    @FXML
    void h_hilfe(MouseEvent event) {
        button_hilfe.setScaleX(1.1);
        button_hilfe.setScaleY(1.1);
    }

    @FXML
    void he_analyse(MouseEvent event) {
        button_analysemodus.setScaleX(1.0);
        button_analysemodus.setScaleY(1.0);
    }

    @FXML
    void he_entdecken(MouseEvent event) {
        button_entdeckermodus.setScaleX(1.0);
        button_entdeckermodus.setScaleY(1.0);
    }

    @FXML
    void he_hilfe(MouseEvent event) {
        button_hilfe.setScaleX(1.0);
        button_hilfe.setScaleY(1.0);
    }

    @FXML
    void hilfe(MouseEvent event) throws Exception {
        app.hilfeOeffnen();
    }

    @FXML
    void h_logo(MouseEvent event) {
        logo.setScaleX(1.1);
        logo.setScaleY(1.1);
    }

    @FXML
    void he_logo(MouseEvent event) {
        logo.setScaleX(1.0);
        logo.setScaleY(1.0);
    }
    
    @FXML
    void infos(MouseEvent event) throws IOException {
        this.openURL("https://instagram.com/tris.pold");
        this.openURL("https://instagram.com/melvinredeker");
    }
    
    @FXML
    void treasureisland(MouseEvent event) throws IOException {
        this.openURL("mailto:melvinredeker@hotmail.com,tristan.lippold@outlook.de?subject=Dings%2C%20Treasure%20Island%3A%20Mega!&body=Servus%2C%20gr%C3%BCzi%2C%20moin!%0D%0A%0D%0AWyldes%20Brett%20habt%20ihr%20da%20gedropt.%20Aber%20%C3%A4h%2C%20was%20ich%20noch%20sagen%20wollte%3A%0D%0A%0D%0A%0D%0A%0D%0AHauta%20rein.%0D%0AIrgendwer%2C%20der%20eure%20Vorlage%20nutzt.");
    }

    @FXML
    void initialize() {
    }
}