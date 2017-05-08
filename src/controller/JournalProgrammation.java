package controller;

import gui.MainApp;
import model.ModelDataTable;
import tools.Context;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import javabeans.Marche;
import javabeans.TypePrestation;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ComboBox;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.ModelOpen;
import pattern.dao.DAO;
import pattern.dao.MarcheDAO;
import pattern.factory.DAOFactory;

/**
 *
 * @author Arthur Lekane
 */
public class JournalProgrammation implements EventHandler {

    @FXML
    private AnchorPane anchorPane;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ComboBox<String> searchTypePrestation;

    @FXML
    private TextField searchInput;

    @FXML
    private Button rechercher;

    @FXML
    private TableView<Marche> marcheTable;

    @FXML
    private TableColumn<Marche, ?> idColumn;

    @FXML
    private TableColumn<Marche, ?> nomColumn;

    @FXML
    private TableColumn<Marche, ?> montantColumn;

    @FXML
    private TableColumn<Marche, ?> dateDebutColumn;

    @FXML
    private TableColumn<Marche, ?> dateAttributionColumn;

    @FXML
    private TableColumn<Marche, ?> dateSignatureColumn;

    @FXML
    private TableColumn<Marche, ?> dateDemarrageColumn;

    @FXML
    private TableColumn<Marche, ?> dateFinColumn;

    @FXML
    private TableColumn<Marche, String> contractantColumn;

    @FXML
    private TableColumn<Marche, ?> typePrestationColumn;

    @FXML
    private Button modifierBtn;

    @FXML
    private Button supprimerBtn;

    @FXML
    private Button detailsBtn;

    private ModelDataTable<Marche> modelTable;
    private List<String> params;
    private List<TableColumn<Marche, ?>> listColumns;

    private MarcheDAO marches;
    
    private Stage dialogStage;

    @FXML
    void initialize() throws SQLException {
        modifierBtn.setDisable(true);
        supprimerBtn.setDisable(true);
        detailsBtn.setDisable(true);

        this.updateTable();

        DAO<TypePrestation> typesprestation = DAOFactory.getTypePrestationDAO();
        List<TypePrestation> tp = (ArrayList<TypePrestation>) typesprestation.findAll();

        ObservableList<String> datas = FXCollections.observableArrayList();
        datas.add("");
        for (int i = 0; i < tp.size(); i++) {
            //System.out.println(tp.get(i).getCode());
            datas.add(tp.get(i).getCode());
        }
        searchTypePrestation.setItems(datas);
        searchTypePrestation.getSelectionModel().select(0);
    }

    @FXML
    protected void handleRechercherButtonOnAction(ActionEvent event) throws SQLException {
        String comboText = (String) searchTypePrestation.getSelectionModel().getSelectedItem();
        String searchText = (String) searchInput.getText();

        modelTable.setJavabeans(marches.findAll(searchText, comboText));
        modelTable.loadTableJavabean(marcheTable, listColumns, params);
    }

    @FXML
    protected void handleFilterTypePrestationOnAction(ActionEvent event) throws SQLException {
        String comboText = (String) searchTypePrestation.getSelectionModel().getSelectedItem();
        String searchText = (String) searchInput.getText();

        modelTable.setJavabeans(marches.findAll(searchText, comboText));

        modelTable.loadTableJavabean(marcheTable, listColumns, params);
    }

    @FXML
    protected void marcheTableOnMouseClicked(MouseEvent event) throws IOException {

        modifierBtn.setDisable(false);
        supprimerBtn.setDisable(false);
        detailsBtn.setDisable(false);

        if (event.getClickCount() == 2) {
            Marche m = marcheTable.getSelectionModel().getSelectedItem();
            Context.getInstance().setObject(m);
            new ModelOpen().loadPage(event, "detail_marche.fxml", true, "Détail marché");
        }
    }

    @FXML
    protected void handleRechercherButton(ActionEvent event) {
        String searchText = (String) searchInput.getText();
        System.out.println("rechercher : " + searchText);
    }

    public void updateTable() throws SQLException {
        marches = (MarcheDAO) DAOFactory.getMarcheDAO();

        modelTable = new ModelDataTable<>();
        modelTable.setJavabeans(marches.findAll());

        params = new ArrayList<>();
        params.add("id");
        params.add("nom");
        params.add("montant");
        params.add("dateDebut");
        params.add("dateAttribution");
        params.add("dateSignature");
        params.add("dateDemarrage");
        params.add("dateFin");
        params.add("autoriteContractante");
        params.add("codeTypePrestation");

        listColumns = new ArrayList<>();
        listColumns.add(idColumn);
        listColumns.add(nomColumn);
        listColumns.add(montantColumn);
        listColumns.add(dateDebutColumn);
        listColumns.add(dateAttributionColumn);
        listColumns.add(dateSignatureColumn);
        listColumns.add(dateDemarrageColumn);
        listColumns.add(dateFinColumn);
        listColumns.add(contractantColumn);
        listColumns.add(typePrestationColumn);

        modelTable.loadTableJavabean(marcheTable, listColumns, params);
    }

    @FXML
    protected void editerMarcheOnAction(ActionEvent event) throws IOException, SQLException {
        Marche tempMarche = marcheTable.getSelectionModel().getSelectedItem();
        //System.out.println(tempMarche.getNom());
        boolean okClicked = MainApp.showMarcheEditDialog(tempMarche, "Editer le marché numéro " + tempMarche.getId(), "nouveau");
        if (okClicked) {
            new ModelOpen().loadPage(event, "journal_programmation.fxml", true, "Journal de programmation");

            dialogStage = new Stage();
            dialogStage.initModality(Modality.WINDOW_MODAL);

            Button ok = new Button("Ok");
            VBox vbox = new VBox(new Text("Le marché " + tempMarche.getId() + " a été modifié avec succès"), ok);
            vbox.setAlignment(Pos.CENTER);
            vbox.setPadding(new Insets(15));

            dialogStage.setScene(new Scene(vbox));
            dialogStage.show();

            ok.setOnAction(this);
        }
    }

    @FXML
    protected void supprimerMarcheOnAction(ActionEvent event) throws IOException, SQLException {
        Marche tempMarche = marcheTable.getSelectionModel().getSelectedItem();

        Alert alert = new Alert(AlertType.WARNING, "voulez vous vraiment supprimer ?", ButtonType.YES, ButtonType.NO);
        alert.setTitle("Supprimer le marché " + tempMarche.getId());
        alert.setHeaderText("Attention");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.YES) {
            MarcheDAO marcheDao = (MarcheDAO) DAOFactory.getMarcheDAO();

            marcheDao.delete(tempMarche);

            new ModelOpen().loadPage(event, "journal_programmation.fxml", true, "Journal de programmation");

        } else {
            // ... user chose NO or closed the dialog
        }
    }

    @FXML
    protected void detailsMarcheOnAction(ActionEvent event) throws IOException, SQLException {
        Marche m = marcheTable.getSelectionModel().getSelectedItem();
        if (m != null) {
            Context.getInstance().setObject(m);
            new ModelOpen().loadPage(event, "detail_marche.fxml", false, "Détail marché");
        }
    }

    @Override
    public void handle(Event event) {
        dialogStage.close();
    }
}
