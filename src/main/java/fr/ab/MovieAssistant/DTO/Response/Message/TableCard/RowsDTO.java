package fr.ab.MovieAssistant.DTO.Response.Message.TableCard;

import java.util.List;

public class RowsDTO {
    private List<CellDTO> cells;
    private Boolean dividerAfter;

    public List<CellDTO> getCells() {
        return cells;
    }

    public void setCells(List<CellDTO> cells) {
        this.cells = cells;
    }

    public Boolean getDividerAfter() {
        return dividerAfter;
    }

    public void setDividerAfter(Boolean dividerAfter) {
        this.dividerAfter = dividerAfter;
    }
}
