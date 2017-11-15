package com.dewangan.jyotirmay;

import com.dewangan.jyotirmay.auth.GreetingAuthenticator;
import com.dewangan.jyotirmay.core.*;
import com.dewangan.jyotirmay.db.*;
import com.dewangan.jyotirmay.resources.*;
import io.dropwizard.Application;
import io.dropwizard.auth.AuthDynamicFeature;
import io.dropwizard.auth.AuthValueFactoryProvider;
import io.dropwizard.auth.basic.BasicCredentialAuthFilter;
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
                    Employee.class,
                    Sense.class,
                    SemanticLink.class,
                    LexicalLink.class,
                    Sample.class,
                    HindiLanguage.class,
                    UrduLanguage.class
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
        //Create Employee DAO.
        final EmployeeDAO employeeDAO = new EmployeeDAO(hibernateBundle.getSessionFactory());
        final SenseDAO senseDAO = new SenseDAO(hibernateBundle.getSessionFactory());
        final SemanticLinkDAO semanticLinkDAO = new SemanticLinkDAO(hibernateBundle.getSessionFactory());
        final LexicalLinkDAO lexicalLinkDAO = new LexicalLinkDAO(hibernateBundle.getSessionFactory());
        final SampleDAO sempleDAO = new SampleDAO(hibernateBundle.getSessionFactory());
        final HindiLanguageDAO hindiLanguageDAO = new HindiLanguageDAO(hibernateBundle.getSessionFactory());
        final UrduLanguageDAO urduLanguageDAO = new UrduLanguageDAO(hibernateBundle.getSessionFactory());

        //Create Jersey client.
        final Client client = new JerseyClientBuilder(environment)
                .using(configuration.getJerseyClientConfiguration())
                .build(getName());
        //Register authenticator.

        environment.jersey().register(new AuthDynamicFeature(
                new BasicCredentialAuthFilter.Builder<User>()
                        .setAuthenticator(new GreetingAuthenticator(configuration.getLogin(),
                                configuration.getPassword()))
                        .setRealm("SECURITY REALM")
                        .buildAuthFilter()));
        environment.jersey().register(RolesAllowedDynamicFeature.class);
        //environment.jersey().register(new AuthValueFactoryProvider.Binder<>(User.class));
        //Register a simple resource.
        environment.jersey().register(new HelloResource());
        //Register a secured resource.
        environment.jersey().register(new SecuredHelloResource());
        //Register a database-backed resource.
        environment.jersey().register(new EmployeesResource(employeeDAO));
        environment.jersey().register(new WordResource(senseDAO, semanticLinkDAO, lexicalLinkDAO, sempleDAO,
                                                        hindiLanguageDAO, urduLanguageDAO));
        //Register a resource using Jersey client.
        environment.jersey().register(
                new ConverterResource(
                        client,
                        configuration.getApiURL(),
                        configuration.getApiKey())
        );
    }

}
