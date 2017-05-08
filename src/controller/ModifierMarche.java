
package controller;

import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javabeans.Marche;
import javabeans.TypePrestation;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import pattern.dao.DAO;
import pattern.dao.MarcheDAO;
import pattern.factory.DAOFactory;

public class ModifierMarche {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField nomMarche;

    @FXML
    private TextField montantMarche;

    @FXML
    private DatePicker dateDebutMarche;

    @FXML
    private DatePicker dateFinMarche;

    @FXML
    private ComboBox<String> typePrestationMarche;

    @FXML
    private DatePicker dateDemarrage;

    @FXML
    private DatePicker dateSignature;

    @FXML
    private DatePicker dateAttribution;

    @FXML
    private TextField autoriteContractante;

    @FXML
    private Button enregistrerMarche;

    @FXML
    private Button validerMarcheBtn;

    @FXML
    private Button annulerMarche;
    
    private Stage dialogStage;
    private Stage parentStage;
    private Marche marche;
    private boolean okClicked = false;
    private MarcheDAO marcheDao;

    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd LLLL yyyy");
        
    @FXML
    public void annulerMarcheOnAction(ActionEvent event) {
        dialogStage.close();
    }

    @FXML
    public void enregistrerMarcheOnAction(ActionEvent event) throws SQLException {
        if (isInputValid()) {
            //person.setFirstName(firstNameField.getText());
            marcheDao = (MarcheDAO) DAOFactory.getMarcheDAO();
            marche = new Marche();
            marche.setNom(nomMarche.getText());
            marche.setDateDebut(Date.from(dateDebutMarche.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant()));
            marche.setDateFin(Date.from(dateFinMarche.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant()));
            marche.setMontant(Integer.parseInt(montantMarche.getText()));
            marche.setDateAttribution(Date.from(dateAttribution.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant()));
            marche.setDateSignature(Date.from(dateSignature.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant()));
            marche.setDateDemarrage(Date.from(dateDemarrage.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant()));
            marche.setAutoriteContractante(autoriteContractante.getText());
            //typePrestationMarche.getSelectionModel().select(0);
            if (typePrestationMarche.getSelectionModel().getSelectedItem() == null) {
                typePrestationMarche.getSelectionModel().select(0);
            }
            marche.setCodeTypePrestation(typePrestationMarche.getSelectionModel().getSelectedItem());
            //System.out.println("Select"+typePrestationMarche.getSelectionModel().getSelectedItem());
            marcheDao.create(marche);

            okClicked = true;
            dialogStage.close();
            
            
        }
    }    

    @FXML
    public void validerMarcheOnAction(ActionEvent event) throws SQLException {

        if (isInputValid()) {
            //person.setFirstName(firstNameField.getText());
            marcheDao = (MarcheDAO) DAOFactory.getMarcheDAO();
            Marche marche = new Marche();
            marche.setId(this.marche.getId());
            marche.setNom(nomMarche.getText());
            marche.setDateDebut(Date.from(dateDebutMarche.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant()));
            marche.setDateFin(Date.from(dateFinMarche.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant()));
            marche.setMontant(Integer.parseInt(montantMarche.getText()));
            //typePrestationMarche.getSelectionModel().select(0);
            marche.setCodeTypePrestation(typePrestationMarche.getSelectionModel().getSelectedItem());
                    
            marcheDao.update(marche);

            okClicked = true;
            dialogStage.close();
        }
    }    
    
    private boolean isInputValid() {
        String errorMessage = "";

        if (nomMarche.getText() == null || nomMarche.getText().length() == 0) {
            errorMessage += "Nom invalide!\n";
        }
        if (dateDebutMarche.getValue() == null || dateDebutMarche.getValue().format(this.formatter).length() == 0) {
            errorMessage += "Date debut marché invalide!\n";
        }
        if (dateFinMarche.getValue() == null || dateFinMarche.getValue().format(this.formatter).length() == 0) {
            errorMessage += "Date fin marché invalide\n";
        }

        if (errorMessage.length() == 0) {
            return true;
        } else {
            // Show the error message.
            Alert alert = new Alert(AlertType.ERROR);
            alert.initOwner(dialogStage);
            alert.setTitle("Champs invalides");
            alert.setHeaderText("Vérifiez les champs invalides s'il vous plait");
            alert.setContentText(errorMessage);

            alert.showAndWait();

            return false;
        }
    }

    @FXML
    void initialize() {

    }

    /**
     * Sets the stage of this dialog.
     *
     * @param dialogStage
     */
    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    public void setParentStage(Stage primaryStage) {
        this.parentStage = primaryStage;
    }

    /**
     * Returns true if the user clicked OK, false otherwise.
     *
     * @return
     */
    public boolean isOkClicked() {
        return okClicked;
    }

    /**
     * Sets the person to be edited in the dialog.
     *
     * @param person
     */
    public void setMarche(Marche marche) throws SQLException {
        this.marche = marche;
        
        //DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
        
        DAO<TypePrestation> typesprestation = DAOFactory.getTypePrestationDAO();
        List<TypePrestation> tp = (ArrayList<TypePrestation>) typesprestation.findAll();
        
        ObservableList<String>  datas = FXCollections.observableArrayList();
        //datas.add("");
        for (int i = 0; i < tp.size(); i++) {
            //System.out.println(tp.get(i).getCode());
            datas.add(tp.get(i).getCode());
        }
        typePrestationMarche.setItems(datas);
        //typePrestationMarche.getSelectionModel().select(0);
        
        if (marche != null) {
            nomMarche.setText(marche.getNom());
            dateDebutMarche.setValue(Instant.ofEpochMilli(marche.getDateDebut().getTime()).atZone(ZoneId.systemDefault()).toLocalDate());
            dateFinMarche.setValue(Instant.ofEpochMilli(marche.getDateFin().getTime()).atZone(ZoneId.systemDefault()).toLocalDate());
            montantMarche.setText(marche.getMontant()+"");
            typePrestationMarche.getSelectionModel().select(marche.getCodeTypePrestation());
        }
    }

    public void setInvisible(String invisible) {
        if (invisible.equals("nouveau")) {
            this.enregistrerMarche.setVisible(false);
        } else {//modifier
            this.validerMarcheBtn.setVisible(false);
        }
    }
}
