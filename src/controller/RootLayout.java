package controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import model.ModelOpen;

public class RootLayout {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;
    
    @FXML
    private Button exitButton;

    @FXML
    private Menu menuMenu;

    @FXML
    void initialize() {
    }
    
    @FXML
    void menuMenuOnAction(ActionEvent event) throws IOException {
        
    }
    
    @FXML
    void mainMenuOnAction(ActionEvent event) throws IOException {
        new ModelOpen().loadPage(event, "Menu.fxml");
    }
}
