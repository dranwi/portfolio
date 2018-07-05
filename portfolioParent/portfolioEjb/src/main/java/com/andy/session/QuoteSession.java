package com.andy.session;

import java.util.List;

import javax.ejb.Local;

import com.andy.portfolioModel.Quote;

@Local
public interface QuoteSession {
	public void createQuote(Quote quote);
	public List<Quote> findBySymbol(String symbol);
}
