package controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import model.ModelOpen;

public class ProcedureController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField codeTextField;

    @FXML
    private TextField nomTextField;

    @FXML
    private TableView<?> procedureTableView;

    @FXML
    private TableColumn<?, ?> codeTableColumn;

    @FXML
    private TableColumn<?, ?> nomTableColumn;

    @FXML
    private Button enregistrerButton;

    @FXML
    private Button modifierButton;

    @FXML
    private Button quitterButton;

    @FXML
    private Button supprimerButton;

    @FXML
    void initialize() {
        
    }
    
    @FXML
    void enregistrerButtonOnAction(ActionEvent event) {

    }

    @FXML
    void modifierButtonOnAction(ActionEvent event) {

    }


    @FXML
    void supprimerButtonOnAction(ActionEvent event) {

    }
    
    @FXML
    void quitterButtonOnAction(ActionEvent event) throws IOException {
        new ModelOpen().loadPage(event, "Menu.fxml", false, "Menu principal");
    }
}
