package api;

public class Results {
    public String formattedAddress;
    public Geometry geometry;

    public double getLat() {
        return geometry.getLocation().getLat();
    }

    public void setLat(double lat) {
        geometry.getLocation().setLat(lat);
    }

    public double getLng() {
        return geometry.getLocation().getLng();
    }

    public void setLng(double lng) {
        geometry.getLocation().setLng(lng);
    }

    public String getFormattedAddress() {
        return formattedAddress;
    }

    public void setFormattedAddress(String formattedAddress) {
        this.formattedAddress = formattedAddress;
    }
}
