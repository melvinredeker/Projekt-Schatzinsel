import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.event.EventHandler;
import javafx.scene.paint.Color;
import javafx.stage.StageStyle;

public class App extends Application {
    
    private Management management;

    Stage stage;
    c_main_menu mainController;
    c_discovermode_island inselController;
    c_discovermode_endcard zielController;
    c_analysismode analyseController;
    c_help_window hilfeController;
    Stage hilfeStage;

    private double xOffset;
    private double yOffset;

    @Override
    public void start(Stage primaryStage) throws Exception {
        stage = primaryStage;
        if(hilfeStage !=null){
            stage.setOnCloseRequest(
                event -> hilfeStage.close()
            );
        }

        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("/view/main_menu.fxml"));
        
        Parent root = fxmlLoader.load();
        
        this.mainController = (c_main_menu) fxmlLoader.getController();
        this.mainController.setApplication(this);

        stage.setScene(new Scene(root));
        stage.setResizable(false);
        stage.setTitle("Treasure Island");
        stage.getIcons().add(new Image("resources/gui_images/Title_Logo_3.png"));
        stage.show();

        this.management = new Management();
        this.management.ladeIniDatei();
    }

    public void openMainmenu() throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("/view/main_menu.fxml"));
        Parent root = fxmlLoader.load();
        this.mainController = (c_main_menu) fxmlLoader.getController();
        this.mainController.setApplication(this);

        stage.setScene(new Scene(root));
        stage.show();

        this.management.leereSchiffsFolge();
    }

    public void openDiscoverIsland() throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("/view/discovermode_island.fxml"));
        Parent root = fxmlLoader.load();
        this.inselController = (c_discovermode_island) fxmlLoader.getController();
        this.inselController.setApplication(this);

        stage.setScene(new Scene(root));
        stage.show();

        this.inselController.newIsland(this.management.getCurrentIslandName());
    }

    public void openDiscoverEndcard() throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("/view/discovermode_endcard.fxml"));
        Parent root = fxmlLoader.load();
        this.zielController = (c_discovermode_endcard) fxmlLoader.getController();
        this.zielController.setApplication(this);

        stage.setScene(new Scene(root));
        stage.show();

        this.zielController.newIsland(this.management.getCurrentIslandName());
    }

    public void openAnalysis() throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("/view/analysismode.fxml"));
        Parent root = fxmlLoader.load();
        this.analyseController = (c_analysismode) fxmlLoader.getController();
        this.analyseController.setzeApp(this);

        stage.setScene(new Scene(root));
        stage.show();

        this.analyseController.setzeEingabeFeldInhalt(management.gibSchiffsfolge());
        this.analyseController.bearbeiteEingabe();
    }

    public void openHelp() throws Exception {
        if(hilfeStage == null) {

            hilfeStage = new Stage();

            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("/view/help_window.fxml"));
            Parent root = fxmlLoader.load();
            this.hilfeController = (c_help_window) fxmlLoader.getController();
            this.hilfeController.setApplication(this);

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
                        hilfeStage.setX(event.getScreenX() - xOffset);
                        hilfeStage.setY(event.getScreenY() - yOffset);
                    }
                }); 

            hilfeStage.setScene(scene);
            hilfeStage.setResizable(false);
            hilfeStage.setTitle("Treasure Island - Hilfe");
            hilfeStage.getIcons().add(new Image("resources/gui_images/Title_Logo_3.png"));
            hilfeStage.initStyle(StageStyle.UNDECORATED);
            hilfeStage.initStyle(StageStyle.TRANSPARENT);

            //hilfeStage.setStyle("-fx-background-color: transparent;");
            hilfeStage.show();
        } else {
            if(!hilfeStage.isShowing()) {
                hilfeStage.centerOnScreen();
                hilfeStage.show();
            } else {
                hilfeStage.toFront();
            }
        }
    }

    public Management getManagement() {
        return this.management;
    }

    public Scene getScene() {
        return this.stage.getScene();
    }

    public void closeHelp(){
        hilfeStage.hide();
    }
        
    public static void main(String[] args) throws Exception {
        Application.launch(args);
    }
    
    public boolean istOptimalerWeg(String pEingabe){
        return true;
    }
    
}