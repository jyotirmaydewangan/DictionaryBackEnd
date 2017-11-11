package com.dewangan.jyotirmay.util;

/**
 * Created by jyotirmay.d on 10/11/17.
 */
public enum Language {
    HINDI("hindi"),
    URDU("urdu");

    private String language;

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    Language(String language) {
        this.language = language;
    }

    public static Language getLanguage(String language) {
        try {
            return valueOf(language);
        } catch (Exception ex) {
            return null;
        }
    }
}
