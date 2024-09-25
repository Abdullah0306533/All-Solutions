package com.projects.solutionpack.repository.passwordsaverrepository;

import android.app.Application;
import android.os.Handler;
import android.os.Looper;

import androidx.lifecycle.LiveData;


import com.projects.solutionpack.database.LoginDAO;
import com.projects.solutionpack.database.LoginDatabase;
import com.projects.solutionpack.model.passwordsavermodel.Logins;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Repository class that abstracts the data sources and provides a clean API for data access to the rest of the application.
 */
public class PasswordSaverRepository {
    private final LoginDAO loginDAO;
    private final ExecutorService executor;
    private final Handler mainThreadHandler;

    /**
     * Constructor for the Repository class.
     *
     * @param application The application context.
     */
    public PasswordSaverRepository(Application application) {
        // Get the database instance
        LoginDatabase database = LoginDatabase.getInstance(application);

        // Get the DAO from the database instance
        this.loginDAO = database.getLoginDAO();

        // Initialize a single-threaded executor for background operations
        this.executor = Executors.newSingleThreadExecutor();

        // Initialize a handler for the main thread
        this.mainThreadHandler = new Handler(Looper.getMainLooper());
    }

    /**
     * Deletes a login entry from the database.
     *
     * @param logins The login entry to be deleted.
     */
    public void delete(final Logins logins) {
        executor.execute(new Runnable() {
            @Override
            public void run() {
                loginDAO.delete(logins);
            }
        });
    }

    /**
     * Inserts a login entry into the database.
     *
     * @param login The login entry to be inserted.
     */
    public void insert(final Logins login) {
        executor.execute(new Runnable() {
            @Override
            public void run() {
                loginDAO.insert(login);
            }
        });
    }

    /**
     * Updates a login entry in the database.
     *
     * @param logins The login entry to be updated.
     */
    public void change(final Logins logins) {
        executor.execute(new Runnable() {
            @Override
            public void run() {
                loginDAO.change(logins);
            }
        });
    }

    /**
     * retrieves login with specific id
     * @param id
     * @return
     */
    public LiveData<Logins> getById(int id){
      return   loginDAO.getLoginById(id);
    }

    /**
     * Retrieves all login entries from the database.
     *
     * @return A LiveData list of all login entries.
     */
    public LiveData<List<Logins>> getAllLogins() {
        return loginDAO.getAllLogins();
    }
}
