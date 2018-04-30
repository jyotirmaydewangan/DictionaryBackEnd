package com.dewangan.jyotirmay.language;

import org.hibernate.annotations.NamedQueries;
import org.hibernate.annotations.NamedQuery;

import javax.persistence.*;

/**
 * Created by jyotirmay.d on 10/11/17.
 */
@Entity
@Table(name = "hindi")
@NamedQueries({
        @NamedQuery(name = "findHindiWordByWordId", query = "from HindiLanguage h where h.wordId = :wordId"),
        @NamedQuery(name = "findHindiWordByWord", query = "from HindiLanguage h where h.targetWord = :word"),
        @NamedQuery(name = "findTopHindiWordByWordId", query = "from HindiLanguage h where h.wordId = :wordId Order By h.id asc"),
        @NamedQuery(name = "findTopNonTextHindiWordByWordId", query = "from HindiLanguage h where h.partOfSpeech != :partOfSpeech and h.wordId = :wordId Order By h.id asc"),
        @NamedQuery(name = "findHindiWordList", query = "select distinct w.targetWord from HindiLanguage w where w.targetWord like :begin and w.targetWord not like :space")
})
public class HindiLanguage implements BaseLanguage {
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
