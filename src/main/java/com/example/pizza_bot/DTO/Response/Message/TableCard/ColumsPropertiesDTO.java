package com.example.pizza_bot.DTO.Response.Message.TableCard;

import com.example.pizza_bot.Enum.HorizontalAlignment;

public class ColumsPropertiesDTO {
    private String header;
    private HorizontalAlignment horizontalAlignment;

    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    public HorizontalAlignment getHorizontalAlignment() {
        return horizontalAlignment;
    }

    public void setHorizontalAlignment(HorizontalAlignment horizontalAlignment) {
        this.horizontalAlignment = horizontalAlignment;
    }
}
