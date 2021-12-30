import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.image.Image;
//import java.awt.Desktop;
//import java.io.IOException;
//import java.net.URI;
//import java.net.URISyntaxException;
//import javafx.event.Event;
import javafx.scene.input.MouseEvent;
import javafx.event.EventHandler;
import javafx.scene.paint.Color;
import javafx.stage.StageStyle;
//import javafx.scene.layout.Pane;
//import javafx.scene.text.TextAlignment;
//import javafx.scene.control.Label;
//import javafx.scene.text.Font;
//import javafx.scene.image.ImageView;
//import javafx.scene.control.OverrunStyle;
//import javafx.geometry.Pos;
//import javafx.scene.image.WritableImage;
//import javafx.stage.FileChooser;
//import java.io.File;
//import java.awt.image.BufferedImage;
//import javafx.embed.swing.SwingFXUtils;
//import javax.imageio.ImageIO;
//import java.util.logging.Logger;
//import java.util.logging.Level;
//import javafx.scene.SnapshotParameters;
//import javafx.stage.FileChooser;
//import javax.swing.JFileChooser;
//import java.io.IOException;
//import javax.swing.JFrame;
//import javax.swing.filechooser.FileNameExtensionFilter;

public class App extends Application {
    private Schatzinselanwendung anwendung;

    Stage stage;
    mainController mainController;
    inselController inselController;
    zielController zielController;
    analyseController analyseController;
    hilfeController hilfeController;
    Stage hilfeStage;

    private double xOffset;
    private double yOffset;

    public void UndecoratedMovableStage() {
        this.xOffset = 0.0;
        this.yOffset = 0.0;
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        stage = primaryStage;
        if(hilfeStage !=null){
            stage.setOnCloseRequest(
                event -> hilfeStage.close()
            );
        }

        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("/fxml/main.fxml"));
        
        Parent root = fxmlLoader.load();
        
        this.mainController = (mainController) fxmlLoader.getController();
        this.mainController.setzeApp(this);

        stage.setScene(new Scene(root));
        stage.setResizable(false);
        stage.setTitle("Treasure Island");
        stage.getIcons().add(new Image("resources/gui_images/Title_Logo_3.png"));
        stage.show();

        this.anwendung = new Schatzinselanwendung();
        //this.anwendung.erstelleKonstellationDefault(); //Ini-Datei!
        this.anwendung.ladeIniDatei();
    }

    public void startOeffnen() throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("/fxml/main.fxml"));
        Parent root = fxmlLoader.load();
        this.mainController = (mainController) fxmlLoader.getController();
        this.mainController.setzeApp(this);

        stage.setScene(new Scene(root));
        stage.show();

        this.anwendung.leereSchiffsFolge();
    }

    public void entdeckenOeffnen() throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("/fxml/Entdeckermodus_Insel.fxml"));
        Parent root = fxmlLoader.load();
        this.inselController = (inselController) fxmlLoader.getController();
        this.inselController.setzeApp(this);

        stage.setScene(new Scene(root));
        stage.show();

        this.inselController.neueInsel(this.anwendung.gibAktuelleInselBezeichnung());
    }

    public void zielOeffnen() throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("/fxml/Entdeckermodus_Ziel.fxml"));
        Parent root = fxmlLoader.load();
        this.zielController = (zielController) fxmlLoader.getController();
        this.zielController.setzeApp(this);

        stage.setScene(new Scene(root));
        stage.show();

        this.zielController.neueInsel(this.anwendung.gibAktuelleInselBezeichnung());
    }

    public void analyseOeffnen() throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("/fxml/Analysemodus.fxml"));
        Parent root = fxmlLoader.load();
        this.analyseController = (analyseController) fxmlLoader.getController();
        this.analyseController.setzeApp(this);

        stage.setScene(new Scene(root));
        stage.show();

        this.analyseController.setzeEingabeFeldInhalt(anwendung.gibSchiffsfolge());
        this.analyseController.bearbeiteEingabe();
    }

    public void hilfeOeffnen() throws Exception {
        if(hilfeStage == null) {

            hilfeStage = new Stage();

            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("/fxml/Hilfe.fxml"));
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

    public Schatzinselanwendung gibAnwendung() {
        return this.anwendung;
    }

    public Scene gibSzene() {
        return this.stage.getScene();
    }

    public void hilfeSchließen(){
        hilfeStage.hide();
    }
        
    public static void main(String[] args) throws Exception {
        Application.launch(args);
    }
    
    public boolean istOptimalerWeg(String pEingabe){
        return true;
    }
    
}