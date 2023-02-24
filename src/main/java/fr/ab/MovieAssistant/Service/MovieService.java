package fr.ab.MovieAssistant.Service;

import fr.ab.MovieAssistant.DTO.*;
import fr.ab.MovieAssistant.DTO.API.*;
import fr.ab.MovieAssistant.DTO.Request.ContextDTO;
import fr.ab.MovieAssistant.DTO.Response.Message.BasicCard.BasicCardDTO;
import fr.ab.MovieAssistant.DTO.Response.Message.Carousel.CarouselSelectDTO;
import fr.ab.MovieAssistant.DTO.Response.Message.Carousel.ItemDTO;
import fr.ab.MovieAssistant.DTO.Response.Message.Carousel.SelectItemInfoDTO;
import fr.ab.MovieAssistant.DTO.Response.Message.MessageDTO;
import fr.ab.MovieAssistant.DTO.Response.Message.SimpleResponse.SimpleResponseDTO;
import fr.ab.MovieAssistant.DTO.Response.Message.SimpleResponse.SimpleResponsesDTO;
import fr.ab.MovieAssistant.DTO.Response.Message.Suggestion.SuggestionDTO;
import fr.ab.MovieAssistant.DTO.Response.Message.Suggestion.SuggestionsDTO;
import fr.ab.MovieAssistant.DTO.Request.QueryRequestDTO;
import fr.ab.MovieAssistant.DTO.Response.Message.TableCard.ColumsPropertiesDTO;
import fr.ab.MovieAssistant.DTO.Response.Message.TableCard.RowsDTO;
import fr.ab.MovieAssistant.DTO.Response.Message.TableCard.TableCardDTO;
import fr.ab.MovieAssistant.DTO.Response.Message.TableCard.CellDTO;
import fr.ab.MovieAssistant.DTO.Response.WebhookReponseDTO;
import fr.ab.MovieAssistant.Enum.HorizontalAlignment;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class MovieService {

    private final List<String> genres = List.of("Action", "Aventure", "Animation", "Comédie", "Crime", "Drame", "Familial", "Fantastique", "Histoire", "Musique", "Mystère", "Romance", "Science-Fiction", "Téléfilm", "Thriller", "Western");

    private final String API_KEY = "api_key=f5762d995d659b32a1306b4e9da16f59";
    private final String LANGUAGE = "language=fr-FR";
    private final String QUERY = "query=";

    private final String BASE_URL = "https://api.themoviedb.org/3";
    private final String API_GENRE = "/genre/movie/list";
    private final String API_DISCOVERY_MOVIE = "/discover/movie";
    private final String API_MOVIE = "/movie/";
    private final String API_IMAGE = "https://image.tmdb.org/t/p/original";
    private final String SITE_MOVIE_DB = "https://www.themoviedb.org/movie/";
    private final String API_SEARCH = "/search/movie";
    private final String API_WATCH_PROVIDERS = "/watch/providers";
    //https://api.themoviedb.org/3/movie/19995/watch/providers?api_key=f5762d995d659b32a1306b4e9da16f59


    public WebhookReponseDTO getMovie(QueryRequestDTO queryRequestDTO) {

        if(queryRequestDTO.getQueryResult().getParameters().getGenre()==null && queryRequestDTO.getQueryResult().getParameters().getFilm()==null){
            List<ContextDTO> context = queryRequestDTO.getQueryResult().getOutputContexts();
            for (ContextDTO contextDTO : context) {
                if(contextDTO.getParameters().containsKey("OPTION")) {
                    return this.getExplainMovie(contextDTO);
                }
            }
            return this.getMovieByGenre(queryRequestDTO.getQueryResult().getParameters().getGenre().toLowerCase());
        }
        else if(queryRequestDTO.getQueryResult().getParameters().getFilm()!=null){
            return this.getDistributor(queryRequestDTO.getQueryResult().getParameters().getFilm().toLowerCase());
        }

        if(queryRequestDTO.getQueryResult().getParameters().getGenre().equals("")){
            return this.getGenreForUser(false);
        }

        return this.getMovieByGenre(queryRequestDTO.getQueryResult().getParameters().getGenre().toLowerCase());

    }

    private WebhookReponseDTO getExplainMovie(ContextDTO contextDTO) {

        RestTemplate restTemplate = new RestTemplate();
        WebhookReponseDTO webhookReponseDTO = new WebhookReponseDTO();

        List<MessageDTO> messageDTOList = new ArrayList<>();
        MessageDTO messageSimpleResponse = new MessageDTO();
        messageSimpleResponse.setPlatform("ACTIONS_ON_GOOGLE");
        SimpleResponsesDTO simpleResponsesDTO = new SimpleResponsesDTO();
        SimpleResponseDTO simpleResponseDTO = new SimpleResponseDTO();
        simpleResponseDTO.setTextToSpeech("Voici les informations !!");
        simpleResponsesDTO.setSimpleResponses(List.of(simpleResponseDTO));
        messageSimpleResponse.setSimpleResponses(simpleResponsesDTO);
        messageDTOList.add(messageSimpleResponse);

        MessageDTO messageCard= new MessageDTO();
        messageCard.setPlatform("ACTIONS_ON_GOOGLE");
        BasicCardDTO basicCardDTO = new BasicCardDTO();

        MovieDTO movieDTO = restTemplate.getForObject(BASE_URL + API_MOVIE + contextDTO.getParameters().get("OPTION") + "?" + API_KEY + "&" + LANGUAGE, MovieDTO.class);

        if(movieDTO.getHomepage() == null || movieDTO.getHomepage() == "") {
            movieDTO.setHomepage(SITE_MOVIE_DB + movieDTO.getId());
        }

        basicCardDTO.setTitle(movieDTO.getTitle());
        basicCardDTO.setSubtitle("Sorti le " + movieDTO.getRelease_date());
        basicCardDTO.setFormattedText(movieDTO.getOverview());
        //image
        ImageDTO imageDTO = new ImageDTO();
        imageDTO.setImageUri(API_IMAGE + movieDTO.getPoster_path());
        imageDTO.setAccessibilityText(movieDTO.getTitle());
        basicCardDTO.setImage(imageDTO);
        //buttons
        ButtonDTO buttonDTO = new ButtonDTO();
        buttonDTO.setTitle("Voir le film");
        OpenUriActionDTO openUriActionDTO = new OpenUriActionDTO();
        openUriActionDTO.setUri(movieDTO.getHomepage());
        buttonDTO.setOpenUriAction(openUriActionDTO);
        basicCardDTO.setButtons(List.of(buttonDTO));

        messageCard.setBasicCard(basicCardDTO);
        messageDTOList.add(messageCard);
        webhookReponseDTO.setFulfillmentMessages(messageDTOList);

        return webhookReponseDTO;
    }


    //https://api.themoviedb.org/3/search/movie?api_key=f5762d995d659b32a1306b4e9da16f59&query=avatar&language=fr-FR
    private WebhookReponseDTO getDistributor(String film){
        WebhookReponseDTO webhookReponseDTO = new WebhookReponseDTO();

        RestTemplate restTemplate = new RestTemplate();

        MovieListDTO movieListDTO = restTemplate.getForObject(BASE_URL + API_SEARCH + "?" + API_KEY + "&" + LANGUAGE + "&" + QUERY + film, MovieListDTO.class);
        if(movieListDTO == null || movieListDTO.getResults().isEmpty()){
            webhookReponseDTO.setFulfillmentText("Je n'ai pas trouvé de film avec ce nom");
            return webhookReponseDTO;
        }
        MovieDTO movie = movieListDTO.getResults().get(0);

        String request = BASE_URL + API_MOVIE + movie.getId() + API_WATCH_PROVIDERS + "?" + API_KEY;
        WatchProvidersDTO watchProvidersDTO = restTemplate.getForObject(BASE_URL + API_MOVIE + movie.getId() + API_WATCH_PROVIDERS + "?" + API_KEY, WatchProvidersDTO.class);

        List<MessageDTO> messageDTOList = new ArrayList<>();
        MessageDTO messageSimpleResponse = new MessageDTO();
        messageSimpleResponse.setPlatform("ACTIONS_ON_GOOGLE");
        SimpleResponsesDTO simpleResponsesDTO = new SimpleResponsesDTO();
        SimpleResponseDTO simpleResponseDTO = new SimpleResponseDTO();
        simpleResponseDTO.setTextToSpeech("Voici les platforms que je te propose !!");
        simpleResponsesDTO.setSimpleResponses(List.of(simpleResponseDTO));
        messageSimpleResponse.setSimpleResponses(simpleResponsesDTO);
        messageDTOList.add(messageSimpleResponse);

        MessageDTO messageTable = new MessageDTO();
        messageTable.setPlatform("ACTIONS_ON_GOOGLE");
        TableCardDTO tableCardDTO = new TableCardDTO();
        tableCardDTO.setTitle(movie.getTitle());
        tableCardDTO.setSubtitle("Pour plus d'informations : " + watchProvidersDTO.getResults().getFR().getLink());
        List<ColumsPropertiesDTO> columsPropertiesDTOS = new ArrayList<>();
        ColumsPropertiesDTO columsPropertiesDTO = new ColumsPropertiesDTO();
        columsPropertiesDTO.setHeader("Pays");
        columsPropertiesDTO.setHorizontalAlignment(HorizontalAlignment.CENTER);
        columsPropertiesDTOS.add(columsPropertiesDTO);
        ColumsPropertiesDTO columsPropertiesDTO2 = new ColumsPropertiesDTO();
        columsPropertiesDTO2.setHeader("Plateforme");
        columsPropertiesDTO2.setHorizontalAlignment(HorizontalAlignment.CENTER);
        columsPropertiesDTOS.add(columsPropertiesDTO2);

        tableCardDTO.setColumnProperties(columsPropertiesDTOS);

        List<RowsDTO> rowsDTOS = new ArrayList<>();

        if(watchProvidersDTO.getResults().getFR().getFlatrate() != null){
            rowsDTOS.addAll(this.getFlatrate(watchProvidersDTO.getResults().getFR().getFlatrate(),"France"));
        }

        if(watchProvidersDTO.getResults().getUS().getFlatrate() != null){
            rowsDTOS.addAll(this.getFlatrate(watchProvidersDTO.getResults().getUS().getFlatrate(),"Etats-Unis"));
        }

        if(watchProvidersDTO.getResults().getBE().getFlatrate() != null){
            rowsDTOS.addAll(this.getFlatrate(watchProvidersDTO.getResults().getBE().getFlatrate(),"Belgique"));
        }

        if(watchProvidersDTO.getResults().getJP().getFlatrate() != null){
            rowsDTOS.addAll(this.getFlatrate(watchProvidersDTO.getResults().getJP().getFlatrate(),"Japon"));

        }

        if(watchProvidersDTO.getResults().getRU().getFlatrate() != null){
            rowsDTOS.addAll(this.getFlatrate(watchProvidersDTO.getResults().getRU().getFlatrate(),"Russie"));
        }

        if(rowsDTOS.isEmpty()){
            webhookReponseDTO.setFulfillmentText("Je n'ai pas trouvé de plateforme pour ce film");
            return webhookReponseDTO;
        }
        tableCardDTO.setRows(rowsDTOS);
        messageTable.setTableCard(tableCardDTO);
        messageDTOList.add(messageTable);
        webhookReponseDTO.setFulfillmentMessages(messageDTOList);

        return webhookReponseDTO;
    }

    private List<RowsDTO> getFlatrate(List<FlatrateDTO> list, String pays){
        List<RowsDTO> rowsDTOS = new ArrayList<>();
        for (FlatrateDTO flatrateDTO : list) {
            RowsDTO rowsDTO = new RowsDTO();
            List<CellDTO> cellDTOS = new ArrayList<>();
            CellDTO cellDTO = new CellDTO();
            cellDTO.setText(pays);
            cellDTOS.add(cellDTO);
            CellDTO cellDTO2 = new CellDTO();
            cellDTO2.setText(flatrateDTO.getProvider_name());
            cellDTOS.add(cellDTO2);
            rowsDTO.setCells(cellDTOS);
            rowsDTOS.add(rowsDTO);
        }
        return rowsDTOS;
    }

    //"Action";"Aventure";"Animation";"Comédie";"Crime";""Drame";"Familial";
    // "Fantastique";"Histoire";""Musique";"Mystère";"Romance";"Science-Fiction";
    // "Téléfilm";"Thriller";""Western"
    private WebhookReponseDTO getMovieByGenre(String genre) {
        RestTemplate restTemplate = new RestTemplate();
        WebhookReponseDTO webhookReponseDTO = new WebhookReponseDTO();

        List<MessageDTO> messageDTOList = new ArrayList<>();
        MessageDTO messageSimpleResponse = new MessageDTO();
        messageSimpleResponse.setPlatform("ACTIONS_ON_GOOGLE");
        SimpleResponsesDTO simpleResponsesDTO = new SimpleResponsesDTO();
        SimpleResponseDTO simpleResponseDTO = new SimpleResponseDTO();
        simpleResponseDTO.setTextToSpeech("Voici les films que je te propose !!");
        simpleResponsesDTO.setSimpleResponses(List.of(simpleResponseDTO));
        messageSimpleResponse.setSimpleResponses(simpleResponsesDTO);
        messageDTOList.add(messageSimpleResponse);

        MessageDTO messageCarousel = new MessageDTO();
        messageCarousel.setPlatform("ACTIONS_ON_GOOGLE");
        CarouselSelectDTO carouselSelectDTO = new CarouselSelectDTO();
        List<ItemDTO> itemDTOList = new ArrayList<>();

        Long idGenre = 0L;

        GenreMovieDbDTO genres = restTemplate.getForObject(BASE_URL + API_GENRE + "?" + API_KEY + "&" + LANGUAGE, GenreMovieDbDTO.class);

        for (GenreDTO genreDTO : genres.getGenres()) {
            if (genreDTO.getName().toLowerCase().equals(genre)) {
                idGenre = genreDTO.getId();

                ResultDiscoverMovie resultDM = restTemplate.getForObject(BASE_URL + API_DISCOVERY_MOVIE + "?" + API_KEY + "&" + LANGUAGE + "&with_genres=" + idGenre, ResultDiscoverMovie.class);

                for (DiscoverMovieDTO discoverMovieDTO : resultDM.getResults()) {
                    ItemDTO itemDTO = new ItemDTO();
                    SelectItemInfoDTO selectItemInfoDTO = new SelectItemInfoDTO();
                    selectItemInfoDTO.setKey(discoverMovieDTO.getId().toString());
                    selectItemInfoDTO.setSynonyms(List.of(discoverMovieDTO.getTitle()));
                    itemDTO.setInfo(selectItemInfoDTO);
                    itemDTO.setTitle(discoverMovieDTO.getTitle());
                    itemDTO.setDescription(discoverMovieDTO.getOverview());
                    ImageDTO imageDTO = new ImageDTO();
                    imageDTO.setImageUri(API_IMAGE + discoverMovieDTO.getPoster_path());
                    imageDTO.setAccessibilityText(discoverMovieDTO.getTitle());
                    itemDTO.setImage(imageDTO);
                    itemDTOList.add(itemDTO);
                }

                Collections.shuffle(itemDTOList);
                carouselSelectDTO.setItems(itemDTOList.subList(0, 3));
                messageCarousel.setCarouselSelect(carouselSelectDTO);
                messageDTOList.add(messageCarousel);

                webhookReponseDTO.setFulfillmentMessages(messageDTOList);

                return webhookReponseDTO;
            }
        }

        if (idGenre == 0L) {
            webhookReponseDTO = this.getGenreForUser(true);
            return webhookReponseDTO;
        }

        return webhookReponseDTO;
    }

    public WebhookReponseDTO getGenreForUser(Boolean error) {

        WebhookReponseDTO webhookReponseDTO = new WebhookReponseDTO();
        List<MessageDTO> messageDTOList = new ArrayList<>();

        if(error) {
            MessageDTO messageSimpleResponse = new MessageDTO();
            messageSimpleResponse.setPlatform("ACTIONS_ON_GOOGLE");
            SimpleResponsesDTO simpleResponsesDTO = new SimpleResponsesDTO();
            SimpleResponseDTO simpleResponseDTO = new SimpleResponseDTO();
            simpleResponseDTO.setTextToSpeech("Je n'ai pas compris votre demande");
            simpleResponsesDTO.setSimpleResponses(List.of(simpleResponseDTO));
            messageSimpleResponse.setSimpleResponses(simpleResponsesDTO);
            messageDTOList.add(messageSimpleResponse);
        }

        MessageDTO messageDTO = new MessageDTO();
        messageDTO.setPlatform("ACTIONS_ON_GOOGLE");
        SimpleResponsesDTO simpleResponsesDTO = new SimpleResponsesDTO();
        SimpleResponseDTO simpleResponseDTO = new SimpleResponseDTO();
        simpleResponseDTO.setTextToSpeech("Quel genre de film cherchez-vous ?");
        simpleResponsesDTO.setSimpleResponses(List.of(simpleResponseDTO));
        messageDTO.setSimpleResponses(simpleResponsesDTO);
        messageDTOList.add(messageDTO);


        MessageDTO messageDTO2 = new MessageDTO();
        messageDTO2.setPlatform("ACTIONS_ON_GOOGLE");
        SuggestionsDTO suggestionsDTO = new SuggestionsDTO();
        for (String genre : genres) {
            SuggestionDTO suggestionDTO = new SuggestionDTO();
            suggestionDTO.setTitle(genre);
            suggestionsDTO.addSuggestion(suggestionDTO);
        }
        messageDTO2.setSuggestions(suggestionsDTO);
        messageDTOList.add(messageDTO2);

        webhookReponseDTO.setFulfillmentMessages(messageDTOList);

        return webhookReponseDTO;
    }
}
