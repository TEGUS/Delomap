package controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import model.ModelOpen;

public class MenuController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button journalProgButton;


    @FXML
    void initialize() {
    }
    
    @FXML
    void journalProgButtonOnAction(ActionEvent event) throws IOException {
        new ModelOpen().loadPage(event, "journal_programmation.fxml", true, "Journal de programmation");
    }
    
    @FXML
    void exitButtonOnAction(ActionEvent event) {
        System.exit(0);
    }
}
