/**
 * 
 */
package com.retailcomp.pricing.main;

import java.util.Map;

import com.retailcomp.pricing.pojo.PricingConstants;
import com.retailcomp.pricing.pojo.SurveyItem;
import com.retailcomp.pricing.service.CalculationService;
import com.retailcomp.pricing.service.IOService;
import com.retailcomp.pricing.service.impl.CalculationServiceImpl;
import com.retailcomp.pricing.service.impl.IOServiceImpl;
import com.retailcomp.pricing.util.PricingEngineUtils;

/**
 * @author Pavan This class is used as a starting point , that reads input
 *         parameters to generate competing prices
 *
 */
public class ExecuteInputPrices {

	/**
	 * @param args
	 *            This is the starting point of logic , takes the file path as
	 *            input Input file contains survey details
	 */
	public static void main(String[] inputArgs) {
		ExecuteInputPrices objExecuteInputPrices=new ExecuteInputPrices();
		try {
			if (null != inputArgs && inputArgs.length==2) {
				objExecuteInputPrices.executeInputPricesLogic(inputArgs);
			} else {
				PricingEngineUtils.logTheEvent(PricingConstants.INVALID_INTIAL_ARGUMENTS);
			}
		} catch (Exception e) {
			PricingEngineUtils.logTheEvent(e.getMessage());
		}

	}
	
	private void executeInputPricesLogic(String[] inputArgs) throws Exception{
		final IOService ioService = new IOServiceImpl();
		final CalculationService calcService=new CalculationServiceImpl();
		Map<String,SurveyItem> mapOfSurveyItems=null;
		if(null!= inputArgs[0] && null!=inputArgs[1]){
		/**
		 * Load the configuration file
		 */
		PricingEngineUtils.logTheEvent(PricingConstants.START + PricingConstants.LOAD_CONFIG_PATH);
		ioService.loadMasterConfiguration(inputArgs[0]);
		PricingEngineUtils.logTheEvent(PricingConstants.COMPLETE + PricingConstants.LOAD_CONFIG_PATH);

		/**
		 * Reading the input file and forming the survey object
		 */
		PricingEngineUtils.logTheEvent(PricingConstants.START + PricingConstants.READ_SURVEY_FILE);
		mapOfSurveyItems=ioService.readSurveyInput(inputArgs[1]);
		PricingEngineUtils.logTheEvent(PricingConstants.COMPLETE + PricingConstants.READ_SURVEY_FILE);


		/**
		 * Calculate the recommended prices based ,exclude promotion and data error 
		 */
		PricingEngineUtils.logTheEvent(PricingConstants.CALCULATE_PREFERRED_PRICE);
		if(null!=mapOfSurveyItems){
		calcService.runCalculationForSurveyItems(mapOfSurveyItems);
		}
		}
		else{
			PricingEngineUtils.logTheEvent(PricingConstants.INVALID_INTIAL_ARGUMENTS);
		}
	}
}
