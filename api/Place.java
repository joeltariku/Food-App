package api;

public class Place {
    private DisplayName displayName;
    private String formattedAddress;
    private float rating;

    public String getText() {
        return displayName.getText();
    }

    public void setText(String text) {
        displayName.setText(text);
    }

    public String getFormattedAddress() {
        return formattedAddress;
    }

    public void setFormattedAddress(String formattedAddress) {
        this.formattedAddress = formattedAddress;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }
}
