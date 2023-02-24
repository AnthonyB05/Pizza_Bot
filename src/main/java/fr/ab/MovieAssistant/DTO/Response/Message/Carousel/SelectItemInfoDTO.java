package fr.ab.MovieAssistant.DTO.Response.Message.Carousel;

import java.util.List;

public class SelectItemInfoDTO {
    private String key;
    private List<String> synonyms;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public List<String> getSynonyms() {
        return synonyms;
    }

    public void setSynonyms(List<String> synonyms) {
        this.synonyms = synonyms;
    }
}
