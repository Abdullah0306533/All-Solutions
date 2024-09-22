package com.projects.solutionpack.views;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.databinding.DataBindingUtil;

import com.projects.solutionpack.R;
import com.projects.solutionpack.databinding.ActivityMainPageBinding;
import com.projects.solutionpack.viewmodel.MyViewModel;

public class MainPageActivity extends AppCompatActivity {

    ActivityMainPageBinding activityMainPageBinding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main_page);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        MyViewModel viewModel=new MyViewModel(this);
        activityMainPageBinding= DataBindingUtil.setContentView(this,R.layout.activity_main_page);
        activityMainPageBinding.ll.setOnClickListener(view -> {viewModel.getSignOut();});

    }
}