package api;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpRequest.BodyPublishers;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;

import com.google.gson.Gson;

public class Eaties {

    private Places places;
    private Results results;
    private Gson gson;
    String jsonRequest;
    HttpRequest postRequest;

    public Eaties(String text) throws Exception {
        resetFields(text);
    }

    public Eaties() throws Exception {
        results = new Results();
        places = new Places();
        gson = new Gson();
    }

    public HttpRequest makePostRequest(String jsonRequest) throws Exception {
        HttpRequest postRequest = HttpRequest.newBuilder()
                .uri(new URI("https://places.googleapis.com/v1/places:searchText"))
                .header("X-Goog-Api-Key", "YOUR_API_KEY_HERE")
                .header("X-Goog-FieldMask",
                        "places.displayName.text,places.formattedAddress,places.rating,places.priceLevel,places.goodForWatchingSports")
                .POST(BodyPublishers.ofString(jsonRequest))
                .build();
        return postRequest;
    }

    public void run() throws Exception {
        jsonRequest = gson.toJson(places);
        postRequest = makePostRequest(jsonRequest);
        HttpClient httpClient = HttpClient.newHttpClient();
        HttpResponse<String> postResponse = httpClient.send(postRequest, BodyHandlers.ofString());
        places = gson.fromJson(postResponse.body(), Places.class);
    }

    public void resetFields(String text) {
        results = new Results();
        places = new Places();
        places.setTextQuery(text);
        gson = new Gson();
    }

    public Places getPlaces() {
        return places;
    }

    public Results getResults() {
        return results;
    }

    public static void main(String[] args) throws Exception {
        Eaties eaties = new Eaties("Pizza");
        eaties.run();
        Results results = eaties.getResults();
        Places places = eaties.getPlaces();
        System.out.println(results);

        for (Place p : eaties.places.places) {
            System.out.println(p.getFormattedAddress().toString());
        }

    }

}
