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
    public List<BaseLanguage> findTargetWordByWord(String word);
    public List<BaseLanguage> findEnglishWordByTargetWord(String word);
    public List<String> findWordList(String ch, Integer start, Integer limit);
    public List<String> findWordListAutoList(String ch, Integer start, Integer limit);
    public Integer findWordCount(String ch);
}
