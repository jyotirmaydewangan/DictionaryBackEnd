package com.dewangan.jyotirmay.core;

import javax.persistence.*;

/**
 * Created by jyotirmay.d on 05/05/18.
 */
@Entity
@Table(name = "Synonyms")
public class Synonym {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "wordId")
    private Word word;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "synonymWordId")
    private Word synonymWord;

    private String partOfSpeech;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Word getWord() {
        return word;
    }

    public void setWord(Word word) {
        this.word = word;
    }

    public Word getSynonymWord() {
        return synonymWord;
    }

    public void setSynonymWord(Word synonymWord) {
        this.synonymWord = synonymWord;
    }

    public String getPartOfSpeech() {
        return partOfSpeech;
    }

    public void setPartOfSpeech(String partOfSpeech) {
        this.partOfSpeech = partOfSpeech;
    }
}
