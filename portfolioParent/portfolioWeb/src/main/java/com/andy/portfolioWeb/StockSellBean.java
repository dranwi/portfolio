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
import com.andy.portfolioModel.Sale;
import com.andy.portfolioModel.Stock;
import com.andy.session.StockSession;
import com.andy.vd.StockBuyDetailVD;
import com.andy.vd.StockSellDetailVD;


@Named("StockSellBean")
@SessionScoped
public class StockSellBean implements Serializable {
	private static final long serialVersionUID = -5836528907193714299L;
	private static Logger logger = Logger.getLogger("com.andy.portfolioWeb.StockSellBean");
	
	@Inject
	PortfolioManageBean portfolioManageBean;
	
	@Inject
	StockSellDetailVD stockSellDetailVD;
	
	@Inject
	StockSession stockSession;
	
	Portfolio portfolio;
	List<Stock> stockList;
	Stock stock;
	String buyDate;
	
	
	
	public StockSellBean() {
	}
	
	public String stockSellAction() throws Exception{
			
		String stockName = stockSellDetailVD.getSelectedStockName();
		stock = stockSession.findByName(stockName);
		Sale sale = new Sale();
		sale.setDate(makeDate(stockSellDetailVD));
		sale.setCurr(stockSellDetailVD.getCurrency());
		sale.setQuote(makeQuote(stockSellDetailVD));
		sale.setAmount(makeAmount(stockSellDetailVD));
		sale.setValue(makeValue(stockSellDetailVD));		
		stock.addTrade(sale);
		stockSession.update(stock);		
		return "STOCK_SOLD";
	}
	
	Double makeValue(StockSellDetailVD vd) {
		Integer i = Integer.valueOf(vd.getAmount());
		Double d = Double.valueOf(vd.getQuote());
		Double v = d*i;
		return v;
	}
	
	Double makeQuote(StockSellDetailVD vd) {
		Double d = Double.valueOf(vd.getQuote());
		return d;
	}
	
	Integer makeAmount(StockSellDetailVD vd) {
		Integer i = Integer.valueOf(vd.getAmount());
		return i;
	}
	
	String makeDate(StockSellDetailVD vd) throws Exception{
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
		String name = stockSellDetailVD.getSelectedStockName();
		logger.info("SELECTED STOCK NAME: " + name);
		return name;
	}

	public void setSelectedStockName(String selectedStockName) {
		stockSellDetailVD.setSelectedStockName(selectedStockName);
	}
	
	public String getYear() {
		return stockSellDetailVD.getYear();
	}

	public void setYear(String year) {
		stockSellDetailVD.setYear(year);
	}

	public String getMonth() {
		return stockSellDetailVD.getMonth();
	}

	public void setMonth(String month) {
		stockSellDetailVD.setMonth(month);
	}

	public String getDay() {
		return stockSellDetailVD.getDay();
	}

	public void setDay(String day) {
		stockSellDetailVD.setDay(day);
	}

	public String getCurrency() {
		return stockSellDetailVD.getCurrency();
	}

	public void setCurrency(String currency) {
		stockSellDetailVD.setCurrency(currency);
	}

	public String getQuote() {
		return stockSellDetailVD.getQuote();
	}

	public void setQuote(String quote) {
		stockSellDetailVD.setQuote(quote);
	}

	public String getAmount() {
		return stockSellDetailVD.getAmount();
	}

	public void setAmount(String amount) {
		stockSellDetailVD.setAmount(amount);
	}
	
}

	


