package com.retailcomp.pricing.service;

import java.util.Map;

import com.retailcomp.pricing.pojo.SurveyItem;

/**
 * @author Pavan
 * This service will have methods to do the below calculations
 * 1. average of given item of survey   
 * 2. chosen price determination
 * 3. recommended price for the item
 * */
public interface CalculationService {

	/**
	 * Method will iterate through survey map and proceeds with calling
	 * calculation of preferred price
	 * 
	 * @author Pavan
	 * @param mapOfSurveyItems
	 */
	public void runCalculationForSurveyItems(Map<String,SurveyItem> mapOfSurveyItems) throws Exception;
	
	/**
	 * Iterate through the price list which is a sorted set and take the lowest
	 * element as preferred price Apply logic over preferred price of lower
	 * cutoff and upper cutoff 50% below and 50% above
	 * 
	 * @author Pavan
	 * @param objSurveyItem
	 */
	public void calculatePreferredPrice(SurveyItem objSurveyItem) throws Exception;
}
