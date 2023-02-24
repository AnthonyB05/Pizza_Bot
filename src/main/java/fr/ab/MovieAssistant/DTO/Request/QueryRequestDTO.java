package fr.ab.MovieAssistant.DTO.Request;

import java.io.Serializable;

public class QueryRequestDTO  implements Serializable {
    private QueryResultDTO queryResult;

    public QueryResultDTO getQueryResult() {
        return queryResult;
    }

    public void setQueryResult(QueryResultDTO queryResult) {
        this.queryResult = queryResult;
    }

    @Override
    public String toString() {
        return "QueryRequestDTO{" +
                "queryResult=" + queryResult +
                '}';
    }
}
