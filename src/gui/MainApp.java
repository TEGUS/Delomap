/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import controller.EnvoiDoc;
import controller.ModifierMarche;
import controller.RootLayout;
import java.io.IOException;
import javabeans.Document;
import javabeans.Marche;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 *
 * @author Arthur_2
 */
public class MainApp extends Application {

    private static Stage primaryStage;
    public static BorderPane rootLayout;


    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("Acceuil");

        initRootLayout();

        showMarketOverview();
    }

    private void initRootLayout() {
        try {
            // on charge le fichier fxml correspondant au root_layout
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("root_layout.fxml"));
            rootLayout = (BorderPane) loader.load();

            // Give the controller access to the main app.
            RootLayout controller = loader.getController();
            controller.setMainApp(this);
            controller.setInvisibleMenuBar();

            // on affiche la scène contenant ce root layout
            Scene scene = new Scene(rootLayout);
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void showMarketOverview() {
        try {
            // charger le journal de programmation
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("Accueil.fxml"));
            AnchorPane marketOverview = (AnchorPane) loader.load();

            // ajouter la vue des marchés au centre du root layout
            rootLayout.setCenter(marketOverview);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static boolean showMarcheEditDialog(Marche marche, String titre, String invisible) {
        try {
            // Load the fxml file and create a new stage for the popup dialog.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("modifier_marche.fxml"));
            AnchorPane page = (AnchorPane) loader.load();

            // Create the dialog Stage.
            Stage dialogStage = new Stage();
            dialogStage.setTitle(titre);
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(primaryStage);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);

            // Set marché into the controller.
            ModifierMarche controller = loader.getController();
            controller.setDialogStage(dialogStage);
            //controller.setParentStage(this.primaryStage);
            controller.setMarche(marche);
            controller.setInvisible(invisible);

            // Show the dialog and wait until the user closes it
            dialogStage.showAndWait();

            return controller.isOkClicked();
            
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    
    public static boolean showEnvoiDialog(Document document, String titre) {
        try {
            // Load the fxml file and create a new stage for the popup dialog.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("envoi_doc.fxml"));
            AnchorPane page = (AnchorPane) loader.load();

            // Create the dialog Stage.
            Stage dialogStage = new Stage();
            dialogStage.setTitle(titre);
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(primaryStage);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);

            // Set marché into the controller.
            EnvoiDoc controller = loader.getController();
            controller.setDialogStage(dialogStage);
            //controller.setParentStage(this.primaryStage);
            controller.setDocument(document);

            // Show the dialog and wait until the user closes it
            dialogStage.showAndWait();

            return controller.isOkClicked();
            
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    
    public Stage getPrimaryStage() {
        return primaryStage;
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
