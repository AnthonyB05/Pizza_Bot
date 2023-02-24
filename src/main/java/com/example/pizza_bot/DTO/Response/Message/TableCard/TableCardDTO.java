package com.example.pizza_bot.DTO.Response.Message.TableCard;

import com.example.pizza_bot.DTO.ButtonDTO;
import com.example.pizza_bot.DTO.ImageDTO;

import java.util.List;

public class TableCardDTO {
    private String title;
    private String subtitle;
    private ImageDTO image;
    private List<ColumsPropertiesDTO> columnProperties;
    private List<RowsDTO> rows;
    private List<ButtonDTO> button;


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }

    public ImageDTO getImage() {
        return image;
    }

    public void setImage(ImageDTO image) {
        this.image = image;
    }

    public List<ButtonDTO> getButton() {
        return button;
    }

    public void setButton(List<ButtonDTO> button) {
        this.button = button;
    }

    public List<ColumsPropertiesDTO> getColumnProperties() {
        return columnProperties;
    }

    public void setColumnProperties(List<ColumsPropertiesDTO> columnProperties) {
        this.columnProperties = columnProperties;
    }

    public List<RowsDTO> getRows() {
        return rows;
    }

    public void setRows(List<RowsDTO> rows) {
        this.rows = rows;
    }
}
