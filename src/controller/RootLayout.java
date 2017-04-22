package controller;

import javafx.fxml.FXML;
import javafx.scene.control.MenuItem;
import javafx.scene.input.MouseEvent;

import gui.MainApp;
import javabeans.Marche;
import javafx.event.ActionEvent;

public class RootLayout {

    // Reference to the main application.
    private MainApp mainApp;
    
    @FXML
    private MenuItem NouveauMarcheMenuItem;

    /**
     * Is called by the main application to give a reference back to itself.
     * 
     * @param mainApp
     */
    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;

        // Add observable list data to the table
        //personTable.setItems(mainApp.getPersonData());
    }

    @FXML
    protected void nouveauMarcheOnAction(ActionEvent event) {
        Marche tempMarche = null; //new Marche();
        boolean okClicked = mainApp.showMarcheEditDialog(tempMarche);
        if (okClicked) {
            //MainApp.getPersonData().add(tempPerson);
        }
    }
}
