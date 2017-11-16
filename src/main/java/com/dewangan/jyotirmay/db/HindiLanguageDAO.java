package com.dewangan.jyotirmay.db;

import com.dewangan.jyotirmay.core.BaseLanguage;
import com.dewangan.jyotirmay.core.HindiLanguage;

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

    @Override
    public List<BaseLanguage> findTargetWordByWordId(Integer wordId) {
        Query getResource = namedQuery("findHindiWordByWordId").setParameter("wordId", wordId);
        return (ArrayList<BaseLanguage>) getResource.list();
    }

    @Override
    public BaseLanguage findTopTargetWordByWordId(Integer wordId){
        Query getResource = namedQuery("findTopHindiWordByWordId").setParameter("wordId", wordId).setMaxResults(1);
        return (BaseLanguage) getResource.uniqueResult();
    }
}
