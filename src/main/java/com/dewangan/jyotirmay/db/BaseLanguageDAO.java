package com.dewangan.jyotirmay.db;

import com.dewangan.jyotirmay.core.BaseLanguage;

import java.util.List;

/**
 * Created by jyotirmay.d on 10/11/17.
 */

public interface BaseLanguageDAO {
    public List<BaseLanguage> findTargetWordByWordId(Integer wordId);
    public BaseLanguage findTopTargetWordByWordId(Integer wordId);
}
