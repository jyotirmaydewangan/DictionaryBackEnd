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

    public List<Word> findWordList(String ch, Integer start) {
        Query getResource = namedQuery("findWordList")
                .setParameter("begin", ch+"%")
                .setParameter("space", "% %")
                .setFirstResult(start).setMaxResults(80);

        return (ArrayList<Word>) getResource.list();
    }

    public Integer findWordCount(String ch) {
        Query getResource = namedQuery("findWordList").setParameter("begin", ch+"%").setParameter("space", "% %");
        return getResource.list().size();
    }
}
