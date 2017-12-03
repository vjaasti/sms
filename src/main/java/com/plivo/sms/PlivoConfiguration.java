package com.plivo.sms;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import io.dropwizard.Configuration;
import io.dropwizard.db.DataSourceFactory;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.validator.constraints.NotEmpty;


public class PlivoConfiguration extends Configuration {
    @NotEmpty
    private String template;

    @NotEmpty
    private String defaultName = "Stranger";

    @NotEmpty
    private String cacheHost;
    
    @NotEmpty
    private String cachePort;
    
    @NotEmpty
    private String cacheExpiry;
    
    @NotEmpty
    private String counterTime;
    
    @NotEmpty
    private String counterLimit;
    
    
    @JsonProperty
    public String getCounterTime() {
		return counterTime;
	}

    @JsonProperty
	public void setCounterTime(String counterTime) {
		this.counterTime = counterTime;
	}

    @JsonProperty
	public String getCounterLimit() {
		return counterLimit;
	}

    @JsonProperty
	public void setCounterLimit(String counterLimit) {
		this.counterLimit = counterLimit;
	}

	@NotEmpty
    private String test;
    
    @JsonProperty
    public String getTest() {
		return test;
	}

    @JsonProperty
	public void setTest(String test) {
		this.test = test;
	}
    
    @NotEmpty
    private String nummin;
   
	@NotEmpty
    private String nummax;
    
	@NotEmpty
    private String textmin;
    
    @NotEmpty
    private String textmax;
    

    @Valid
	@NotNull
	private DataSourceFactory database = new DataSourceFactory();
    
    
    @JsonProperty
    public String getCacheExpiry() {
		return cacheExpiry;
	}

    @JsonProperty
	public void setCacheExpiry(String cacheExpiry) {
		this.cacheExpiry = cacheExpiry;
	}

    @JsonProperty
	public String getCacheHost() {
		return cacheHost;
	}

    @JsonProperty
	public void setCacheHost(String cacheHost) {
		this.cacheHost = cacheHost;
	}

    @JsonProperty
	public String getCachePort() {
		return cachePort;
		
	}

    @JsonProperty
	public void setCachePort(String cachePort) {
		this.cachePort = cachePort;
		System.out.println("@@@@@@@@@@@@@@@@@"+Integer.parseInt(this.getCachePort()));
	}

	
    @JsonProperty
    public String getNummin() {
		return this.nummin;
	}

    @JsonProperty
	public void setNummin(String nummin) {
		this.nummin =nummin;
	}

	
    @JsonProperty
    public String getNummax() {
		return nummax;
	}

    @JsonProperty
	public void setNummax(String nummax) {
		this.nummax = nummax;
	}

	

    @JsonProperty
    public String getTextmin() {
		return textmin;
	}

    @JsonProperty
	public void setText_min(String text_min) {
		this.textmin = text_min;
	}

	

	@JsonProperty
    public String getTextmax() {
		return textmax;
	}

    @JsonProperty
	public void setText_max(String textmax) {
		this.textmax = textmax;
	}

	@JsonProperty
    public String getTemplate() {
        return template;
    }

    @JsonProperty
    public void setTemplate(String template) {
        this.template = template;
    }

    @JsonProperty
    public String getDefaultName() {
        return defaultName;
    }

    @JsonProperty
    public void setDefaultName(String name) {
        this.defaultName = name;
    }

    @JsonProperty("database")
	public void setDataSourceFactory(DataSourceFactory factory) {
		this.database = factory;
	}

	@JsonProperty("database")
	public DataSourceFactory getDataSourceFactory() {
		return database;
	}
}