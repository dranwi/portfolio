package com.andy.vd;

import java.io.Serializable;

import javax.enterprise.context.RequestScoped;

@RequestScoped
public class StockSellDetailVD implements Serializable {
	private static final long serialVersionUID = 627051712293226069L;
	
	String selectedStockName;
	String year;
	String month;
	String day;
	String currency;
	String quote;
	String amount;
	
	public StockSellDetailVD() {}

	public String getSelectedStockName() {
		return selectedStockName;
	}

	public void setSelectedStockName(String selectedStockName) {
		this.selectedStockName = selectedStockName;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public String getMonth() {
		return month;
	}

	public void setMonth(String month) {
		this.month = month;
	}

	public String getDay() {
		return day;
	}

	public void setDay(String day) {
		this.day = day;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public String getQuote() {
		return quote;
	}

	public void setQuote(String quote) {
		this.quote = quote;
	}

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}
	

}
