package com.dewangan.jyotirmay.response;

import java.util.Objects;
import java.util.Set;

/**
 * Created by jyotirmay.d on 10/11/17.
 */
public class CompleteWordNode implements BaseWordNode {
    private Integer wordId;
    private String word;
    private String tWord;
    private String partOfSpeech;
    private String definition;
    private Set<String> exmples;

    public String gettWord() {
        return tWord;
    }

    public void settWord(String tWord) {
        this.tWord = tWord;
    }

    public Set<String> getExmples() {
        return exmples;
    }

    public void setExmples(Set<String> exmples) {
        this.exmples = exmples;
    }

    public String getDefinition() {
        return definition;
    }

    public void setDefinition(String definition) {
        this.definition = definition;
    }

    public Integer getWordId() {
        return wordId;
    }

    public void setWordId(Integer wordId) {
        this.wordId = wordId;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public String getPartOfSpeech() {
        return partOfSpeech;
    }

    public void setPartOfSpeech(String partOfSpeech) {
        this.partOfSpeech = partOfSpeech;
    }


    @Override
    public boolean equals(Object o) {

        if (o == this) return true;
        if (!(o instanceof CompleteWordNode)) {
            return false;
        }
        CompleteWordNode wordNode = (CompleteWordNode)o;
        return  Objects.equals(word, wordNode.getWord()) &&
                Objects.equals(partOfSpeech, wordNode.getPartOfSpeech());
    }

    @Override
    public int hashCode() {
        return Objects.hash(word, partOfSpeech);
    }
}
