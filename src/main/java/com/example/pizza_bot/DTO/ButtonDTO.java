package com.example.pizza_bot.DTO;

public class ButtonDTO {
    private String title;
    private com.example.pizza_bot.DTO.OpenUriActionDTO openUriAction;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public com.example.pizza_bot.DTO.OpenUriActionDTO getOpenUriAction() {
        return openUriAction;
    }

    public void setOpenUriAction(com.example.pizza_bot.DTO.OpenUriActionDTO openUriAction) {
        this.openUriAction = openUriAction;
    }
}
