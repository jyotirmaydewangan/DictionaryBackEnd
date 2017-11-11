package com.dewangan.jyotirmay.core;

/**
 * Created by jyotirmay.d on 09/11/17.
 */
import org.hibernate.annotations.NamedQueries;
import org.hibernate.annotations.NamedQuery;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "semanticLinks")
@NamedQueries({
        @NamedQuery(name = "findSemanticLinkBySynsetId", query = "from SemanticLink s where s.synset1Id = :synsetId")
})
public class SemanticLink {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Integer synset1Id;

    private Integer synset2Id;

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

    public Integer getSynset2Id() {
        return synset2Id;
    }

    public void setSynset2Id(Integer synset2Id) {
        this.synset2Id = synset2Id;
    }

    public String getRelatedBy() {
        return relatedBy;
    }

    public void setRelatedBy(String relatedBy) {
        this.relatedBy = relatedBy;
    }
}
