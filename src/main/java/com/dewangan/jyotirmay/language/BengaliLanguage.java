/**
 * Created by jyotirmay.d on 20/01/18.
 */
package com.dewangan.jyotirmay.language;

import org.hibernate.annotations.NamedQueries;
import org.hibernate.annotations.NamedQuery;
import javax.persistence.*;

/**
 * Created by jyotirmay.d on 10/11/17.
 */
@Entity
@Table(name = "bengali")
@NamedQueries({
        @NamedQuery(name = "findBengaliWordByWordId", query = "from BengaliLanguage h where h.wordId = :wordId"),
        @NamedQuery(name = "findBengaliWordByWord", query = "from BengaliLanguage h where h.targetWord = :word"),
        @NamedQuery(name = "findTopBengaliWordByWordId", query = "from BengaliLanguage h where h.wordId = :wordId Order By h.id asc"),
        @NamedQuery(name = "findTopNonTextBengaliWordByWordId", query = "from BengaliLanguage h where h.partOfSpeech != :partOfSpeech and h.wordId = :wordId Order By h.id asc"),
        @NamedQuery(name = "findBengaliWordList", query = "select distinct w.targetWord from BengaliLanguage w where w.targetWord like :begin and w.targetWord not like :space")
})
public class BengaliLanguage implements BaseLanguage {
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
