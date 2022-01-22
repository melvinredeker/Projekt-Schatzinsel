import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

/**
 * The 'c_help_window' class provides and associates methods called by the fxml file 'help_window.fxml'
 * @author Melvin Redeker
 * @version 2022-01-02
 */
public class c_help_window {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ImageView imageview_exit;
    
    private App app;

    /**
     * Sets the variable 'app' to provide hiding window
     * @param pApplication
     */
    public void setApplication(App pApplication) {
        app = pApplication;
    }
    
    /**
     *  Hide window via Application method
     * @param event (ImageView "imageview_exit" clicked)
     */
    @FXML
    void exit(MouseEvent event) {
        app.closeHelp();
    }

    /**
     * Set imageviewscale to 1.1
     * @param event (Hover on imageview "imageview_exit")
     */
    @FXML
    void h_exit(MouseEvent event) {
        imageview_exit.setScaleX(1.1);
        imageview_exit.setScaleY(1.1);
    }

    /**
     * Reset imageviewscale to 1.0
     * @param event (Hover exit on imageview "imageview_exit")
     */
    @FXML
    void he_exit(MouseEvent event) {
        imageview_exit.setScaleX(1.0);
        imageview_exit.setScaleY(1.0);
    }

    /**
     * Called when first shown
     */
    @FXML
    void initialize() {        
    }
}
