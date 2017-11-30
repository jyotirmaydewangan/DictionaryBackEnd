package com.dewangan.jyotirmay.core;

/**
 * Created by jyotirmay.d on 10/11/17.
 */

import org.hibernate.annotations.NamedQueries;
import org.hibernate.annotations.NamedQuery;

import javax.persistence.*;


@Entity
@Table(name = "lexicalLinks")
@NamedQueries({
        @NamedQuery(name = "findLexicalLinkSynsetId", query = "from LexicalLink s where s.synset1Id = :synsetId and s.relatedBy in :relatedByList"),
        @NamedQuery(name = "findLexicalLinkByWordId", query = "from LexicalLink s where s.word1Id = :wordId and s.relatedBy in :relatedByList")
})
public class LexicalLink {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer synset1Id;
    private Integer word1Id;
    private Integer synset2Id;
    private Integer word2Id;
    private String relatedBy;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getSynset1Id() {
        return synset1Id;
    }

    public void setSynset1Id(Integer synset1Id) {
        this.synset1Id = synset1Id;
    }

    public Integer getWord1Id() {
        return word1Id;
    }

    public void setWord1Id(Integer word1Id) {
        this.word1Id = word1Id;
    }

    public Integer getSynset2Id() {
        return synset2Id;
    }

    public void setSynset2Id(Integer synset2Id) {
        this.synset2Id = synset2Id;
    }

    public Integer getWord2Id() {
        return word2Id;
    }

    public void setWord2Id(Integer word2Id) {
        this.word2Id = word2Id;
    }

    public String getRelatedBy() {
        return relatedBy;
    }

    public void setRelatedBy(String relatedBy) {
        this.relatedBy = relatedBy;
    }

}
