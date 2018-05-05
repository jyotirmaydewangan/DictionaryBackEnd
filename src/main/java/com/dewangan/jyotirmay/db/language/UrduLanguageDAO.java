package com.dewangan.jyotirmay.db.language;

import com.dewangan.jyotirmay.language.BaseLanguage;
import com.dewangan.jyotirmay.language.UrduLanguage;
import io.dropwizard.hibernate.AbstractDAO;
import org.hibernate.Query;
import org.hibernate.SessionFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jyotirmay.d on 10/11/17.
 */
public class UrduLanguageDAO extends AbstractDAO<UrduLanguage> implements BaseLanguageDAO {
    public UrduLanguageDAO(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    public List<BaseLanguage> findTargetWordByWord(String word) {
        Query getResource = namedQuery("findUrduWordByWord").setParameter("word", word);
        return (ArrayList<BaseLanguage>) getResource.list();
    }

    public List<BaseLanguage> findEnglishWordByTargetWord(String word) {
        Query getResource = namedQuery("findEnglishWordByUrduWord").setParameter("word", word);
        return (ArrayList<BaseLanguage>) getResource.list();
    }

    public List<String> findWordList(String ch, Integer start) {
        Query getResource = namedQuery("findUrduWordList")
                .setParameter("begin", ch+"%")
                .setParameter("space", "% %")
                .setFirstResult(start).setMaxResults(80);

        return (ArrayList<String>) getResource.list();
    }

    public Integer findWordCount(String ch) {
        Query getResource = namedQuery("findUrduWordList").setParameter("begin", ch+"%").setParameter("space", "% %");
        return getResource.list().size();
    }
}
