package utils;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum Language {
    ENGLISH("EN", "English"),
    RUSSIAN("RU", "Pусский"),
    SPANISH("ES", "Español"),
    GERMAN("DE", "Deutsch");

    public final String code;
    public final String name;
}
