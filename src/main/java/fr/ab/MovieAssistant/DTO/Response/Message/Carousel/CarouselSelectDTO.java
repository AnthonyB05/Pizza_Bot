package fr.ab.MovieAssistant.DTO.Response.Message.Carousel;

import java.util.List;

public class CarouselSelectDTO {

    private List<ItemDTO> items;

    public List<ItemDTO> getItems() {
        return items;
    }

    public void setItems(List<ItemDTO> items) {
        this.items = items;
    }
}
