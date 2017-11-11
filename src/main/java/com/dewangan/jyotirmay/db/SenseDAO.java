package com.dewangan.jyotirmay.db;

import com.dewangan.jyotirmay.core.Sense;
import io.dropwizard.hibernate.AbstractDAO;
import org.hibernate.Query;
import org.hibernate.SessionFactory;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by jyotirmay.d on 01/11/17.
 */
public class SenseDAO extends AbstractDAO<Sense> {
    public SenseDAO(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    public List<Sense> findByWord(String word) {
        Query getResource = namedQuery("findSenseByWord").setParameter("word", word);
        return (ArrayList<Sense>) getResource.list();
    }

    public List<Sense> findSenseBySynsetId(Integer synsetId) {
        Query getResource = namedQuery("findSenseBySynsetId").setParameter("synsetId", synsetId);
        return (ArrayList<Sense>) getResource.list();
    }

    public List<Sense> findSenseByWordId(Integer wordId) {
        Query getResource = namedQuery("findSenseByWordId").setParameter("wordId", wordId);
        return (ArrayList<Sense>) getResource.list();
    }
}
