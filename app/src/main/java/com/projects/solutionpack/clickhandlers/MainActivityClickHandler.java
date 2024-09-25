package com.projects.solutionpack.clickhandlers;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.Toast;

import com.projects.solutionpack.views.AddNewLogin;


/**
 * Click handler class for handling click events in the MainActivity.
 */
public class MainActivityClickHandler {

    private Context context;

    /**
     * Constructor for the MainActivityClickHandler class.
     *
     * @param context The context of the calling activity.
     */
    public MainActivityClickHandler(Context context) {
        this.context = context;
    }

    /**
     * Handles the click event for the view.
     *
     * @param view The view that was clicked.
     */
    public void onClick(View view) {
        Toast.makeText(context, "add new contacts", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(view.getContext(), AddNewLogin.class);
        context.startActivity(intent);
    }
}
