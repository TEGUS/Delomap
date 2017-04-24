/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import controller.RootLayout;
import gui.MainApp;
import java.io.IOException;
import javafx.event.Event;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.MenuBar;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

/**
 *
 * @author Aurelien KOUAM
 */
public class ModelOpen {

    public void loadPage(Event event, String fxml, boolean visible, String title) throws IOException {
        MenuBar m = (MenuBar) MainApp.rootLayout.getScene().lookup("#menuBar");
        if (visible) {
            m.setVisible(true);
        } else {
            m.setVisible(false);
        }
        Stage s = (Stage) MainApp.rootLayout.getScene().getWindow();
        s.setTitle(title);
        
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(MainApp.class.getResource(fxml));
        AnchorPane marketOverview = (AnchorPane) loader.load();
        MainApp.rootLayout.setCenter(marketOverview);
    }
}
