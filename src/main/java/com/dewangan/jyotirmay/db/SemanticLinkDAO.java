package com.dewangan.jyotirmay.db;

import com.dewangan.jyotirmay.core.SemanticLink;
import io.dropwizard.hibernate.AbstractDAO;
import org.hibernate.Query;
import org.hibernate.SessionFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jyotirmay.d on 09/11/17.
 */
public class SemanticLinkDAO extends AbstractDAO<SemanticLink> {
    public SemanticLinkDAO(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    public List<SemanticLink> findSemanticLinkBySynsetId(Integer synsetId) {
        Query getResource = namedQuery("findSemanticLinkBySynsetId").setParameter("synsetId", synsetId);
        return (ArrayList<SemanticLink>) getResource.list();
    }
}
