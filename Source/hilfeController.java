import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

public class hilfeController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ImageView imageview_beenden;
    
    private App app;

    public void setzeApp(App pApp) {
        app = pApp;
    }
    
    @FXML
    void beenden(MouseEvent event) {
        app.hilfeSchließen();
    }

    @FXML
    void h_beenden(MouseEvent event) {
        imageview_beenden.setScaleX(1.1);
        imageview_beenden.setScaleY(1.1);
    }

    @FXML
    void he_beenden(MouseEvent event) {
        imageview_beenden.setScaleX(1.0);
        imageview_beenden.setScaleY(1.0);
    }

    @FXML
    void initialize() {
    }
}
