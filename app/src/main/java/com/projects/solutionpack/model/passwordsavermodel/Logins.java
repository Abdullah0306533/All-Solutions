package com.projects.solutionpack.model.passwordsavermodel;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

/**
 * Entity class representing a login record in the database.
 */
@Entity(tableName = "login_table")
public class Logins {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "login_id")
    private int id;

    @ColumnInfo(name = "login")
    private String login;

    @ColumnInfo(name = "password")
    private String password;

    /**
     * Default constructor required for Room.
     */
    public Logins() {
    }

    /**
     * Returns the ID of the login.
     *
     * @return the login ID
     */
    public int getId() {
        return id;
    }

    /**
     * Sets the ID of the login.
     *
     * @param id the login ID
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Returns the login name.
     *
     * @return the login name
     */
    public String getLogin() {
        return login;
    }

    /**
     * Sets the login name.
     *
     * @param login the login name
     */
    public void setLogin(String login) {
        this.login = login;
    }

    /**
     * Returns the password.
     *
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * Sets the password.
     *
     * @param password the password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /** initialize all feilds
     * constructor
     * @param login
     * @param password
     */
    public Logins(String login, String password) {
        this.login = login;
        this.password = password;
    }
}
