package com.dewangan.jyotirmay.core;

import org.hibernate.annotations.NamedQueries;
import org.hibernate.annotations.NamedQuery;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "senses")
@NamedQueries({
        @NamedQuery(name = "findSenseByWord", query = "from Sense s where s.word = :word"),
        @NamedQuery(name = "findSenseBySynsetId", query = "from Sense s where s.synsetId = :synsetId"),
        @NamedQuery(name = "findSenseByWordId", query = "from Sense s where s.wordId = :wordId")
})
public class Sense {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Integer wordId;

    private String word;

    private Integer synsetId;

    private String partOfSpeech;

    private String lexdomain;

    private String definition;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public Integer getSynsetId() {
        return synsetId;
    }

    public void setSynsetId(Integer synsetId) {
        this.synsetId = synsetId;
    }

    public String getPartOfSpeech() {
        return partOfSpeech;
    }

    public void setPartOfSpeech(String partOfSpeech) {
        this.partOfSpeech = partOfSpeech;
    }

    public String getLexdomain() {
        return lexdomain;
    }

    public void setLexdomain(String lexdomain) {
        this.lexdomain = lexdomain;
    }

    public String getDefinition() {
        return definition;
    }

    public void setDefinition(String definition) {
        this.definition = definition;
    }
}

