package com.myheritage.model;

import com.google.gson.annotations.SerializedName;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.io.Serializable;

/**
 * Created by Noa on 23/05/2016.
 */

@DatabaseTable(tableName = "RelationShipUsers")
public class RelationShipUsers implements Serializable {

    private static final long serialVersionUID = 1L;

    public RelationShipUsers(){}

    public RelationShipUsers(User primaryUser, User secondaryUser, String relationShipType)
    {
        this.primaryUser = primaryUser;
        this.secondaryUser = secondaryUser;
        this.relationShipType = relationShipType;
    }

    @DatabaseField(generatedId = true, columnName = "id")
    private int id;

    @SerializedName("primaryUser")
    @DatabaseField(foreign = true, columnName = "primaryUser")
    public User primaryUser;

    @SerializedName("secondaryUser")
    @DatabaseField(foreign = true, columnName = "secondaryUser")
    public User secondaryUser;

    // 1 - partner
    // 2 - parent
    // 3 - brother
    @SerializedName("relationShipType")
    public String relationShipType;
}
