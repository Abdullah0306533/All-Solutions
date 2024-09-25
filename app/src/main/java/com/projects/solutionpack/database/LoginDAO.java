package com.projects.solutionpack.database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;


import com.projects.solutionpack.model.passwordsavermodel.Logins;

import java.util.List;

/**
 * Data Access Object (DAO) for the Logins entity.
 */
@Dao
public interface LoginDAO {

    /**
     * Deletes a login record from the database.
     *
     * @param logins the login record to delete
     */
    @Delete
    void delete(Logins logins);

    /**
     * Inserts a new login record into the database.
     *
     * @param logins the login record to insert
     */
    @Insert
    void insert(Logins logins);

    /**
     * Updates an existing login record in the database.
     *
     * @param logins the login record to update
     */
    @Update
    void change(Logins logins);

    /**
     * Retrieves all login records from the database.
     *
     * @return a LiveData list of all login records
     */
    @Query("SELECT * FROM login_table")
    LiveData<List<Logins>> getAllLogins();

    /**
     * Retrieves a login record by its ID.
     *
     * @param id the ID of the login record
     * @return a LiveData object containing the login record
     */

    @Query("SELECT * FROM login_table WHERE login_id = :id")
    LiveData<Logins> getLoginById(int id);
}
