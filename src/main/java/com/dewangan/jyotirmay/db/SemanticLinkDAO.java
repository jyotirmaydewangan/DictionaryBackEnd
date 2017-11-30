package com.dewangan.jyotirmay.db;

import com.dewangan.jyotirmay.core.SemanticLink;
import io.dropwizard.hibernate.AbstractDAO;
import org.hibernate.Query;
import org.hibernate.SessionFactory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by jyotirmay.d on 09/11/17.
 */
public class SemanticLinkDAO extends AbstractDAO<SemanticLink> {
    public SemanticLinkDAO(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    public List<SemanticLink> findSemanticLinkBySynsetId(Integer synsetId) {
        List<String> relatedByList = new ArrayList<String>(Arrays.asList("derivation", "also", "similar", "antonym"));
        Query getResource = namedQuery("findSemanticLinkBySynsetId").setParameter("synsetId", synsetId).setParameterList("relatedByList", relatedByList);
        return (ArrayList<SemanticLink>) getResource.list();
    }
}
