package com.retailcomp.pricing.service;

import java.util.Map;

import com.retailcomp.pricing.pojo.SurveyItem;

/**
 * @author Pavan
 * This service will have methods to do the I/O interaction
 * 1. Read properties file with master configuration   
 * 2. Read the survey input as a file  
 * */
public interface IOService {

	/**
	 * @param pathOfPropertiesFile
	 *            This method will load all the master configuration parameters
	 *            from the configuration file Alternatively, these configuration
	 *            values can be loaded from database also
	 */
	public void loadMasterConfiguration(String pathOfPropertiesFile) throws Exception;
	
	/**
	 * @param pathOfSurveyFile
	 *            This method will read the survey items from the input file,
	 *            form the required Map containing itemName to item parameters
	 *            mapping
	 */
	public Map<String,SurveyItem> readSurveyInput(String pathOfSurveyFile) throws Exception;
}
