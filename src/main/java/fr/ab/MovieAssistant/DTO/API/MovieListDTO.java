package fr.ab.MovieAssistant.DTO.API;

import java.util.ArrayList;
import java.util.List;

public class MovieListDTO {

    private List<MovieDTO> results;

    public List<MovieDTO> getResults() {
        return results;
    }

    public void setResults(List<MovieDTO> results) {
        this.results = results;
    }
}
