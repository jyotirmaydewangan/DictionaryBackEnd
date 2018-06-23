package com.dewangan.jyotirmay.db;


import com.dewangan.jyotirmay.core.Word;
import io.dropwizard.hibernate.AbstractDAO;
import org.hibernate.Query;
import org.hibernate.SessionFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jyotirmay.d on 26/01/18.
 */
public class WordDAO extends AbstractDAO<Word> {
    public WordDAO(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    public Word findByWord(String word) {
        Query getResource = namedQuery("findWordByWord").setParameter("word", word);

        if(getResource.list().isEmpty())
            return null;

        return (Word) getResource.list().get(0);
    }

    public List<Word> findWordList(String ch, Integer start, Integer limit) {
        Query getResource = namedQuery("findWordList")
                .setParameter("begin", ch+"%")
                .setParameter("space", "% %")
                .setFirstResult(start).setMaxResults(limit);

        return (ArrayList<Word>) getResource.list();
    }


    public List<Word> findWordListAutoList(String ch, Integer start, Integer limit) {
        Query getResource = namedQuery("findWordListAutoList")
                .setParameter("begin", ch+"%")
                .setFirstResult(start).setMaxResults(limit);

        return (ArrayList<Word>) getResource.list();
    }


    public Integer findWordCount(String ch) {
        Query getResource = namedQuery("findWordList").setParameter("begin", ch+"%").setParameter("space", "% %");
        return getResource.list().size();
    }
}
