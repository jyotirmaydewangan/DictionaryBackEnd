package com.dewangan.jyotirmay.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.*;

/**
 * Created by jyotirmay.d on 07/11/17.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class MeaningResponse {
    private String englishWord;
    private Map<String, Set<BaseWordNode>> meaning = new HashMap<>();

    public String getEnglishWord() {
        return englishWord;
    }

    public void setEnglishWord(String englishWord) {
        this.englishWord = englishWord;
    }

    public Map<String, Set<BaseWordNode>> getMeaning() {
        return meaning;
    }

    public void setMeaning(Map<String, Set<BaseWordNode>> meaning) {
        this.meaning = meaning;
    }
}