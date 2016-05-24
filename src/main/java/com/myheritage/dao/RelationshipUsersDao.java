package com.myheritage.dao;

import com.j256.ormlite.dao.BaseDaoImpl;
import com.j256.ormlite.support.ConnectionSource;
import com.myheritage.model.RelationShipUsers;

import java.sql.SQLException;

/**
 * Created by Noa on 24/05/2016.
 */
public class RelationshipUsersDao extends BaseDaoImpl<RelationShipUsers, String> {

    public RelationshipUsersDao(ConnectionSource connectionSource,
                         Class<RelationShipUsers> relationShipUsersClass) throws SQLException {
        super(connectionSource, relationShipUsersClass);

    }
}
