package com.projects.solutionpack.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.projects.solutionpack.model.passwordsavermodel.Logins;


/**
 * The Room database class for managing login-related data.
 */
@Database(entities = {Logins.class}, version = 1)
public abstract class LoginDatabase extends RoomDatabase {

    /**
     * Provides the Data Access Object (DAO) for the Logins table.
     *
     * @return the LoginDAO instance
     */
    public abstract LoginDAO getLoginDAO();

    private static LoginDatabase dbInstance;

    /**
     * Returns the singleton instance of LoginDatabase.
     *
     * @param context the application context
     * @return the singleton instance of LoginDatabase
     */
    public static synchronized LoginDatabase getInstance(Context context) {
        // Using singleton pattern to create a single instance of the database
        if (dbInstance == null) {
            dbInstance = Room.databaseBuilder(context.getApplicationContext(),
                            LoginDatabase.class, "logins_db")
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return dbInstance;
    }
}
