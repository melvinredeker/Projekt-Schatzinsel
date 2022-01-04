import model.Automat.*;

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
    mainController mainController;
    inselController inselController;
    zielController zielController;
    analyseController analyseController;
    hilfeController hilfeController;
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
        fxmlLoader.setLocation(getClass().getResource("/view/main.fxml"));
        
        Parent root = fxmlLoader.load();
        
        this.mainController = (mainController) fxmlLoader.getController();
        this.mainController.setzeApp(this);

        stage.setScene(new Scene(root));
        stage.setResizable(false);
        stage.setTitle("Treasure Island");
        stage.getIcons().add(new Image("resources/gui_images/Title_Logo_3.png"));
        stage.show();

        this.anwendung = new Schatzinselanwendung();
        this.anwendung.ladeIniDatei();
    }

    public void openMainmenu() throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("/view/main.fxml"));
        Parent root = fxmlLoader.load();
        this.mainController = (mainController) fxmlLoader.getController();
        this.mainController.setzeApp(this);

        stage.setScene(new Scene(root));
        stage.show();

        this.anwendung.leereSchiffsFolge();
    }

    public void openDiscoverIsland() throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("/view/Entdeckermodus_Insel.fxml"));
        Parent root = fxmlLoader.load();
        this.inselController = (inselController) fxmlLoader.getController();
        this.inselController.setzeApp(this);

        stage.setScene(new Scene(root));
        stage.show();

        this.inselController.neueInsel(this.anwendung.gibAktuelleInselBezeichnung());
    }

    public void openDiscoverEndcard() throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("/view/Entdeckermodus_Ziel.fxml"));
        Parent root = fxmlLoader.load();
        this.zielController = (zielController) fxmlLoader.getController();
        this.zielController.setzeApp(this);

        stage.setScene(new Scene(root));
        stage.show();

        this.zielController.neueInsel(this.anwendung.gibAktuelleInselBezeichnung());
    }

    public void openAnalysis() throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("/view/Analysemodus.fxml"));
        Parent root = fxmlLoader.load();
        this.analyseController = (analyseController) fxmlLoader.getController();
        this.analyseController.setzeApp(this);

        stage.setScene(new Scene(root));
        stage.show();

        this.analyseController.setzeEingabeFeldInhalt(anwendung.gibSchiffsfolge());
        this.analyseController.bearbeiteEingabe();
    }

    public void openHelp() throws Exception {
        if(hilfeStage == null) {

            hilfeStage = new Stage();

            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("/view/Hilfe.fxml"));
            Parent root = fxmlLoader.load();
            this.hilfeController = (hilfeController) fxmlLoader.getController();
            this.hilfeController.setzeApp(this);

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

    public Schatzinselanwendung getManagement() {
        return this.anwendung;
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