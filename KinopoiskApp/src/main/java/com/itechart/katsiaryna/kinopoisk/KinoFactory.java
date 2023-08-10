package com.itechart.katsiaryna.kinopoisk;

public class KinoFactory {
    public Loader getLoader(SearchType type) {
        return switch (type) {
            case ONLINE_SEARCH -> new OnlineLoader();
            case FILE_SEARCH -> new FileLoader();
        };
    }
}
