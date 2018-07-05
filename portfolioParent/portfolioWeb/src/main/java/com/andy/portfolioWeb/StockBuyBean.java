package com.andy.portfolioWeb;


import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.logging.Logger;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.andy.portfolioModel.DateString;
import com.andy.portfolioModel.Portfolio;
import com.andy.portfolioModel.Purchase;
import com.andy.portfolioModel.Stock;
import com.andy.session.StockSession;
import com.andy.vd.StockBuyDetailVD;

@Named("StockBuyBean")
@SessionScoped
public class StockBuyBean implements Serializable{
	private static final long serialVersionUID = 3705396917678603978L;
	private static Logger logger = Logger.getLogger("com.andy.portfolioWeb.StockBuyBean");
	
	@Inject
	PortfolioManageBean portfolioManageBean;
	
	@Inject
	StockBuyDetailVD stockBuyDetailVD;
	
	@Inject
	StockSession stockSession;
	
	Portfolio portfolio;
	List<Stock> stockList;
	Stock stock;
	String buyDate;
	
	
	
	public StockBuyBean() {
	}
	
	public String stockBuyAction() throws Exception{
			
		String stockName = stockBuyDetailVD.getSelectedStockName();
		stock = stockSession.findByName(stockName);
		Purchase purchase = new Purchase();
		purchase.setDate(makeDate(stockBuyDetailVD));
		purchase.setCurr(stockBuyDetailVD.getCurrency());
		purchase.setQuote(makeQuote(stockBuyDetailVD));
		purchase.setAmount(makeAmount(stockBuyDetailVD));
		purchase.setValue(makeValue(stockBuyDetailVD));		
		stock.addTrade(purchase);
		stockSession.update(stock);		
		return "STOCK_BOUGHT";
	}
	
	Double makeValue(StockBuyDetailVD vd) {
		Integer i = Integer.valueOf(vd.getAmount());
		Double d = Double.valueOf(vd.getQuote());
		Double v = d*i;
		return v;
	}
	
	Double makeQuote(StockBuyDetailVD vd) {
		Double d = Double.valueOf(vd.getQuote());
		return d;
	}
	
	Integer makeAmount(StockBuyDetailVD vd) {
		Integer i = Integer.valueOf(vd.getAmount());
		return i;
	}
	
	String makeDate(StockBuyDetailVD vd) throws Exception{
		String date;
		String year = vd.getYear();
		String month = vd.getMonth();
		String day = vd.getDay();
		date = new DateString(day,month,year).getString();
		return date;
	}
	
	public List<Stock> getStockList() {
		portfolio = portfolioManageBean.getSelectedPortfolio();
		stockList = portfolio.getStockList();	
		return stockList;
	}

	public String getSelectedStockName() {
		String name = stockBuyDetailVD.getSelectedStockName();
		logger.info("SELECTED STOCK NAME: " + name);
		return name;
	}

	public void setSelectedStockName(String selectedStockName) {
		stockBuyDetailVD.setSelectedStockName(selectedStockName);
	}
	
	public String getYear() {
		return stockBuyDetailVD.getYear();
	}

	public void setYear(String year) {
		stockBuyDetailVD.setYear(year);
	}

	public String getMonth() {
		return stockBuyDetailVD.getMonth();
	}

	public void setMonth(String month) {
		stockBuyDetailVD.setMonth(month);
	}

	public String getDay() {
		return stockBuyDetailVD.getDay();
	}

	public void setDay(String day) {
		stockBuyDetailVD.setDay(day);
	}

	public String getCurrency() {
		return stockBuyDetailVD.getCurrency();
	}

	public void setCurrency(String currency) {
		stockBuyDetailVD.setCurrency(currency);
	}

	public String getQuote() {
		return stockBuyDetailVD.getQuote();
	}

	public void setQuote(String quote) {
		stockBuyDetailVD.setQuote(quote);
	}

	public String getAmount() {
		return stockBuyDetailVD.getAmount();
	}

	public void setAmount(String amount) {
		stockBuyDetailVD.setAmount(amount);
	}
	
}
