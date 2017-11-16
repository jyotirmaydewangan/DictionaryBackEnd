package com.dewangan.jyotirmay.db;

import com.dewangan.jyotirmay.core.BaseLanguage;
import com.dewangan.jyotirmay.core.UrduLanguage;
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

    @Override
    public List<BaseLanguage> findTargetWordByWordId(Integer wordId) {
        Query getResource = namedQuery("findUrduWordByWordId").setParameter("wordId", wordId);
        return (ArrayList<BaseLanguage>) getResource.list();
    }

    @Override
    public BaseLanguage findTopTargetWordByWordId(Integer wordId){
        Query getResource = namedQuery("findTopUrduWordByWordId").setParameter("wordId", wordId).setMaxResults(1);
        return (BaseLanguage) getResource.uniqueResult();
    }
}
