package com.retailcomp.pricing.pojo;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Pavan
 * This is a plain object with getter and setter methods ; maintains all parameters of a given product
 * The object is updated while reading the input survey file
 */
public class SurveyItem implements Serializable{

	private static final long serialVersionUID = -8670599073454288601L;
	
	private String nameOfItem;
	
	private String itemParameter;
	
	private Double multiplicationFactor=0d;
	
	private Double preferredPrice=null;
	
	private BigInteger totalSamplesforItem=BigInteger.ZERO;
	
	private List<Double> priceList=new ArrayList<>();
	
	private Double sumPrice=0d;
	
//	private Map<String,Double> competitiorPriceMap; /** for future use*/

	public String getNameOfItem() {
		return nameOfItem;
	}

	public void setNameOfItem(String nameOfItem) {
		this.nameOfItem = nameOfItem;
	}

	public String getItemParameter() {
		return itemParameter;
	}

	public void setItemParameter(String itemPameter) {
		this.itemParameter = itemPameter;
	}

	public BigInteger getTotalSamplesforItem() {
		return totalSamplesforItem;
	}

	public void setTotalSamplesforItem(BigInteger totalSamplesforItem) {
		this.totalSamplesforItem = totalSamplesforItem;
	}

	public Double getSumPrice() {
		return sumPrice;
	}

	public void setSumPrice(Double sumPrice) {
		this.sumPrice = sumPrice;
	}

	public Double getPreferredPrice() {
		return preferredPrice;
	}

	public void setPreferredPrice(Double preferredPrice) {
		this.preferredPrice = preferredPrice;
	}

	public Double getMultiplicationFactor() {
		return multiplicationFactor;
	}

	public void setMultiplicationFactor(Double multiplicationFactor) {
		this.multiplicationFactor = multiplicationFactor;
	}

	public List<Double> getPriceList() {
		return priceList;
	}

	public void setPriceList(List<Double> priceList) {
		this.priceList = priceList;
	}

	/*@Override
	public String toString() {
		return "SurveyItem [nameOfItem=" + nameOfItem + ", itemParameter=" + itemParameter + ", multiplicationFactor="
				+ multiplicationFactor + ", preferredPrice=" + preferredPrice + ", totalSamplesforItem="
				+ totalSamplesforItem + ", priceList=" + priceList + ", sumPrice=" + sumPrice + "]";
	}

	*/

}
