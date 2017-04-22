/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pattern.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import jdbc.ConnectionMySQL;

/**
 *
 * @author Aurelien KOUAM
 * @param <JavaBean>
 */
public abstract class DAO<JavaBean> {
    protected Connection connect = ConnectionMySQL.getInstanceConnection();

    public abstract void create(JavaBean obj) throws SQLException;
    public abstract void update(JavaBean obj) throws SQLException;
    public abstract void delete(JavaBean obj) throws SQLException;
    public abstract List<JavaBean> findAll() throws SQLException;
    public abstract JavaBean findById(int id) throws SQLException;
}
