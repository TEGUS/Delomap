package controller;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import javabeans.TypeProcedure;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
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

public class TypeProcedureController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField codeTextField;

    @FXML
    private TextField nomTextField;

    @FXML
    private TableView<TypeProcedure> tableView;

    @FXML
    private TableColumn<TypeProcedure, ?> codeTableColumn;

    @FXML
    private TableColumn<TypeProcedure, ?> nomTableColumn;

    @FXML
    private Button enregistrerButton;

    @FXML
    private Button modifierButton;

    @FXML
    private Button quitterButton;

    @FXML
    private Button supprimerButton;
    
    private ModelDataTable<TypeProcedure> modelTable;
    private List<String> params;
    private List<TableColumn<TypeProcedure, ?>> listColumns;
    private Stage dialogStage;

    private DAO<TypeProcedure> dao;

    @FXML
    void initialize() throws SQLException {
        dao = DAOFactory.getTypeProcedureDAO();

        modelTable = new ModelDataTable<>();

        params = new ArrayList<>();
        params.add("code");
        params.add("nom");

        listColumns = new ArrayList<>();
        listColumns.add(codeTableColumn);
        listColumns.add(nomTableColumn);

        reset();
    }
    
    public void reset() throws SQLException {
        codeTextField.clear();
        nomTextField.clear();
        modifierButton.setDisable(true);
        supprimerButton.setDisable(true);
        enregistrerButton.setDisable(false);
        modelTable.setJavabeans(dao.findAll());
        modelTable.loadTableJavabean((TableView<TypeProcedure>) tableView, listColumns, params);
    }
    
    @FXML
    void enregistrerButtonOnAction(ActionEvent event) throws SQLException {
        if (isInputValid()) {
            TypeProcedure tp = new TypeProcedure(
                    codeTextField.getText(),
                    nomTextField.getText()
            );
            dao.create(tp);
            reset();
            ModelDialogNotification.show("Enrégistrement effectué!");
        }
    }

    @FXML
    void modifierButtonOnAction(ActionEvent event) throws SQLException {
        if (isInputValid()) {
            TypeProcedure typePrestation = tableView.getSelectionModel().getSelectedItem();
            typePrestation.setCode(codeTextField.getText());
            typePrestation.setNom(nomTextField.getText());
            dao.update(typePrestation);
            reset();
            ModelDialogNotification.show("Modification effectuée!");
        }
    }

    @FXML
    void quitterButtonOnAction(ActionEvent event) throws IOException {
        new ModelOpen().loadPage(event, "Menu.fxml", false, "Menu principal");
    }

    @FXML
    void supprimerButtonOnAction(ActionEvent event) throws SQLException {
        TypeProcedure tp = tableView.getSelectionModel().getSelectedItem();
        
        Alert alert = new Alert(AlertType.WARNING, "voulez vous vraiment supprimer ?", ButtonType.YES, ButtonType.NO);
        alert.setTitle("Supprimer le type " + tp.getNom());
        alert.setHeaderText("Attention");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.YES) {
            dao.delete(tp);
            reset();
            ModelDialogNotification.show("Suppression effectuée!");
        }
    }

    @FXML
    void tableViewOnMouseClicked(MouseEvent event) {
        enregistrerButton.setDisable(true);
        modifierButton.setDisable(false);
        supprimerButton.setDisable(false);

        TypeProcedure a = tableView.getSelectionModel().getSelectedItem();
        codeTextField.setText(a.getCode());
        nomTextField.setText(a.getNom());
    }
    
    private boolean isInputValid() {
        String errorMessage = "";

        if (codeTextField.getText() == null || codeTextField.getText().length() == 0) {
            errorMessage += "Code invalide!\n";
        }

        if (nomTextField.getText() == null || nomTextField.getText().length() == 0) {
            errorMessage += "Nom invalide!\n";
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
