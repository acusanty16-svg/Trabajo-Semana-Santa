package controller;



import com.google.gson.Gson;
import model.Biblioteca;
import model.Libro;
import org.json.JSONArray;
import org.json.JSONObject;


import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class APIController {

    public Biblioteca getAllBooks(){
        String url = "https://stephen-king-api.onrender.com/api/books";
        HttpClient client = null;

        client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest
                .newBuilder()
                .uri(URI.create(url))
                .GET()
                .build();
        try {
            Gson gson = new Gson();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            String stringLibros = response.body();
            JSONObject jsonLibros = new JSONObject(stringLibros);
            JSONArray arrayLibros = jsonLibros.getJSONArray("data");
            List<Libro> listaLibros = new ArrayList<>();
            Biblioteca biblioteca = new Biblioteca();
            for (int i = 0; i < arrayLibros.length(); i++) {
                JSONObject jsonLibro = arrayLibros.getJSONObject(i);
                Libro libro = gson.fromJson(jsonLibro.toString(), Libro.class);
                System.out.printf("[%d\t%s\t%d]%n",libro.getId(),libro.getTitle(),libro.getYear());
                listaLibros.add(libro);
            }
            biblioteca.setTodosLosLibros(listaLibros);
            return biblioteca;
        } catch (Exception e) {
            System.out.println("Error en el procesamiento del HTTP");
            return null;
        }
    }
    public void getInformationFromABook(int id){
        String url = "https://stephen-king-api.onrender.com/api/book/"+id;
        HttpClient client = null;

        client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest
                .newBuilder()
                .uri(URI.create(url))
                .GET()
                .build();
        try {
            Gson gson = new Gson();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            String stringLibros = response.body();
            JSONObject jsonLibros = new JSONObject(stringLibros);
            JSONObject jsonData = jsonLibros.getJSONObject("data");
            Libro libro = gson.fromJson(jsonData.toString(), Libro.class);
            System.out.printf("%s: %s%n", libro.getTitle(),Arrays.toString(libro.getNotes()));

        } catch (Exception e) {
            System.out.println("Error en el procesamiento del HTTP");
        }finally {
            try{
                client.close();
            }catch (NullPointerException e){
                System.out.println("Error en el cerrado");
            }
        }
    }
}
