/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

/**
 *
 * @author Arthur_2
 */
public class MainApp extends Application {
    
    private Stage primaryStage;
    private BorderPane rootLayout;
    
    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("Liste des marchés");
        
        initRootLayout();
        
        showMarketOverview();
    }

    private void initRootLayout() {
        try {
            // on charge le fichier fxml correspondant au root_layout
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("root_layout.fxml"));
            rootLayout = (BorderPane) loader.load();
            
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
            loader.setLocation(MainApp.class.getResource("journal_programmation.fxml"));
            AnchorPane marketOverview = (AnchorPane) loader.load();
            
            // ajouter la vue des marchés au centre du root layout
            rootLayout.setCenter(marketOverview);
        } catch (IOException e) {
            e.printStackTrace();
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
