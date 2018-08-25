package com.dewangan.jyotirmay.core;

import org.hibernate.annotations.NamedQueries;
import org.hibernate.annotations.NamedQuery;

import javax.persistence.*;

/**
 * Created by jyotirmay.d on 25/08/18.
 */
@Entity
@Table(name = "analysis")
public class Analysis {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "wordId")
    private Word engWord;

    private Integer tCount;

    private Integer dCount;

    private Integer syCount;

    private Integer saCount;

    private Integer value;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Word getEngWord() {
        return engWord;
    }

    public void setEngWord(Word engWord) {
        this.engWord = engWord;
    }

    public Integer gettCount() {
        return tCount;
    }

    public void settCount(Integer tCount) {
        this.tCount = tCount;
    }

    public Integer getdCount() {
        return dCount;
    }

    public void setdCount(Integer dCount) {
        this.dCount = dCount;
    }

    public Integer getSyCount() {
        return syCount;
    }

    public void setSyCount(Integer syCount) {
        this.syCount = syCount;
    }

    public Integer getSaCount() {
        return saCount;
    }

    public void setSaCount(Integer saCount) {
        this.saCount = saCount;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }
}
