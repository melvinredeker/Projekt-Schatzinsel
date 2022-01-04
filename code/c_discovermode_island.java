import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

/**
 * The 'c_discovermode_island' class provides and associates methods called by the fxml file 'discovermode_island.fxml'
 * @author Melvin Redeker
 * @version 2022-01-02
 */
public class c_discovermode_island {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ImageView wallpaper;

    @FXML
    private Button button_a;

    @FXML
    private Button button_b;

    @FXML
    private TextField textxfield_islandname;

    @FXML
    private Button button_mainmenu;

    private App app;

    private Stage closeStage;
    private c_closure_window closeController;
    
    private double xOffset;
    private double yOffset;

    /**
     * Sets the variable 'app' to provide hiding window
     * @param pApplication
     */
    public void setApplication(App pApplication) {
        app = pApplication;
    }
    
    /**
     * Calls the showClose method
     * @param event (Button "button_mainmenu" clicked)
     */
    @FXML
    void mainmenu(ActionEvent event) {
        this.showClose();
    }
    
    /**
     * Opens the next island if available, else opens the endcard window
     * @param event (Button "button_a" clicked)
     */
    @FXML
    void ship_a(ActionEvent event) {
        Management management = this.app.getManagement();
        management.useShip("A");
            if(management.isAtEnd()) {
                this.app.openAnalysisEndcard();
            } else {
                this.newIsland(management.getCurrentIslandName());
            }
    }

    /**
     * Opens the next island if available, else opens the endcard window
     * @param event (Button "button_b" clicked)
     */
    @FXML
    void ship_b(ActionEvent event) {
        Management management = this.app.getManagement();
        management.useShip("B");
            if(management.isAtEnd()) {
                this.app.openAnalysisEndcard();
            } else {
                this.newIsland(management.getCurrentIslandName());
            }
    }

    /**
     * Changes the wallpaper to the image file named like the parameter 'pName'. If none is found the wallpaper will be set to the default image.
     * @param pName
     */
    public void newIsland(String pName) {
        insel_name.setText(pName);
        
        File file1 = new File("resources/wallpapers/" + pName + ".jpeg");
        File file2 = new File("resources/wallpapers/" + pName + ".jpg");
        File file3 = new File("resources/wallpapers/" + pName + ".png");
        
        if (file1.exists()) {
            this.wallpapper.setImage(new Image("resources/wallpapers/" + pName + ".jpeg"));
        } else if(file2.exists()) {
            this.wallpaper.setImage(new Image("resources/wallpapers/" + pName + ".jpg"));
        } else if(file3.exists()) {
            this.wallpaper.setImage(new Image("resources/wallpapers/" + pName + ".png"));
        } else {
            this.wallpaper.setImage(new Image(("resources/wallpapers/default.png")));
        }
        
        wallpaper.setPreserveRatio(false);
        
    }

    /**
     * Shows the close window
     * @throws Exception
     */
    public void showClose() throws Exception {
        if(closeStage == null) {

            closeStage = new Stage();

            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("/view/closure_window.fxml"));
            Parent root = fxmlLoader.load();
            this.closeController = (closeController) fxmlLoader.getController();
            this.closeController.setApplication(this.app);
            this.closeController.setIslandController(this);

            Scene scene = new Scene(root);
            scene.setFill(Color.TRANSPARENT);

            root.setOnMousePressed(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent event) {
                        xOffset = event.getSceneX();
                        yOffset = event.getSceneY();
                    }
                });
            root.setOnMouseDragged(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent event) {
                        closeStage.setX(event.getScreenX() - xOffset);
                        closeStage.setY(event.getScreenY() - yOffset);
                    }
                }); 

            closeStage.setScene(scene);
            closeStage.setResizable(false);
            closeStage.initStyle(StageStyle.UNDECORATED);
            closeStage.initStyle(StageStyle.TRANSPARENT);

            closeStage.show();
        } else {
            if(!closeStage.isShowing()) {
                closeStage.centerOnScreen();
                closeStage.show();
            } else {
                closeStage.toFront();
            }
        }
    }

    /**
     * Hides the closure window
     * @throws Exception
     */
    public void hideClose() throws Exception {
        this.closeStage.hide();
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
     * Set buttonscale to 1.1
     * @param event (Hover on button "button_a")
     */
    @FXML
    void h_ship_a(MouseEvent event) {
        this.button_a.setScaleX(1.1);
        this.button_a.setScaleY(1.1);
    }

    /**
     * Set buttonscale to 1.1
     * @param event (Hover on button "button_b")
     */
    @FXML
    void h_ship_b(MouseEvent event) {
        this.button_b.setScaleX(1.1);
        this.button_b.setScaleY(1.1);
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
     * Reset buttonscale to 1.0
     * @param event (Hover exit on button "button_a")
     */
    @FXML
    void he_ship_a(MouseEvent event) {
        this.button_a.setScaleX(1.0);
        this.button_a.setScaleY(1.0);
    }

    /**
     * Reset buttonscale to 1.0
     * @param event (Hover exit on button "button_b")
     */
    @FXML
    void he_ship_b(MouseEvent event) {
        this.button_b.setScaleX(1.0);
        this.button_b.setScaleY(1.0);
    }

    /**
     * Called when first shown
     */
    @FXML
    void initialize() {
    }
}
