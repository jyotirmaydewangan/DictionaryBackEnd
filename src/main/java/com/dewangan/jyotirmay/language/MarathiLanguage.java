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
@Table(name = "marathi")
@NamedQueries({
        @NamedQuery(name = "findMarathiWordByWordId", query = "from MarathiLanguage h where h.wordId = :wordId"),
        @NamedQuery(name = "findMarathiWordByWord", query = "from MarathiLanguage h where h.targetWord = :word"),
        @NamedQuery(name = "findTopMarathiWordByWordId", query = "from MarathiLanguage h where h.wordId = :wordId Order By h.id asc"),
        @NamedQuery(name = "findTopNonTextMarathiWordByWordId", query = "from MarathiLanguage h where h.partOfSpeech != :partOfSpeech and h.wordId = :wordId Order By h.id asc")
})
public class MarathiLanguage implements BaseLanguage {
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
