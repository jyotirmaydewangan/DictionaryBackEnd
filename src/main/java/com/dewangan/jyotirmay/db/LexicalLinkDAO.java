package com.dewangan.jyotirmay.db;

import com.dewangan.jyotirmay.core.LexicalLink;
import io.dropwizard.hibernate.AbstractDAO;
import org.hibernate.Query;
import org.hibernate.SessionFactory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by jyotirmay.d on 10/11/17.
 */
public class LexicalLinkDAO extends AbstractDAO<LexicalLink> {
    public LexicalLinkDAO(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    public List<LexicalLink> findLexicalLinkBySynsetId(Integer synsetId) {
        List<String> relatedByList = new ArrayList<String>(Arrays.asList("derivation", "also", "similar", "antonym"));
        Query getResource = namedQuery("findLexicalLinkSynsetId").setParameter("synsetId", synsetId).setParameterList("relatedByList", relatedByList);
        return (ArrayList<LexicalLink>) getResource.list();
    }

    public List<LexicalLink> findLexicalLinkByWordId(Integer wordId) {
        List<String> relatedByList = new ArrayList<String>(Arrays.asList("derivation", "also", "similar", "antonym"));
        Query getResource = namedQuery("findLexicalLinkByWordId").setParameter("wordId", wordId).setParameterList("relatedByList", relatedByList);
        return (ArrayList<LexicalLink>) getResource.list();
    }
}
