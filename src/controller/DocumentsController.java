package controller;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import javabeans.Document;
import javabeans.Marche;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import model.ModelDataTable;
import model.ModelDialogNotification;
import model.ModelOpen;
import pattern.dao.DAO;
import pattern.dao.MarcheDAO;
import pattern.factory.DAOFactory;

public class DocumentsController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField statutTextField;

    @FXML
    private TextField nomTextField;

    @FXML
    private TextField delaiTextfield;

    @FXML
    private TableView<Document> tableView;

    @FXML
    private TableColumn<Document, ?> nomTableColumn;

    @FXML
    private TableColumn<Document, ?> marcheTableColumn;

    @FXML
    private TableColumn<Document, ?> delaiTableColumn;

    @FXML
    private TableColumn<Document, ?> statutTableColumn;

    @FXML
    private TableColumn<Document, ?> phaseTableColumn;

    @FXML
    private Button supprimerButton;

    @FXML
    private Button enregistrerButton;

    @FXML
    private Button exitButton;

    @FXML
    private ComboBox<Marche> marcheComboBox;

    @FXML
    private Button modifierButton;

    @FXML
    private ComboBox<String> phaseComboBox;
    
    private ModelDataTable<Document> modelTable;
    private List<String> params;
    private List<TableColumn<Document, ?>> listColumns;
    private Stage dialogStage;

    private DAO<Document> dao;
    private MarcheDAO marcheDAO;

    @FXML
    void initialize() throws SQLException {
        dao = DAOFactory.getDocumentDAO();
        marcheDAO = (MarcheDAO) DAOFactory.getMarcheDAO();

        modelTable = new ModelDataTable<>();

        params = new ArrayList<>();
        params.add("nom");
        params.add("Marche");
        params.add("delaiTransmission");
        params.add("statut");
        params.add("phase");

        listColumns = new ArrayList<>();
        listColumns.add(nomTableColumn);
        listColumns.add(marcheTableColumn);
        listColumns.add(delaiTableColumn);
        listColumns.add(statutTableColumn);
        listColumns.add(phaseTableColumn);

        reset();
        
        //ListMarche
        ObservableList<Marche> datas = FXCollections.observableArrayList();
        for (Marche m : marcheDAO.findAll()) {
            datas.add(m);
        }
        marcheComboBox.setItems(datas);
        marcheComboBox.getSelectionModel().select(0);
        
        //Phase
        ObservableList<String> datasPhase = FXCollections.observableArrayList();
        datasPhase.add("Exécution");
        datasPhase.add("Présentation");
        phaseComboBox.setItems(datasPhase);
        phaseComboBox.getSelectionModel().select(0);
        
    }
    
    public void reset() throws SQLException {
        statutTextField.clear();
        delaiTextfield.clear();
        nomTextField.clear();
        modifierButton.setDisable(true);
        supprimerButton.setDisable(true);
        enregistrerButton.setDisable(false);
        
        modelTable.setJavabeans(dao.findAll());
        modelTable.loadTableJavabean(tableView, listColumns, params);
    }
    
    @FXML
    void enregistrerButtonOnAction(ActionEvent event) throws SQLException {
        if (isInputValid()) {
            Document d = new Document();
            d.setDelaiTransmission(delaiTextfield.getText());
            d.setMarche(marcheComboBox.getSelectionModel().getSelectedItem());
            d.setNom(nomTextField.getText());
            d.setStatut(statutTextField.getText());
            d.setPhase(phaseComboBox.getSelectionModel().getSelectedItem());
            dao.create(d);
            reset();
            ModelDialogNotification.show("Enrégistrement effectué!");
        }
    }

    @FXML
    void exitButtonOnAction(ActionEvent event) throws IOException {
        new ModelOpen().loadPage(event, "Menu.fxml", false, "Menu principal");
    }

    @FXML
    void marcheComboBoxOnAction(ActionEvent event) {

    }

    @FXML
    void modifierButtonOnAction(ActionEvent event) throws SQLException {
        if (isInputValid()) {
            Document d = tableView.getSelectionModel().getSelectedItem();
            d.setDelaiTransmission(delaiTextfield.getText());
            d.setMarche(marcheComboBox.getSelectionModel().getSelectedItem());
            d.setNom(nomTextField.getText());
            d.setStatut(statutTextField.getText());
            d.setPhase(phaseComboBox.getSelectionModel().getSelectedItem());
            dao.update(d);
            reset();
            ModelDialogNotification.show("Modification effectuée!");
        }
    }

    @FXML
    void phaseComboBoxOnAction(ActionEvent event) {

    }

    @FXML
    void supprimerButtonOnAction(ActionEvent event) throws SQLException {
        Document d = tableView.getSelectionModel().getSelectedItem();
        
        Alert alert = new Alert(AlertType.WARNING, "voulez vous vraiment supprimer ?", ButtonType.YES, ButtonType.NO);
        alert.setTitle("Supprimer le type " + d.getNom());
        alert.setHeaderText("Attention");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.YES) {
            dao.delete(d);
            reset();
            ModelDialogNotification.show("Suppression effectuée!");
        }
    }

    @FXML
    void tableViewOnMouseClicked(MouseEvent event) {
        enregistrerButton.setDisable(true);
        modifierButton.setDisable(false);
        supprimerButton.setDisable(false);

        Document d = tableView.getSelectionModel().getSelectedItem();
        System.out.println(d);
        nomTextField.setText(d.getNom());
        statutTextField.setText(d.getStatut());
        delaiTextfield.setText(d.getDelaiTransmission());
        marcheComboBox.getSelectionModel().select(d.getMarche());
        phaseComboBox.getSelectionModel().select(d.getPhase());
    }
    
    private boolean isInputValid() {
        String errorMessage = "";

        if (statutTextField.getText() == null || statutTextField.getText().length() == 0) {
            errorMessage += "Statut invalide!\n";
        }

        if (nomTextField.getText() == null || nomTextField.getText().length() == 0) {
            errorMessage += "Nom invalide!\n";
        }
        
        if (delaiTextfield.getText() == null || delaiTextfield.getText().length() == 0) {
            errorMessage += "Delai invalide!\n";
        }

        if (errorMessage.length() == 0) {
            return true;
        } else {
            // Show the error message.
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.initOwner(dialogStage);
            alert.setTitle("Champs invalides");
            alert.setHeaderText("Vérifiez les champs invalides s'il vous plait");
            alert.setContentText(errorMessage);

            alert.showAndWait();

            return false;
        }
    }
}
