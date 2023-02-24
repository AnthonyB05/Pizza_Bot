package fr.ab.MovieAssistant.DTO.Request;

import java.io.Serializable;
import java.util.List;

public class QueryResultDTO  implements Serializable {
    private String queryText;
    private ParameterDTO parameters;
    private List<ContextDTO> outputContexts;

    public String getQueryText() {
        return queryText;
    }

    public void setQueryText(String queryText) {
        this.queryText = queryText;
    }

    public ParameterDTO getParameters() {
        return parameters;
    }

    public void setParameters(ParameterDTO parameters) {
        this.parameters = parameters;
    }

    public List<ContextDTO> getOutputContexts() {
        return outputContexts;
    }

    public void setOutputContexts(List<ContextDTO> outputContexts) {
        this.outputContexts = outputContexts;
    }
}
