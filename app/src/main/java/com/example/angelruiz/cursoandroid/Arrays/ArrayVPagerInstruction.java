package com.example.angelruiz.cursoandroid.Arrays;

public class ArrayVPagerInstruction {
    private String title;
    private Integer image;
    private String description;
    private Integer colorBackground;

    public ArrayVPagerInstruction(String title, Integer image, String description, Integer colorBackground) {
        this.title = title;
        this.image = image;
        this.description = description;
        this.colorBackground = colorBackground;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getImage() {
        return image;
    }

    public void setImage(Integer image) {
        this.image = image;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getColorBackground() {
        return colorBackground;
    }

    public void setColorBackground(Integer colorBackground) {
        this.colorBackground = colorBackground;
    }
}
