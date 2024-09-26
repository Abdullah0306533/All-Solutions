package com.projects.solutionpack.clickhandlers;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.Toast;

import com.projects.solutionpack.views.PasswordSaverActivity;
import com.projects.solutionpack.model.passwordsavermodel.Logins;
import com.projects.solutionpack.viewmodel.ViewModelPasswordSaver;


public class SaveDataClickHandler {
    private final Context context;
    private final Logins logins;
    private final ViewModelPasswordSaver viewModel;

    public SaveDataClickHandler(Context context, Logins logins, ViewModelPasswordSaver viewModel) {
        this.context = context;
        this.logins = logins;
        this.viewModel = viewModel;
    }

    // Method to handle the click event of submit button
    public void onSubmitBtnClicked(View view) {
        // Check if login or password is empty
        if (logins.getLogin() == null || logins.getPassword() == null) {
            // Show toast message if any field is empty
            Toast.makeText(context, "Fill the fields", Toast.LENGTH_SHORT).show();
        } else {
            // Create intent to start MainActivity
            Intent intent = new Intent(context, PasswordSaverActivity.class);

            // Create a new instance of Logins with provided login and password
            Logins newLogin = new Logins(logins.getLogin(), logins.getPassword());

            // Add the new login to ViewModel
            viewModel.add(newLogin);

            // Start MainActivity
            context.startActivity(intent);
        }
    }
}
