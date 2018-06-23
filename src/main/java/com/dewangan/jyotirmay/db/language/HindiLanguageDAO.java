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

    public List<BaseLanguage> findTargetWordByWord(String word) {
        Query getResource = namedQuery("findHindiWordByWord").setParameter("word", word);
        return (ArrayList<BaseLanguage>) getResource.list();
    }


    public List<BaseLanguage> findEnglishWordByTargetWord(String word) {
        Query getResource = namedQuery("findEnglishWordByHindiWord").setParameter("word", word);
        return (ArrayList<BaseLanguage>) getResource.list();
    }

    public List<String> findWordList(String ch, Integer start, Integer limit) {
        Query getResource = namedQuery("findHindiWordList")
                .setParameter("begin", ch+"%")
                .setParameter("space", "% %")
                .setFirstResult(start).setMaxResults(limit);

        return (ArrayList<String>) getResource.list();
    }

    public List<String> findWordListAutoList(String ch, Integer start, Integer limit) {
        Query getResource = namedQuery("findHindiWordListAutoList")
                .setParameter("begin", ch+"%")
                .setFirstResult(start).setMaxResults(limit);

        return (ArrayList<String>) getResource.list();
    }

    public Integer findWordCount(String ch) {
        Query getResource = namedQuery("findHindiWordList").setParameter("begin", ch+"%").setParameter("space", "% %");
        return getResource.list().size();
    }
}
