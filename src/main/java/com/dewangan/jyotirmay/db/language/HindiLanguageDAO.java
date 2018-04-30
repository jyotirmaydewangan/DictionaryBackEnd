package com.dewangan.jyotirmay.db.language;

import com.dewangan.jyotirmay.language.BaseLanguage;
import com.dewangan.jyotirmay.language.HindiLanguage;

import io.dropwizard.hibernate.AbstractDAO;
import org.hibernate.Query;
import org.hibernate.SessionFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jyotirmay.d on 10/11/17.
 */
public class HindiLanguageDAO extends AbstractDAO<HindiLanguage> implements BaseLanguageDAO {

    public HindiLanguageDAO(SessionFactory sessionFactory) {
        super(sessionFactory);
    }


    public List<BaseLanguage> findTargetWordByWordId(Integer wordId) {
        Query getResource = namedQuery("findHindiWordByWordId").setParameter("wordId", wordId);
        return (ArrayList<BaseLanguage>) getResource.list();
    }

    public List<BaseLanguage> findTargetWordByWord(String word) {
        Query getResource = namedQuery("findHindiWordByWord").setParameter("word", word);
        return (ArrayList<BaseLanguage>) getResource.list();
    }

    public BaseLanguage findTopTargetWordByWordId(Integer wordId){
        Query getResource = namedQuery("findTopHindiWordByWordId").setParameter("wordId", wordId).setMaxResults(1);
        return (BaseLanguage) getResource.uniqueResult();
    }

    public BaseLanguage findTopNonTextTargetWordByWordId(Integer wordId){
        String text = "text";
        Query getResource = namedQuery("findTopNonTextHindiWordByWordId").setParameter("wordId", wordId).setParameter("partOfSpeech", text).setMaxResults(1);
        return (BaseLanguage) getResource.uniqueResult();
    }

    public List<String> findWordList(String ch, Integer start) {
        Query getResource = namedQuery("findHindiWordList")
                .setParameter("begin", ch+"%")
                .setParameter("space", "% %")
                .setFirstResult(start).setMaxResults(80);

        return (ArrayList<String>) getResource.list();
    }

    public Integer findWordCount(String ch) {
        Query getResource = namedQuery("findHindiWordList").setParameter("begin", ch+"%").setParameter("space", "% %");
        return getResource.list().size();
    }
}
