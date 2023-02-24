package fr.ab.MovieAssistant.DTO.Response;

import fr.ab.MovieAssistant.DTO.Request.ContextDTO;
import fr.ab.MovieAssistant.DTO.Response.Message.MessageDTO;

import java.io.Serializable;
import java.util.List;

public class WebhookReponseDTO implements Serializable {
    private String fulfillmentText;
    private List<MessageDTO> fulfillmentMessages;
    private String source;
    private String payload;
    private ContextDTO outputContexts;
    private String followupEventInput;
    private String sessionEntityTypes;

    public String getFulfillmentText() {
        return fulfillmentText;
    }

    public void setFulfillmentText(String fulfillmentText) {
        this.fulfillmentText = fulfillmentText;
    }

    public List<MessageDTO> getFulfillmentMessages() {
        return fulfillmentMessages;
    }

    public void setFulfillmentMessages(List<MessageDTO> fulfillmentMessages) {
        this.fulfillmentMessages = fulfillmentMessages;
    }

    public String getPayload() {
        return payload;
    }

    public void setPayload(String payload) {
        this.payload = payload;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public ContextDTO getOutputContexts() {
        return outputContexts;
    }

    public void setOutputContexts(ContextDTO outputContexts) {
        this.outputContexts = outputContexts;
    }

    public String getFollowupEventInput() {
        return followupEventInput;
    }

    public void setFollowupEventInput(String followupEventInput) {
        this.followupEventInput = followupEventInput;
    }

    public String getSessionEntityTypes() {
        return sessionEntityTypes;
    }

    public void setSessionEntityTypes(String sessionEntityTypes) {
        this.sessionEntityTypes = sessionEntityTypes;
    }

    @Override
    public String toString() {
        return "WebhookReponseDTO{" +
                "fulfillmentText='" + fulfillmentText + '\'' +
                ", fulfillmentMessages='" + fulfillmentMessages + '\'' +
                ", source='" + source + '\'' +
                ", payload=" + payload +
                ", outputContexts='" + outputContexts + '\'' +
                ", followupEventInput='" + followupEventInput + '\'' +
                ", sessionEntityTypes='" + sessionEntityTypes + '\'' +
                '}';
    }
}
