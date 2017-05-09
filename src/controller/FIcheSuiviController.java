package controller;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.time.Instant;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import javabeans.FicheSuivi;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import model.ModelDataTable;
import model.ModelDialogNotification;
import model.ModelOpen;
import pattern.dao.DAO;
import pattern.factory.DAOFactory;

public class FIcheSuiviController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField designationTextField;

    @FXML
    private TextField montantTextField;
    
    @FXML
    private TextArea motifsTextField;

    @FXML
    private TextArea observationsTextField;

    @FXML
    private TableView<FicheSuivi> tableView;

    @FXML
    private TableColumn<FicheSuivi, ?> designationColumn;

    @FXML
    private TableColumn<FicheSuivi, ?> montantColumn;

    @FXML
    private TableColumn<FicheSuivi, ?> dateReelLancementColumn;

    @FXML
    private TableColumn<FicheSuivi, ?> dateReelReception;

    @FXML
    private TableColumn<FicheSuivi, ?> motifColumn;

    @FXML
    private TableColumn<FicheSuivi, ?> observationColumn;

    @FXML
    private Button enregistrerButton;

    @FXML
    private Button modifierButton;

    @FXML
    private Button quitterButton;

    @FXML
    private Button SupressionButton;

    @FXML
    private DatePicker dateReceptionDatePicker;

    @FXML
    private DatePicker dateLancementDatePicker;

    private ModelDataTable<FicheSuivi> modelTable;
    private List<String> params;
    private List<TableColumn<FicheSuivi, ?>> listColumns;
    private Stage dialogStage;

    private DAO<FicheSuivi> ficheSuiviDAO;

    @FXML
    void initialize() throws SQLException {
        ficheSuiviDAO = DAOFactory.getFicheSuiviDAO();

        modelTable = new ModelDataTable<>();

        params = new ArrayList<>();
        params.add("designation");
        params.add("montant");
        params.add("dateLancement");
        params.add("dateReception");
        params.add("motif");
        params.add("observation");

        listColumns = new ArrayList<>();
        listColumns.add(designationColumn);
        listColumns.add(montantColumn);
        listColumns.add(dateReelLancementColumn);
        listColumns.add(dateReelReception);
        listColumns.add(motifColumn);
        listColumns.add(observationColumn);

        reset();
    }

    public void reset() throws SQLException {
        designationTextField.clear();
        montantTextField.clear();
        motifsTextField.clear();
        observationsTextField.clear();
        modifierButton.setDisable(true);
        SupressionButton.setDisable(true);
        enregistrerButton.setDisable(false);
        modelTable.setJavabeans(ficheSuiviDAO.findAll());
        modelTable.loadTableJavabean(tableView, listColumns, params);
    }

    @FXML
    void SupressionButtonOnAction(ActionEvent event) throws SQLException {
        FicheSuivi ficheSuivi = tableView.getSelectionModel().getSelectedItem();

        Alert alert = new Alert(AlertType.WARNING, "voulez vous vraiment supprimer ?", ButtonType.YES, ButtonType.NO);
        alert.setTitle("Supprimer le type " + ficheSuivi.getDesignation());
        alert.setHeaderText("Attention");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.YES) {
            ficheSuiviDAO.delete(ficheSuivi);
            reset();
            ModelDialogNotification.show("Suppression effectuée!");
        }
    }

    @FXML
    void enregistrerButtonOnAction(ActionEvent event) throws SQLException {
        if (isInputValid()) {
            FicheSuivi ficheSuivi = new FicheSuivi(
                    designationTextField.getText(),
                    Integer.parseInt(montantTextField.getText()),
                    Date.from(dateReceptionDatePicker.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant()),
                    Date.from(dateLancementDatePicker.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant()),
                    motifsTextField.getText(),
                    observationsTextField.getText()
            );
            ficheSuiviDAO.create(ficheSuivi);
            reset();
            ModelDialogNotification.show("Enrégistrement effectué!");
        }
    }

    @FXML
    void modifierButtonOnAction(ActionEvent event) throws SQLException {
        if (isInputValid()) {
            FicheSuivi fs = tableView.getSelectionModel().getSelectedItem();
            fs.setDesignation(designationTextField.getText());
            fs.setMontant(Integer.parseInt(montantTextField.getText()));
            fs.setDateReception(Date.from(dateReceptionDatePicker.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant()));
            fs.setDateLancement(Date.from(dateLancementDatePicker.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant()));
            fs.setMotif(motifsTextField.getText());
            fs.setObservation(observationsTextField.getText());
            ficheSuiviDAO.update(fs);
            reset();
            ModelDialogNotification.show("Modification effectuée!");
        }
    }

    @FXML
    void quitterButtonOnAction(ActionEvent event) throws IOException {
        new ModelOpen().loadPage(event, "Menu.fxml", false, "Menu principal");
    }

    @FXML
    void tableViewOnMouseClicked(MouseEvent event) {
        FicheSuivi fs = tableView.getSelectionModel().getSelectedItem();
        if (fs != null) {
            enregistrerButton.setDisable(true);
            modifierButton.setDisable(false);
            SupressionButton.setDisable(false);
            designationTextField.setText(fs.getDesignation());
            montantTextField.setText(fs.getMontant() + "");
            dateReceptionDatePicker.setValue(Instant.ofEpochMilli(fs.getDateReception().getTime()).atZone(ZoneId.systemDefault()).toLocalDate());
            dateLancementDatePicker.setValue(Instant.ofEpochMilli(fs.getDateLancement().getTime()).atZone(ZoneId.systemDefault()).toLocalDate());
            motifsTextField.setText(fs.getMotif());
            observationsTextField.setText(fs.getObservation());
        }

    }

    private boolean isInputValid() {
        String errorMessage = "";

        if (designationTextField.getText() == null || designationTextField.getText().length() == 0) {
            errorMessage += "Designation invalide!\n";
        }

        if (montantTextField.getText() == null || montantTextField.getText().length() == 0) {
            errorMessage += "Montant invalide!\n";
        }

        if (motifsTextField.getText() == null || motifsTextField.getText().length() == 0) {
            errorMessage += "Motif invalide!\n";
        }

        if (observationsTextField.getText() == null || observationsTextField.getText().length() == 0) {
            errorMessage += "Observation invalide!\n";
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
