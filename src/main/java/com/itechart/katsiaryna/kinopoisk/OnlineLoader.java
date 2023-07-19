package com.itechart.katsiaryna.kinopoisk;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;
public class OnlineLoader implements Loader {
    String apiKey = "5d4a611d-cc1a-48a8-9ba4-8d8254b57c4b";
    String apiUrl = "https://kinopoiskapiunofficial.tech/api/v2.1/films/";

    @Override
    public Film findMovie() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Введите ID фильма: ");
        int filmId = sc.nextInt();
        System.out.println("film id is " + filmId);
        try {
            System.out.println("Ищу фильм в интернете...");
            URL url = new URL(apiUrl + filmId);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("accept", "application/json");
            conn.setRequestProperty("X-API-KEY", apiKey);

            BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));

            String line;
            StringBuilder response = new StringBuilder();
            while ((line = reader.readLine()) != null) {
                response.append(line);
            }
            System.out.println("Загружаю фильм...");

            System.out.println(response);

            System.out.println("Характеристики фильма:");

            String responseString = String.valueOf(response);
            ObjectMapper mapper = new ObjectMapper();
            Film film = mapper.readValue(responseString, Film.class);
            System.out.println(film.toString());

            reader.close();
            conn.disconnect();

        } catch (IOException ex) {
            System.out.println("Ошибка запроса: " + ex.getMessage());
            throw new RuntimeException(ex);
        }
        return null;
    }
}

