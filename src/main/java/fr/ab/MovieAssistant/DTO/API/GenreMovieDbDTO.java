package fr.ab.MovieAssistant.DTO.API;

import java.util.List;

public class GenreMovieDbDTO {
    private List<GenreDTO> genres;

    public List<GenreDTO> getGenres() {
        return genres;
    }

    public void setGenres(List<GenreDTO> genres) {
        this.genres = genres;
    }

}
