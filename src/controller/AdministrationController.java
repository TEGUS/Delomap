package controller;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import javabeans.Administration;
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
import pattern.factory.DAOFactory;

public class AdministrationController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField codeTextField;

    @FXML
    private TextField nomTextField;

    @FXML
    private TextField emailTextField;

    @FXML
    private ComboBox<String> typeComboBox;

    @FXML
    private Button enregistrerButton;

    @FXML
    private Button modifierButton;

    @FXML
    private Button quitterButton;

    @FXML
    private Button supprimerButton;

    @FXML
    private TableView<Administration> adminTableView;

    @FXML
    private TableColumn<Administration, String> codeTableColumn;

    @FXML
    private TableColumn<Administration, String> typeTableColumn;

    @FXML
    private TableColumn<Administration, String> nomTableView;

    @FXML
    private TableColumn<Administration, String> emailTableView;

    private ModelDataTable<Administration> modelTable;
    private List<String> params;
    private List<TableColumn<Administration, ?>> listColumns;
    private Stage dialogStage;

    private DAO<Administration> administrationDAO;

    @FXML
    void initialize() throws SQLException {
        administrationDAO = DAOFactory.getAdministrationDAO();

        modelTable = new ModelDataTable<>();

        params = new ArrayList<>();
        params.add("code");
        params.add("nom");
        params.add("type");
        params.add("adresseMail");

        listColumns = new ArrayList<>();
        listColumns.add(codeTableColumn);
        listColumns.add(typeTableColumn);
        listColumns.add(nomTableView);
        listColumns.add(emailTableView);

        reset();

        //SetTypes
        typeComboBox.getItems().clear();
        ObservableList<String> datas = FXCollections.observableArrayList();
        datas.add("Ministère");
        typeComboBox.setItems(datas);
        typeComboBox.getSelectionModel().select(0);
    }

    public void reset() throws SQLException {
        codeTextField.clear();
        nomTextField.clear();
        emailTextField.clear();
        modifierButton.setDisable(true);
        supprimerButton.setDisable(true);
        enregistrerButton.setDisable(false);
        modelTable.setJavabeans(administrationDAO.findAll());
        modelTable.loadTableJavabean(adminTableView, listColumns, params);
    }

    @FXML
    void enregistrerButtonOnAction(ActionEvent event) throws SQLException {
        if (isInputValid()) {
            Administration administration = new Administration(
                    codeTextField.getText(),
                    nomTextField.getText(),
                    (String) typeComboBox.getSelectionModel().getSelectedItem(),
                    emailTextField.getText()
            );
            administrationDAO.create(administration);
            reset();
            ModelDialogNotification.show("Enrégistrement effectué!");
        }
    }

    @FXML
    void modifierButtonOnAction(ActionEvent event) throws SQLException {
        if (isInputValid()) {
            Administration administration = new Administration(
                    codeTextField.getText(),
                    nomTextField.getText(),
                    (String) typeComboBox.getSelectionModel().getSelectedItem(),
                    emailTextField.getText()
            );
            administrationDAO.update(administration);
            reset();
            ModelDialogNotification.show("Modification effectuée!");
        }
    }

    @FXML
    void supprimerButtonOnAction(ActionEvent event) throws SQLException {
        Administration a = adminTableView.getSelectionModel().getSelectedItem();
        
        Alert alert = new Alert(AlertType.WARNING, "voulez vous vraiment supprimer ?", ButtonType.YES, ButtonType.NO);
        alert.setTitle("Supprimer l'administration " + a.getNom());
        alert.setHeaderText("Attention");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.YES) {
            administrationDAO.delete(a);
            reset();
            ModelDialogNotification.show("Suppression effectuée!");
        }
    }

    @FXML
    void adminTableViewOnMouseClicked(MouseEvent event) {
        enregistrerButton.setDisable(true);
        modifierButton.setDisable(false);
        supprimerButton.setDisable(false);

        Administration a = adminTableView.getSelectionModel().getSelectedItem();
        codeTextField.setText(a.getCode());
        nomTextField.setText(a.getNom());
        typeComboBox.getSelectionModel().select(a.getType());
        emailTextField.setText(a.getAdresseMail());
    }

    @FXML
    void typeComboBoxOnAction(ActionEvent event) {

    }

    @FXML
    void quitterButtonOnAction(ActionEvent event) throws IOException {
        new ModelOpen().loadPage(event, "Menu.fxml", false, "Menu principal");
    }

    private boolean isInputValid() {
        String errorMessage = "";

        if (codeTextField.getText() == null || codeTextField.getText().length() == 0) {
            errorMessage += "Code invalide!\n";
        }

        if (nomTextField.getText() == null || nomTextField.getText().length() == 0) {
            errorMessage += "Nom invalide!\n";
        }

        if (emailTextField.getText() == null || emailTextField.getText().length() == 0) {
            errorMessage += "Email invalide!\n";
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
