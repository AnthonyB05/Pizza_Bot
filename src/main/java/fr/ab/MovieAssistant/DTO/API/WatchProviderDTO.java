package fr.ab.MovieAssistant.DTO.API;

import com.fasterxml.jackson.annotation.JsonProperty;

public class WatchProviderDTO {

    @JsonProperty("FR")
    private LanguageDTO FR;
    @JsonProperty("BE")
    private LanguageDTO BE;
    @JsonProperty("JP")
    private LanguageDTO JP;
    @JsonProperty("US")
    private LanguageDTO US;
    @JsonProperty("RU")
    private LanguageDTO RU;

    public LanguageDTO getFR() {
        return FR;
    }

    public void setFR(LanguageDTO FR) {
        this.FR = FR;
    }

    public LanguageDTO getBE() {
        return BE;
    }

    public void setBE(LanguageDTO BE) {
        this.BE = BE;
    }

    public LanguageDTO getJP() {
        return JP;
    }

    public void setJP(LanguageDTO JP) {
        this.JP = JP;
    }

    public LanguageDTO getUS() {
        return US;
    }

    public void setUS(LanguageDTO US) {
        this.US = US;
    }

    public LanguageDTO getRU() {
        return RU;
    }

    public void setRU(LanguageDTO RU) {
        this.RU = RU;
    }



}
