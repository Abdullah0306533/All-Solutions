package com.projects.solutionpack.views;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.projects.solutionpack.R;
import com.projects.solutionpack.model.passwordsavermodel.Logins;
import com.projects.solutionpack.viewmodel.ViewModelPasswordSaver;


/**
 * Activity for editing login details.
 */
public class Edit extends AppCompatActivity {

    private ViewModelPasswordSaver viewModel; // View model instance
    private Logins logins; // Object to store login details
    private EditText pas; // EditText for password
    private EditText log; // EditText for login
    private Button button; // Button to submit changes

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this); // Enable edge-to-edge display
        setContentView(R.layout.activity_edit); // Set layout
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        }); // Apply system insets

        // Initialize views
        pas = findViewById(R.id.editTextPassword2);
        log = findViewById(R.id.editTextLogin2);
        button = findViewById(R.id.submitBtn2);

        // Initialize ViewModel
        viewModel = new ViewModelProvider(this).get(ViewModelPasswordSaver.class);
        int id = getIntent().getIntExtra("id", -1); // Get login ID from intent

        // Load existing login details
        viewModel.findById(id).observe(this, new Observer<Logins>() {
            @Override
            public void onChanged(Logins loginDetails) {
                if (loginDetails != null) {
                    logins = loginDetails; // Store login details
                    log.setText(logins.getLogin()); // Set login text
                    pas.setText(logins.getPassword()); // Set password text
                }
            }
        });

        // Set onClickListener for the button
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Get updated login and password
                String updatedLogin = log.getText().toString().trim();
                String updatedPassword = pas.getText().toString().trim();

                // Check if fields are empty
                if (updatedLogin.isEmpty() || updatedPassword.isEmpty()) {
                    Toast.makeText(Edit.this, "Please fill in both fields", Toast.LENGTH_SHORT).show();
                    return;
                }

                // Update login details
                logins.setLogin(updatedLogin);
                logins.setPassword(updatedPassword);

                // Update login in the database
                viewModel.change(logins);

                // Navigate back to MainActivity
                Intent i = new Intent(Edit.this, MainActivity.class);
                startActivity(i);
            }
        });
    }
    @Override
    public void onBackPressed() {
        // Custom behavior when the back button is pressed
        super.onBackPressed();
        // Additional code if needed
        Intent i=new Intent(Edit.this, MainActivity.class);
        startActivity(i);
    }
}
