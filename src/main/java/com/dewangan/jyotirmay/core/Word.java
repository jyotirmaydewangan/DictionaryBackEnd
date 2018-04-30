package com.dewangan.jyotirmay.core;

import org.hibernate.annotations.NamedQueries;
import org.hibernate.annotations.NamedQuery;

import javax.persistence.*;

/**
 * Created by jyotirmay.d on 26/01/18.
 */
@Entity
@Table(name = "words")
@NamedQueries({
        @NamedQuery(name = "findWordList", query = "from Word w where w.lemma like :begin and w.lemma not like :space")
})
public class Word {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer wordid;
    private String lemma;

    public Integer getWordid() {
        return wordid;
    }

    public void setWordid(Integer wordid) {
        this.wordid = wordid;
    }

    public String getLemma() {
        return lemma;
    }

    public void setLemma(String lemma) {
        this.lemma = lemma;
    }
}
