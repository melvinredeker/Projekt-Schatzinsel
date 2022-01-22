import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;

/**
 * The 'c_discovermode_endcard' class provides and associates methods called by the fxml file 'c_discovermode_endcard.fxml'
 * @author Melvin Redeker
 * @version 2022-01-02
 */
public class c_discovermode_endcard {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button button_analysismode;

    @FXML
    private Button button_main_menu;

    @FXML
    private Text label_information;

    @FXML
    private TextField textfield_islandname;

    private App app;

    /**
     * Sets the variable 'app' to provide hiding the discover window and return to the main menu
     * @param pApplication
     */
    public void setApplication(App pApplication){
        this.app = pApplication;

        if(app.istOptimalerWeg(null)){
            label_information.setText("Sie haben den optimalen Weg gefunden!");
        }
        else{
            label_information.setText("Ihr Weg ist noch nicht optimal!");
        }

    }
    
    /**
     * Calls the openAnalysis method from 'app'
     * @param event (Button "button_analysismode" clicked)
     */
    @FXML
    void analysismode(ActionEvent event) throws Exception {
        this.app.openAnalysis();
    }

    /**
     * Calls the openMainmenu method from 'app'
     * @param event (Button "button_main_menu" clicked)
     */    
    @FXML
    void main_menu(ActionEvent event) throws Exception{
        this.app.openMainmenu();
    }

    /**
     * Sets the island name to the parameter 'pName'
     * @param pName
     */
    public void newIsland(String pName) {
        this.textfield_islandname.setText(pName);
    }

    /**
     * Set buttonscale to 1.1
     * @param event (Hover on button "button_analysismode")
     */
    @FXML
    void h_analysismode(MouseEvent event) {
        this.button_analysismode.setScaleX(1.1);
        this.button_analysismode.setScaleY(1.1);
    }

    /**
     * Set buttonscale to 1.1
     * @param event (Hover on button "button_main_menu")
     */
    @FXML
    void h_main_menu(MouseEvent event) {
        this.button_main_menu.setScaleX(1.1);
        this.button_main_menu.setScaleY(1.1);
    }

    /**
     * Reset buttonscale to 1.0
     * @param event (Hover exit on button "button_analysismode")
     */
    @FXML
    void he_analysismode(MouseEvent event) {
        this.button_analysismode.setScaleX(1.0);
        this.button_analysismode.setScaleY(1.0);
    }

    /**
     * Reset buttonscale to 1.0
     * @param event (Hover exit on button "button_main_menu")
     */
    @FXML
    void he_main_menu(MouseEvent event) {
        this.button_main_menu.setScaleX(1.0);
        this.button_main_menu.setScaleY(1.0);
    }

    /**
     * Called when first shown
     */
    @FXML
    void initialize() {
    }
}