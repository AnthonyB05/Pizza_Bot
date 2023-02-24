package com.example.pizza_bot.DTO.Response.Message.Suggestion;

import java.util.ArrayList;
import java.util.List;

public class SuggestionsDTO {
     private List<SuggestionDTO> suggestions = new ArrayList<>();

    public List<SuggestionDTO> getSuggestions() {
        return suggestions;
    }

    public void setSuggestions(List<SuggestionDTO> suggestions) {
        this.suggestions = suggestions;
    }

    public List<SuggestionDTO> addSuggestion(SuggestionDTO suggestions) {
        this.suggestions.add(suggestions);
        return this.suggestions;
    }
}
