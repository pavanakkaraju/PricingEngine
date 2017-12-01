/**
 * 
 */
package com.retailcomp.pricing.service.impl;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;

import com.retailcomp.pricing.pojo.PricingConstants;
import com.retailcomp.pricing.pojo.SurveyItem;
import com.retailcomp.pricing.service.IOService;
import com.retailcomp.pricing.util.PricingEngineUtils;

/**
 * @author Pavan
 *
 */
public class IOServiceImpl implements IOService {

	/**
	 * 
	 */
	public IOServiceImpl() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param pathOfPropertiesFile
	 *            This method will load all the master configuration parameters
	 *            from the configuration file Alternatively, these configuration
	 *            values can be loaded from database also
	 */
	public void loadMasterConfiguration(String pathOfPropertiesFile) throws Exception {
		Properties propMaster = new Properties();

		File propFile = new File(pathOfPropertiesFile);

		// using try over input stream
		try (InputStream isMasterFile = new FileInputStream(propFile)) {
			// load the config file from the input path
			propMaster.load(isMasterFile);

			// iterate over properties to form the initial parameter map
			if (null != propMaster && null != propMaster.keySet()) {
				Iterator<Object> iterPropMaster = propMaster.keySet().iterator();
				Object tempObject = null;
				while (iterPropMaster.hasNext()) {
					tempObject = iterPropMaster.next();
					PricingConstants.supplyDemandMap.put(String.valueOf(tempObject),
							new Double(propMaster.getProperty(String.valueOf(tempObject))));
				}
			}
		} catch (Exception e) {
			System.out.println(PricingConstants.ERROR_WHILE_READING_CONFIG_FILE + pathOfPropertiesFile);
			propFile = null;
			throw e;
		}
		propFile = null;
	}

	@SuppressWarnings("unused")
	@Override
	/**
	 * @param pathOfSurveyFile
	 *            This method will read the survey items from the input file,
	 *            form the required Map containing itemName to item parameters
	 *            mapping
	 */
	public Map<String, SurveyItem> readSurveyInput(String pathOfSurveyFile) throws Exception {

		Map<String, SurveyItem> mapOfSurveyItems = new HashMap<>();
		SurveyItem objInputSurveyItem = null;
		BufferedReader surveyBufferedReader = null;
		try (InputStreamReader surveyFileReader = new InputStreamReader(new FileInputStream(pathOfSurveyFile),
				"UTF-8")) {
			surveyBufferedReader = new BufferedReader(surveyFileReader);
			String line = null;
			int currentLineNumber = 1;
			int lineNumberRef = 0;
			int numberOfProducts = 0;
			int numberOfSamples = 0;
			lineNumberRef = PricingConstants.supplyDemandMap.get(PricingConstants.NUMBER_OF_PRODUCTS).intValue();

			while ((line = surveyBufferedReader.readLine()) != null && line.trim().length() > 0) {
				/**
				 * Logic to read first line which has number of products
				 */
				if (currentLineNumber == lineNumberRef) {
					numberOfProducts = Integer.parseInt(line);
				}
				/**
				 * Logic to read products names and supply demand parameters
				 */
				else if (currentLineNumber > lineNumberRef && currentLineNumber <= (lineNumberRef + numberOfProducts)) {
					objInputSurveyItem = new SurveyItem();
					PricingEngineUtils.splitNameAndParameter(line.trim(), objInputSurveyItem, mapOfSurveyItems);
				}
				/**
				 * Logic to get number of surveyed items
				 */
				else if (currentLineNumber == (lineNumberRef + numberOfProducts + 1)) {
					numberOfSamples = Integer.parseInt(line);
				}
				/**
				 * Logic to products competitor prices
				 */
				else {
					PricingEngineUtils.splitPriceFromCompetetior(line.trim(), mapOfSurveyItems);
				}
				currentLineNumber++;
			}
			return mapOfSurveyItems;
		} catch (Exception e) {
			PricingEngineUtils.logTheEvent(PricingConstants.INVALID_SURVEY_PARAM);
			throw e;
		} finally {
			if (null != surveyBufferedReader) {
				surveyBufferedReader.close();
				surveyBufferedReader = null;
			}
		}
	}
}
