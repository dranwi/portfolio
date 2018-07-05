package com.andy.session;

import java.util.List;

import javax.ejb.Local;

import com.andy.portfolioModel.Portfolio;
import com.andy.portfolioModel.Stock;


@Local
public interface StockSession {
	public boolean createStock(String name);
	public List<Stock> findAll();
	public Stock findByName(String name);
	public Stock findByNameWithTimeline(String name);
	public void update(Stock stock);
}
