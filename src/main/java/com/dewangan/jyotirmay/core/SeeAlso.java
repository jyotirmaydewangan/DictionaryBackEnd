package com.dewangan.jyotirmay.core;

import javax.persistence.*;

/**
 * Created by jyotirmay.d on 05/05/18.
 */
@Entity
@Table(name = "seeAlso")
public class SeeAlso {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "wordId")
    private Word word;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "similarWordId")
    private Word similarWord;

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

    public Word getSimilarWord() {
        return similarWord;
    }

    public void setSimilarWord(Word similarWord) {
        this.similarWord = similarWord;
    }


}
