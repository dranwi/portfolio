package com.andy.service;

import java.util.List;

import javax.ejb.Local;

import com.andy.portfolioModel.Quote;

@Local
public interface QuoteService {
	public void persiste(Quote quote);
	public List<Quote> findBySymbol(String symbol);
}
