package com.retailcomp.pricing.util;

import java.math.BigInteger;
import java.sql.Timestamp;
import java.util.Map;

import com.retailcomp.pricing.pojo.PricingConstants;
import com.retailcomp.pricing.pojo.SurveyItem;

public class PricingEngineUtils {

	/**
	 * Utility method used to split the product name and demand supply
	 * parameters and add it to survey item map
	 * Set the multiplication factor to survey item object
	 * 
	 * @author PAVAN
	 * @param lineFromFile
	 * @param objInputSurveyItem 
	 * @param mapOfSurveyItems
	 */
	public static void splitNameAndParameter(String line, SurveyItem objInputSurveyItem,
			Map<String, SurveyItem> mapOfSurveyItems) throws Exception {
		
		if (null != line && null != objInputSurveyItem && line.length() > 3) {

			// set the Item parameter and name
			objInputSurveyItem.setItemParameter(line.substring(line.length() - 3).replaceAll(" ", ""));
			objInputSurveyItem.setNameOfItem(line.substring(0, line.length() - 4).replaceAll(" ", ""));

			mapOfSurveyItems.put(objInputSurveyItem.getNameOfItem(), objInputSurveyItem);

			// set the multiplication factor to survey item object
			if (null != objInputSurveyItem.getItemParameter()
					&& null != PricingConstants.supplyDemandMap.get(objInputSurveyItem.getItemParameter())) {
				objInputSurveyItem.setMultiplicationFactor(
						PricingConstants.supplyDemandMap.get(objInputSurveyItem.getItemParameter()));
			} else {
				logTheEvent(PricingConstants.SKIP_LINE + line);
			}

		} else {
			logTheEvent(PricingConstants.SKIP_LINE + line);
		}
	}
	
	/**
	 * Utility method used to get price against a competitor from survey and add
	 * it to survey object later will be referred to get the average
	 * <space> is considered as Delimiter
	 * @author Pavan
	 */
	public static void splitPriceFromCompetetior(String line, Map<String, SurveyItem> mapOfSurveyItems) throws Exception {
		try {
			if (null != line && null != mapOfSurveyItems) {
				String[] surveyItem = line.split("\\s+");
				if (surveyItem.length == 3) {
					SurveyItem	objInputSurveyItem = mapOfSurveyItems.get(surveyItem[0]);
					if (null != objInputSurveyItem) {
						objInputSurveyItem.getPriceList().add(Double.parseDouble(surveyItem[2]));
						objInputSurveyItem.setTotalSamplesforItem(objInputSurveyItem.getTotalSamplesforItem().add(BigInteger.ONE));
					} else {
						logTheEvent(PricingConstants.SKIP_LINE_INVALID_PRODUCT + line);
					}
				} else {
					logTheEvent(PricingConstants.SKIP_LINE + line);
				}
			} else {
				logTheEvent(PricingConstants.SKIP_LINE + line);
			}
		} catch (Exception e) {
			logTheEvent(PricingConstants.SKIP_LINE + line +" "+e.getMessage());
		}

	}
	
	public static void logTheEvent(String msg){		
		System.out.println(new Timestamp(System.currentTimeMillis()) +":: "+ msg);
	}

}
