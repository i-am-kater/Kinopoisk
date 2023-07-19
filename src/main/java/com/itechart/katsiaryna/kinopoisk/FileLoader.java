package com.itechart.katsiaryna.kinopoisk;

import java.io.*;
import java.util.Scanner;
public class FileLoader implements Loader {
    @Override
    public Film findMovie() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Введите название фильма: ");
        String movieName = sc.nextLine();
        System.out.println("Ищу фильм в файле...");

        try (InputStream inputStream = this.getClass().getResourceAsStream("/movies.txt")) {
            assert inputStream != null;
            Scanner scanner = new Scanner(inputStream);
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] filmData = line.split(";"); // Разделение строки с информацией о фильме
                String title = filmData[0].trim();

                if (title.equalsIgnoreCase(movieName)) {
                    Film film = new Film(title, filmData[1].trim(), filmData[2].trim(), filmData[3].trim(), filmData[4].trim());
                    System.out.println(film.toString());
                    break;
                }
            }

            } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
        return null;
    }
}
