package fr.ab.MovieAssistant.DTO;

public class ButtonDTO {
    private String title;
    private OpenUriActionDTO openUriAction;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public OpenUriActionDTO getOpenUriAction() {
        return openUriAction;
    }

    public void setOpenUriAction(OpenUriActionDTO openUriAction) {
        this.openUriAction = openUriAction;
    }
}
