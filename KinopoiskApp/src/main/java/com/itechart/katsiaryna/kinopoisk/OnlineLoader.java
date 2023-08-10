package com.itechart.katsiaryna.kinopoisk;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Properties;
import java.util.Scanner;

public class OnlineLoader implements Loader {
    @Override
    public Film findMovie() {
        Properties properties = new Properties();
        Scanner sc = new Scanner(System.in);
        System.out.println("Введите ID фильма: ");
        int filmId = sc.nextInt();
        System.out.println("film id is " + filmId);
        try (InputStream inputStream = getClass().getResourceAsStream("/application.properties")) {
            properties.load(inputStream);
            String apiKey = properties.getProperty("apiKey");
            String apiUrl = properties.getProperty("apiUrl");

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

