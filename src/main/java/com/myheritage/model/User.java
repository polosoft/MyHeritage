package com.myheritage.model;

import com.google.gson.annotations.SerializedName;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Noa on 19/05/2016.
 */
@DatabaseTable(tableName = "User")
public class User implements Serializable {

    private static final long serialVersionUID = -222864131214757024L;

    public User(){}

    @DatabaseField(generatedId = true, columnName = "id")
    public int id;

    @SerializedName("firstName")
    @DatabaseField(canBeNull = true, columnName = "firstName")
    public String firstName;

    @SerializedName("lastName")
    @DatabaseField(canBeNull = true, columnName = "lastName")
    public String lastName;

    @SerializedName("birthDate")
    @DatabaseField(canBeNull = true, columnName = "birthDate")
    public String birthDate;

    @SerializedName("countryOfBirth")
    @DatabaseField(canBeNull = true, columnName = "countryOfBirth")
    public String countryOfBirth;

//    @DatabaseField(canBeNull = true, foreign = true, foreignAutoRefresh = true)
    public List<User> relationShipUsers;

}
