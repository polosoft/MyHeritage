package com.myheritage.utils.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;
import com.myheritage.dao.RelationshipUsersDao;
import com.myheritage.dao.UserAnswerDAO;
import com.myheritage.model.RelationShipUsers;
import com.myheritage.model.User;

import java.sql.SQLException;

/**
 * Created by Noa on 19/05/2016.
 */
public class UsersDbHelper extends OrmLiteSqliteOpenHelper {

    private static final String TAG = "RANDTUNE DATABASE";

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "Users.db";

    private RelationshipUsersDao relationshipUsersDao = null;
    private UserAnswerDAO userAnswerDAO = null;

    Context context;

    public UsersDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase database, ConnectionSource connectionSource) {

        try {
            TableUtils.createTableIfNotExists(connectionSource, User.class);
            TableUtils.createTableIfNotExists(connectionSource, RelationShipUsers.class);
        }

        catch (SQLException e){

            Log.e(TAG, "error creating DB " + DATABASE_NAME);

            throw new RuntimeException(e);
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, ConnectionSource connectionSource, int oldVer,
                          int newVer){

        switch (oldVer) {
            case 1:
                updateFromVersion1(db, connectionSource, oldVer, newVer);
                break;

            default:
                // no updates needed
                break;
        }
    }

    @Override
    public void close(){
        super.close();
        userAnswerDAO = null;
    }

    private void updateFromVersion1(SQLiteDatabase db, ConnectionSource connectionSource, int oldVer, int newVer) {

        //some update here
        onUpgrade(db, connectionSource, oldVer + 1, newVer);
    }

    public UserAnswerDAO getUserAnswerDAO() {

        if(userAnswerDAO == null){
            try {
                userAnswerDAO = new UserAnswerDAO(getConnectionSource(), User.class);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return userAnswerDAO;
    }

    public RelationshipUsersDao getRelationshipUsersDao() {

        if(relationshipUsersDao == null){
            try {
                relationshipUsersDao = new RelationshipUsersDao(getConnectionSource(), RelationShipUsers.class);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return relationshipUsersDao;
    }
}
