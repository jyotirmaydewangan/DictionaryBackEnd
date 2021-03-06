package com.dewangan.jyotirmay.db.language;

import com.dewangan.jyotirmay.core.Word;
import com.dewangan.jyotirmay.language.BaseLanguage;
import org.hibernate.Query;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jyotirmay.d on 10/11/17.
 */

public interface BaseLanguageDAO {
    public List<BaseLanguage> findTargetWordByWordId(Integer wordId);
    public List<BaseLanguage> findTargetWordByWord(String word);
    public BaseLanguage findTopTargetWordByWordId(Integer wordId);
    public BaseLanguage findTopNonTextTargetWordByWordId(Integer wordId);
    public List<String> findWordList(String ch, Integer start);
    public Integer findWordCount(String ch);
}
