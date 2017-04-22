/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import gui.MainApp;
import java.io.IOException;
import javafx.event.Event;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;

/**
 *
 * @author Aurelien KOUAM
 */
public class ModelOpen {
 
    public void loadPage(Event event, String fxml) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(MainApp.class.getResource(fxml));
        AnchorPane marketOverview = (AnchorPane) loader.load();
        MainApp.rootLayout.setCenter(marketOverview);
    }
}
