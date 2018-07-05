package com.andy.portfolioWeb;

import java.io.Serializable;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.logging.Logger;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.andy.portfolioModel.Portfolio;
import com.andy.portfolioModel.Purchase;
import com.andy.portfolioModel.Quote;
import com.andy.portfolioModel.Sale;
import com.andy.portfolioModel.Stock;
import com.andy.portfolioModel.Trade;
import com.andy.session.QuoteSession;

@SessionScoped
@Named("PortfolioViewBean")
public class PortfolioViewBean implements Serializable {
	private static final long serialVersionUID = 4746686264842756850L;
	private static Logger logger = Logger.getLogger("com.andy.portfolioWeb.PortfolioViewBean");
	
	List<TradeItem> tradeList;
	Portfolio portfolio;
	List<Stock> stockList;
	
	@Inject
	PortfolioManageBean pfoManageBean;
	
	@Inject
	QuoteSession quoteSession;

	public PortfolioViewBean() {
		
	}
	
	public List<TradeItem> getTradeList() {
		portfolio = pfoManageBean.getSelectedPortfolio();
		stockList = portfolio.getStockList();
		tradeList = new ArrayList<TradeItem>();
		for (Stock s : stockList) {
			Double totalTradeSum = 0.0;
			Double totalPresSum = 0.0;
			Double totalProfit = 0.0;
			Double totalMargin = 0.0;
			List<TradeItem> subTradeList =  new ArrayList<TradeItem>();
			for (Trade t : s.getTradeList()) {
				TradeItem item = new TradeItem();
				//Date
				item.setDate(t.getDate());
				
				//Trade
				String tType = (t instanceof Purchase) ? "BUY" : "SELL";
				item.setTradeType(tType);
				
				//Symbol
				item.setSymbol(s.getName());
				
				//Currency
				item.setCurrency(t.getCurr());	
				
				
				//Amount
				Integer amount = t.getAmount();
				if(t instanceof Sale) {amount = amount * (-1);}
				String amountString = this.makeIntegerString(amount);
				item.setAmount(amountString);
				
				
				//TradeQuote
				String tradeQuote = this.makeDoubleString(t.getQuote());
				item.setTradeQuote(tradeQuote);
				
				
				//PresQuote 
				Double presQuoteD = this.findQuote(s.getName(), "2018.06.22");
				String presQuote = this.makeDoubleString(presQuoteD);
				item.setPresQuote(presQuote);  

				//TradeSum
				Double amountD = new Double(item.getAmount());
				Double tradeSumD = amountD * t.getQuote();
				totalTradeSum = totalTradeSum + tradeSumD;
				Integer tradeSumI = tradeSumD.intValue();
				String tradeSumString = this.makeIntegerString(tradeSumI);
				item.setTradeSum(tradeSumString);
				
				
				//PresSum
				Double presSumD = amountD * presQuoteD;	
				Integer presSumI = presSumD.intValue();	
				totalPresSum = totalPresSum + presSumD;
				String presSumString = this.makeIntegerString(presSumI);
				item.setPresSum(presSumString);
				
				
				//Profit
				Integer profit = new Double(presSumD - tradeSumD).intValue();
				String profitString = this.makeIntegerString(profit);
				item.setProfit(profitString);
						
				//Margin
				Double margin = profit.doubleValue() / tradeSumD;
				String stringMargin = this.makeMargin(margin) + "%";
				item.setMargin(stringMargin);
								
				subTradeList.add(item);
				logger.info("ADDED TRADEITEM TO LIST FOR " + item.getSymbol() + " WITH PRESQUOTE " + item.getPresQuote());
			}
			if (subTradeList.size() > 0) {
				TradeItem firstItem = subTradeList.get(0);
				
				Integer totalTradeSumI = totalTradeSum.intValue();
				String totalTradeSumS = this.makeIntegerString(totalTradeSumI);
				firstItem.setTotalTradeSum(totalTradeSumS);
				
				Integer totalPresSumI = totalPresSum.intValue();
				String totalPresSumS = this.makeIntegerString(totalPresSumI);
				firstItem.setTotalPresSum(totalPresSumS);
				
				totalProfit = totalPresSum - totalTradeSum;
				Integer totalProfitI = totalProfit.intValue();
				String totalProfitS = this.makeIntegerString(totalProfitI);
				firstItem.setTotalProfit(totalProfitS);
				
				totalMargin = totalProfit / totalTradeSum;
				String totalMarginS = this.makeMargin(totalMargin) + "%";
				firstItem.setTotalMargin(totalMarginS);
				
				tradeList.addAll(subTradeList);
			}
		}
		return tradeList;
		
	}
	
	Double findQuote(String symbol, String date) {
		List<Quote> quoteList = quoteSession.findBySymbol(symbol);
		logger.info("QUOTESERVICE RETURNED FOR " + symbol + " " + quoteList.size() + " QUOTES");
		for (Quote q : quoteList) {
			logger.info("FOUND " + q.getDate() + " FOR " + date);
			if (date.equals(q.getDate())) {
				//logger.info("RETRUNING VALUE " + q.getValue() + " FOR DATE " + date);
				return q.getValue();
			}
		}
		return 0.0;
	}
	
	String makeMargin(Double margin) {
		margin = margin * 100;
		NumberFormat nf = NumberFormat.getInstance(Locale.GERMAN);
		nf.setMinimumFractionDigits(2);
		nf.setMaximumFractionDigits(2);
		String stringMargin = nf.format(margin);
		return stringMargin;
		
	}
	
	String makeDoubleString(Double d) {
		NumberFormat nf = NumberFormat.getInstance(Locale.GERMAN);
		nf.setMinimumFractionDigits(2);
		nf.setMaximumFractionDigits(2);
		String quote = nf.format(d);
		return quote;
	}
	
	String makeIntegerString(Integer integer) {
		NumberFormat nf = NumberFormat.getInstance(Locale.GERMAN);
		String stringInteger = nf.format(integer);
		return stringInteger;
	}

}
