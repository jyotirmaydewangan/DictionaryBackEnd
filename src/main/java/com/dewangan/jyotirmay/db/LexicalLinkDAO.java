package com.dewangan.jyotirmay.db;

import com.dewangan.jyotirmay.core.LexicalLink;
import io.dropwizard.hibernate.AbstractDAO;
import org.hibernate.Query;
import org.hibernate.SessionFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jyotirmay.d on 10/11/17.
 */
public class LexicalLinkDAO extends AbstractDAO<LexicalLink> {
    public LexicalLinkDAO(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    public List<LexicalLink> findLexicalLinkBySynsetId(Integer synsetId) {
        Query getResource = namedQuery("findLexicalLinkSynsetId").setParameter("synsetId", synsetId);
        return (ArrayList<LexicalLink>) getResource.list();
    }

    public List<LexicalLink> findLexicalLinkByWordId(Integer wordId) {
        Query getResource = namedQuery("findLexicalLinkByWordId").setParameter("wordId", wordId);
        return (ArrayList<LexicalLink>) getResource.list();
    }
}
