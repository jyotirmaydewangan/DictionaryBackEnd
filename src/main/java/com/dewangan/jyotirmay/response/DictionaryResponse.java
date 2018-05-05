package com.dewangan.jyotirmay.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.*;

/**
 * Created by jyotirmay.d on 05/05/18.
 */

@JsonIgnoreProperties(ignoreUnknown = true)
public class DictionaryResponse {
    private String word;
    private Map<String, List<String>> meaning;
    private Map<String, List<String>> definition;
    private Map<String, List<String>> use;
    private Map<String, List<String>> synonym;
    private List<String> antonym;
    private List<String> seeAlso;

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public Map<String, List<String>> getMeaning() {
        return meaning;
    }

    public void setMeaning(Map<String, List<String>> meaning) {
        this.meaning = meaning;
    }

    public void addMeaning(String pos, String meaning){

        if(this.meaning == null) {
            setMeaning(new HashMap<String, List<String>>());
        }

        List localMeaning = getMeaning().get(pos);

        if(localMeaning == null) {
            localMeaning = new ArrayList<String>();
            getMeaning().put(pos, localMeaning);
        }

        localMeaning.add(meaning);
    }

    public Map<String, List<String>> getDefinition() {
        return definition;
    }

    public void setDefinition(Map<String, List<String>> definition) {
        this.definition = definition;
    }

    public void addDefinition(String pos, String definition){

        if(this.definition == null) {
            setDefinition(new HashMap<String, List<String>>());
        }

        List localDefinition = getDefinition().get(pos);

        if(localDefinition == null) {
            localDefinition = new ArrayList<String>();
            getDefinition().put(pos, localDefinition);
        }

        localDefinition.add(definition);
    }

    public Map<String, List<String>> getUse() {
        return use;
    }

    public void setUse(Map<String, List<String>> use) {
        this.use = use;
    }

    public void addUse(String pos, String use){

        if(this.use == null) {
            setUse(new HashMap<String, List<String>>());
        }

        List localUse = getUse().get(pos);

        if(localUse == null) {
            localUse = new ArrayList<String>();
            getUse().put(pos, localUse);
        }

        localUse.add(use);
    }

    public Map<String, List<String>> getSynonym() {
        return synonym;
    }

    public void setSynonym(Map<String, List<String>> synonym) {
        this.synonym = synonym;
    }

    public void addSynonym(String pos, String synonym){

        if(this.synonym == null) {
            setSynonym(new HashMap<String, List<String>>());
        }

        List localSynonym = getSynonym().get(pos);

        if(localSynonym == null) {
            localSynonym = new ArrayList<String>();
            getSynonym().put(pos, localSynonym);
        }

        localSynonym.add(synonym);
    }

    public List<String> getAntonym() {
        return antonym;
    }

    public void setAntonym(List<String> antonym) {
        this.antonym = antonym;
    }

    public void addAntonym(String antonym){

        if(this.antonym == null) {
            setAntonym(new ArrayList<String>());
        }

        List localAntonym = getAntonym();
        localAntonym.add(antonym);
    }

    public List<String> getSeeAlso() {
        return seeAlso;
    }

    public void setSeeAlso(List<String> seeAlso) {
        this.seeAlso = seeAlso;
    }

    public void addSeeAlso(String seeAlso){

        if(this.seeAlso == null) {
            setSeeAlso(new ArrayList<String>());
        }

        List localSeeAlso = getSeeAlso();
        localSeeAlso.add(seeAlso);
    }
}