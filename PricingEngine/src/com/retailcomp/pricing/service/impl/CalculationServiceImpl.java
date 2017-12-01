package com.retailcomp.pricing.service.impl;

import java.util.Collections;
import java.util.Iterator;
import java.util.Map;

import com.retailcomp.pricing.pojo.PricingConstants;
import com.retailcomp.pricing.pojo.SurveyItem;
import com.retailcomp.pricing.service.CalculationService;
import com.retailcomp.pricing.util.PricingEngineUtils;

/**
 * @author Pavan
 *
 */
public class CalculationServiceImpl implements CalculationService {

	/**
	 * Method will iterate through survey map and proceeds with calling
	 * calculation of preferred price
	 * 
	 * @author Pavan
	 * @param mapOfSurveyItems
	 */
	@Override
	public void runCalculationForSurveyItems(Map<String, SurveyItem> mapOfSurveyItems) throws Exception {
		if (null != mapOfSurveyItems && !mapOfSurveyItems.isEmpty()) {
			SurveyItem objSurveyItem = null;
			Iterator<String> iterMapSurvey = mapOfSurveyItems.keySet().iterator();
			while (iterMapSurvey.hasNext()) {
				objSurveyItem = mapOfSurveyItems.get(iterMapSurvey.next());
				calculatePreferredPrice(objSurveyItem);
			}
		}

	}

	/**
	 * Iterate through the price list which is a sorted set and take the lowest
	 * element as preferred price Apply logic over preferred price of lower
	 * cutoff and upper cutoff 50% below and 50% above
	 * 
	 * @author Pavan
	 * @param objSurveyItem
	 */
	public void calculatePreferredPrice(SurveyItem objSurveyItem) throws Exception {
		if (null != objSurveyItem && objSurveyItem.getPriceList().size() > 0) {
			Double totalPrice = 0d;
			Double averageItemPrice = 0d;
			int preferPriceIndex = 0;

			Collections.sort(objSurveyItem.getPriceList());
			objSurveyItem.setPreferredPrice(objSurveyItem.getPriceList().get(0));
			Double[] surveyPriceArray = objSurveyItem.getPriceList()
					.toArray(new Double[objSurveyItem.getPriceList().size()]);

			for (int i = 0; i < surveyPriceArray.length; i++) {
				totalPrice += surveyPriceArray[i];
			}
			objSurveyItem.setSumPrice(totalPrice);
			averageItemPrice = objSurveyItem.getSumPrice() / objSurveyItem.getPriceList().size();

			calculateUpperLowerCutOff(averageItemPrice, objSurveyItem, surveyPriceArray, preferPriceIndex);

			objSurveyItem
					.setPreferredPrice(objSurveyItem.getPreferredPrice() * objSurveyItem.getMultiplicationFactor());
			if (objSurveyItem.getPreferredPrice() > 0) {
				PricingEngineUtils.logTheEvent(objSurveyItem.getNameOfItem() + " "
						+ PricingConstants.preferredFormat.format(objSurveyItem.getPreferredPrice()));
			}
		}
	}

	/**
	 * private method to deduce the lower and upper cut off values
	 * @author Pavan
	 * @param averageItemPrice
	 * @param objSurveyItem
	 * @param surveyPriceArray
	 * @param preferPriceIndex
	 */
	private void calculateUpperLowerCutOff(Double averageItemPrice, SurveyItem objSurveyItem, Double[] surveyPriceArray,
			int preferPriceIndex) {
		Double lowerCutoffPrice = 0d;
		Double upperCutoffPrice = 0d;
		Integer priceIndex = preferPriceIndex;
		lowerCutoffPrice = averageItemPrice
				* PricingConstants.supplyDemandMap.get(PricingConstants.CUTTOF_DISCARD_LOWER);
		upperCutoffPrice = averageItemPrice
				* PricingConstants.supplyDemandMap.get(PricingConstants.CUTTOF_DISCARD_UPPER);

		if (objSurveyItem.getPreferredPrice() < lowerCutoffPrice
				|| objSurveyItem.getPreferredPrice() > upperCutoffPrice) {
			while ((objSurveyItem.getPreferredPrice() < lowerCutoffPrice
					|| objSurveyItem.getPreferredPrice() > upperCutoffPrice)
					&& preferPriceIndex < surveyPriceArray.length) {
				objSurveyItem.setPreferredPrice(surveyPriceArray[priceIndex++]);
			}
		}
	}
}
