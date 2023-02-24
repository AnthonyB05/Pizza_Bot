package fr.ab.MovieAssistant.DTO.Request;


public class ParameterDTO{
    private String genre;
    private String film;

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getFilm() {
        return film;
    }

    public void setFilm(String film) {
        this.film = film;
    }
}
