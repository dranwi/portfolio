package com.andy.portfolioWeb;

import java.util.Date;

public class TradeItem {
	
	String date;
	String tradeType;
	String symbol;
	String currency;
	String amount;
	String tradeQuote;
	String presQuote;
	String tradeSum;
	String totalTradeSum;
	String totalPresSum;
	String totalProfit;
	String totalMargin;
	String presSum;
	String profit;
	String margin;
	
	public TradeItem() {
		totalTradeSum = null;
		totalPresSum = null;
		totalProfit = null;
		totalMargin = null;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}
	
	public String getTradeType() {
		return tradeType;
	}

	public void setTradeType(String tradeType) {
		this.tradeType = tradeType;
	}

	public String getSymbol() {
		return symbol;
	}

	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	public String getTradeQuote() {
		return tradeQuote;
	}

	public void setTradeQuote(String tradeQuote) {
		this.tradeQuote = tradeQuote;
	}

	public String getPresQuote() {
		return presQuote;
	}

	public void setPresQuote(String presQuote) {
		this.presQuote = presQuote;
	}

	public String getTradeSum() {
		return tradeSum;
	}

	public void setTradeSum(String tradeSum) {
		this.tradeSum = tradeSum;
	}

	public String getPresSum() {
		return presSum;
	}

	public void setPresSum(String presSum) {
		this.presSum = presSum;
	}

	public String getProfit() {
		return profit;
	}

	public void setProfit(String profit) {
		this.profit = profit;
	}

	public String getMargin() {
		return margin;
	}

	public void setMargin(String margin) {
		this.margin = margin;
	}

	public String getTotalTradeSum() {
		return totalTradeSum;
	}

	public void setTotalTradeSum(String totalTradeSum) {
		this.totalTradeSum = totalTradeSum;
	}

	public String getTotalPresSum() {
		return totalPresSum;
	}

	public void setTotalPresSum(String totalPresSum) {
		this.totalPresSum = totalPresSum;
	}

	public String getTotalProfit() {
		return totalProfit;
	}

	public void setTotalProfit(String totalProfit) {
		this.totalProfit = totalProfit;
	}

	public String getTotalMargin() {
		return totalMargin;
	}

	public void setTotalMargin(String totalMargin) {
		this.totalMargin = totalMargin;
	}
	
	
	
	
	

}
