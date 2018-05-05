package com.dewangan.jyotirmay.core;

import javax.persistence.*;

/**
 * Created by jyotirmay.d on 05/05/18.
 */
@Entity
@Table(name = "Antonyms")
public class Antonym {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "wordId")
    private Word word;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "antonymWordId")
    private Word antonymWord;

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

    public Word getAntonymWord() {
        return antonymWord;
    }

    public void setAntonymWord(Word antonymWord) {
        this.antonymWord = antonymWord;
    }
}
