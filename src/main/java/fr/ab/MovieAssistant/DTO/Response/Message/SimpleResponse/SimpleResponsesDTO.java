package fr.ab.MovieAssistant.DTO.Response.Message.SimpleResponse;

import java.util.List;

public class SimpleResponsesDTO {

    private List<SimpleResponseDTO> simpleResponses;

    public List<SimpleResponseDTO> getSimpleResponses() {
        return simpleResponses;
    }

    public void setSimpleResponses(List<SimpleResponseDTO> simpleResponses) {
        this.simpleResponses = simpleResponses;
    }
}
