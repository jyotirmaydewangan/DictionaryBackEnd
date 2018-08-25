package com.dewangan.jyotirmay;

import com.dewangan.jyotirmay.core.*;
import com.dewangan.jyotirmay.db.*;
import com.dewangan.jyotirmay.db.language.*;
import com.dewangan.jyotirmay.language.*;
import com.dewangan.jyotirmay.resources.*;
import io.dropwizard.Application;
import io.dropwizard.client.JerseyClientBuilder;
import io.dropwizard.db.DataSourceFactory;
import io.dropwizard.hibernate.HibernateBundle;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import javax.ws.rs.client.Client;
import org.glassfish.jersey.server.filter.RolesAllowedDynamicFeature;

/**
 * The application class.
 *
 * @author Dmitry Noranovich
 */
public class DictionaryBackEndApplication
        extends Application<DictionaryBackEndConfiguration> {

    /**
     * Hibernate bundle.
     */
    private final HibernateBundle<DictionaryBackEndConfiguration> hibernateBundle = new HibernateBundle<DictionaryBackEndConfiguration>(
                    Word.class, Script.class,

                    Antonym.class, Definition.class, Synonym.class, SeeAlso.class, Analysis.class,

                    HindiLanguage.class, UrduLanguage.class, TeluguLanguage.class,
                    BengaliLanguage.class, MarathiLanguage.class
    ) {

        public DataSourceFactory getDataSourceFactory(
                DictionaryBackEndConfiguration configuration
        ) {
            return configuration.getDataSourceFactory();
        }

    };

    /**
     * The main method of the application.
     *
     * @param args command-line arguments
     * @throws Exception any exception while executing the main() method.
     */
    public static void main(final String[] args) throws Exception {
        new DictionaryBackEndApplication().run(args);
    }

    @Override
    public String getName() {
        return "DWGettingStarted";
    }

    @Override
    public void initialize(
            final Bootstrap<DictionaryBackEndConfiguration> bootstrap) {
        bootstrap.addBundle(hibernateBundle);
    }

    @Override
    public void run(final DictionaryBackEndConfiguration configuration, final Environment environment) {

        final WordDAO wordDAO = new WordDAO(hibernateBundle.getSessionFactory());
        final ScriptDAO scriptDAO = new ScriptDAO(hibernateBundle.getSessionFactory());

        final HindiLanguageDAO hindiLanguageDAO = new HindiLanguageDAO(hibernateBundle.getSessionFactory());
        final UrduLanguageDAO urduLanguageDAO = new UrduLanguageDAO(hibernateBundle.getSessionFactory());
        final TeluguLanguageDAO teluguLanguageDAO = new TeluguLanguageDAO(hibernateBundle.getSessionFactory());
        final BengaliLanguageDAO bengaliLanguageDAO = new BengaliLanguageDAO(hibernateBundle.getSessionFactory());
        final MarathiLanguageDAO marathiLanguageDAO = new MarathiLanguageDAO(hibernateBundle.getSessionFactory());

        //Register a simple resource.
        environment.jersey().register(new WordResource(wordDAO, scriptDAO,
                                                        hindiLanguageDAO, urduLanguageDAO,
                                                        teluguLanguageDAO, bengaliLanguageDAO,
                                                        marathiLanguageDAO));
    }

}
