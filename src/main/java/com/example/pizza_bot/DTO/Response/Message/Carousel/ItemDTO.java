package com.example.pizza_bot.DTO.Response.Message.Carousel;

import com.example.pizza_bot.DTO.ImageDTO;

public class ItemDTO {

    private SelectItemInfoDTO info;
    private String title;
    private String description;
    private ImageDTO image;

    public SelectItemInfoDTO getInfo() {
        return info;
    }

    public void setInfo(SelectItemInfoDTO info) {
        this.info = info;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ImageDTO getImage() {
        return image;
    }

    public void setImage(ImageDTO image) {
        this.image = image;
    }
}
