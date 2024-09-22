package com.projects.solutionpack.authentication;

import android.content.Intent;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.databinding.DataBindingUtil;

import com.google.firebase.auth.FirebaseAuth;
import com.projects.solutionpack.R;
import com.projects.solutionpack.databinding.ActivityMainBinding;
import com.projects.solutionpack.viewmodel.MyViewModel;
import com.projects.solutionpack.views.MainPageActivity;

public class LoginActivity extends AppCompatActivity {

    private MyViewModel viewModel;
    private ActivityMainBinding activityMainBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);

        // Use DataBinding to set the content view
        activityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        // Handle window insets
        ViewCompat.setOnApplyWindowInsetsListener(activityMainBinding.getRoot(), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        if (FirebaseAuth.getInstance().getCurrentUser() != null) {
            // User is signed in, skip login and go to MainPageActivity
            Intent intent = new Intent(this, MainPageActivity.class);
            startActivity(intent);
            finish();  // Optional: Finish this activity to prevent back navigation to the login screen
        }

        // Initialize ViewModel
        viewModel = new MyViewModel(this);

        // Set up sign-up click listener
        activityMainBinding.signUp.setOnClickListener(view -> {
            viewModel.getSignup();
        });
        activityMainBinding.loginButton.setOnClickListener(view -> {
            viewModel.getSignIn(
               activityMainBinding.emailField.getText().toString(),
               activityMainBinding.passwordField.getText().toString());
        });
    }

}
