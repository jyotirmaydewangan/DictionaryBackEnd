package com.dewangan.jyotirmay.language;

import com.dewangan.jyotirmay.core.Word;
import org.hibernate.annotations.NamedQueries;
import org.hibernate.annotations.NamedQuery;

import javax.persistence.*;

/**
 * Created by jyotirmay.d on 10/11/17.
 */
@Entity
@Table(name = "urdu")
@NamedQueries({
       @NamedQuery(name = "findUrduWordByWord", query = "from UrduLanguage u where u.engWord.englishWord = :word and u.partOfSpeech != 'invalid'"),
       @NamedQuery(name = "findEnglishWordByUrduWord", query = "from UrduLanguage h where h.targetWord = :word"),
       @NamedQuery(name = "findUrduWordList", query = "select distinct w.targetWord from UrduLanguage w where w.targetWord like :begin and w.targetWord not like :space"),
       @NamedQuery(name = "findUrduWordListAutoList", query = "select distinct w.targetWord from UrduLanguage w where w.targetWord like :begin")

})
public class UrduLanguage implements BaseLanguage {
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
