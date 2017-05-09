/**
 * Sample Skeleton for 'detail_marche.fxml' Controller Class
 */

package controller;

import gui.MainApp;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javabeans.Document;
import javabeans.Marche;
import javabeans.Procedure;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.ModelDataTable;
import model.ModelOpen;
import pattern.dao.DocumentDAO;
import pattern.dao.ProcedureDAO;
import pattern.factory.DAOFactory;
import tools.Context;

public class DetailMarche implements EventHandler {

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;
    
        @FXML
    private Label nomLabel;

    @FXML
    private Label montantLabel;

    @FXML
    private Label dateDebutLabel;

    @FXML
    private Label dateFinLabel;

    @FXML
    private ListView<Procedure> procedureListView;

    @FXML
    private Label typePrestationLabel;

    @FXML
    private Button PrecedentButton;

    @FXML
    private Button suivantButton;

    @FXML
    private TableView<Document> tableView;

    @FXML
    private TableColumn<Document, ?> idTableColumn;

    @FXML
    private TableColumn<Document, ?> phaseTableColumn;

    @FXML
    private TableColumn<Document, ?> nomTableColumn;

    @FXML
    private TableColumn<Document, ?> archiveTableColumn;

    @FXML
    private TableColumn<Document, ?> statutTableColumn;

    private ModelDataTable<Document> modelTable;
    private List<String> params;
    private List<TableColumn<Document, ?>> listColumns;
    
    private Marche marche;
    private ProcedureDAO procedureDAO;
    private DocumentDAO documentDAO;

    private Stage dialogStage;
    
    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() throws SQLException {
        procedureDAO = (ProcedureDAO)DAOFactory.getProcedureDAO();
        documentDAO = (DocumentDAO) DAOFactory.getDocumentDAO();
        
        marche = (Marche) Context.getInstance().getObject();
        nomLabel.setText(marche.getNom());
        montantLabel.setText(marche.getMontant()+"");
        dateDebutLabel.setText(marche.getDateDebut().toString());
        dateFinLabel.setText(marche.getDateFin().toString());
        
        procedureListView.getItems().addAll(procedureDAO.findByMarche(marche.getId()));
        
        modelTable = new ModelDataTable<>();

        params = new ArrayList<>();
        params.add("id");
        params.add("phase");
        params.add("nom");
        params.add("archive");
        params.add("statut");

        listColumns = new ArrayList<>();
        listColumns.add(idTableColumn);
        listColumns.add(phaseTableColumn);
        listColumns.add(nomTableColumn);
        listColumns.add(archiveTableColumn);
        listColumns.add(statutTableColumn);
        modelTable.setJavabeans((List)documentDAO.findByMarche(marche.getId()));
        modelTable.loadTableJavabean(tableView, listColumns, params);
        
    }
    
    @FXML
    void procedureListViewOnMouseClicked(MouseEvent event) {

    }

    @FXML
    void tableViewOnMouseClicked(MouseEvent event) throws IOException {
        
        if (event.getClickCount() == 2) {
            Document document = tableView.getSelectionModel().getSelectedItem();
            System.out.println(document);
            boolean okClicked = MainApp.showEnvoiDialog(document, "Envoi du document " + document.getNom());
            if (okClicked) {
                new ModelOpen().loadPage(event, "detail_marche.fxml", false, "Détail marché");

                dialogStage = new Stage();
                dialogStage.initModality(Modality.WINDOW_MODAL);

                Button ok = new Button("Ok");
                VBox vbox = new VBox(new Text("Le document " + document.getNom() + " a bien été envoyé"), ok);
                vbox.setAlignment(Pos.CENTER);
                vbox.setPadding(new Insets(15));

                dialogStage.setScene(new Scene(vbox));
                dialogStage.show();

                ok.setOnAction(this);
            }
        }
    }
    
    @FXML
    void precedentOnAction(ActionEvent event) throws IOException {
        new ModelOpen().loadPage(event, "journal_programmation.fxml", true, "Journal de programmation");
    }

    @Override
    public void handle(Event event) {
        dialogStage.close();
    }
}
