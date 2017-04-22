/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 *
 * @author Aurelien KOUAM
 * @param <JavaBean>
 */
public class ModelDataTable<JavaBean> {
    private List<JavaBean> javabeans;
    
    public void loadTableJavabean(TableView<JavaBean> table, List<TableColumn<JavaBean, ?>> listColumn, List<String> map) {
        int i = 0;
        for (String t : map) {
            listColumn.get(i).setCellValueFactory(new PropertyValueFactory(t));
            table.getColumns().set(i, listColumn.get(i));
            i++;
        }

        ObservableList<JavaBean> models = FXCollections.observableArrayList(javabeans);
        table.setItems(models);
    }

    public List<JavaBean> getJavabeans() {
        return javabeans;
    }

    public void setJavabeans(List<JavaBean> javabeans) {
        this.javabeans = javabeans;
    }
    
}
