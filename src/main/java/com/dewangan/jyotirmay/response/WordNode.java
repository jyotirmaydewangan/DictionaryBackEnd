package com.dewangan.jyotirmay.response;

import java.util.Objects;

/**
 * Created by jyotirmay.d on 10/11/17.
 */
public class WordNode implements BaseWordNode{
    private Integer wordId;
    private String word;
    private String tWord;
    private String partOfSpeech;

    public String gettWord() {
        return tWord;
    }

    public void settWord(String tWord) {
        this.tWord = tWord;
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
        if (!(o instanceof WordNode)) {
            return false;
        }
        WordNode wordNode = (WordNode)o;
        return  Objects.equals(word, wordNode.getWord()) &&
                Objects.equals(partOfSpeech, wordNode.getPartOfSpeech());
    }

    @Override
    public int hashCode() {
        return Objects.hash(word, partOfSpeech);
    }
}
