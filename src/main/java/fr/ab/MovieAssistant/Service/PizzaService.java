package fr.ab.MovieAssistant.Service;

import fr.ab.MovieAssistant.DTO.ImageDTO;
import fr.ab.MovieAssistant.DTO.PizzaDTO;
import fr.ab.MovieAssistant.DTO.Request.ContextDTO;
import fr.ab.MovieAssistant.DTO.Request.QueryRequestDTO;
import fr.ab.MovieAssistant.DTO.Response.Message.Carousel.CarouselSelectDTO;
import fr.ab.MovieAssistant.DTO.Response.Message.Carousel.ItemDTO;
import fr.ab.MovieAssistant.DTO.Response.Message.Carousel.SelectItemInfoDTO;
import fr.ab.MovieAssistant.DTO.Response.Message.MessageDTO;
import fr.ab.MovieAssistant.DTO.Response.Message.SimpleResponse.SimpleResponseDTO;
import fr.ab.MovieAssistant.DTO.Response.Message.SimpleResponse.SimpleResponsesDTO;
import fr.ab.MovieAssistant.DTO.Response.Message.Suggestion.SuggestionDTO;
import fr.ab.MovieAssistant.DTO.Response.Message.Suggestion.SuggestionsDTO;
import fr.ab.MovieAssistant.DTO.Response.WebhookReponseDTO;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PizzaService {

    private final List<String> bases = List.of("Tomate", "Crème", "Sucre");


    public WebhookReponseDTO getPizza(QueryRequestDTO queryRequestDTO) {

        if (queryRequestDTO.getQueryResult().getParameters().getBase() == null) {
            List<ContextDTO> context = queryRequestDTO.getQueryResult().getOutputContexts();
            for (ContextDTO contextDTO : context) {
                if(contextDTO.getParameters().containsKey("OPTION")) {
                    return this.getExplainPizza(contextDTO);
                }
            }
            return getbase(false);
        }

        if(queryRequestDTO.getQueryResult().getParameters().getBase().equals("")){
            return getbase(false);
        }

        return getPizzaByBase(queryRequestDTO.getQueryResult().getParameters().getBase().toLowerCase());

    }

    private WebhookReponseDTO getExplainPizza(ContextDTO contextDTO) {

        WebhookReponseDTO webhookReponseDTO = new WebhookReponseDTO();

        List<MessageDTO> messageDTOList = new ArrayList<>();
        MessageDTO messageSimpleResponse = new MessageDTO();
        messageSimpleResponse.setPlatform("ACTIONS_ON_GOOGLE");
        SimpleResponsesDTO simpleResponsesDTO = new SimpleResponsesDTO();
        SimpleResponseDTO simpleResponseDTO = new SimpleResponseDTO();
        simpleResponseDTO.setTextToSpeech("Un supplément ? ");
        simpleResponsesDTO.setSimpleResponses(List.of(simpleResponseDTO));
        messageSimpleResponse.setSimpleResponses(simpleResponsesDTO);
        messageDTOList.add(messageSimpleResponse);

        return webhookReponseDTO;
    }

    private WebhookReponseDTO getPizzaByBase(String base) {

        List<PizzaDTO> pizzaTomate = new ArrayList<>();
        PizzaDTO pizzaMARGUERITE = new PizzaDTO();
        pizzaMARGUERITE.setName("MARGUERITE");
        pizzaMARGUERITE.setPrice("16,00€");
        pizzaMARGUERITE.setImage("https://assets.afcdn.com/recipe/20200206/107152_w1024h1024c1cx176cy267.webp");
        pizzaTomate.add(pizzaMARGUERITE);

        PizzaDTO pizzaROYALE = new PizzaDTO();
        pizzaROYALE.setName("ROYALE");
        pizzaROYALE.setPrice("15,00€");
        pizzaROYALE.setImage("https://img.cuisineaz.com/660x660/2013/12/20/i95731-pizza-royale.jpg");
        pizzaTomate.add(pizzaROYALE);

        List<PizzaDTO> pizzaCreme = new ArrayList<>();
        PizzaDTO pizzaSAUMON= new PizzaDTO();
        pizzaMARGUERITE.setName("SAUMON");
        pizzaMARGUERITE.setPrice("20,00€");
        pizzaMARGUERITE.setImage("https://www.yumelise.fr/wp-content/uploads/2021/06/pizza-saumon-fume-500x500.jpg");
        pizzaTomate.add(pizzaMARGUERITE);

        PizzaDTO pizzaHAWAIENNE = new PizzaDTO();
        pizzaROYALE.setName("HAWAIENNE");
        pizzaROYALE.setPrice("25,00€");
        pizzaROYALE.setImage("https://assets.afcdn.com/recipe/20170328/63885_w1024h576c1cx1500cy1000.webp");
        pizzaTomate.add(pizzaROYALE);

        List<PizzaDTO> pizzaSucre = new ArrayList<>();
        PizzaDTO pizzaNUTELLA = new PizzaDTO();
        pizzaMARGUERITE.setName("NUTELLA");
        pizzaMARGUERITE.setPrice("27,00€");
        pizzaMARGUERITE.setImage("https://cdn.shopify.com/s/files/1/0571/4136/2868/articles/four_a_pizza_recette_giuliz_nutella_noisettes_1200x1200.jpg?v=1637559828");
        pizzaTomate.add(pizzaMARGUERITE);

        PizzaDTO pizzaFruit = new PizzaDTO();
        pizzaROYALE.setName("Fruit");
        pizzaROYALE.setPrice("30,00€");
        pizzaROYALE.setImage("https://www.fashioncooking.fr/wp-content/uploads/2020/06/sweet-pizza-aux-fruits.jpg");
        pizzaTomate.add(pizzaROYALE);


        WebhookReponseDTO webhookReponseDTO = new WebhookReponseDTO();

        List<MessageDTO> messageDTOList = new ArrayList<>();
        MessageDTO messageSimpleResponse = new MessageDTO();
        messageSimpleResponse.setPlatform("ACTIONS_ON_GOOGLE");
        SimpleResponsesDTO simpleResponsesDTO = new SimpleResponsesDTO();
        SimpleResponseDTO simpleResponseDTO = new SimpleResponseDTO();
        simpleResponseDTO.setTextToSpeech("Voici les pizzas que je te propose !!");
        simpleResponsesDTO.setSimpleResponses(List.of(simpleResponseDTO));
        messageSimpleResponse.setSimpleResponses(simpleResponsesDTO);
        messageDTOList.add(messageSimpleResponse);

        MessageDTO messageCarousel = new MessageDTO();
        messageCarousel.setPlatform("ACTIONS_ON_GOOGLE");
        CarouselSelectDTO carouselSelectDTO = new CarouselSelectDTO();
        List<ItemDTO> itemDTOList = new ArrayList<>();

        if (base.equals("tomate")) {
            for (PizzaDTO pizzaDTO : pizzaTomate) {
                ItemDTO itemDTO = new ItemDTO();
                SelectItemInfoDTO selectItemInfoDTO = new SelectItemInfoDTO();
                selectItemInfoDTO.setKey(pizzaDTO.getName().toString());
                selectItemInfoDTO.setSynonyms(List.of(pizzaDTO.getName()));
                itemDTO.setTitle(pizzaDTO.getName());
                itemDTO.setDescription(pizzaDTO.getPrice());
                ImageDTO imageDTO = new ImageDTO();
                imageDTO.setImageUri(pizzaDTO.getImage());
                imageDTO.setAccessibilityText(pizzaDTO.getName());
                itemDTO.setImage(imageDTO);
                itemDTOList.add(itemDTO);
            }
        } else if (base.equals("crème")) {
            for (PizzaDTO pizzaDTO : pizzaCreme) {
                ItemDTO itemDTO = new ItemDTO();
                SelectItemInfoDTO selectItemInfoDTO = new SelectItemInfoDTO();
                selectItemInfoDTO.setKey(pizzaDTO.getName().toString());
                selectItemInfoDTO.setSynonyms(List.of(pizzaDTO.getName()));
                itemDTO.setInfo(selectItemInfoDTO);
                itemDTO.setTitle(pizzaDTO.getName());
                itemDTO.setDescription(pizzaDTO.getPrice());
                ImageDTO imageDTO = new ImageDTO();
                imageDTO.setImageUri(pizzaDTO.getImage());
                imageDTO.setAccessibilityText(pizzaDTO.getName());
                itemDTO.setImage(imageDTO);
                itemDTOList.add(itemDTO);
            }
        } else if (base.equals("sucre")) {
            for (PizzaDTO pizzaDTO : pizzaSucre) {
                ItemDTO itemDTO = new ItemDTO();
                SelectItemInfoDTO selectItemInfoDTO = new SelectItemInfoDTO();
                selectItemInfoDTO.setKey(pizzaDTO.getName().toString());
                selectItemInfoDTO.setSynonyms(List.of(pizzaDTO.getName()));
                itemDTO.setTitle(pizzaDTO.getName());
                itemDTO.setDescription(pizzaDTO.getPrice());
                ImageDTO imageDTO = new ImageDTO();
                imageDTO.setImageUri(pizzaDTO.getImage());
                imageDTO.setAccessibilityText(pizzaDTO.getName());
                itemDTO.setImage(imageDTO);
                itemDTOList.add(itemDTO);
            }
        }

        carouselSelectDTO.setItems(itemDTOList);
        messageCarousel.setCarouselSelect(carouselSelectDTO);
        messageDTOList.add(messageCarousel);

        webhookReponseDTO.setFulfillmentMessages(messageDTOList);
        return webhookReponseDTO;
    }

    public WebhookReponseDTO getbase(Boolean error) {

        WebhookReponseDTO webhookReponseDTO = new WebhookReponseDTO();
        List<MessageDTO> messageDTOList = new ArrayList<>();

        if (error) {
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
        simpleResponseDTO.setTextToSpeech("Quel base pour votre pizza voulez vous ?");
        simpleResponsesDTO.setSimpleResponses(List.of(simpleResponseDTO));
        messageDTO.setSimpleResponses(simpleResponsesDTO);
        messageDTOList.add(messageDTO);

        MessageDTO messageDTO2 = new MessageDTO();
        messageDTO2.setPlatform("ACTIONS_ON_GOOGLE");
        SuggestionsDTO suggestionsDTO = new SuggestionsDTO();
        for (String base : bases) {
            SuggestionDTO suggestionDTO = new SuggestionDTO();
            suggestionDTO.setTitle(base);
            suggestionsDTO.addSuggestion(suggestionDTO);
        }
        messageDTO2.setSuggestions(suggestionsDTO);
        messageDTOList.add(messageDTO2);

        webhookReponseDTO.setFulfillmentMessages(messageDTOList);

        return webhookReponseDTO;
    }
}
