package com.retailcomp.pricing.pojo;

import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Pavan This class holds the master configuration values read from
 *         initial configuration file and maintain various status messages. This
 *         object prevents frequent I/O calls , configuration will be loaded
 *         once during application startup
 */
public class PricingConstants {

	public static Map<String, Double> supplyDemandMap = new HashMap<>(); //not marking as final as this map will be updated only once during app startup;

	public static final String LOAD_CONFIG_PATH = "Intial configuration parameters";
	public static final String READ_SURVEY_FILE = "Read survey file";
	public static final String CALCULATE_PREFERRED_PRICE = "Calculate preferred price";
	public static final String INVALID_INTIAL_CONFIG_PATH = "Exiting service; Invalid inital config path";
	public static final String INVALID_SURVEY_PARAM = "Exiting service; Invalid survey parameters";
	public static final String INVALID_INTIAL_ARGUMENTS = "Exiting service; Invalid inital arguments \n usage: java -jar pricingEngine.jar <absPathOfconfigFile> <absPathOfInputSurveyFile>";
	public static final String ERROR_WHILE_READING_CONFIG_FILE = "Exiting service; Error while reading config file";
	public static final String SKIP_LINE = "Line not in expected format ;Skipping line ";
	public static final String SKIP_LINE_INVALID_PRODUCT = "Invalid product ;Skipping line ";
	public static final String NUMBER_OF_PRODUCTS = "NUMBER_OF_PRODUCTS";
	public static final String COMPLETE = "Completed: ";
	public static final String START = "Start: ";
	public static final String CUTTOF_DISCARD_LOWER = "CUTTOF_DISCARD_LOWER";
	public static final String CUTTOF_DISCARD_UPPER = "CUTTOF_DISCARD_UPPER";
	public static final DecimalFormat preferredFormat = new DecimalFormat("#0.00");
}
