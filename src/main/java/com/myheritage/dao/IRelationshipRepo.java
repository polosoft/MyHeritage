package com.myheritage.dao;

import com.myheritage.model.RelationShipUsers;
import com.myheritage.model.User;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by Noa on 24/05/2016.
 */
public interface IRelationshipRepo {

    public void SaveRelationshipUser(RelationShipUsers relationShipUsers) throws SQLException;

}
