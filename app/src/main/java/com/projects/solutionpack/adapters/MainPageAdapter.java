package com.projects.solutionpack.adapters;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.projects.solutionpack.databinding.MainPageEachItomBinding;
import com.projects.solutionpack.model.mainpagemodel.Tool;
import java.util.List;

public class MainPageAdapter extends RecyclerView.Adapter<MainPageAdapter.ViewHolder> {

    private final List<Tool> toolList;
    private final OnItemClickListener listener;

    // Constructor to initialize the tool list and listener
    public MainPageAdapter(List<Tool> toolList, OnItemClickListener listener) {
        this.toolList = toolList;
        this.listener = listener;
    }

    // Interface for handling item clicks
    public interface OnItemClickListener {
        void onItemClick(Tool tool);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        MainPageEachItomBinding binding = MainPageEachItomBinding.inflate(inflater, parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Tool currentTool = toolList.get(position);

        holder.binding.setFill(currentTool);
// Log the image resource ID to verify
        Log.d("loggy", "Image Resource ID: " + currentTool.getImageResource());

        holder.binding.getRoot().setOnClickListener(v -> listener.onItemClick(currentTool));  // Set click listener
    }

    @Override
    public int getItemCount() {
        return toolList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        MainPageEachItomBinding binding;

        public ViewHolder(MainPageEachItomBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
