package com.dewangan.jyotirmay.language;

import org.hibernate.annotations.NamedQueries;
import org.hibernate.annotations.NamedQuery;

import javax.persistence.*;

/**
 * Created by jyotirmay.d on 10/11/17.
 */
@Entity
@Table(name = "urdu")
@NamedQueries({
        @NamedQuery(name = "findUrduWordByWordId", query = "from UrduLanguage u where u.wordId = :wordId"),
        @NamedQuery(name = "findUrduWordByWord", query = "from UrduLanguage u where u.targetWord = :word"),
        @NamedQuery(name = "findTopUrduWordByWordId", query = "from UrduLanguage u where u.wordId = :wordId Order By u.id asc"),
        @NamedQuery(name = "findTopNonTextUrduWordByWordId", query = "from UrduLanguage u where u.partOfSpeech != :partOfSpeech and u.wordId = :wordId Order By u.id asc")
})
public class UrduLanguage implements BaseLanguage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer id;
    public Integer wordId;
    public String targetWord;
    public String partOfSpeech;

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

    public String getTargetWord() {
        return targetWord;
    }

    public void setTargetWord(String targetWord) {
        this.targetWord = targetWord;
    }

    public String getPartOfSpeech() {
        return partOfSpeech;
    }

    public void setPartOfSpeech(String partOfSpeech) {
        this.partOfSpeech = partOfSpeech;
    }
}
