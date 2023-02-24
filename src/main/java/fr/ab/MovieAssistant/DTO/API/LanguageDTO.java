package fr.ab.MovieAssistant.DTO.API;

import java.util.List;

public class LanguageDTO {

    private String link;
    private List<FlatrateDTO> flatrate;

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public List<FlatrateDTO> getFlatrate() {
        return flatrate;
    }

    public void setFlatrate(List<FlatrateDTO> flatrate) {
        this.flatrate = flatrate;
    }
}
