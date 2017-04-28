/**
 * Sample Skeleton for 'detail_marche.fxml' Controller Class
 */

package controller;

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
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;
import model.ModelDataTable;
import model.ModelOpen;
import pattern.dao.DocumentDAO;
import pattern.dao.ProcedureDAO;
import pattern.factory.DAOFactory;
import tools.Context;

public class DetailMarche {

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
    private TableView<Object> tableView;

    @FXML
    private TableColumn<Object, ?> idTableColumn;

    @FXML
    private TableColumn<Object, ?> phaseTableColumn;

    @FXML
    private TableColumn<Object, ?> nomTableColumn;

    @FXML
    private TableColumn<Object, ?> archiveTableColumn;

    @FXML
    private TableColumn<Object, ?> statutTableColumn;

    private ModelDataTable<Object> modelTable;
    private List<String> params;
    private List<TableColumn<Object, ?>> listColumns;
    
    private Marche marche;
    private ProcedureDAO procedureDAO;
    private DocumentDAO documentDAO;

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
        params.add("delaiTransmission");
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
    void tableViewOnMouseClicked(MouseEvent event) {

    }
    
    @FXML
    void precedentOnAction(ActionEvent event) throws IOException {
        new ModelOpen().loadPage(event, "journal_programmation.fxml", true, "Journal de programmation");
    }
}
