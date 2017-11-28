package com.dewangan.jyotirmay.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.*;

/**
 * Created by jyotirmay.d on 07/11/17.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class MeaningResponse {
    private String toWord;
    private Set<BaseWordNode> targetWord;
    private Map<Integer, Set<BaseWordNode>> synonym;
    private Map<String, Set<BaseWordNode>> relatedBy = new HashMap();

    public Set<BaseWordNode> getTargetWord() {
        return targetWord;
    }

    public void setTargetWord(Set<BaseWordNode> targetWord) {
        this.targetWord = targetWord;
    }

    public Map<Integer, Set<BaseWordNode>> getSynonym() {
        return synonym;
    }

    public void setSynonym(Map<Integer, Set<BaseWordNode>> synonym) {
        this.synonym = synonym;
    }

    public String getToWord() {
        return toWord;
    }

    public void setToWord(String toWord) {
        this.toWord = toWord;
    }

    public Map<String, Set<BaseWordNode>> getRelatedBy() {
        return relatedBy;
    }

    public void setRelatedBy(Map<String, Set<BaseWordNode>> relatedBy) {
        this.relatedBy = relatedBy;
    }
}