/**
 * Sample Skeleton for 'detail_marche.fxml' Controller Class
 */

package controller;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javabeans.Marche;
import javabeans.Procedure;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import model.ModelOpen;
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

    
    Marche marche;
    ProcedureDAO procedureDAO;

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() throws SQLException {
        procedureDAO = (ProcedureDAO)DAOFactory.getProcedureDAO();
        
        marche = (Marche) Context.getInstance().getObject();
        nomLabel.setText(marche.getNom());
        montantLabel.setText(marche.getMontant()+"");
        dateDebutLabel.setText(marche.getDateDebut().toString());
        dateFinLabel.setText(marche.getDateFin().toString());
        
        System.out.println(procedureDAO.findByMarche(marche.getId()).size());
        procedureListView.getItems().addAll(procedureDAO.findByMarche(marche.getId()));
    }
    
    @FXML
    void precedentOnAction(ActionEvent event) throws IOException {
        new ModelOpen().loadPage(event, "journal_programmation.fxml");
    }
}
