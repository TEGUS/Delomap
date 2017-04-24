package controller;

import javafx.scene.control.MenuItem;

import gui.MainApp;
import javabeans.Marche;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import model.ModelOpen;

public class RootLayout {


    @FXML
    private MenuBar menuBar;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;
    
    @FXML
    private Button exitButton;

    @FXML
    private Menu menuMenu;
    
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
    protected void nouveauMarcheOnAction(ActionEvent event) throws IOException, SQLException {
        Marche tempMarche = null; //new Marche();
        boolean okClicked = mainApp.showMarcheEditDialog(tempMarche);
        if (okClicked) {
            new ModelOpen().loadPage(event, "journal_programmation.fxml", true, "Journal de programmation");
        }
    }

    @FXML
    void initialize() {
        
    }
    
    @FXML
    void menuMenuOnAction(ActionEvent event) throws IOException {
        
    }
    
    @FXML
    void mainMenuOnAction(ActionEvent event) throws IOException {
        new ModelOpen().loadPage(event, "Menu.fxml", false, "Menu principal");
    }
    
    public void setInvisibleMenuBar() {
        menuBar.setVisible(false);
    }
    
    public void setVisibleMenuBar() {
        menuBar.setVisible(true);
    }
}
