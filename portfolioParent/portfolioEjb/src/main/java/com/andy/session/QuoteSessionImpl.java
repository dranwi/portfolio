package com.andy.session;

import java.io.Serializable;
import java.util.List;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Inject;

import com.andy.portfolioModel.Quote;
import com.andy.service.QuoteService;

@Stateless
public class QuoteSessionImpl implements QuoteSession, Serializable {
	private static final long serialVersionUID = -3167219767779500476L;
	
	@Inject
	QuoteService dao;

	@Override
	public void createQuote(Quote quote) {
		dao.persiste(quote);
	}

	@Override
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public List<Quote> findBySymbol(String symbol) {
		List<Quote> quoteList = dao.findBySymbol(symbol);
		return quoteList;
	}

}
