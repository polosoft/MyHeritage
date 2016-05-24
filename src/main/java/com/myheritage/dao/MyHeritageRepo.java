package com.myheritage.dao;

import android.content.Context;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.stmt.PreparedQuery;
import com.j256.ormlite.stmt.QueryBuilder;
import com.j256.ormlite.stmt.SelectArg;
import com.myheritage.model.RelationShipUsers;
import com.myheritage.model.User;
import com.myheritage.utils.db.HelperFactory;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by Noa on 23/05/2016.
 */
public  class MyHeritageRepo implements IUserRepo, IRelationshipRepo {

    private Dao<User, String> _userDao = HelperFactory.getHelper().getUserAnswerDAO();
    private Dao<RelationShipUsers, String> _relationshipUsersDao = HelperFactory.getHelper().getRelationshipUsersDao();


    @Override
    public List GetUsers() throws SQLException {
        return _userDao.queryForAll();
    }

    @Override
    public User GetUser(String id) throws SQLException {
        User user = _userDao.queryForId(id);
        user.relationShipUsers = lookupRelationsUsersForUser(user);
        return user;
    }

    @Override
    public void DeleteUser(User deleteProduct) throws SQLException {

    }

    @Override
    public void SaveUser(User saveUser) throws SQLException {
        _userDao.create(saveUser);
    }

    @Override
    public void UpdateUser(User updateUser) throws SQLException {

    }

    @Override
    public void SaveRelationshipUser(RelationShipUsers relationShipUsers) throws SQLException {
        _relationshipUsersDao.create(relationShipUsers);
    }

    private PreparedQuery<User> relationsForUserQuery = null;

    private List<User> lookupRelationsUsersForUser(User user) throws SQLException {
        if (relationsForUserQuery == null) {
            relationsForUserQuery = makeRelationsForUserQuery();
        }
        relationsForUserQuery.setArgumentHolderValue(0, user);
        return _userDao.query(relationsForUserQuery);
    }

    /**
     * Build our query for Post objects that match a User.
     */
    private PreparedQuery<User> makeRelationsForUserQuery() throws SQLException {

        QueryBuilder<RelationShipUsers, String> userRelationshipQb = _relationshipUsersDao.queryBuilder();
        userRelationshipQb.selectColumns("id");
        SelectArg userSelectArg = new SelectArg();
        userRelationshipQb.where().eq("primaryUser", userSelectArg);

        // build our outer query for Post objects
        QueryBuilder<User, String> userQb = _userDao.queryBuilder();
        // where the id matches in the id from the inner query
        userQb.where().in("id", userQb);
        return userQb.prepare();
    }

}
