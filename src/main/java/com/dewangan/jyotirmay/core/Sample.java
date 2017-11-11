package com.dewangan.jyotirmay.core;

import org.hibernate.annotations.NamedQueries;
import org.hibernate.annotations.NamedQuery;

import javax.persistence.*;

/**
 * Created by jyotirmay.d on 10/11/17.
 */

/*
`id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `synset_id` int(10) unsigned NOT NULL DEFAULT '0',
  `sample_seq` smallint(5) unsigned NOT NULL DEFAULT '0',
  `sample` mediumtext NOT NULL,
 */
@Entity
@Table(name = "samples")
@NamedQueries({
        @NamedQuery(name = "findSampleBySynsetId", query = "from Sample s where s.synsetId = :synsetId")
})
public class Sample {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer synsetId;
    private Integer sampleSeq;
    private String sample;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getSynsetId() {
        return synsetId;
    }

    public void setSynsetId(Integer synsetId) {
        this.synsetId = synsetId;
    }

    public Integer getSampleSeq() {
        return sampleSeq;
    }

    public void setSampleSeq(Integer sampleSeq) {
        this.sampleSeq = sampleSeq;
    }

    public String getSample() {
        return sample;
    }

    public void setSample(String sample) {
        this.sample = sample;
    }
}
