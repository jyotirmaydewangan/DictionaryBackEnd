package com.dewangan.jyotirmay.core;

import org.hibernate.annotations.NamedQueries;
import org.hibernate.annotations.NamedQuery;

import javax.persistence.*;
import java.util.List;

/**
 * Created by jyotirmay.d on 26/01/18.
 */
@Entity
@Table(name = "Words")
@NamedQueries({
        @NamedQuery(name = "findWordByWord", query = "from Word w where w.englishWord = :word"),
        @NamedQuery(name = "findWordList", query = "from Word w where w.englishWord like :begin and w.englishWord not like :space"),
        @NamedQuery(name = "findSortedWordList", query = "from Word w where w.englishWord like :begin order by w.analysis.id asc"),
        @NamedQuery(name = "findWordListAutoList", query = "from Word w where w.englishWord like :begin")
})
public class Word {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String englishWord;

    @OneToOne(fetch = FetchType.LAZY, mappedBy = "engWord")
    private Analysis analysis;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "word")
    private List<Definition> definitions;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "word")
    private List<Synonym> synonyms;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "word")
    private List<Antonym> antonyms;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "word")
    private List<SeeAlso> seeAlso;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEnglishWord() {
        return englishWord;
    }

    public void setEnglishWord(String englishWord) {
        this.englishWord = englishWord;
    }

    public List<Definition> getDefinitions() {
        return definitions;
    }

    public void setDefinitions(List<Definition> definitions) {
        this.definitions = definitions;
    }

    public List<Synonym> getSynonyms() {
        return synonyms;
    }

    public void setSynonyms(List<Synonym> synonyms) {
        this.synonyms = synonyms;
    }

    public List<Antonym> getAntonyms() {
        return antonyms;
    }

    public void setAntonyms(List<Antonym> antonyms) {
        this.antonyms = antonyms;
    }

    public List<SeeAlso> getSeeAlso() {
        return seeAlso;
    }

    public void setSeeAlso(List<SeeAlso> seeAlso) {
        this.seeAlso = seeAlso;
    }

    public Analysis getAnalysis() {
        return analysis;
    }

    public void setAnalysis(Analysis analysis) {
        this.analysis = analysis;
    }
}
