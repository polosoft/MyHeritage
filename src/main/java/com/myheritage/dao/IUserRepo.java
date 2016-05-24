package com.myheritage.dao;

import com.myheritage.model.User;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by Noa on 23/05/2016.
 */
public interface IUserRepo {

    public List GetUsers() throws SQLException;
    public User GetUser(String id) throws SQLException;
    public void DeleteUser(User deleteProduct) throws SQLException;
    public void SaveUser(User saveUser) throws SQLException;
    public void UpdateUser(User updateUser) throws SQLException;

}
