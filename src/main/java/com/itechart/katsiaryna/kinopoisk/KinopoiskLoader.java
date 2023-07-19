package com.itechart.katsiaryna.kinopoisk;

import java.util.Scanner;

public class KinopoiskLoader {
    public static void main(String[] args) {

        System.out.println("Выберите способ поиска фильма: ");
        Scanner sc = new Scanner(System.in);
        KinoFactory kinoFactory = new KinoFactory();
        String type = sc.nextLine();

        Loader loader = kinoFactory.getLoader(SearchType.valueOf(type));
        loader.findMovie();

        Film film = loader.findMovie();
        System.out.println(film);

    }
}