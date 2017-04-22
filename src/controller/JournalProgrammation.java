package controller;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import javabeans.Marche;
import javabeans.TypePrestation;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ComboBox;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import pattern.dao.DAO;
import pattern.dao.MarcheDAO;
import pattern.factory.DAOFactory;

/**
 * 
 * @author Arthur Lekane
 */
public class JournalProgrammation {

    @FXML
    private AnchorPane anchorPane;
    
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button rechercher;

    @FXML
    private TextField searchInput;

    @FXML
    private ComboBox<String> searchTypePrestation;

    @FXML
    private TableView<Marche> marcheTable;

    @FXML
    private TableColumn<Marche, Integer> idColumn;

    @FXML
    private TableColumn<Marche, String> nomColumn;

    @FXML
    private TableColumn<Marche, Integer> montantColumn;

    @FXML
    private TableColumn<Marche, Date> dateDebutColumn;

    @FXML
    private TableColumn<Marche, Date> dateFinColumn;

    @FXML
    private TableColumn<Marche, String> typePrestationColumn;

    private ModelDataTable<Marche> modelTable;
    private List<String> params ;
    private List<TableColumn<Marche, ?>> listColumns;
    
    private MarcheDAO marches;

    @FXML
    void initialize() throws SQLException {
        marches = (MarcheDAO) DAOFactory.getMarcheDAO();
        //System.out.println(marches.findAll().size());
//        for (Marche m : marches.findAll()) {
//            System.out.println(m);
//        }
        modelTable = new ModelDataTable<>();
        modelTable.setJavabeans(marches.findAll());
        
        params = new ArrayList<>();
        params.add("id");
        params.add("Nom");
        params.add("Montant");
        params.add("DateDebut");
        params.add("DateFin");
        params.add("prestation");
        
        listColumns = new ArrayList<>();
        listColumns.add(idColumn);
        listColumns.add(nomColumn);
        listColumns.add(montantColumn);
        listColumns.add(dateDebutColumn);
        listColumns.add(dateFinColumn);
        listColumns.add(typePrestationColumn);
        
        modelTable.loadTableJavabean(marcheTable, listColumns, params);
        
        DAO<TypePrestation> typesprestation = DAOFactory.getTypePrestationDAO();
        List<TypePrestation> tp = (ArrayList<TypePrestation>) typesprestation.findAll();
        
        ObservableList<String>  datas = FXCollections.observableArrayList();
        datas.add("");
        for (int i = 0; i < tp.size(); i++) {
            //System.out.println(tp.get(i).getCode());
            datas.add(tp.get(i).getCode());
        }
        searchTypePrestation.setItems(datas);
        //searchTypePrestation.getSelectionModel().select(0);
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
        if (event.getClickCount() == 2) {
            Marche m = marcheTable.getSelectionModel().getSelectedItem();
            AnchorPane anc = FXMLLoader.load(getClass().getResource("/gui/detail_marche.fxml"));
            anchorPane.getChildren().clear();
            anchorPane.getChildren().add(anc);
        }
    }
    
    @FXML 
    protected void handleRechercherButton(ActionEvent event) {
        String searchText = (String) searchInput.getText();
        System.out.println("rechercher : "+searchText);
    }
}
