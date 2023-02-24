package com.example.pizza_bot.Service;

import com.example.pizza_bot.DTO.Request.QueryRequestDTO;
import com.example.pizza_bot.DTO.Response.Message.MessageDTO;
import com.example.pizza_bot.DTO.Response.Message.SimpleResponse.SimpleResponseDTO;
import com.example.pizza_bot.DTO.Response.Message.SimpleResponse.SimpleResponsesDTO;
import com.example.pizza_bot.DTO.Response.Message.Suggestion.SuggestionDTO;
import com.example.pizza_bot.DTO.Response.Message.Suggestion.SuggestionsDTO;
import com.example.pizza_bot.DTO.Response.WebhookReponseDTO;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PizzaService {

    private final List<String> genres = List.of("Tomate", "Crème", "Sucre");

    public WebhookReponseDTO getPizza(QueryRequestDTO queryRequestDTO) {
        WebhookReponseDTO webhookReponseDTO = new WebhookReponseDTO();
        return webhookReponseDTO;
    }

    public WebhookReponseDTO getbase(Boolean error) {

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
        simpleResponseDTO.setTextToSpeech("Quel base pour votre pizza voulez vous ?");
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
