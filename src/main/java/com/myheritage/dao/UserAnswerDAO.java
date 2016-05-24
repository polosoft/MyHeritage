package com.myheritage.dao;

import com.j256.ormlite.dao.BaseDaoImpl;
import com.j256.ormlite.support.ConnectionSource;
import com.myheritage.model.User;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by Noa on 23/05/2016.
 */
public class UserAnswerDAO extends BaseDaoImpl<User, String> {

    public UserAnswerDAO(ConnectionSource connectionSource,
                           Class<User> userClass) throws SQLException {
        super(connectionSource, userClass);

    }
}
