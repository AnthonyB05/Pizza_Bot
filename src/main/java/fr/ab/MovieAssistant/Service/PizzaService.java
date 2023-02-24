package fr.ab.MovieAssistant.Service;

import fr.ab.MovieAssistant.DTO.DessertDTO;
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
                if (contextDTO.getParameters().containsKey("OPTION")) {
                    return this.getChoice(contextDTO);
                }
            }
            return getbase(false);
        }

        if (queryRequestDTO.getQueryResult().getParameters().getBase().equals("")) {
            return getbase(false);
        }

        return getPizzaByBase(queryRequestDTO.getQueryResult().getParameters().getBase().toLowerCase());

    }

    private WebhookReponseDTO getChoice(ContextDTO contextDTO) {

        WebhookReponseDTO webhookReponseDTO = new WebhookReponseDTO();

        List<MessageDTO> messageDTOList = new ArrayList<>();
        MessageDTO messageSimpleResponse = new MessageDTO();
        messageSimpleResponse.setPlatform("ACTIONS_ON_GOOGLE");
        SimpleResponsesDTO simpleResponsesDTO = new SimpleResponsesDTO();
        SimpleResponseDTO simpleResponseDTO = new SimpleResponseDTO();
        simpleResponseDTO.setTextToSpeech("Un supplément ? avec votre pizza " + contextDTO.getParameters().get("BASE") + " ?");
        simpleResponsesDTO.setSimpleResponses(List.of(simpleResponseDTO));
        messageSimpleResponse.setSimpleResponses(simpleResponsesDTO);
        messageDTOList.add(messageSimpleResponse);


        MessageDTO messageDTO2 = new MessageDTO();
        messageDTO2.setPlatform("ACTIONS_ON_GOOGLE");
        SuggestionsDTO suggestionsDTO = new SuggestionsDTO();

        SuggestionDTO suggestionDTOoui = new SuggestionDTO();
        suggestionDTOoui.setTitle("oui");
        suggestionsDTO.addSuggestion(suggestionDTOoui);

        SuggestionDTO suggestionDTOnon = new SuggestionDTO();
        suggestionDTOnon.setTitle("non");
        suggestionsDTO.addSuggestion(suggestionDTOnon);

        messageDTO2.setSuggestions(suggestionsDTO);
        messageDTOList.add(messageDTO2);

        webhookReponseDTO.setFulfillmentMessages(messageDTOList);

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
        PizzaDTO pizzaSAUMON = new PizzaDTO();
        pizzaSAUMON.setName("SAUMON");
        pizzaSAUMON.setPrice("20,00€");
        pizzaSAUMON.setImage("https://www.yumelise.fr/wp-content/uploads/2021/06/pizza-saumon-fume-500x500.jpg");
        pizzaCreme.add(pizzaSAUMON);

        PizzaDTO pizzaHAWAIENNE = new PizzaDTO();
        pizzaHAWAIENNE.setName("HAWAIENNE");
        pizzaHAWAIENNE.setPrice("25,00€");
        pizzaHAWAIENNE.setImage("https://assets.afcdn.com/recipe/20170328/63885_w1024h576c1cx1500cy1000.webp");
        pizzaCreme.add(pizzaHAWAIENNE);

        List<PizzaDTO> pizzaSucre = new ArrayList<>();
        PizzaDTO pizzaNUTELLA = new PizzaDTO();
        pizzaNUTELLA.setName("NUTELLA");
        pizzaNUTELLA.setPrice("27,00€");
        pizzaNUTELLA.setImage("https://cdn.shopify.com/s/files/1/0571/4136/2868/articles/four_a_pizza_recette_giuliz_nutella_noisettes_1200x1200.jpg?v=1637559828");
        pizzaSucre.add(pizzaNUTELLA);

        PizzaDTO pizzaFruit = new PizzaDTO();
        pizzaFruit.setName("Fruit");
        pizzaFruit.setPrice("30,00€");
        pizzaFruit.setImage("https://www.fashioncooking.fr/wp-content/uploads/2020/06/sweet-pizza-aux-fruits.jpg");
        pizzaSucre.add(pizzaFruit);


        List<DessertDTO> dessertDTOS = new ArrayList<>();
        DessertDTO dessertBrownie = new DessertDTO();
        dessertBrownie.setName("Brownie");
        dessertBrownie.setPrice("3€");
        dessertBrownie.setImage("https://lalignegourmande.fr/wp-content/uploads/2020/09/BROWNIE-FULL-CHOCOLATE-scaled.jpg");
        dessertDTOS.add(dessertBrownie);

        DessertDTO dessertTiramisu = new DessertDTO();
        dessertBrownie.setName("Tiramisu");
        dessertBrownie.setPrice("4€");
        dessertBrownie.setImage("https://www.galbani.fr/wp-content/uploads/2017/07/le_veritable_tiramisu_par_il_gusto_italiano_0.png");
        dessertDTOS.add(dessertTiramisu);


        if (base.equals("oui")) {

            WebhookReponseDTO webhookReponseDTO = new WebhookReponseDTO();

            List<MessageDTO> messageDTOList = new ArrayList<>();
            MessageDTO messageSimpleResponse = new MessageDTO();
            messageSimpleResponse.setPlatform("ACTIONS_ON_GOOGLE");
            SimpleResponsesDTO simpleResponsesDTO = new SimpleResponsesDTO();
            SimpleResponseDTO simpleResponseDTO = new SimpleResponseDTO();
            simpleResponseDTO.setTextToSpeech("Voici les desserts que je te propose !!");
            simpleResponsesDTO.setSimpleResponses(List.of(simpleResponseDTO));
            messageSimpleResponse.setSimpleResponses(simpleResponsesDTO);
            messageDTOList.add(messageSimpleResponse);

            MessageDTO messageCarousel = new MessageDTO();
            messageCarousel.setPlatform("ACTIONS_ON_GOOGLE");
            CarouselSelectDTO carouselSelectDTO = new CarouselSelectDTO();
            List<ItemDTO> itemDTOList = new ArrayList<>();

            for (DessertDTO dessertDTO : dessertDTOS) {
                ItemDTO itemDTO = new ItemDTO();
                SelectItemInfoDTO selectItemInfoDTO = new SelectItemInfoDTO();
                selectItemInfoDTO.setKey(dessertDTO.getName().toString());
                selectItemInfoDTO.setSynonyms(List.of(dessertDTO.getName()));
                itemDTO.setTitle(dessertDTO.getName());
                itemDTO.setDescription(dessertDTO.getPrice());
                ImageDTO imageDTO = new ImageDTO();
                imageDTO.setImageUri(dessertDTO.getImage());
                imageDTO.setAccessibilityText(dessertDTO.getName());
                itemDTO.setImage(imageDTO);
                itemDTOList.add(itemDTO);
            }
            carouselSelectDTO.setItems(itemDTOList);
            messageCarousel.setCarouselSelect(carouselSelectDTO);
            messageDTOList.add(messageCarousel);

            webhookReponseDTO.setFulfillmentMessages(messageDTOList);
            return webhookReponseDTO;
        } else if (base.equals("non")) {
            WebhookReponseDTO webhookReponseDTO = new WebhookReponseDTO();
            //TODO calcul
            return webhookReponseDTO;
        } else {
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
            } else if (base.equals("creme")) {
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

