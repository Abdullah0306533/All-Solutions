package com.projects.solutionpack.views;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.util.SparseArray;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.mlkit.vision.common.InputImage;
import com.google.mlkit.vision.text.Text;
import com.google.mlkit.vision.text.TextRecognition;
import com.google.mlkit.vision.text.latin.TextRecognizerOptions;
import com.projects.solutionpack.R;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ImageToTextActivity extends AppCompatActivity {

    private static final String TAG = "ImageToTextActivity";
    private CardView cardView;
    private ImageView imageView;
    private TextView extracted_text;
    private Button buttonSnap, buttonDetect;
    private Bitmap bitmap;
    private Uri photoURI;

    private static final int REQUEST_IMAGE_CAPTURE = 1;
    private static final int PERMISSION_CODE = 200;
    private String currentPhotoPath;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_image_to_text);

        // Set window insets for edge-to-edge layout
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Initialize views
        imageView = findViewById(R.id.preview_image);
        cardView = findViewById(R.id.card_view);
        buttonSnap = findViewById(R.id.button_snap);
        buttonDetect = findViewById(R.id.button_detect);
        extracted_text=findViewById(R.id.extracted_text);

        // Apply animations
        startAnimations();

        // Snap button to capture image
        buttonSnap.setOnClickListener(view -> {
            if (checkPermission()) {
                captureImage();
            } else {
                requestPermission();
            }
        });

        // Detect button to detect text from image
        buttonDetect.setOnClickListener(view -> {
            if (bitmap != null) {
                detectText();
            } else {
                Toast.makeText(this, "Capture an image first!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void detectText() {
        // Initialize the ML Kit Text Recognition
        InputImage image = InputImage.fromBitmap(bitmap, 0);
        com.google.mlkit.vision.text.TextRecognizer recognizer = TextRecognition.getClient(TextRecognizerOptions.DEFAULT_OPTIONS);

        recognizer.process(image)
                .addOnSuccessListener(visionText -> {
                    String detectedText = visionText.getText();
                    if (detectedText.isEmpty()) {
                        Toast.makeText(ImageToTextActivity.this, "No text found in the image", Toast.LENGTH_SHORT).show();
                    } else {
                        // Display the detected text (for now as a Toast, you may update this to show in a TextView or another activity)
                        //Toast.makeText(ImageToTextActivity.this, detectedText, Toast.LENGTH_LONG).show();
                        extracted_text.setText(detectedText);
                    }
                })
                .addOnFailureListener(e -> {
                    Log.e(TAG, "Text detection failed", e);
                    Toast.makeText(ImageToTextActivity.this, "Failed to detect text", Toast.LENGTH_SHORT).show();
                });
    }

    private boolean checkPermission() {
        int cameraPermission = ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.CAMERA);
        return cameraPermission == PackageManager.PERMISSION_GRANTED;
    }

    private void requestPermission() {
        ActivityCompat.requestPermissions(this, new String[]{
                Manifest.permission.CAMERA
        }, PERMISSION_CODE);
    }

    private void captureImage() {
        // Intent to open the camera
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            // Create the File where the photo should go
            File photoFile = null;
            try {
                photoFile = createImageFile();
            } catch (IOException ex) {
                Log.e(TAG, "Error occurred while creating the file", ex);
                return;
            }
            // Continue only if the File was successfully created
            if (photoFile != null) {
                photoURI = FileProvider.getUriForFile(this, "com.projects.solutionpack.fileprovider", photoFile);
                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
                startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
            }
        }
    }

    private File createImageFile() throws IOException {
        // Create an image file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";
        File storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File image = File.createTempFile(
                imageFileName,  /* prefix */
                ".jpg",         /* suffix */
                storageDir      /* directory */
        );

        // Save a file: path for use with ACTION_VIEW intents
        currentPhotoPath = image.getAbsolutePath();
        return image;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            try {
                bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), photoURI);
                imageView.setImageBitmap(bitmap);
                Log.d(TAG, "Image captured and displayed successfully");
            } catch (IOException e) {
                Log.e(TAG, "Error while loading the image", e);
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == PERMISSION_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                captureImage();
            } else {
                Log.w(TAG, "Camera permission denied");
                Toast.makeText(this, "Permission denied", Toast.LENGTH_SHORT).show();
            }
        }
    }

    // Animations on views
    private void startAnimations() {
        // Load animations from XML
        Animation fadeInAnimation = AnimationUtils.loadAnimation(this, R.anim.fade_in);
        Animation slideUpAnimation = AnimationUtils.loadAnimation(this, R.anim.slide_up);

        // Apply fade-in animation to the CardView
        cardView.startAnimation(fadeInAnimation);

        // Apply slide-up animation to the buttons
        buttonSnap.startAnimation(slideUpAnimation);
        buttonDetect.startAnimation(slideUpAnimation);
    }
}
