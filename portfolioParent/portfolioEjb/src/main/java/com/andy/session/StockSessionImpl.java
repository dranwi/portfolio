package com.andy.session;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Inject;

import com.andy.portfolioModel.Portfolio;
import com.andy.portfolioModel.Quote;
import com.andy.portfolioModel.Stock;
import com.andy.service.PortfolioService;
import com.andy.service.QuoteService;
import com.andy.service.StockService;

import java.util.Date;
import java.util.HashMap;


@Stateless
public class StockSessionImpl implements StockSession, Serializable {
	private static final long serialVersionUID = 1260563591991348148L;

	@Inject
	private StockService dao; 
	
	@Inject
	private QuoteService quoteDao;
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public boolean createStock(String name) {

		Stock p = dao.findByName(name);
		if (p != null) {
			// name is not unique
			return false;
		}
		
		Stock stock = new Stock();
		stock.setName(name);
		
		dao.persist(stock);
		return true;
	}
	
	
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public List<Stock> findAll() {
		return dao.findAll();
	}
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public Stock findByName(String name) {
		return dao.findByName(name);
	}
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public Stock findByNameWithTimeline(String name) {
		Stock stock =  dao.findByName(name);
		List<Quote> quoteList = quoteDao.findBySymbol(name);
		Map<String,Double> timeline = new HashMap<String,Double>();
		for (Quote q : quoteList) {
			timeline.put(q.getDate(), q.getValue());
		}
		stock.setTimelineQuote(timeline);
		return stock;
	}
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void update(Stock stock) {
		dao.merge(stock);
	}

}
