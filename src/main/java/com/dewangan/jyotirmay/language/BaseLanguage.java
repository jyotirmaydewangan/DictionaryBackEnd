package com.dewangan.jyotirmay.language;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Created by jyotirmay.d on 10/11/17.
 */
public interface BaseLanguage {
    public Integer getId();
    public Integer getWordId();
    public String getTargetWord();
    public String getPartOfSpeech();
}
