/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.time.Instant;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javabeans.Administration;
import javabeans.Document;
import javabeans.Marche;
import javabeans.TypePrestation;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import pattern.dao.DAO;
import pattern.dao.DocumentDAO;
import pattern.dao.MarcheDAO;
import pattern.factory.DAOFactory;

/**
 *
 * @author Arthur_2
 */

public class EnvoiDoc {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button envoyerButton;

    @FXML
    private ComboBox<String> destinataireCombo;

    @FXML
    private Label nomDocumentLabel;
    private Stage dialogStage;
    private Document document;
    private DocumentDAO documentDao;
    
    private boolean okClicked = false;

    @FXML
    void envoyerOnAction(ActionEvent event) throws SQLException {
        
        documentDao = (DocumentDAO) DAOFactory.getDocumentDAO();

        documentDao.update(this.document.getId());

        okClicked = true;
        dialogStage.close();
    }

    @FXML
    void initialize() {
        
    }

    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    public void setDocument(Document document) throws SQLException {
        this.document = document;
        
        //DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
        
        DAO<Administration> administration = DAOFactory.getAdministrationDAO();
        List<Administration> a = (ArrayList<Administration>) administration.findAll();
        
        ObservableList<String>  datas = FXCollections.observableArrayList();
        //datas.add("");
        for (int i = 0; i < a.size(); i++) {
            //System.out.println(tp.get(i).getCode());
            datas.add(a.get(i).getNom());
        }
        destinataireCombo.setItems(datas);
        //typePrestationMarche.getSelectionModel().select(0);
        
        nomDocumentLabel.setText(document.getNom());
    }

    public boolean isOkClicked() {
        return okClicked;
    }
}
