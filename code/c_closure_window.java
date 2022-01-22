import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;

/**
 * The 'c_closure_window' class provides and associates methods called by the fxml file 'closure_window.fxml'
 * @author Melvin Redeker
 * @version 2022-01-02
 */
public class c_closure_window {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button button_mainmenu;

    @FXML
    private Button button_cancel;
    
    private App app;
    private c_discovermode_island islandController;
    
    /**
     * Sets the variable 'app' to provide hiding the discover window and return to the main menu
     * @param pApplication
     */
    public void setApplication(App pApplication){
        this.app = pApplication;
    }
    
    /**
     * Sets the variable 'islandController' to the given parameter
     * @param pIslandController
     */
    public void setIslandController(c_discovermode_island pIslandController){
        this.islandController = pIslandController;
    }

    /**
     * Hide window via islandController method 
     * @param event (Button "button_cancel" pressed)
     */
    @FXML
    void cancel(ActionEvent event) throws Exception {
        islandController.hideClose();
    }

    /**
     * Show mainstage and hide closure- and discorver window
     * @param event (Button "button_mainmenu" pressed)
     */
    @FXML
    void mainmenu(ActionEvent event) throws Exception {
        this.app.openMainmenu();
        this.islandController.hideClose();
    }

    /**
     * Set buttonscale to 1.1
     * @param event (Hover on button "button_cancel")
     */
    @FXML
    void h_cancel(MouseEvent event) {
        this.button_cancel.setScaleX(1.1);
        this.button_cancel.setScaleY(1.1);
    }

    /**
     * Set buttonscale to 1.1
     * @param event (Hover on button "button_mainmenu")
     */
    @FXML
    void h_mainmenu(MouseEvent event) {
        this.button_mainmenu.setScaleX(1.1);
        this.button_mainmenu.setScaleY(1.1);
    }

    /**
     * Reset buttonscale to 1.0
     * @param event (Hover exit on button "button_cancel")
     */
    @FXML
    void he_cancel(MouseEvent event) {
        this.button_cancel.setScaleX(1.0);
        this.button_cancel.setScaleY(1.0);
    }

    /**
     * Reset buttonscale to 1.0
     * @param event (Hover exit on button "button_mainmenu")
     */
    @FXML
    void he_mainmenu(MouseEvent event) {
        this.button_mainmenu.setScaleX(1.0);
        this.button_mainmenu.setScaleY(1.0);
    }

    /**
     * Called when first shown
     */
    @FXML
    void initialize() {        
    }

}
