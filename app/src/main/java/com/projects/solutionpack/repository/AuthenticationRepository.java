package com.projects.solutionpack.repository;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.databinding.DataBindingUtil;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.projects.solutionpack.R;
import com.projects.solutionpack.authentication.LoginActivity;
import com.projects.solutionpack.databinding.DialogLayoutBinding;
import com.projects.solutionpack.views.MainPageActivity;

public class AuthenticationRepository {
    private DialogLayoutBinding dialogLayoutBinding;
    private FirebaseAuth firebaseAuth;
    private final Context context;

    public AuthenticationRepository(Context context) {
        this.context = context;
        firebaseAuth = FirebaseAuth.getInstance();
        dialogLayoutBinding = DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.dialog_layout, null, false);


    }

    public void getSignUp() {
        // Create and show the dialog
        AlertDialog dialog = new AlertDialog.Builder(context)
                .setView(dialogLayoutBinding.getRoot())
                .setCancelable(true)
                .create();
        dialog.show();

        // Set up the sign-up button click listener
        dialogLayoutBinding.signupButton.setOnClickListener(view -> {
            String email = dialogLayoutBinding.inputEmail.getText().toString().trim();
            String password = dialogLayoutBinding.inputPassword.getText().toString().trim();

            // Validate input
            if (email.isEmpty() || password.isEmpty()) {
                Toast.makeText(context, "Please fill in all fields", Toast.LENGTH_SHORT).show();
                return;
            }

            // Sign up the user
            firebaseAuth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener(task -> {
                        if (task.isSuccessful()) {
                            FirebaseUser user = firebaseAuth.getCurrentUser();
                            Toast.makeText(context, "Sign Up Successful: " + user.getEmail(), Toast.LENGTH_SHORT).show();
                            dialog.dismiss();  // Close the dialog

                            // Start Main Page Activity
                            Intent intent = new Intent(context, MainPageActivity.class);
                            context.startActivity(intent);
                        } else {
                            Toast.makeText(context, "Sign Up Failed: " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });
        });
    }

    public void signIn(String email,String password) {
        FirebaseUser currentUser = firebaseAuth.getCurrentUser();

        // Check if the user is already signed in
        if (currentUser != null) {
            // User is already signed in, proceed to the MainPageActivity
            Intent intent = new Intent(context, MainPageActivity.class);
            context.startActivity(intent);
        } else {
            // If the user is not signed in, proceed with the normal sign-in flow
            // Validate input
            if (email.isEmpty() || password.isEmpty()) {
                Toast.makeText(context, "Please fill in all fields", Toast.LENGTH_SHORT).show();
                return;
            }

            // Sign in the user with email and password
            firebaseAuth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener(task -> {
                        if (task.isSuccessful()) {
                            // Successful sign-in, navigate to MainPageActivity
                            Intent intent = new Intent(context, MainPageActivity.class);
                            context.startActivity(intent);
                        } else {
                            Toast.makeText(context, "Sign In Failed: " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }).addOnFailureListener(e -> {
                        Toast.makeText(context, "Invalid Credentials", Toast.LENGTH_SHORT).show();
                    });
        }
    }

    public void signOut(){
        firebaseAuth.signOut();
        Intent i=new Intent(context, LoginActivity.class);
            context.startActivity(i);

    }
}
