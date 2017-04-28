package controller;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import javabeans.TypePrestation;
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

public class TypePrestationController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField codeTextField;

    @FXML
    private TextField nomTextField;

    @FXML
    private TableView<TypePrestation> tableView;

    @FXML
    private TableColumn<TypePrestation, ?> codeTableColumn;

    @FXML
    private TableColumn<TypePrestation, ?> nomTableColumn;

    @FXML
    private Button enregistrerButton;

    @FXML
    private Button modifierButton;

    @FXML
    private Button quitterButton;

    @FXML
    private Button supprimerButton;

    private ModelDataTable<TypePrestation> modelTable;
    private List<String> params;
    private List<TableColumn<TypePrestation, ?>> listColumns;
    private Stage dialogStage;

    private DAO<TypePrestation> typePrestationDAO;

    @FXML
    void initialize() throws SQLException {
        typePrestationDAO = DAOFactory.getTypePrestationDAO();

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
        modelTable.setJavabeans(typePrestationDAO.findAll());
        modelTable.loadTableJavabean(tableView, listColumns, params);
    }

    @FXML
    void enregistrerButtonOnAction(ActionEvent event) throws SQLException {
        if (isInputValid()) {
            TypePrestation typePrestation = new TypePrestation(
                    codeTextField.getText(),
                    nomTextField.getText()
            );
            typePrestationDAO.create(typePrestation);
            reset();
            ModelDialogNotification.show("Enrégistrement effectué!");
        }
    }

    @FXML
    void modifierButtonOnAction(ActionEvent event) throws SQLException {
        if (isInputValid()) {
            TypePrestation typePrestation = new TypePrestation(
                    codeTextField.getText(),
                    nomTextField.getText()
            );
            typePrestationDAO.update(typePrestation);
            reset();
            ModelDialogNotification.show("Modification effectuée!");
        }
    }

    @FXML
    void tableViewOnMouseClicked(MouseEvent event) {
        enregistrerButton.setDisable(true);
        modifierButton.setDisable(false);
        supprimerButton.setDisable(false);

        TypePrestation a = tableView.getSelectionModel().getSelectedItem();
        codeTextField.setText(a.getCode());
        nomTextField.setText(a.getNom());
    }

    @FXML
    void supprimerButtonOnAction(ActionEvent event) throws SQLException {
        TypePrestation tp = tableView.getSelectionModel().getSelectedItem();
        
        Alert alert = new Alert(AlertType.WARNING, "voulez vous vraiment supprimer ?", ButtonType.YES, ButtonType.NO);
        alert.setTitle("Supprimer le type " + tp.getNom());
        alert.setHeaderText("Attention");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.YES) {
            typePrestationDAO.delete(tp);
            reset();
            ModelDialogNotification.show("Suppression effectuée!");
        }
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
