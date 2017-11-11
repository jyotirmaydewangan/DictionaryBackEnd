package com.dewangan.jyotirmay.db;

import com.dewangan.jyotirmay.core.Sample;
import io.dropwizard.hibernate.AbstractDAO;
import org.hibernate.Query;
import org.hibernate.SessionFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jyotirmay.d on 10/11/17.
 */
public class SampleDAO extends AbstractDAO<Sample> {
    public SampleDAO(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    public List<Sample> findSampleBySynsetId(Integer synsetId) {
        Query getResource = namedQuery("findSampleBySynsetId").setParameter("synsetId", synsetId);
        return (ArrayList<Sample>) getResource.list();
    }
}
