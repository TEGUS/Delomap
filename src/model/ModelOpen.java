/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.IOException;
import javafx.event.Event;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 *
 * @author Aurelien KOUAM
 */
public class ModelOpen {
 
    public void loadPage(Event event, String fxml) throws IOException {
        ((Node)(event.getSource())).getScene().getWindow().hide();
        // Création du loader.
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(fxml));
        // Chargement du FXML.
        AnchorPane ap = (AnchorPane) fxmlLoader.load();
        // Création de la scène.
        Stage stage = new Stage();
        //stage.initModality(Modality.APPLICATION_MODAL);
        stage.setScene(new Scene(ap));
        stage.show();
    }
}
