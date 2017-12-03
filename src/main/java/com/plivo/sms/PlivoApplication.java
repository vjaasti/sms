package com.plivo.sms;

import org.skife.jdbi.v2.DBI;

import io.dropwizard.Application;
import io.dropwizard.jdbi.DBIFactory;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import com.plivo.sms.resources.InBoundSMSResource;
import com.plivo.sms.resources.OutBoundSMSResource;
import com.plivo.sms.cache.CacheManagerFactory;
import com.plivo.sms.db.dao.AccountDAO;
import com.plivo.sms.db.dao.PhoneNumberDAO;
import com.plivo.sms.health.TemplateHealthCheck;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class PlivoApplication extends Application<PlivoConfiguration> {

	private final Logger myLogger = LoggerFactory.getLogger(PlivoApplication.class);
    public static void main(final String[] args) throws Exception {
        new PlivoApplication().run(args);
    }

    @Override
    public String getName() {
        return "plivo";
    }

    @Override
    public void initialize(final Bootstrap<PlivoConfiguration> bootstrap) {
        // TODO: application initialization
    }

    @Override
    public void run(PlivoConfiguration configuration,
                Environment environment) {

        
        final DBIFactory factory = new DBIFactory();
		final DBI jdbi = factory.build(environment, configuration.getDataSourceFactory(), "mysql");
		AccountDAO accountDAO = jdbi.onDemand(AccountDAO.class);
		PhoneNumberDAO phoneNumberDAO = jdbi.onDemand(PhoneNumberDAO.class);
		
		try {
			CacheManagerFactory.createCacheManager("memcache", configuration.getCacheHost(),
					Integer.parseInt(configuration.getCachePort()));
		} catch (Exception e) {
			myLogger.error("Error while connecting to cache "+e.getMessage());
			e.printStackTrace();
		}
		
        final InBoundSMSResource inboundSMSResource = new InBoundSMSResource(configuration, accountDAO, phoneNumberDAO);
        
        final OutBoundSMSResource outboundSMSResource = new OutBoundSMSResource(configuration, accountDAO, phoneNumberDAO);
        
        
        final TemplateHealthCheck healthCheck =
            new TemplateHealthCheck(configuration.getTemplate());
        environment.healthChecks().register("template", healthCheck);

        environment.jersey().register(inboundSMSResource);
        environment.jersey().register(outboundSMSResource);
        
    }


}
