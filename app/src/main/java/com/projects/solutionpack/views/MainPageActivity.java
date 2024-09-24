package com.projects.solutionpack.views;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;
import com.projects.solutionpack.R;
import com.projects.solutionpack.adapters.MainPageAdapter;
import com.projects.solutionpack.databinding.ActivityMainPageBinding;
import com.projects.solutionpack.model.mainpagemodel.Tool;
import com.projects.solutionpack.viewmodel.MainPageViewModel;

import java.util.ArrayList;
import java.util.List;

public class MainPageActivity extends AppCompatActivity {

    private ActivityMainPageBinding activityMainPageBinding;
    private List<Tool> toolList = new ArrayList<>();
    private MainPageViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityMainPageBinding = DataBindingUtil.setContentView(this, R.layout.activity_main_page);

        // Apply edge-to-edge system insets
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Initialize tool list (you can replace this with actual data)
        initializeToolList();

        // Set up RecyclerView with adapter
        setUpRecyclerView();

        //to get signed out
        viewModel=new MainPageViewModel(this);
        activityMainPageBinding.logOut.setOnClickListener(view -> {
            Toast.makeText(this, "signed out", Toast.LENGTH_SHORT).show();
            viewModel.getSignOut();});
    }

    private void initializeToolList() {
        // Example data for tools
        toolList.add(new Tool("Weather", "Check Weather and locations of cities all across the world", R.drawable.weather));
        toolList.add(new Tool("Image To Text","Extract text from your Images",R.drawable.converter1));
        // Add more tools as needed
    }

    private void setUpRecyclerView() {
        MainPageAdapter adapter = new MainPageAdapter(toolList, this::navigateToToolActivity);
        activityMainPageBinding.recyclerView.setLayoutManager(new LinearLayoutManager(this));
        activityMainPageBinding.recyclerView.setAdapter(adapter);
    }

    private void navigateToToolActivity(Tool tool) {
        Intent intent = null;

        // Navigate to different activities based on the tool selected
        switch (tool.getToolTitle()) {
            case "Weather":
                // Launch activity for Tool 1
                intent = new Intent(MainPageActivity.this, WeatherActivity.class);

                break;
                //Todo:create alot of tools and place the startActivity at end of switch
            case "Image To Text":

            default:
                //nothing
                intent=new Intent(MainPageActivity.this,ImageToTextActivity.class);
        }
        startActivity(intent);


    }

}
