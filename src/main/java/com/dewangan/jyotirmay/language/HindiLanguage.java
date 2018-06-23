package com.dewangan.jyotirmay.language;

import com.dewangan.jyotirmay.core.Word;
import org.hibernate.annotations.NamedQueries;
import org.hibernate.annotations.NamedQuery;

import javax.persistence.*;

/**
 * Created by jyotirmay.d on 10/11/17.
 */
@Entity
@Table(name = "Hindi")
@NamedQueries({
        @NamedQuery(name = "findHindiWordByWord", query = "from HindiLanguage h where h.engWord.englishWord = :word"),
        @NamedQuery(name = "findEnglishWordByHindiWord", query = "from HindiLanguage h where h.targetWord = :word"),
        @NamedQuery(name = "findHindiWordList", query = "select distinct w.targetWord from HindiLanguage w where w.targetWord like :begin and w.targetWord not like :space"),
        @NamedQuery(name = "findHindiWordListAutoList", query = "select distinct w.targetWord from HindiLanguage w where w.targetWord like :begin")
})
public class HindiLanguage implements BaseLanguage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "wordId")
    public Word engWord;

    public String targetWord;
    public String partOfSpeech;

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
