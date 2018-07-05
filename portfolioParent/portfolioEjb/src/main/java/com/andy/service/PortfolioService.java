package com.andy.service;

import java.util.List;

import javax.ejb.Local;
import com.andy.portfolioModel.Portfolio;

@Local
public interface PortfolioService {
	public Portfolio findByName(String name);
	public void persist(Portfolio portfolio);
	public List<Portfolio> findAll();
	public void merge(Portfolio portfolio);
}
