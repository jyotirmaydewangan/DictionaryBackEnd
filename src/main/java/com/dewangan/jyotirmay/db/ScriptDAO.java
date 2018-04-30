package com.dewangan.jyotirmay.db;

import com.dewangan.jyotirmay.core.Script;
import io.dropwizard.hibernate.AbstractDAO;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by jyotirmay.d on 26/01/18.
 */
public class ScriptDAO extends AbstractDAO<Script>{
    public ScriptDAO(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    public Script findScript(String lang) {
        Query getResource = namedQuery("findScript").setParameter("lang", lang);

        return (Script)getResource.list().get(0);
    }
}

