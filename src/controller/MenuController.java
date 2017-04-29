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
    private Button typeProcedureButton;

    @FXML
    private Button typePrestationButton;

    @FXML
    private Button documentsButton;

    @FXML
    private Button administrationButton;

    @FXML
    private Button proceduresButton;

    @FXML
    private Button nouveauMarcheButton;

    @FXML
    private Button ajoutAdminButton;

    @FXML
    private Button retourButton;

    @FXML
    private Button exitButton;


    @FXML
    void initialize() {
    }
    
    @FXML
    void journalProgButtonOnAction(ActionEvent event) throws IOException {
        new ModelOpen().loadPage(event, "journal_programmation.fxml", true, "Journal de programmation");
    }
    
    @FXML
    void proceduresButtonOnAction(ActionEvent event) throws IOException {
        new ModelOpen().loadPage(event, "Procedure.fxml", true, "Procédures");
    }
    
    @FXML
    void administrationButtonOnAction(ActionEvent event) throws IOException {
        
    }
    
    @FXML
    void typePrestationButtonOnAction(ActionEvent event) throws IOException {
        new ModelOpen().loadPage(event, "TypePrestation.fxml", true, "Types Prestation");
    }

    @FXML
    void ajoutAdminButtonAction(ActionEvent event) throws IOException {
        new ModelOpen().loadPage(event, "Administration.fxml", true, "Administration");
    }

    @FXML
    void documentsButtonOnAction(ActionEvent event) throws IOException {
        new ModelOpen().loadPage(event, "ListeDocuments.fxml", true, "Documents");
    }

    @FXML
    void nouveauMarcheButtonOnAction(ActionEvent event) {

    }

    @FXML
    void retourButtonOnAction(ActionEvent event) throws IOException {
        new ModelOpen().loadPage(event, "Accueil.fxml", true, "Accueil");
    }

    @FXML
    void typeProcedureButtonOnAction(ActionEvent event) throws IOException {
        new ModelOpen().loadPage(event, "TypeProcedure.fxml", true, "Type Procédure");
    }
    
    @FXML
    void exitButtonOnAction(ActionEvent event) {
        System.exit(0);
    }
}
