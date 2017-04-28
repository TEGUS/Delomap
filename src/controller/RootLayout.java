package controller;

import javafx.scene.control.MenuItem;

import gui.MainApp;
import javabeans.Marche;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.ModelOpen;

public class RootLayout implements EventHandler {


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

    Stage dialogStage;
    
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
        boolean okClicked = mainApp.showMarcheEditDialog(tempMarche, "Enregistrer un nouveau marché", "editer");
        if (okClicked) {
            new ModelOpen().loadPage(event, "journal_programmation.fxml", true, "Journal de programmation");
            
            dialogStage = new Stage();
            dialogStage.initModality(Modality.WINDOW_MODAL);

            Button ok = new Button("Ok");
            VBox vbox = new VBox(new Text("Nouveau marché créé avec succès"), ok);
            vbox.setAlignment(Pos.CENTER);
            vbox.setPadding(new Insets(15));

            dialogStage.setScene(new Scene(vbox));
            dialogStage.show();
            
            ok.setOnAction(this);
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

    @Override
    public void handle(Event event) {
        dialogStage.close();
    }
}
