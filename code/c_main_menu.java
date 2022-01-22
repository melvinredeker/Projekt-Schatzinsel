import java.net.URI;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import java.awt.Desktop;
import java.io.IOException;
import java.net.URISyntaxException;

/**
 * The 'c_main_menu' class provides and associates methods called by the fxml file 'main_menu.fxml'
 * @author Melvin Redeker
 * @version 2022-01-02
 */
public class c_main_menu {

    @FXML
    private ResourceBundle resources;

    @FXML
    private ImageView imgageview_logo;

    @FXML
    private ImageView imageview_analysismode;

    @FXML
    private ImageView imageview_discovermode;

    @FXML
    private ImageView imageview_help;

    private App app;

    /**
     * Sets the variable 'app' to provide hiding the current window and opening others
     * @param pApplication
     */
    public void setApplication(App pApplication) {
        app = pApplication;
    }
    
    /**
     * Opens the provided string from the parameter as URL in the default browser
     * @param pUrl 
     */
    public void openURL(String pURL) throws IOException {
        Desktop desktop = java.awt.Desktop.getDesktop();
        try {
            URI oURL = new URI(pURL);
            desktop.browse(oURL);
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }

    /**
     * Opens the analysismode window and hides the main menu
     * @param event (ImageView "imageview_analysismode" clicked)
     */
    @FXML
    void analyse(MouseEvent event) throws Exception {
        this.app.openAnalysis();
    }

    /**
     * Opens the discovermode window and hides the main menu
     * @param event (ImageView "imageview_discovermode" clicked)
     */
    @FXML
    void discover(MouseEvent event) throws Exception {
        this.app.openDiscoverIsland();
    }

    /**
     * Opens the discovermode window 
     * @param event (ImageView "imageview_help" clicked)
     */
    @FXML
    void help(MouseEvent event) throws Exception {
        this.app.openHelp();
    }

    /**
     * Opens the Instagram profiles of creditors
     * @param event (Credit label clicked)
     */
    @FXML
    void informations(MouseEvent event) throws IOException {
        this.openURL("https://instagram.com/tris.pold");
        this.openURL("https://instagram.com/melvinredeker");
    }

    /**
     * Opens the default mail program to contact the creditors 
     * @param event (ImageView "imgageview_logo" clicked)
     */
    @FXML
    void treasureisland(MouseEvent event) throws IOException {
        this.openURL("mailto:melvinredeker@hotmail.com,tristan.lippold@outlook.de?subject=Dings%2C%20Treasure%20Island%3A%20Mega!&body=Servus%2C%20gr%C3%BCzi%2C%20moin!%0D%0A%0D%0AWyldes%20Brett%20habt%20ihr%20da%20gedropt.%20Aber%20%C3%A4h%2C%20was%20ich%20noch%20sagen%20wollte%3A%0D%0A%0D%0A%0D%0A%0D%0AHauta%20rein.%0D%0AIrgendwer%2C%20der%20eure%20Vorlage%20nutzt.");
    }

    /**
     * Set imageviewscale to 1.1
     * @param event (Hover on imageview "imageview_analysismode")
     */
    @FXML
    void h_analyse(MouseEvent event) {
        this.imageview_analysismode.setScaleX(1.1);
        this.imageview_analysismode.setScaleY(1.1);
    }

    /**
     * Set imageviewscale to 1.1
     * @param event (Hover on imageview "imageview_discovermode")
     */
    @FXML
    void h_discover(MouseEvent event) {
        this.imageview_discovermode.setScaleX(1.1);
        this.imageview_discovermode.setScaleY(1.1);
    }

    /**
     * Set imageviewscale to 1.1
     * @param event (Hover on imageview "imageview_help")
     */
    @FXML
    void h_help(MouseEvent event) {
        this.imageview_help.setScaleX(1.1);
        this.imageview_help.setScaleY(1.1);
    }

    /**
     * Set imageviewscale to 1.1
     * @param event (Hover on imageview "imgageview_logo")
     */
    @FXML
    void h_logo(MouseEvent event) {
        this.imgageview_logo.setScaleX(1.1);
        this.imgageview_logo.setScaleY(1.1);
    }

    /**
     * Reset imageviewscale to 1.0
     * @param event (Hover exit on imageview "imageview_analysismode")
     */
    @FXML
    void he_analyse(MouseEvent event) {
        this.imageview_analysismode.setScaleX(1.0);
        this.imageview_analysismode.setScaleY(1.0);
    }

    /**
     * Reset imageviewscale to 1.0
     * @param event (Hover exit on imageview "imageview_discovermode")
     */
    @FXML
    void he_discover(MouseEvent event) {
        this.imageview_discovermode.setScaleX(1.0);
        this.imageview_discovermode.setScaleY(1.0);
    }

    /**
     * Reset imageviewscale to 1.0
     * @param event (Hover exit on imageview "imageview_help")
     */
    @FXML
    void he_help(MouseEvent event) {
        this.imageview_help.setScaleX(1.0);
        this.imageview_help.setScaleY(1.0);
    }

    /**
     * Reset imageviewscale to 1.0
     * @param event (Hover exit on imageview "imgageview_logo")
     */
    @FXML
    void he_logo(MouseEvent event) {
        this.imgageview_logo.setScaleX(1.0);
        this.imgageview_logo.setScaleY(1.0);
    }

    /**
     * Called when first shown
     */
    @FXML
    void initialize() {
    }
}