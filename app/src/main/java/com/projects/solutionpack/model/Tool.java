package com.projects.solutionpack.model;

import androidx.databinding.BindingAdapter;

public class Tool {
    private String toolTitle;
    private String toolDescription;
    private int imageResource;

    //constructor


    public Tool(String toolTitle, String toolDescription, int imageResource) {
        this.toolTitle = toolTitle;
        this.toolDescription = toolDescription;
        this.imageResource = imageResource;
    }

    public Tool() {
    }
    //getters and setters


    public String getToolTitle() {
        return toolTitle;
    }
    public void setToolTitle(String toolTitle) {
        this.toolTitle = toolTitle;
    }

    public String getToolDescription() {
        return toolDescription;
    }

    public void setToolDescription(String toolDescription) {
        this.toolDescription = toolDescription;
    }

    public int getImageResource() {
        return imageResource;
    }

    public void setImageResource(int imageResource) {
        this.imageResource = imageResource;
    }
}
