package com.dewangan.jyotirmay.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.*;

/**
 * Created by jyotirmay.d on 05/05/18.
 */

@JsonIgnoreProperties(ignoreUnknown = true)
public class DictionaryResponse {
    private String word;
    private Map<String, Set<String>> meaning;
    private Map<String, Set<String>> definition;
    private Map<String, Set<String>> use;
    private Map<String, Set<String>> synonym;
    private Set<String> antonym;
    private Set<String> seeAlso;

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public Map<String, Set<String>> getMeaning() {
        return meaning;
    }

    public void setMeaning(Map<String, Set<String>> meaning) {
        this.meaning = meaning;
    }

    public void addMeaning(String pos, String meaning){

        if(this.meaning == null) {
            setMeaning(new HashMap<String, Set<String>>());
        }

        Set localMeaning = getMeaning().get(pos);

        if(localMeaning == null) {
            localMeaning = new HashSet<String>();
            getMeaning().put(pos, localMeaning);
        }

        localMeaning.add(meaning);
    }

    public Map<String, Set<String>> getDefinition() {
        return definition;
    }

    public void setDefinition(Map<String, Set<String>> definition) {
        this.definition = definition;
    }

    public void addDefinition(String pos, String definition){

        if(this.definition == null) {
            setDefinition(new HashMap<String, Set<String>>());
        }

        Set localDefinition = getDefinition().get(pos);

        if(localDefinition == null) {
            localDefinition = new HashSet<String>();
            getDefinition().put(pos, localDefinition);
        }

        localDefinition.add(definition);
    }

    public Map<String, Set<String>> getUse() {
        return use;
    }

    public void setUse(Map<String, Set<String>> use) {
        this.use = use;
    }

    public void addUse(String pos, String use){

        if(this.use == null) {
            setUse(new HashMap<String, Set<String>>());
        }

        Set localUse = getUse().get(pos);

        if(localUse == null) {
            localUse = new HashSet<String>();
            getUse().put(pos, localUse);
        }

        localUse.add(use);
    }

    public Map<String, Set<String>> getSynonym() {
        return synonym;
    }

    public void setSynonym(Map<String, Set<String>> synonym) {
        this.synonym = synonym;
    }

    public void addSynonym(String pos, String synonym){

        if(this.synonym == null) {
            setSynonym(new HashMap<String, Set<String>>());
        }

        Set localSynonym = getSynonym().get(pos);

        if(localSynonym == null) {
            localSynonym = new HashSet<String>();
            getSynonym().put(pos, localSynonym);
        }

        localSynonym.add(synonym);
    }

    public Set<String> getAntonym() {
        return antonym;
    }

    public void setAntonym(Set<String> antonym) {
        this.antonym = antonym;
    }

    public void addAntonym(String antonym){

        if(this.antonym == null) {
            setAntonym(new HashSet<String>());
        }

        Set localAntonym = getAntonym();
        localAntonym.add(antonym);
    }

    public Set<String> getSeeAlso() {
        return seeAlso;
    }

    public void setSeeAlso(Set<String> seeAlso) {
        this.seeAlso = seeAlso;
    }

    public void addSeeAlso(String seeAlso){

        if(this.seeAlso == null) {
            setSeeAlso(new HashSet<String>());
        }

        Set localSeeAlso = getSeeAlso();
        localSeeAlso.add(seeAlso);
    }
}