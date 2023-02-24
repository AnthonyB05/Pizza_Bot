package com.example.pizza_bot.DTO.Response.Message;

import com.example.pizza_bot.DTO.Response.Message.BasicCard.BasicCardDTO;
import com.example.pizza_bot.DTO.Response.Message.Carousel.CarouselSelectDTO;
import com.example.pizza_bot.DTO.Response.Message.SimpleResponse.SimpleResponsesDTO;
import com.example.pizza_bot.DTO.Response.Message.Suggestion.SuggestionsDTO;
import com.example.pizza_bot.DTO.Response.Message.TableCard.TableCardDTO;

public class MessageDTO {
    private String platform;
    private SimpleResponsesDTO simpleResponses;
    private SuggestionsDTO suggestions;
    private CarouselSelectDTO carouselSelect;
    private BasicCardDTO basicCard;
    private TableCardDTO tableCard;

    public String getPlatform() {
        return platform;
    }

    public void setPlatform(String platform) {
        this.platform = platform;
    }

    public SimpleResponsesDTO getSimpleResponses() {
        return simpleResponses;
    }

    public void setSimpleResponses(SimpleResponsesDTO simpleResponses) {
        this.simpleResponses = simpleResponses;
    }

    public SuggestionsDTO getSuggestions() {
        return suggestions;
    }

    public void setSuggestions(SuggestionsDTO suggestions) {
        this.suggestions = suggestions;
    }

    public CarouselSelectDTO getCarouselSelect() {
        return carouselSelect;
    }

    public void setCarouselSelect(CarouselSelectDTO carouselSelect) {
        this.carouselSelect = carouselSelect;
    }

    public BasicCardDTO getBasicCard() {
        return basicCard;
    }

    public void setBasicCard(BasicCardDTO basicCard) {
        this.basicCard = basicCard;
    }

    public TableCardDTO getTableCard() {
        return tableCard;
    }

    public void setTableCard(TableCardDTO tableCard) {
        this.tableCard = tableCard;
    }

}


